package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Utils;
import Project.util.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class Plongeur extends Aventurier {

    private static final String NOM = "Plongeur";
    private static final Utils.Pion PION = Utils.Pion.NOIR;

    public Plongeur() {
        super();
    }

    public Plongeur(Vector2 position) {
        super(position);
    }

    @Override
    public boolean seDeplacer() {

        //Declaration
        Controleur c = Controleur.getControleur();
        ArrayList<Vector2> pos;
        Grille grille = c.getGrille();
        Vector2 oldPosition = position;

        boolean fini;
        do {
            pos = getPosDeplacement();
            pos.add(position);
            Iterator<Vector2> iterator = pos.iterator();
            while (iterator.hasNext()) {
                Vector2 p = iterator.next();
                if (p.x < 0 || p.x > 5 || p.y < 0 || p.y > 5) {
                    iterator.remove();
                }
            }
            Vector2 positionSelected = c.getPosClic(pos);
            fini = (positionSelected.x == position.x && positionSelected.y == position.y) && grille.getTuile(position) != null;
            position = positionSelected;
        } while ((grille.getTuile(position) == null || grille.getTuile(position).isInnondee()) && !fini);//non fini
        return !(position.x == oldPosition.x && position.y == oldPosition.y);
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
