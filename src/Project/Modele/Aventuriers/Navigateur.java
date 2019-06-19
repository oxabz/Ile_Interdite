package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Utils;
import Project.util.Vector2;

public class Navigateur extends Aventurier {

    private static final String NOM = "Navigateur";

    public Navigateur() {
        super();
    }

    public Navigateur(Vector2 position) {
        super(position);
    }

    @Override
    public String getNom() {
        return NOM;
    }

    private static final Utils.Pion PION = Utils.Pion.JAUNE;

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
