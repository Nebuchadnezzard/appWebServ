package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-d�claration dans son bloc static

public class MediathequeDataJDBC implements PersistentMediatheque {
	private static String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
	private static String user = "GRP202US4";
	private static String password = "GRP202US4";

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Mediatheque.getInstance().setData(new MediathequeDataJDBC());
	}

	private MediathequeDataJDBC() {
	}

	// renvoie la liste de tous les documents de la biblioth�que
	@Override
	public synchronized List<Document> tousLesDocuments() {
		List<Document> resultat = null;
		// charger le driver oracle

		try {
			
			// creer la co � la bd
			Connection c = DriverManager.getConnection(url, user, password);

			String req1 = "SELECT * FROM document";
			Statement st = c.createStatement();

			ResultSet res = st.executeQuery(req1);

			while (res.next()) {
				String type = res.getString("type");
				int num = res.getInt("numero");
				String nom = res.getString("nom");
				try {
					Document d = FactoryDocument.creerDoc(nom, num, type);
					resultat.add(d);
				} catch (Exception e) {
					System.out.println("Le type n'existe pas");
				}

			}
			res.close();
			st.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultat;

	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public synchronized Utilisateur getUser(String login, String password) {
		Utilisateur user = null;

		try {
			Connection c = DriverManager.getConnection(MediathequeDataJDBC.url, MediathequeDataJDBC.user,
					MediathequeDataJDBC.password);
			String req = "SELECT * FROM user WHERE login ='" + login + "' AND password='" + password + "'";
			Statement st = c.createStatement();
			ResultSet res = st.executeQuery(req);
			user = new Utilisateur(res.getString("nom"), res.getString("password"), Integer.parseInt(res.getString("typeUtil")));

			res.close();
			st.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public synchronized Document getDocument(int numDocument) {
		String nom;
		String type;
		int num;
		try {
			Connection c = DriverManager.getConnection(url, user, password);
			String req = "SELECT * FROM document WHERE numero ='" + numDocument + "'";
			Statement st = c.createStatement();
			ResultSet res = st.executeQuery(req);
			nom = res.getString("nom");
			type = res.getString("type");
			num = res.getInt("numero");

			res.close();
			st.close();
			c.close();
			return FactoryDocument.creerDoc(nom, num, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;// � changer

	}

	@Override
	public synchronized void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
