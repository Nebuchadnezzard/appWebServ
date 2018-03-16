package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class Livre implements Document {
	private int numero;
	private String nomLivre;
	private Utilisateur emprunteur;
	
	public Livre(String nomLivre, int numero) {
		this.nomLivre = nomLivre;
		this.numero = numero;
		this.emprunteur = null;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		synchronized(this) {
			if(this.emprunteur == null) {
				this.emprunteur = a;
			}
			else {
				throw new EmpruntException();
			}
		}
	}

	@Override
	public void retour() {
		synchronized(this) {
			this.emprunteur = null;
		}
	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		return null;
	}

}
