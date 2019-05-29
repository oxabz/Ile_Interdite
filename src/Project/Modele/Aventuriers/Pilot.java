package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.util.Vector2;

import java.util.ArrayList;

public class Pilot extends Aventurier {
    public Pilot() {
    }

    private boolean deplacemntSpecial = true;

    private static final String NOM  = "Pilote";

    @Override
    public void seDeplacer() {
        if (deplacemntSpecial){
            Controleur c = Controleur.getControleur();
            ArrayList<Vector2> pos = new ArrayList<>();
            Grille grille = c.getGrille();
            for (int i = 0; i < grille.getSizeX(); i++) {
                for (int j = 0; j < grille.getSizeY(); j++) {
                    Tuile t = grille.getTuile(i,j);
                    if (t!=null && (position.x != i|| position.y != j)){
                        pos.add(new Vector2(i,j));
                    }
                }
            }
            Vector2 p = c.getPosClick(pos);
            if (p!=null){
                position = p;
                deplacemntSpecial = false;
            }
        }else {
            super.seDeplacer();
        }
    }

    @Override
    public String getNom() {
        return NOM;
    }
}
