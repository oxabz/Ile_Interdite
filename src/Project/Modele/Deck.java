package Project.Modele;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

	/**
	 * ATTRIBUTES
	 */

	private LinkedList<Carte> pioche;
	private LinkedList<Carte> defausse;

	/**
	 * CONSTRUCTOR
	 */

	public Deck() {
		this.pioche = new LinkedList<>();
		this.defausse = new LinkedList<>();
	}

	/**
	 * METHODS
	 */

	public void addCartePiocheDebut(Carte carte) {
		this.pioche.addFirst(carte);
	}

	public void addCartesPiocheDebut(LinkedList<Carte> cartes) {
		for (int i = cartes.size()-1; i >= 0 ; i++) {
			pioche.addFirst(cartes.get(i));
		}
	}

	public void addCartePiocheFin(Carte carte) {
		this.pioche.addLast(carte);
	}

	public void addCarteDefausseDebut(Carte carte) {
		this.defausse.addFirst(carte);
	}

	public void addCarteDefausseFin(Carte carte) {
		this.defausse.addLast(carte);
	}

	public void melangerCartesDefausse() {
		Collections.shuffle(this.defausse);
	}

	public void melangerCartesPioche() {
		Collections.shuffle(this.pioche);
	}

	/**
	 * GETTERS
	 */

	public LinkedList<Carte> getPioche() {
		return pioche;
	}

	public LinkedList<Carte> getDefausse() {
		return defausse;
	}
}
