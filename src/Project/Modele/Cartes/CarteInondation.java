package Project.Modele.Cartes;

import Project.Modele.Carte;
import Project.Modele.Tuile;

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
