package persistantdata.documents;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class CD implements Document {
	private int numero;
	private String nomCD;
	private Utilisateur emprunteur;
	private String auteur;

	public CD(String nomCD, String auteur, int numero, Utilisateur user) {
		this.nomCD = nomCD;
		this.auteur = auteur;
		this.numero = numero;
		this.emprunteur = user;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		if (this.emprunteur == null) {
			this.emprunteur = a;
		} else {
			throw new EmpruntException();
		}

	}

	@Override
	public void retour() {
		this.emprunteur = null;
	}

	@Override
	public Object[] affiche() {
		Object[] objAffiche = new Object[] {
				numero,
				nomCD,
				auteur,
				"CD",
				(emprunteur != null)?emprunteur.getNom():"Aucun"
		};
		return objAffiche;
	}

}
