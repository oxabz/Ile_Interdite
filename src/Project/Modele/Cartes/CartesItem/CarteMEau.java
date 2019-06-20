package Project.Modele.Cartes.CartesItem;

import Project.Modele.Cartes.CarteItem;

public class CarteMEau extends CarteItem {
    
    /* CONSTRUCTEURS */
    
    /**
     * 
     * @param nom le nom de la carte à créer
     * @deprecated utiliser {@link #CarteMEau(String, String)} à la place
     */
    
    @Deprecated
    public CarteMEau(String nom) {
        super(nom);
    }
    /**
     * 
     * @param nom le nom de la carte à créer
     * @param image le nom de l'image correspondante
     */
    public CarteMEau(String nom, String image) {
        super(nom, image);
        
    }
    

}
