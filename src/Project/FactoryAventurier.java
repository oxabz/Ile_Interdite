/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Modele.Aventurier;
import Project.Modele.Aventuriers.*;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.Modele.Tuiles.TuileApparition;
import Project.util.Vector2;

import java.util.ArrayList;

/**
 *
 * @author seiglebq
 */
public class FactoryAventurier {
    private static final String EXPLORATEUR = "Explorateur";
    private static final String INGENIEUR = "Ingenieur";
    private static final String MESSAGER = "Messager";
    private static final String NAVIGATEUR = "Navigateur";
    private static final String PILOTE = "Pilote";
    private static final String PLONGEUR = "Plongeur";


    public static ArrayList<Aventurier> getAventuriers(Grille grille){
        ArrayList<Aventurier> aventuriers = new ArrayList<>();
        for (int i = 0; i < grille.getSizeX(); i++) {
            for (int j = 0; j < grille.getSizeY(); j++) {
                Tuile t = grille.getTuile(i,j);
                if (t != null && t instanceof TuileApparition) {
                    switch (((TuileApparition) t).getAventurier()){
                        case EXPLORATEUR:
                            aventuriers.add(new Explorateur(new Vector2(i,j)));
                            break;
                        case INGENIEUR:
                            aventuriers.add(new Ingenieur(new Vector2(i,j)));
                            break;
                        case MESSAGER:
                            aventuriers.add(new Messager(new Vector2(i,j)));
                            break;
                        case NAVIGATEUR:
                            aventuriers.add(new Navigateur(new Vector2(i,j)));
                            break;
                        case PILOTE:
                            aventuriers.add(new Pilote(new Vector2(i,j)));
                            break;
                        case PLONGEUR:
                            aventuriers.add(new Plongeur(new Vector2(i,j)));
                            break;
                    }
                }
            }
        }
        return aventuriers;
    }

    public static ArrayList<Aventurier> getAventuriersTest(Grille grille) {

        ArrayList<Aventurier> aventuriers = new ArrayList<>();
        for (int i = 0; i < grille.getSizeX(); i++) {
            for (int j = 0; j < grille.getSizeY(); j++) {
                Tuile t = grille.getTuile(i,j);
                if (t != null && t instanceof TuileApparition) {
                    switch (((TuileApparition) t).getAventurier()){
                        case MESSAGER:
                            aventuriers.add(new Messager(new Vector2(i,j)));
                            break;
                        case NAVIGATEUR:
                            aventuriers.add(new Navigateur(new Vector2(i,j)));
                            break;
                        case PILOTE:
                            aventuriers.add(new Pilote(new Vector2(i,j)));
                            break;
                        case PLONGEUR:
                            aventuriers.add(new Plongeur(new Vector2(i,j)));
                            break;
                    }
                }
            }
        }
        return aventuriers;
    }
}
