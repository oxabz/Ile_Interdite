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

    private final static CarteMEau CARTE_MONTEE_DES_EAUX = new CarteMEau("Montée des eaux");
    private final static CarteSacSable CARTE_SAC_DE_SABLE = new CarteSacSable("Sac de sable");
    private final static CarteHelicoptere CARTE_HELICOPTERE = new CarteHelicoptere("Hélicoptère");
    private final static CarteTresor CARTE_TRESOR_COUPE = new CarteTresor("carte Coupe", Utils.Tresor.COUPE);
    private final static CarteTresor CARTE_TRESOR_CRISTAL = new CarteTresor("carte Cristal", Utils.Tresor.CRISTAL);
    private final static CarteTresor CARTE_TRESOR_PIERRE = new CarteTresor("carte Pierre", Utils.Tresor.PIERRE);
    private final static CarteTresor CARTE_TRESOR_STATUE = new CarteTresor("carte Statue", Utils.Tresor.STATUE);

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
        FactoryGrille g = new FactoryGrille();
        Deck deck = new Deck();
        
        for (Tuile[] uneLigne : g.getGrilleTest().getTuiles()) {
            for (Tuile uneTuile : uneLigne) {
                deck.addCartePiocheDebut(new CarteInondation(uneTuile.getNom(), uneTuile));
            }
        }
        deck.melangerCartesPioche();
        return deck;
    }

}
