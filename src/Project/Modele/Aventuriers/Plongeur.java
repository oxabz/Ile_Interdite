package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class Plongeur extends Aventurier {

    private static final String NOM = "Plongeur";

    public Plongeur() {
        super();
    }

    public Plongeur(Vector2 position) {
        super(position);
    }

    @Override
    public void seDeplacer() {

        //Declaration
        Controleur c = Controleur.getControleur();
        ArrayList<Vector2> pos;
        Grille grille = c.getGrille();

        do {
            pos = getPosDeplacement();

            Iterator<Vector2> iterator = pos.iterator();
            while (iterator.hasNext()) {
                Vector2 p = iterator.next();
                if (grille.getTuile(p) == null) {
                    iterator.remove();
                }
            }
            position = c.getPosClick(pos);
        } while (grille.getTuile(position) == null);//non fini
    }

    @Override
    public String getNom() {
        return NOM;
    }
}
