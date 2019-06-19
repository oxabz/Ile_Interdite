package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Utils;
import Project.util.Vector2;

public class Messager extends Aventurier {

    private static final String NOM = "Messager";

    public Messager() {
        super();
    }

    public Messager(Vector2 position) {
        super(position);
    }

    @Override
    public String getNom() {
        return NOM;
    }

    private static final Utils.Pion PION = Utils.Pion.GRIS;

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
