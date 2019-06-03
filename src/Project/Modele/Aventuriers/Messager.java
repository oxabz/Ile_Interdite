package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Vector2;

public class Messager extends Aventurier {

    private static final String NOM  = "Messager";

    public Messager(Vector2 position) {
        super(position);
    }

    @Override
    public String getNom() {
        return NOM;
    }
}
