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
import Project.Modele.Tuiles.TuileTresor;
import Project.util.Utils.Tresor;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

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
            "Les Dunes de l\u0027Illusion",
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
            "La Tour de Guet",
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
                case "La Caverne des Ombres":
                case "La Caverne du Brasier":
                    t = new TuileTresor(nom, Tresor.CRISTAL);
                    break;
                case "Le Jardin des Hurlements":
                case "Le Jardin des Murmures":
                    t = new TuileTresor(nom, Tresor.STATUE);
                    break;
                case "Le Palais de Corail":
                case "Le Palais des Marees":
                    t = new TuileTresor(nom, Tresor.COUPE);
                    break;
                case "Le Temple de La Lune":
                case "Le Temple du Soleil":
                    t = new TuileTresor(nom, Tresor.PIERRE);
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
        for (Tuile t:
             getTuiles().values()) {

        }

        return null;
    }

    /**
     * Génère une grille avec des tuiles
     * @return
     */
    static public Grille genererGrilleAleatoire() {
        // Créer la grille
        Grille g = new Grille();
        // Créer toutes les instances de tuiles et la stock dans une variable
        HashMap<String,Tuile> tuiles = getTuiles();
        // Récupère toutes les tuiles vides de la grille
        Tuile[][] tuilesG = g.getTuiles();

        // Création d'un array list qui contiendra tous les noms de tuile à ajouter
        LinkedList<String> distribution_tuiles = new LinkedList<>();

        // On ajoute tous les noms des tuiles
        for(String nom : TUILES_NAMES) {
            distribution_tuiles.add(nom);
        }

        // On met la liste des noms dans un ordre aléatoire
        Collections.shuffle(distribution_tuiles);

        // Tableau correspondant à l'index début de la colonne pour chaque ligne
        final int[] debut_colonne = {2,1,0,0,1,2};
        // La fin est calculée en retirant l'index de début au nombre de colonne dans une ligne

        // Pour toutes les lignes
        for(int y = 0; y < g.getSizeX(); y++) {

            // Pour toutes les colonnes selon les indexs ci-dessus
            for(int x = debut_colonne[y]; x < g.getSizeX()-debut_colonne[y]; x++) {

                // À la position x, y...
                tuilesG[x][y] =
                // ..on récupère l'instance créée dans la liste tuiles
                tuiles.get(
                    // ..avec un nom aléatoire qu'on retire de la liste chapinée
                    distribution_tuiles.poll()
                );

            }
        }

        // Sauvegarde de la grille
        derniereGrille = g;

        return g;
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

        // Sauvegarde de la grille
        derniereGrille = g;

        return g;
    }

    private static Grille derniereGrille;

    public static Grille getDerniereGrille() {
        return derniereGrille;
    }
}


