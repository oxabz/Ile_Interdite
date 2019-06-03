package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Vector2;

import java.util.ArrayList;

public class Explorateur extends Aventurier {

    private static final String NOM  = "Explorateur";

    public Explorateur(Vector2 position) {
        super(position);
    }

    @Override
    protected ArrayList<Vector2> getPosDeplacement(){
        ArrayList<Vector2> pos = new ArrayList<>();
        for (int i = -1 ; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (0 != i|| 0 != j){
                    pos.add(new Vector2(position.x+i,position.y +j));
                }
            }
        }
        return pos;
    }

    @Override
    public void assecher(){
        Controleur c = Controleur.getControleur();
        Grille g = c.getGrille();
        ArrayList<Vector2> pos = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (g.getTuile(position.add(i,j))!=null&&g.getTuile(position.add(i,0)).isInnondee()){
                    pos.add(position.add(i,j));
                }
            }

        }
        Vector2 aAssecher = c.getPosClick(pos);
        if(aAssecher != null){
            g.getTuile(aAssecher).setInnondee(false);
        }
    }

    @Override
    public String getNom() {
        return NOM;
    }
}
