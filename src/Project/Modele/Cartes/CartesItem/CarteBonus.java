package Project.Modele.Cartes.CartesItem;

import Project.Modele.Cartes.CarteItem;

public abstract class CarteBonus extends CarteItem {
    /*
    CONSTRUCTOR
     */

    public CarteBonus(String image) {
        super(image);
    }

    /*
    METHODS
     */

    abstract void actionCarte();
}
