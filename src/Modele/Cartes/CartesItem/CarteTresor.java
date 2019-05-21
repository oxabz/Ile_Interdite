package Modele.Cartes.CartesItem;

import Modele.Cartes.CarteItem;
import util.Utils;


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
