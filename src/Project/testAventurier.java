package Project;

import Project.Modele.Aventurier;
import Project.util.Vector2;

public class testAventurier {
    public static void main(String[] args) {
        Controleur c = Controleur.getControleur();
        Aventurier av = new Aventurier();
        av.setPosition(new Vector2(5,4));
        for (int i = 0; i < 10; i++) {
            av.seDeplacer();
        }

    }
}
