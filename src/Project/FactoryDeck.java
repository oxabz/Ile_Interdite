/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this teplate file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Modele.Cartes.CarteInondation;
import Project.Modele.Cartes.CartesItem.CarteHelicoptere;
import Project.Modele.Cartes.CartesItem.CarteMEau;
import Project.Modele.Cartes.CartesItem.CarteSacSable;
import Project.Modele.Cartes.CartesItem.CarteTresor;

import java.util.HashMap;

import Project.Modele.Deck;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.util.Utils;

/**
 *
 * @author seiglebq
 */
public class FactoryDeck {

    private final static CarteMEau CARTE_MONTEE_DES_EAUX = new CarteMEau("Montée des eaux", "MonteeDesEaux");
    private final static CarteSacSable CARTE_SAC_DE_SABLE = new CarteSacSable("Sac de sable", "SacsDeSable");
    private final static CarteHelicoptere CARTE_HELICOPTERE = new CarteHelicoptere("Hélicoptère", "Helicoptere");
    private final static CarteTresor CARTE_TRESOR_COUPE = new CarteTresor("Carte Coupe", "Calice", Utils.Tresor.COUPE);
    private final static CarteTresor CARTE_TRESOR_CRISTAL = new CarteTresor("Carte Cristal", "Cristal", Utils.Tresor.CRISTAL);
    private final static CarteTresor CARTE_TRESOR_PIERRE = new CarteTresor("Carte Pierre", "Pierre", Utils.Tresor.PIERRE);
    private final static CarteTresor CARTE_TRESOR_STATUE = new CarteTresor("Carte Statue", "Zephyr", Utils.Tresor.STATUE);

    public static Deck getDeckItems() {
        Deck deck = new Deck();
        for (int i = 0; i < 3; i++) {
            deck.addCartePiocheDebut(CARTE_MONTEE_DES_EAUX);
        }
        for (int i = 0; i < 2; i++) {
            deck.addCartePiocheDebut(CARTE_SAC_DE_SABLE);
        }
        for (int i = 0; i < 5; i++) {
            deck.addCartePiocheDebut(CARTE_TRESOR_COUPE);
        }
        for (int i = 0; i < 5; i++) {
            deck.addCartePiocheDebut(CARTE_TRESOR_CRISTAL);
        }
        for (int i = 0; i < 5; i++) {
            deck.addCartePiocheDebut(CARTE_TRESOR_PIERRE);
        }
        for (int i = 0; i < 5; i++) {
            deck.addCartePiocheDebut(CARTE_TRESOR_STATUE);
        }
        for (int i = 0; i < 3; i++) {
            deck.addCartePiocheDebut(CARTE_HELICOPTERE);
        }
        deck.melangerCartesPioche();
        return deck;

    }

    public static Deck getDeckItemsTest() {
        Deck deck = new Deck();
        // Tour 1
        deck.addCartePiocheFin(CARTE_TRESOR_CRISTAL);
        deck.addCartePiocheFin(CARTE_HELICOPTERE);

        deck.addCartePiocheFin(CARTE_TRESOR_COUPE);
        deck.addCartePiocheFin(CARTE_SAC_DE_SABLE);

        deck.addCartePiocheFin(CARTE_TRESOR_CRISTAL);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);

        // Tour 2
        deck.addCartePiocheFin(CARTE_TRESOR_CRISTAL);
        deck.addCartePiocheFin(CARTE_TRESOR_CRISTAL);

