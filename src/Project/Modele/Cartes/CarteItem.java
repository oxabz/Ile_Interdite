package Project.Modele.Cartes;

import Project.Modele.Carte;

public abstract class CarteItem extends Carte {

    /* CONSTRUCTEURS */
    /**
     * permet de créer une carte item
     *
     * @param nom le nom de la carte
     * @deprecated utiliser {@link #CarteItem(String, String)} à la place
     */
    @Deprecated
    public CarteItem(String nom) {
        super(nom);
    }

    /**
     * permet de créer une carte item
     *
     * @param nom le nom de la carte
     * @param image le nom de l'image correspondante
     */
    public CarteItem(String nom, String image) {
        super(nom, image);
    }

}
