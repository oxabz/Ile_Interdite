package Project.Modele.Cartes.CartesItem;

import Project.Modele.Cartes.CarteItem;
import Project.util.Utils;

public class CarteTresor extends CarteItem {

    /* ATTRIBUTS */
    private final Utils.Tresor tresor;

    /* CONSTRUCTEURS */

    /**
     *
     * @param nom le nom de la carte
     * @param tresor le trésor lié à la carte
     * @deprecated utiliser {@link #CarteTresor(.String, String, Tresor)} à la place 
     * 
     */
    
    @Deprecated
    public CarteTresor(String nom, Utils.Tresor tresor) {
        super(nom);
        this.tresor = tresor;
    }

    /**
     *
     * @param nom le jnom de la carte
     * @param image le nom de l'image correspondante
     * @param tresor le trésor lié à la carte
     */
    public CarteTresor(String nom, String image, Utils.Tresor tresor) {
        super(nom, image);
        this.tresor = tresor;
    }

    /* GETTERS & SETTERS */
    public Utils.Tresor getTresor() {
        return tresor;
    }
}
