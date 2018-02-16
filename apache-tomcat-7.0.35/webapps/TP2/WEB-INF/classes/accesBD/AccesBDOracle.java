package accesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesBDOracle {

	private static String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
	private static String user = "GRP202US4";
	private static String password = "GRP202US4";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		tousPilotes();
		//volsParVille("Paris");
		//volsParVillePrepared("Paris");
	}

	public static String tousPilotes() throws ClassNotFoundException, SQLException {
		String resultat = null;;
		// charger le driver oracle
		Class.forName("oracle.jdbc.OracleDriver");
		// creer la co à la bd
		Connection c = DriverManager.getConnection(url, user, password);

		String req1 = "SELECT matricule, nom, age FROM pilote";
		Statement st = c.createStatement();

		ResultSet res = st.executeQuery(req1);

		while (res.next()) {
			int mat = res.getInt("Matricule");
			String nom = res.getString("Nom");
			int age = res.getInt("Age");
			resultat += "mat : " + mat + " nom : " + nom + " age : " + age +"\n";
		}
		res.close();
		st.close();
		c.close();
		return resultat;
	}

	public static void volsParVille(String ville) throws ClassNotFoundException, SQLException {
		// charger le driver oracle
		Class.forName("oracle.jdbc.OracleDriver");
		// creer la co à la bd
		Connection c = DriverManager.getConnection(url, user, password);

		String req2 = "SELECT numvol, villearrivee FROM vol WHERE villedepart = '" + ville + "'";

		Statement st = c.createStatement();

		ResultSet res = st.executeQuery(req2);

		while (res.next()) {
			String numvol = res.getString("numvol");
			String arr = res.getString("villearrivee");
			System.out.println("num : " + numvol + "   arr :" + arr);
		}

		res.close();
		st.close();
		c.close();
	}

	public static void volsParVillePrepared(String ville) throws ClassNotFoundException, SQLException {
		// charger le driver oracle
		Class.forName("oracle.jdbc.OracleDriver");
		// creer la co à la bd
		Connection c = DriverManager.getConnection(url, user, password);

		String req2 = "SELECT numvol, villearrivee FROM vol WHERE villedepart = ?";

		PreparedStatement st = c.prepareStatement(req2);
		st.setString(1, ville);

		ResultSet res = st.executeQuery();

		while (res.next()) {
			String numvol = res.getString("numvol");
			String arr = res.getString("villearrivee");
			System.out.println("num : " + numvol + "   arr :" + arr);
		}

		res.close();
		st.close();
		c.close();
	}

}
