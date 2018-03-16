package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class CD implements Document{
	private int numero;
	private String nomCD;
	private Utilisateur emprunteur;
	private boolean estEmprunte;
	
	public CD(String nomCD, int numero) {
		this.nomCD = nomCD;
		this.numero = numero;
		this.estEmprunte = false;
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
