package Project.Modele.Cartes.CartesItem;

import Project.Modele.Cartes.CarteItem;
import Project.util.Utils;


public class CarteTresor  extends CarteItem {
    /*
    ATTRIBUTES
     */

    private Utils.Tresor tresor;

    /*
    CONSTRUCTOR
     */

    public CarteTresor(String nom, Utils.Tresor tresor) {
        super(nom);
        this.tresor = tresor;
    }
    
    public CarteTresor(String nom, String image, Utils.Tresor tresor) {
        super(nom, image);
        this.tresor = tresor;
    }

    /*
    GETTER SETTER
     */

    public Utils.Tresor getTresor() {
        return tresor;
    }
}
