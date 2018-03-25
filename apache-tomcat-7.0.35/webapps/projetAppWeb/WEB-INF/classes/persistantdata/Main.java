package persistantdata;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("persistantdata.MediathequeDataJDBC");
		
		Utilisateur user = Mediatheque.getInstance().getUser("Jacques", "abcd");

	}

}
