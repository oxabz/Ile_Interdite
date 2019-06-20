package Project.Modele.Cartes;

import Project.Modele.Carte;

public abstract class CarteItem extends Carte {
    
    /* CONSTRUCTEURS */
    
    /**
     * 
     * @param nom 
     */
    public CarteItem(String nom) {
        super(nom);
    }
    
    public CarteItem(String nom, String image) {
        super(nom, image);
    }


}
