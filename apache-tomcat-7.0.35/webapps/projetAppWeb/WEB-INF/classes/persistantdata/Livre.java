package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class Livre implements Document {
	private int numero;
	private String nomLivre;
	private boolean estEmprunte;
	
	public Livre(String nomLivre, int numero) {
		this.nomLivre = nomLivre;
		this.numero = numero;
		this.estEmprunte = false;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		synchronized(this) {
			if(this.estEmprunte == false) {
				a.emprunter(this);
				this.estEmprunte = true;
			}
			else {
				throw new EmpruntException();
			}
		}
		
		
	}

	@Override
	public void retour() {
		synchronized(this) {
			this.estEmprunte = false;
		}
	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		return null;
	}

}
