package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Vector2;

public class Ingenieur extends Aventurier {

    private static final String NOM  = "Ingenieur";

    public Ingenieur(Vector2 position) {
        super(position);
    }

    @Override
    public void assecher() {
        super.assecher();//Berk
        super.assecher();
    }

    @Override
    public String getNom() {
        return NOM;
    }
}
