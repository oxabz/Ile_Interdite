package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Utils;
import Project.util.Vector2;

public class Navigateur extends Aventurier {

    /* ATTRIBUTS */
    private static final String NOM = "Navigateur";
    private static final Utils.Pion PION = Utils.Pion.JAUNE;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier de type navigateur
     *
     * @deprecated utiliser {@link #Navigateur(Vector2) à la place
     */
    @Deprecated
    public Navigateur() {
        super();
    }

    /**
     * Créer un aventurier de type pilote
     *
     * @param position la position de départ du navigateur
     */
    public Navigateur(Vector2 position) {
        super(position);
    }

    @Override
    public String getNom() {
        return NOM;
    }

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
