package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;

public class Ingenieur extends Aventurier {

    private static final String NOM  = "Ingenieur";

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
