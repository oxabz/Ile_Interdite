/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Modele.Aventurier;
import Project.Modele.Aventuriers.Ingenieur;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.Modele.Tuiles.Heliport;
import Project.Modele.Tuiles.TuileApparition;

import java.util.HashMap;

/**
 *
 * @author seiglebq
 */
public class FactoryGrille {
    private static final String[] TUILES_NAMES = {
            "Le Pont des Abimes",
            "La Porte de Bronze",
            "La Caverne des Ombres",
            "La Porte de Fer",
            "La Porte d\u0027Or",
            "Les Falaises de l\u0027Oubli",
            "Le Palais de Corail",
            "La Porte d\u0027Argent",
            "Les Dunes de l’Illusion",
            "Heliport",
            "La Porte de Cuivre",
            "Le Jardin des Hurlements",
            "La Foret Pourpre",
            "Le Lagon Perdu",
            "Le Marais Brumeux",
            "Observatoire",
            "Le Rocher Fantome",
            "La Caverne du Brasier",
            "Le Temple du Soleil",
            "Le Temple de La Lune",
            "Le Palais des Marees",
            "Le Val du Crepuscule",
            "La Tour du Guet",
            "Le Jardin des Murmures"
    };

    static private HashMap<String, Tuile> getTuiles(){
        HashMap<String, Tuile> tuilles = new HashMap<>();
        for (String nom :
                TUILES_NAMES) {
            Tuile t;
            switch (nom){
                case "La Porte de Bronze":
                    t = new TuileApparition(nom, "Ingenieur");
                    break;
                case "La Porte de Fer":
                    t = new TuileApparition(nom, "Plongeur");
                    break;
                case "La Porte d\u0027Argent":
                    t = new TuileApparition(nom, "Messager");
                    break;
                case "La Porte d\u0027Or":
                    t = new TuileApparition(nom, "Navigateur");
                    break;
                case "La Porte de Cuivre":
                    t = new TuileApparition(nom, "Explorateur");
                    break;
                case "Heliport":
                    t = new Heliport("Pilote");
                    break;
                default:
                    t = new Tuile(nom);
                    break;
            }
            tuilles.put(nom, t);
        }
        return tuilles;
    }

    static public Grille getGrille(){
        return null;
    }

    static public Grille getGrilleTest(){
        Grille g = new Grille();
        HashMap<String,Tuile> tuiles = getTuiles();
        Tuile[][] tuilesG = g.getTuiles();
        tuilesG[2][0] = tuiles.get("Le Pont des Abimes");
        tuilesG[3][0] = tuiles.get("La Porte de Bronze");
        tuilesG[3][0].setInnondee(true);
        tuilesG[1][1] = tuiles.get("La Caverne des Ombres");
        tuilesG[2][1] = tuiles.get("La Porte de Fer");
        tuilesG[3][1] = tuiles.get("La Porte d\u0027Or");
        tuilesG[4][1] = tuiles.get("Les Falaises de l\u0027Oubli");
        tuilesG[0][2] = tuiles.get("Le Palais de Corail");
        tuilesG[1][2] = tuiles.get("La Porte d\u0027Argent");
        //tuilesG[2][2] = tuiles.get("Les Dunes de l’Illusion");
        tuilesG[3][2] = tuiles.get("Heliport");
        tuilesG[4][2] = tuiles.get("La Porte de Cuivre");
        tuilesG[5][2] = tuiles.get("Le Jardin des Hurlements");
        tuilesG[0][3] = tuiles.get("La Foret Pourpre");
        tuilesG[1][3] = tuiles.get("Le Lagon Perdu");
        tuilesG[1][3].setInnondee(true);
        //tuilesG[2][3] = tuiles.get("Le Marais Brumeux");
        tuilesG[3][3] = tuiles.get("Observatoire");
        tuilesG[3][3].setInnondee(true);
        //tuilesG[4][3] = tuiles.get("Le Rocher Fantome");
        tuilesG[5][3] = tuiles.get("La Caverne du Brasier");
        tuilesG[5][3] .setInnondee(true);
        tuilesG[1][4] = tuiles.get("Le Temple du Soleil");
        //tuilesG[2][4] = tuiles.get("Le Temple de La Lune");
        tuilesG[3][4] = tuiles.get("Le Palais des Marees");
        tuilesG[4][4] = tuiles.get("Le Val du Crepuscule");
        tuilesG[2][5] = tuiles.get("La Tour du Guet");
        tuilesG[3][5] = tuiles.get("Le Jardin des Murmures");
        tuilesG[3][5].setInnondee(true);

        return g;
    }
}
