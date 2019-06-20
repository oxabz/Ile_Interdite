package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Utils;
import Project.util.Vector2;

public class Ingenieur extends Aventurier {

    private static final String NOM = "Ingenieur";

    public Ingenieur() {
        super();
    }

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

    private static final Utils.Pion PION = Utils.Pion.ROUGE;

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
