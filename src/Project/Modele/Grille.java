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
    public Grille() {
        tuiles = new Tuile[GRILLE_TAILLE_X][GRILLE_TAILLE_Y];
    }

    /*
    METHODS
     */
    /**
     *
     * @param x coordonnée x de la tuile
     * @param y coordonnée y de la tuile
     * @return la tuile en (x,y), null si rien trouvé
     */
    public Tuile getTuile(int x, int y) {
        if (x < GRILLE_TAILLE_X && x >= 0 && y < GRILLE_TAILLE_Y && y >= 0) {
            return tuiles[x][y];
        } else {
            return null;
        }

    }

    /**
     *
     * @param v le Vector2 de la position de la tuile
     * @return la tuile en (x,y), null si rien trouvé
     */
    public Tuile getTuile(Vector2 v) {
        if (v.x < GRILLE_TAILLE_X && v.x >= 0 && v.y < GRILLE_TAILLE_Y && v.y >= 0) {
            return tuiles[v.x][v.y];
        } else {
            return null;
        }

    }

    /**
     *
     * @param tuile la tuile à retirer
     */
    public void removeTuile(Tuile tuile) {

        boolean found = false;
        int i = 0;
        while (i < tuiles.length && !found) {
            int j = 0;
            while (j < tuiles[i].length && !found) {
                if (tuiles[i][j] == null ? tuile == null : tuiles[i][j].getNom().equals(tuile.getNom())) {
                    tuiles[i][j] = null;
                    found = true;
                }
                j++;
            }
            i++;
        }

    }

    /**
     *
     * @return la liste des tuiles coulées (sous forme de grille)
     */
    public boolean[][] getCoulee() {
        boolean[][] coulee = new boolean[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                coulee[i][j] = tuiles[i][j] == null;
            }
        }
        return coulee;
    }

    /**
     *
     * @return la liste des tuiles innondées (sous forme de grille)
     */
    public boolean[][] getInnondee() {
        boolean[][] innondee = new boolean[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                innondee[i][j] = (tuiles[i][j] != null && tuiles[i][j].isInnondee());
            }
        }
        return innondee;
    }

    /**
     *
     * @return la liste des noms des tuiles (sous forme de grille)
     */
    public String[][] getNames() {
        String[][] names = new String[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                if (tuiles[i][j] != null) {
                    names[i][j] = tuiles[i][j].getNom();
                } else {
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
