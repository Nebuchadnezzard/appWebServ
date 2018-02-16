package mediatheque;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	
	private List<Document> lesDocs;
	private String password;
	private String nom;
	
	public Utilisateur(String nom, String password) {
		this.nom = nom;
		this.password = password;
		this.lesDocs = new ArrayList<Document>();
	}
	
	
}
