package Project.Modele.Cartes.CartesItem;

import Project.Modele.Cartes.CarteItem;

public abstract class CarteBonus extends CarteItem {
    /*
    CONSTRUCTOR
     */

    public CarteBonus(String nom) {
        super(nom);
    }
    
    public CarteBonus(String nom, String image) {
        super(nom,image);
    }

    /*
    METHODS
     */

    public abstract void actionCarte();
}
