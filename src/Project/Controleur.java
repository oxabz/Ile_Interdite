package Project;

import Project.Modele.Grille;
import Project.util.Vector2;

import java.util.Scanner;

public class Controleur {
    /*
    ATTRIBUTES
     */

    Grille grille;

    /*
    SINGLETON THINGY
     */

    private Controleur() {
        grille = new Grille();
    }

    private final static Controleur controleur = new Controleur();

    public static Controleur getControleur() {
        return controleur;
    }

    /*
    METHODS
     */


    public Vector2 getPosClick(Vector2[] clickables){
        //placeholder

        Scanner scanner = new Scanner(System.in);

        for (Vector2 c :
                clickables) {
            System.out.print('{'+Integer.toString(c.x)+','+Integer.toString(c.y)+'}');
        }

        System.out.print('\n');


        while (clickables.length != 0) {
            int x, y;
            System.out.print("x : ");
            x = scanner.nextInt();
            System.out.print("y : ");
            y = scanner.nextInt();

            for (Vector2 c : clickables) {
                if (c.x == x && c.y == y) {
                    return new Vector2(x, y);
                }
            }
        }

        return null;
    }

    /*
    GETTER SETTER
     */

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }
}
