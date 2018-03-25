package mediatheque;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.txw2.Document;

import persistantdata.documents.Livre;

public class Utilisateur {
	
	private String password;
	

	private String nom;
	private int typeUtil;
	private ArrayList<Document> lesDocs;
	
	public Utilisateur(String nom, String password, int typeUtil) {
		this.nom = nom;
		this.password = password;
		this.typeUtil = typeUtil;
		this.lesDocs = new ArrayList<Document>();
	}
	
	public int getType() {
		return this.typeUtil;
	}
	
	public String getPassword() {
		return password;
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<Document> getLesDocs() {
		return lesDocs;
	}
}
