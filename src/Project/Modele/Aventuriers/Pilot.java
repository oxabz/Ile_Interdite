package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class Pilot extends Aventurier {
    public Pilot() {
    }

    private boolean deplacemntSpecial = true;


    //BERK
    @Override
    public void seDeplacer() {
        if (deplacemntSpecial){
            Controleur c = Controleur.getControleur();
            ArrayList<Vector2> pos = new ArrayList();
            Grille grille = c.getGrille();
            for (int i = 0; i < 6; i++) {  //BERK
                for (int j = 0; j < 6; j++) { //BERK

                }
            }
            Vector2 position = c.getPosClick(pos.toArray(Vector2[]::new));
        }else {
            super.seDeplacer();
        }
    }
}
