package Project.Modele;

import Project.util.Utils;

import java.util.HashMap;

public class GameState {

    /*
    ATTRIBUTES
     */

    private HashMap<Utils.Tresor,Boolean> tresors;
    private int niveauEau;

    /*
    CONSTRUCTOR
     */

    public GameState(int difficulte){

        //Initialisation du niveau d'eau en fonction de la difficulté choisie
        niveauEau = difficulte;

        //initialisation de la liste des tresor recolté
        tresors = new HashMap<>();
        for (Utils.Tresor tresor :
                Utils.Tresor.values()) {
            tresors.put(tresor,false);
        }

    }

    /*
    METHODS
     */


    //Sert à sauvegarder la recuperation d'un tresor
    public void recupererTresor(Utils.Tresor tresor){
        Boolean t = tresors.get(tresor);
        t = true;
    }

    //Sert a incrementer le niveau d'eau
    public void incrementNiveau(){
        niveauEau++;
    }

    //Donne le nombre de carte inondation a piocher
    public int getNbDeCarte(){
        if(niveauEau<3){
            return 2;
        }else if(niveauEau<6){
            return 3;
        }else if(niveauEau<8){
            return 4;
        }else if(niveauEau<10){
            return 5;
        }else {
            return 0;
        }
    }

    public int getNiveauEau() {
        return niveauEau;
    }
}
