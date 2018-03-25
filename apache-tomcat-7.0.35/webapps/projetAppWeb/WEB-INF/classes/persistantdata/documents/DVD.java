package persistantdata.documents;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;
import persistantdata.MediathequeDataJDBC;

public class DVD implements Document {
	private int numero;
	private String nomDVD;
	private Utilisateur emprunteur;
	private String auteur;

	public DVD(String nomDVD, String auteur, int numero, Utilisateur user) {
		this.nomDVD = nomDVD;
		this.auteur = auteur;
		this.numero = numero;
		this.emprunteur = user;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		if (this.emprunteur == null) {
			MediathequeDataJDBC.emprunt(this.numero, a.getIdUtil());
			this.emprunteur = a;
		} else {
			throw new EmpruntException();
		}

	}

	@Override
	public void retour() {
		MediathequeDataJDBC.retour(this.numero);
		this.emprunteur = null;
	}

	@Override
	public Object[] affiche() {
		Object[] objAffiche = new Object[] {
				numero,
				nomDVD,
				auteur,
				"DVD",
				(emprunteur != null)?emprunteur.getNom():"Aucun",
				(emprunteur != null)?emprunteur.getIdUtil():"Aucun"
		};
		return objAffiche;
	}

}
