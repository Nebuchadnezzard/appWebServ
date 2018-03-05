package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class DVD implements Document {
	private int numero;
	private String nomDVD;
	private boolean estEmprunte;
	
	public DVD(String nomDVD, int numero) {
		this.nomDVD = nomDVD;
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
