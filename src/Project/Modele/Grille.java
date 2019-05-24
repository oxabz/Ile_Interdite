package Project.Modele;

import Project.util.Vector2;

public class Grille {
    /*
    CONSTANTS
     */

    private static final int GRILLE_TAILLE_X = 6;
    private static final int GRILLE_TAILLE_Y = 6;

    /*
    ATTRIBUTES
     */

    private Tuile[][] tuiles;

    /*
    CONSTRUCTOR
     */

    public Grille(){
        tuiles = new Tuile[GRILLE_TAILLE_X][GRILLE_TAILLE_Y];

        //Berk
        for (int i = 0; i < GRILLE_TAILLE_X; i++) {
            for (int j = 0; j < GRILLE_TAILLE_Y; j++) {
                tuiles[i][j] = new Tuile();
            }
        }
        tuiles[5][5] = null;
    }   //Fin Berk

    /*
    METHODS
     */

    public Tuile getTuile(int x, int y){
        if (x<GRILLE_TAILLE_X && x>=0 && y<GRILLE_TAILLE_Y && y>=0){
            return tuiles[x][y];
        }else {
            return null;
        }

    }

    public Tuile getTuile(Vector2 v){
        if (v.x<GRILLE_TAILLE_X && v.x>=0 && v.y<GRILLE_TAILLE_Y && v.y>=0){
            return tuiles[v.x][v.y];
        }else {
            return null;
        }

    }
}
