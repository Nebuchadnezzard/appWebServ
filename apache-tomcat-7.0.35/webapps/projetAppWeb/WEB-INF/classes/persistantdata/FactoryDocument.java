package persistantdata;

import mediatheque.*;
import persistantdata.documents.CD;
import persistantdata.documents.DVD;
import persistantdata.documents.Livre;

public class FactoryDocument {

	public static Document creerDoc(String nom, int num, String type) {

		switch (type) {
		case "CD":
			return new CD(nom, num);
		case "DVD":
			return new DVD(nom, num);
		case "Livre":
			return new Livre(nom, num);
		}
		return null;

	}
}
