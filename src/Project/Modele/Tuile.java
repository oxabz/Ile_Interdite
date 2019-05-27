package Project.Modele;

import Project.Modele.Tuiles.TuileTresor;

public class Tuile {
	/**
	 * ATTRIBUTES
	 */

	private String nom;
	private Boolean innondee;


	/**
	 * CONSTRUCTOR
	 */
	
	public Tuile(String nom) {
		this.setInnondee(false);
		this.setNom(nom);
	}
	
	/**
	 * METHODS
	 */
    
    public boolean isTuileTresor() {
        return this instanceof TuileTresor;
    }

	/**
	 * GETTERS/SETTERS
	 */
	
	private void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public Boolean isInnondee() {
		return innondee;
	}

	public void setInnondee(Boolean innondee) {
		this.innondee = innondee;
	}
}
