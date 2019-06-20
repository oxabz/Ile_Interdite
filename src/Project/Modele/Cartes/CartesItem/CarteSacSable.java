package Project.Modele.Cartes.CartesItem;

public class CarteSacSable extends CarteBonus {
    /* CONSTRUCTEURS */
    
    /**
     * 
     * @param nom le nom de la carte à créer
     * @deprecated utiliser {@link #CarteSacSable(String, String)} à la place
     */
    
    @Deprecated
    public CarteSacSable(String nom) {
        super(nom);
    }
    
    /**
     * 
     * @param nom le nom de la carte à créer
     * @param image le nom de l'image correspondante
     */
    public CarteSacSable(String nom, String image) {
        super(nom,image);
    }

    @Override
    void actionCarte() {
        
    }


}
