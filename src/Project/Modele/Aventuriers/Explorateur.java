package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Utils;
import Project.util.Vector2;

import java.util.ArrayList;

public class Explorateur extends Aventurier {

    private static final String NOM = "Explorateur";
    public Explorateur() {
        super();
    }

    public Explorateur(Vector2 position) {
        super(position);
    }

    @Override
    public ArrayList<Vector2> getPosDeplacement() {
        ArrayList<Vector2> pos = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (0 != i || 0 != j) {
                    pos.add(new Vector2(position.x + i, position.y + j));
                }
            }
        }
        return pos;
    }

    @Override
    public boolean assecher() {
        Controleur c = Controleur.getControleur();
        Grille g = c.getGrille();
        ArrayList<Vector2> pos = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (g.getTuile(position.add(i, j)) != null && g.getTuile(position.add(i, j)).isInnondee()) {
                    pos.add(position.add(i, j));
                }
            }

        }
        Vector2 aAssecher = c.getPosClick(pos);
        if (aAssecher != null) {
            g.getTuile(aAssecher).setInnondee(false);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getNom() {
        return NOM;
    }

    private static final Utils.Pion PION = Utils.Pion.VERT;

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
