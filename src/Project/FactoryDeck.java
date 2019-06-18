/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Modele.Cartes.CarteInondation;
import Project.Modele.Cartes.CartesItem.CarteHelicoptere;
import Project.Modele.Cartes.CartesItem.CarteMEau;
import Project.Modele.Cartes.CartesItem.CarteSacSable;
import Project.Modele.Cartes.CartesItem.CarteTresor;
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

    public static Deck getDeckInondations() {
        Deck deck = new Deck();
        Grille g;
        g = FactoryGrille.getLastFactoryDeck();
        Tuile[][] tuiles = g.getTuiles();
        for (int i = 0; i < g.getSizeX(); i++) {
            for (int j = 0; j < g.getSizeY(); j++) {
                if (tuiles[i][j] != null) {
                    deck.addCartePiocheDebut(new CarteInondation(tuiles[i][j].getNom(), tuiles[i][j]));
                }
            }
        }
        deck.melangerCartesPioche();
        return deck;
    }

}
