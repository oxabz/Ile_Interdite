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

    public CarteTresor(String image, Utils.Tresor tresor) {
        super(image);
        this.tresor = tresor;
    }

    /*
    GETTER SETTER
     */

    public Utils.Tresor getTresor() {
        return tresor;
    }
}
