package Project.Modele;

import Project.util.Utils;

import java.util.HashMap;

public class GameState {

    /*
    ATTRIBUTES
     */
    private final HashMap<Utils.Tresor, Boolean> tresors;
    private int niveauEau;

    /*
    CONSTRUCTOR
     */
    public GameState(int difficulte) {

        //Initialisation du niveau d'eau en fonction de la difficulté choisie
        niveauEau = difficulte;

        //initialisation de la liste des tresor recolté
        tresors = new HashMap<>();
        for (Utils.Tresor tresor
                : Utils.Tresor.values()) {
            tresors.put(tresor, false);
        }

    }

    /*
    METHODS
     */
    /**
     *
     * @param tresor le trésor à récupérer Sert à sauvegarder la récupération
     * d'un tresor
     */
    public void recupererTresor(Utils.Tresor tresor) {
        tresors.put(tresor, true);
    }

    /**
     * Sert a incrémenter le niveau d'eau
     */
    public void incrementNiveau() {
        niveauEau++;
    }

    /**
     * @return le nombre de carte inondation a piocher
     */
    public int getNbDeCarte() {
        if (niveauEau < 3) {
            return 2;
        } else if (niveauEau < 6) {
            return 3;
        } else if (niveauEau < 8) {
            return 4;
        } else if (niveauEau < 10) {
            return 5;
        } else {
            return 0;
        }
    }

    /* 
    GETTER 
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    public HashMap<Utils.Tresor, Boolean> getTresors() {
        return tresors;
    }
}
