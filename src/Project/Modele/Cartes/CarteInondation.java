package Project.Modele.Cartes;

import Project.Modele.Carte;
import Project.Modele.Tuile;

public class CarteInondation extends Carte {

    /* ATTRIBUTS */
    private final Tuile tuile;

    /* CONSTRUCTEUR */
    /**
     *
     * @param nom le nom de la carte inondation à créer
     * @param tuile le nom de la tuile à lier
     */
    public CarteInondation(String nom, Tuile tuile) {
        super(nom);
        this.tuile = tuile;
    }

    /* GETTERS & SETTERS */
    public Tuile getTuile() {
        return tuile;
    }
}
