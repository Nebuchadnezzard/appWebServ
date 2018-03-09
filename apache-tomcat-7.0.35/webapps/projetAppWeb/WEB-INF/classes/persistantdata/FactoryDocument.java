package persistantdata;

import mediatheque.*;

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
