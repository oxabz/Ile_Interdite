/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Modele.Carte;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteHelicoptere;
import Project.Modele.Cartes.CartesItem.CarteMEau;
import Project.Modele.Cartes.CartesItem.CarteSacSable;
import Project.Modele.Cartes.CartesItem.CarteTresor;
import Project.Modele.Deck;
import Project.util.Utils;
import java.util.ArrayList;

/**
 *
 * @author seiglebq
 */
public class FactoryDeck {

    private static final Deck deckItems = new Deck();
    private static final Deck deckInondations = new Deck();

    private final static CarteMEau CARTE_MONTEE_DES_EAUX = new CarteMEau("Montée des eaux");
    private final static CarteSacSable CARTE_SAC_DE_SABLE = new CarteSacSable("Sac de sable");
    private final static CarteHelicoptere CARTE_HELICOPTERE = new CarteHelicoptere("Hélicoptère");
    private final static CarteTresor CARTE_TRESOR_COUPE = new CarteTresor("carte Coupe", Utils.Tresor.COUPE);
    private final static CarteTresor CARTE_TRESOR_CRISTAL = new CarteTresor("carte Cristal", Utils.Tresor.CRISTAL);
    private final static CarteTresor CARTE_TRESOR_PIERRE = new CarteTresor("carte Pierre", Utils.Tresor.PIERRE);
    private final static CarteTresor CARTE_TRESOR_STATUE = new CarteTresor("carte Statue", Utils.Tresor.STATUE);

    public static void getDeck() {
        for (int i = 0; i < 3; i++) {
            deckItems.addCartePiocheDebut(CARTE_MONTEE_DES_EAUX);
        }
        for (int i = 0; i < 2; i++) {
            deckItems.addCartePiocheDebut(CARTE_SAC_DE_SABLE);
        }
    }

}
