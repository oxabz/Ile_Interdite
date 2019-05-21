package Modele.Cartes;

import Modele.Carte;
import Modele.Tuile;

public class CarteInondation extends Carte {
    /*
    ATTRIBUTES
     */

    private Tuile tuile;

    /*
    CONSTRUCTOR
     */

    public CarteInondation(String image, Tuile tuile) {
        super(image);
        this.tuile = tuile;
    }

    /*
    GETTER SETTER
     */

    public Tuile getTuile() {
        return tuile;
    }
}
