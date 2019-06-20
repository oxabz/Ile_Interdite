package Project.Modele.Cartes.CartesItem;

import Project.Modele.Cartes.CarteItem;

public abstract class CarteBonus extends CarteItem {

    /* CONSTRUCTEURS */
    /**
     *
     * @param nom le nom de la carte bonus
     * @deprecated utiliser {@link #CarteBonus(String, String)} à la place
     */

    @Deprecated
    public CarteBonus(String nom) {
        super(nom);
    }

    public CarteBonus(String nom, String image) {
        super(nom, image);
    }


    /* MÉTHODES */

    /**
     * Permet de faire l'action d'effectuer l'action d'une carte bonus
     */
    public abstract void actionCarte();
}
