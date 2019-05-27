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


    //BERK
    @Override
    public void seDeplacer() {
        if (deplacemntSpecial){
            Controleur c = Controleur.getControleur();
            ArrayList<Vector2> pos = new ArrayList<>();
            Grille grille = c.getGrille();
            for (int i = 0; i < 6; i++) {  //BERK
                for (int j = 0; j < 6; j++) { //BERK
                    Tuile t = grille.getTuile(i,j);
                    if (t!=null && (position.x != i|| position.y != j)){
                        pos.add(new Vector2(i,j));
                    }
                }
            }
            position = c.getPosClick(pos);
        }else {
            super.seDeplacer();
        }
    }
}
