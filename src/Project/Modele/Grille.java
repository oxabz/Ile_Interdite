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

    private final Tuile[][] tuiles;

    /*
    CONSTRUCTOR
     */

    public Grille(){
        tuiles = new Tuile[GRILLE_TAILLE_X][GRILLE_TAILLE_Y];
    }

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

    public void removeTuile(Tuile tuile){
        boolean found = false;
        int i = 0;
        while(i<tuiles.length && !found){
            int j = 0;
            while (j<tuiles[i].length && !found){
                if(tuiles[i][j]==tuile){
                    tuiles[i][j] = null;
                    found = true;
                }
                j++;
            }
            i++;
        }
    }

    public boolean[][] getCoulee(){
        boolean[][] coulee = new boolean[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                coulee[i][j] = tuiles[i][j] == null;
            }
        }
        return coulee;
    }

    public boolean[][] getInnondee(){
        boolean[][] innondee = new boolean[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                innondee[i][j] = (tuiles[i][j] != null && tuiles[i][j].isInnondee());
            }
        }
        return innondee;
    }

    public String[][] getNames(){
        String[][] names = new String[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                if(tuiles[i][j] != null){
                    names[i][j] = tuiles[i][j].getNom();
                }else {
                    names[i][j] = "";
                }
            }
        }
        return names;
    }

    /*
    GETTER SETTER
     */

    public Tuile[][] getTuiles() {
        return tuiles;
    }

    public int getSizeX() {
        return GRILLE_TAILLE_X;
    }

    public int getSizeY() {
        return GRILLE_TAILLE_X;
    }
}