        deck.addCartePiocheFin(CARTE_TRESOR_COUPE);
        deck.addCartePiocheFin(CARTE_TRESOR_COUPE);

        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);

        // Tour 3
        deck.addCartePiocheFin(CARTE_MONTEE_DES_EAUX);
        deck.addCartePiocheFin(CARTE_MONTEE_DES_EAUX);

        deck.addCartePiocheFin(CARTE_TRESOR_COUPE);
        deck.addCartePiocheFin(CARTE_HELICOPTERE);

        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);
        deck.addCartePiocheFin(CARTE_SAC_DE_SABLE);

        deck.addCartePiocheFin(CARTE_HELICOPTERE);
        deck.addCartePiocheFin(CARTE_TRESOR_COUPE);

        // Tour 4
        deck.addCartePiocheFin(CARTE_TRESOR_CRISTAL);
        deck.addCartePiocheFin(CARTE_MONTEE_DES_EAUX);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        deck.addCartePiocheFin(CARTE_TRESOR_PIERRE);
        deck.addCartePiocheFin(CARTE_TRESOR_STATUE);

        return deck;

    }

    public static Deck getDeckInondations() {
        Deck deck = new Deck();
        Grille g;
        g = FactoryGrille.getDerniereGrille();
        for (Tuile[] uneLigne : g.getTuiles()) {
            for (Tuile uneTuile : uneLigne) {
                if (uneTuile != null) {
                    deck.addCartePiocheDebut(new CarteInondation(uneTuile.getNom(), uneTuile));
                }
            }
        }
        deck.melangerCartesPioche();
        return deck;
    }

    public static Deck getDeckInondationsTest() {
        Deck deck = new Deck();
        Grille g;
        g = FactoryGrille.getDerniereGrille();
        HashMap<String, CarteInondation> cartes = new HashMap<>();
        for (Tuile[] uneLigne : g.getTuiles()) {
            for (Tuile uneTuile : uneLigne) {
                if (uneTuile != null) {
                    cartes.put(uneTuile.getNom(), new CarteInondation(uneTuile.getNom(), uneTuile));
                }
            }
        }

        deck.addCartePiocheFin(cartes.get("La Tour de Guet"));
        deck.addCartePiocheFin(cartes.get("Observatoire"));
        
        deck.addCartePiocheFin(cartes.get("Le Pont des Abimes"));
        deck.addCartePiocheFin(cartes.get("Les Falaises de l\u0027Oubli"));
        
        deck.addCartePiocheFin(cartes.get("Les Dunes de l\u0027Illusion"));
        deck.addCartePiocheFin(cartes.get("La Porte de Fer"));
        
        deck.addCartePiocheFin(cartes.get("Le Val du Crepuscule"));
        deck.addCartePiocheFin(cartes.get("Le Lagon Perdu"));
        
        deck.addCartePiocheFin(cartes.get("Le Val du Crepuscule"));
        deck.addCartePiocheFin(cartes.get("Le Lagon Perdu"));

        deck.addCartePiocheFin(cartes.get("Le Pont des Abimes"));
        deck.addCartePiocheFin(cartes.get("Les Falaises de l\u0027Oubli"));

        deck.addCartePiocheFin(cartes.get("Le Rocher Fantome"));
        deck.addCartePiocheFin(cartes.get("Observatoire"));

        deck.addCartePiocheFin(cartes.get("La Porte de Cuivre"));
        deck.addCartePiocheFin(cartes.get("Observatoire"));

        deck.addCartePiocheFin(cartes.get("La Porte de Cuivre"));
        deck.addCartePiocheFin(cartes.get("La Porte de Bronze"));
        deck.addCartePiocheFin(cartes.get("La Caverne des Ombres"));

        deck.addCartePiocheFin(cartes.get("La Porte d\u0027Or"));
        deck.addCartePiocheFin(cartes.get("La Porte de Bronze"));
        deck.addCartePiocheFin(cartes.get("La Caverne des Ombres"));

        deck.addCartePiocheFin(cartes.get("La Porte d\u0027Or"));
        deck.addCartePiocheFin(cartes.get("Le Marais Brumeux"));
        deck.addCartePiocheFin(cartes.get("La Porte de Fer"));

        deck.addCartePiocheFin(cartes.get("Le Jardin des Hurlements"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple de La Lune"));
        
        deck.addCartePiocheFin(cartes.get("Le Palais des Marees"));
        deck.addCartePiocheFin(cartes.get("La Porte d\u0027Argent"));
        deck.addCartePiocheFin(cartes.get("La Foret Pourpre"));
        
        deck.addCartePiocheFin(cartes.get("Le Palais des Marees"));
        deck.addCartePiocheFin(cartes.get("La Porte d\u0027Argent"));
        deck.addCartePiocheFin(cartes.get("La Foret Pourpre"));

        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Jardin des Murmures"));
        deck.addCartePiocheFin(cartes.get("Le Jardin des Murmures"));

        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("La Porte d\u0027Or"));
        deck.addCartePiocheFin(cartes.get("Le Temple de La Lune"));

        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        deck.addCartePiocheFin(cartes.get("Le Temple du Soleil"));
        
        return deck;
    }

}
