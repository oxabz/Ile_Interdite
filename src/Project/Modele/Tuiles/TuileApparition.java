package Project.Modele.Tuiles;

import Project.Modele.Tuile;

public class TuileApparition extends Tuile {

    /* ATTRIBUTS */
    private String aventurier;

    /* CONSTRUCTEUR */

    /**
     *
     * @param nom le nom de la tuile
     * @param aventurier le nom de l'aventurier lié à la tuile
     */

    
    public TuileApparition(String nom, String aventurier) {
        super(nom);
        setAventurier(aventurier);
    }

    /* GETTERS & SETTERS */
    public String getAventurier() {
        return aventurier;
    }

    private void setAventurier(String aventurier) {
        this.aventurier = aventurier;
    }
}
