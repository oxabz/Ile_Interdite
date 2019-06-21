package Project.views;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Cartes.CartesItem.CarteTresor;
import Project.Modele.Deck;
import Project.Modele.Grille;
import Project.util.AdaptativeDimension;
import Project.util.Observe;
import Project.util.Utils;
import Project.util.Vector2;
import Project.views.Elements.*;

import java.awt.*;
import Project.views.Elements.EActions;
import Project.views.Elements.EDeck;
import Project.views.Elements.EGrille;
import Project.views.Elements.EInfo;
import Project.views.Elements.EJoueur;
import Project.views.Elements.EMain;
import Project.views.Elements.ENiveauDEau;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Vue extends Observe {

    private final static int WINDOW_SIZE_X = 1520;
    private final static int WINDOW_SIZE_Y = 920;
    private static final double CARD_SIZE_RATIO = 1.39191919192;
    private static final double DECK_SIZE_RATIO = 1.44191919192;
    private JFrame window;
    private GridBagConstraints constraints;
    private GridBagConstraints constraintsbis;

    //Panels
    private JPanel grilleMainPanel;
    private JPanel joueursPanel;
    private JPanel bottomRightPanel;

    //Elements
    private EGrille grille;
    private EInfo informations;
    private ENiveauDEau niveauEau;
    private EDeck deck;
    private ArrayList< EJoueur> listeJoueurs;
    private EMain main;
    private EActions actions;

    public Vue(Deck deckInondation, Deck deckItem) {
        window = new JFrame("L'Île interdite");
        this.configureWindow(window);

        grilleMainPanel = new JPanel();
        grilleMainPanel.setLayout(new BorderLayout());
        bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        window.setLayout(new GridBagLayout());

        listeJoueurs = new ArrayList<>();
        deck = new EDeck(deckInondation, deckItem);
        actions = new EActions(this);
        main = new EMain(this);
        informations = new EInfo();

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        bottomRightPanel.add(deck, constraints);
        deck.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        bottomRightPanel.add(actions, constraints);
        actions.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        bottomRightPanel.add(informations, constraints);
        informations.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        grilleMainPanel.add(main, BorderLayout.SOUTH);
        main.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

    }

    private void configureWindow(JFrame window) {
        window.setBackground(Color.WHITE);
        window.setSize(getWINDOW_SIZE_X(), getWINDOW_SIZE_Y());
        window.getContentPane().setLayout(new java.awt.BorderLayout());
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);

    }

    public static int getWINDOW_SIZE_X() {
        return WINDOW_SIZE_X;
    }

    public static int getWINDOW_SIZE_Y() {
        return WINDOW_SIZE_Y;
    }    

    public void initialiserNiveauEau(int level) {
        niveauEau = new ENiveauDEau(level);
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        bottomRightPanel.add(niveauEau, constraints);
        niveauEau.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

    }

    public void initialiserGrille(String[][] names, boolean[][] inondee, boolean[][] coulee) {
        grille = new EGrille(names[0].length, names.length, names, this);

        JPanel grillePanel = new JPanel();
        grillePanel.setLayout(new GridBagLayout());
        grillePanel.add(grille);
        grilleMainPanel.add(grillePanel, BorderLayout.CENTER);
        grille.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        grille.setVisible(true);
        grille.paintComponents(window.getGraphics());

    }

    public void initialiserJoueurs(ArrayList<Aventurier> joueurs) {
        joueursPanel = new JPanel();
        joueursPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < joueurs.size(); i++) {
            EJoueur eJoueur = new EJoueur(this, joueurs.get(i));
            this.listeJoueurs.add(eJoueur);
            joueursPanel.add(eJoueur, constraints);
            eJoueur.updateJoueur();
            eJoueur.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));

        }
    }

    public void initialiserAdaptativeSize() {
        new AdaptativeDimension(
                window,
                () -> (int) (deck.getHeight() / DECK_SIZE_RATIO),
                null,
                deck);

        new AdaptativeDimension(
                window,
                null,
                () -> (int) (CARD_SIZE_RATIO * (double) main.getWidth() * 0.125),
                main);

        new AdaptativeDimension(
                window,
                () -> (int) (window.getWidth() * 0.5 < window.getHeight() * 0.85 ? window.getWidth() * 0.5 : window.getHeight() * 0.85),
                () -> (int) grille.getSize().width,
                grille);

        for (EJoueur joueur : listeJoueurs) {
            new AdaptativeDimension(joueur, null, () -> (int) (CARD_SIZE_RATIO * (double) joueur.getWidth() * 0.125), joueur);
        }
    }

    public void initialiserVue() {
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        window.add(grilleMainPanel, constraints);
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        window.add(joueursPanel, constraints);
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        window.add(bottomRightPanel, constraints);
        window.setVisible(true);
    }

    public EDeck getDeck() {
        return deck;
    }

    public EGrille getGrille() {
        return grille;
    }

    public ENiveauDEau getNiveauEau() {
        return niveauEau;
    }

    public enum IhmMode {
        POSITION,
        AVENTURIER,
        ACTION,
        MAIN,
    }

    public EMain getMain() {
        return main;
    }

    public void setMode(IhmMode ihmMode) {
        switch (ihmMode) {
            case ACTION:
                actions.setEnabled(true);
                grille.setEnabled(false);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(false);
                }
                break;
            case POSITION:
                actions.setEnabled(false);
                grille.setEnabled(true);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(false);
                }
                break;
            case MAIN:

                actions.setEnabled(false);
                grille.setEnabled(false);
                main.setEnabled(true);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(false);
                }
                break;
            case AVENTURIER:

                actions.setEnabled(false);
                grille.setEnabled(false);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(true);
                }
                break;

        }
    }

    public EInfo getInformations() {
        return informations;
    }

    public void updateJoueurs() {
        for (EJoueur j
                : listeJoueurs) {
            j.updateJoueur();
        }
    }

    public JFrame getWindow() {
        return window;
    }

    public EActions getActions() {
        return actions;
    }
}
