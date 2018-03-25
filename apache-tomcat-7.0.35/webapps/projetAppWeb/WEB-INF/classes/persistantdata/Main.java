package persistantdata;

import java.util.List;

import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("persistantdata.MediathequeDataJDBC");
		
		Utilisateur user = Mediatheque.getInstance().getUser("Jacques", "abcd");
		System.out.println(user.getNom());
		Document d = Mediatheque.getInstance().getDocument(1);
		
		for(Object o : d.affiche()) {
			System.out.println(o);
		}
		
		Mediatheque.getInstance().tousLesDocuments();
	}

}
