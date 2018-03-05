package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeDataJDBC implements PersistentMediatheque {
	private static String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
	private static String user = "GRP202US4";
	private static String password = "GRP202US4";

	static {
		Mediatheque.getInstance().setData(new MediathequeDataJDBC());
	}

	private MediathequeDataJDBC() {
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		FactoryDocument factory = new FactoryDocument();
		List<Document> resultat = null;
		// charger le driver oracle
		
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				// creer la co à la bd
				Connection c = DriverManager.getConnection(url, user, password);

				String req1 = "SELECT * FROM document";
				Statement st = c.createStatement();

				ResultSet res = st.executeQuery(req1);

				while (res.next()) {
					String type = res.getString("type");
					int num = res.getInt("numero");
					String nom = res.getString("nom");
					try {
						Document d = factory.creerDoc(nom, num, type);
						resultat.add(d);
					} catch (Exception e) {
						System.out.println("Le type n'existe pas");
					}

				}
				res.close();
				st.close();
				c.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
			Class.forName("oracle.jdbc.OracleDriver");
			Connection c = DriverManager.getConnection(this.url, this.user, this.password);
			String req = "SELECT * FROM user WHERE login ='" + login + "' AND password='" + password + "'";
			Statement st = c.createStatement();
			ResultSet res = st.executeQuery(req);
			user = new Utilisateur(res.getString("nom"), res.getString("password"));

			res.close();
			st.close();
			c.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		FactoryDocument factory = new FactoryDocument();
		String nom;
		String type;
		int num;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
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
			return factory.creerDoc(nom, num, type);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;// à changer
		
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
