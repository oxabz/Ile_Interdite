package Project.Modele.Aventuriers;

import Project.Modele.Aventurier;
import Project.util.Vector2;

import java.util.ArrayList;

public class Explorateur extends Aventurier {


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
}
