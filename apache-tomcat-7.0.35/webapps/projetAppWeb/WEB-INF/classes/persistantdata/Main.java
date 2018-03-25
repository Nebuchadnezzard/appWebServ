package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

/**
 * Classe de test
 * @author Jacques
 *
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, EmpruntException {
		Class.forName("persistantdata.MediathequeDataJDBC");
		
		Utilisateur user = Mediatheque.getInstance().getUser("Jacques", "abcd");
		System.out.println(user.getNom());
		Document d = Mediatheque.getInstance().getDocument(1);
		
		for(Object o : d.affiche()) {
			System.out.println(o);
		}
		
		Mediatheque.getInstance().tousLesDocuments();
		
		Document doc = Mediatheque.getInstance().getDocument(1);
		Utilisateur u = Mediatheque.getInstance().getUser("Virginie", "abcd");
		
		Mediatheque.getInstance().emprunt(d, u);
		Mediatheque.getInstance().retour(doc);
	}

}
