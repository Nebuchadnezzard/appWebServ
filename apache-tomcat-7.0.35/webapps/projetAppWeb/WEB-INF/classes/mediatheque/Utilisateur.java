package mediatheque;

import java.util.ArrayList;

public class Utilisateur {
	
	private String password;
	

	private String nom;
	private int typeUtil;
	private int idUtil;
	
	public Utilisateur(int idUtil, String nom, String password, int typeUtil) {
		this.idUtil = idUtil;
		this.nom = nom;
		this.password = password;
		this.typeUtil = typeUtil;
	}
	
	public int getIdUtil() {
		return idUtil;
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
}
