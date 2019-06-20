package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Utils;
import Project.util.Vector2;

public class Messager extends Aventurier {

    /* ATTRIBUTS */
    private static final String NOM = "Messager";
    private static final Utils.Pion PION = Utils.Pion.GRIS;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier de type message
     *
     * @deprecated utiliser {@link #Messager(Vector2)} à la place
     */
    @Deprecated
    public Messager() {
        super();
    }

    /**
     * Créer un aventurier de type messager
     *
     * @param position la position de départ du messager
     */
    public Messager(Vector2 position) {
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
