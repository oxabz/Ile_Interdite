package Project.Modele.Tuiles;

import Project.Modele.Aventurier;
import Project.Modele.Tuile;

public class TuileApparition extends Tuile {

	/**
	 * ATTRIBUTES
	 */

	private String aventurier;

	/**
	 * CONSTRUCTOR
	 */

	public TuileApparition(String nom, String aventurier) {
		super(nom);
		setAventurier(aventurier);
	}

	/**
	 * GETTERS/SETTERS
	 */

	public String getAventurier() {
		return aventurier;
	}

	private void setAventurier(String aventurier) {
		this.aventurier = aventurier;
	}
}
