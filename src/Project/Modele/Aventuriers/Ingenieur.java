package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Utils;
import Project.util.Vector2;

public class Ingenieur extends Aventurier {

    /* ATTRIBUTS */
    private static final String NOM = "Ingenieur";
    private static final Utils.Pion PION = Utils.Pion.ROUGE;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier de type ingénieur
     *
     * @deprecated utiliser {@link #Ingenieur(Vector2)} à la place
     */
    @Deprecated
    public Ingenieur() {
        super();
    }

    /**
     * Créer un aventurier de type ingénieur
     *
     * @param position la position de départ de l'ingénieur
     */
    public Ingenieur(Vector2 position) {
        super(position);
    }

    @Override
    public boolean assecher() {
        boolean b = super.assecher();//Berk
        super.assecher();
        return b;
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
