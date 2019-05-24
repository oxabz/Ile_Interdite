package Project.Modele.Tuiles;

import Project.Modele.Aventurier;
import Project.Modele.Tuile;

public class TuileApparition extends Tuile {

	/**
	 * ATTRIBUTES
	 */

	private Aventurier aventurier;

	/**
	 * CONSTRUCTOR
	 */

	public TuileApparition(String nom, Aventurier aventurier) {
		super(nom);
		setAventurier(aventurier);
	}

	/**
	 * GETTERS/SETTERS
	 */

	public Aventurier getAventurier() {
		return aventurier;
	}

	private void setAventurier(Aventurier aventurier) {
		this.aventurier = aventurier;
	}
}
