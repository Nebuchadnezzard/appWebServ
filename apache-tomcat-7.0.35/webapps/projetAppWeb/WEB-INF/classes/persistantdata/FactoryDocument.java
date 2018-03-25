package persistantdata;

import mediatheque.*;
import persistantdata.documents.CD;
import persistantdata.documents.DVD;
import persistantdata.documents.Livre;

public class FactoryDocument {

	public static Document creerDoc(String nom, String auteur, int num, Utilisateur user, int type) {

		switch (type) {
		case 0: // CD
			return new CD(nom, auteur, num, user);
		case 1: // DVD
			return new DVD(nom, auteur, num, user);
		case 2: // Livre
			return new Livre(nom, auteur, num, user);
		}
		return null;

	}
}
