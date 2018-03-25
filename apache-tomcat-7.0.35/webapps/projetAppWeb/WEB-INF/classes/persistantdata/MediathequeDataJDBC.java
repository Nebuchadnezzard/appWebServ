package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

/**
 * Implémentation de PersistentMediatheque Utilise une DB MySQL en localhost
 * 
 * @author Jacques
 * @see mediatheque.PersistentMediatheque
 * @version 1.0
 */
public class MediathequeDataJDBC implements PersistentMediatheque {
	private static String url = "jdbc:mysql://localhost:3306/mediadb?autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String password = "root";

	static {

		// Chargement du driver JDBC MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Auto-déclaration dans la médiathèque
		Mediatheque.getInstance().setData(new MediathequeDataJDBC());
	}

	private MediathequeDataJDBC() {
	}

	/**
	 * Renvoie la liste de tous les documents de la bibliothèque
	 * 
	 * @see mediatheque.PersistentMediatheque#tousLesDocuments()
	 */
	@Override
	public List<Document> tousLesDocuments() {
		List<Document> resultat = new ArrayList<>();
		// charger le driver oracle

		try {

			Connection c = DriverManager.getConnection(url, user, password);

			// Création et execution de la requete
			String req = "SELECT * FROM document";
			Statement st = c.createStatement();
			ResultSet res = st.executeQuery(req);

			// Récupère tout les documents
			while (res.next()) {
				int type = res.getInt("typeDoc");
				int num = res.getInt("idDoc");
				String nom = res.getString("nomDoc");
				String auteurDoc = res.getString("auteurDoc");
				int numUtil = res.getInt("numUtil");

				resultat.add(FactoryDocument.creerDoc(nom, auteurDoc, num, getUser(numUtil), type));

			}
			res.close();
			st.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultat;

	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		Utilisateur user = null;


		try {
			Connection c = DriverManager.getConnection(MediathequeDataJDBC.url, MediathequeDataJDBC.user,
					MediathequeDataJDBC.password);
			String req = "SELECT * FROM Utilisateur WHERE nomUtil ='" + login + "' AND pwdUtil='" + password + "'";
			Statement st = c.createStatement();
			ResultSet res = st.executeQuery(req);

			// Passage à la première ligne
			res.next();

			// Création d'un objet utilisateur
			user = new Utilisateur(res.getInt("numUtil"), res.getString("nomUtil"), res.getString("pwdUtil"), res.getInt("typeUtil"));
			
			res.close();
			st.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {

		String nomDoc;
		String auteurDoc;
		int typeDoc;
		int idDoc;
		int numUtil;

		// RECUPERE LE DOCUMENT
		try {
			Connection c = DriverManager.getConnection(url, user, password);

			// Création et execution de la requete
			String reqDoc = "SELECT * FROM document WHERE idDoc ='" + numDocument + "'";
			Statement stDoc = c.createStatement();
			ResultSet resDoc = stDoc.executeQuery(reqDoc);

			// Passage à la première ligne
			resDoc.next();

			// Récupèration des infos du doc
			nomDoc = resDoc.getString("nomDoc");
			auteurDoc = resDoc.getString("auteurDoc");
			typeDoc = resDoc.getInt("typeDoc");
			idDoc = resDoc.getInt("idDoc");
			numUtil = resDoc.getInt("numUtil");

			resDoc.close();
			stDoc.close();
			c.close();

		} catch (SQLException e) { // Le doc n'existe pas
			e.printStackTrace();
			return null;
		}
		return FactoryDocument.creerDoc(nomDoc, auteurDoc, idDoc, getUser(numUtil), typeDoc);
	}
	
	/**
	 * Donne un utilisateur à partir de son id
	 * @param idUser
	 * @return Utilisateur
	 */
	private Utilisateur getUser(int idUser) {
		
		if(idUser == 0) {
			return null;
		}
		
		Utilisateur utilisateur = null;
		try {
			Connection c = DriverManager.getConnection(url, user, password);
			// Création et execution de la requete
			String reqUser = "SELECT * FROM Utilisateur WHERE numUtil = '" + idUser + "'";
			Statement stUser = c.createStatement();
			ResultSet resUser = stUser.executeQuery(reqUser);

			// Passage à la première ligne
			resUser.next();

			// Création de l'emprunteur (inutile de chercher ses docuements empruntés
			utilisateur = new Utilisateur(resUser.getInt("numUtil"), 
					resUser.getString("nomUtil"), 
					resUser.getString("pwdUtil"),
					resUser.getInt("typeUtil"));

			resUser.close();
			stUser.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
		
		try {
			Connection c = DriverManager.getConnection(url, user, password);
			
			String reqInsertDoc ="INSERT INTO Document(nomDoc, auteurDoc, typeDoc)"
					+ "VALUES('" + args[0] + "', '" + args[1] + "', " + type + ")";
			Statement stInsertDoc = c.createStatement();
			stInsertDoc.executeUpdate(reqInsertDoc);
			
			stInsertDoc.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void emprunt(int numDoc, int idUtil) {
		
		try {
			Connection c = DriverManager.getConnection(url, user, password);
			
			String reqEmpr = "UPDATE Document SET numUtil =" + idUtil + " WHERE idDoc =" + numDoc;
			Statement stEmpr = c.createStatement();
			stEmpr.executeUpdate(reqEmpr);
			
			stEmpr.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void retour(int idDoc) {

		try {
			Connection c = DriverManager.getConnection(url, user, password);
			
			String reqRet = "UPDATE Document SET numUtil = NULL WHERE idDoc = " + idDoc;
			Statement stRet = c.createStatement();
			stRet.executeUpdate(reqRet);
			
			stRet.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
