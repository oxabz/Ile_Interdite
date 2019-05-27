package Project.Modele.Tuiles;

import Project.Modele.Tuile;
import Project.util.Utils.Tresor;

public class TuileTresor extends Tuile {
	/**
	 * ATTRIBUTES
	 */

	private Tresor tresor;
	
	/**
	 * CONSTRUCTOR
	 */

	public TuileTresor(String nom, Tresor tresor) {
		super(nom);
		setTresor(tresor);
	}

	/**
	 * GETTERS/SETTERS
	 */

	public Tresor getTresor() {
		return tresor;
	}

	private void setTresor(Tresor tresor) {
		this.tresor = tresor;
	}
}
