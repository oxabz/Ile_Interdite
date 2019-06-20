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

    private final static int WINDOW_SIZE_X = 1715;
    private final static int WINDOW_SIZE_Y = 1050;
    private static final double CARD_SIZE_RATIO = 1.39191919192;
    private static final double DECK_SIZE_RATIO = 1.44191919192;
    private JFrame window;
    private GridBagConstraints constraints;

    //Elements
    private EGrille grille;
    private EInfo informations;
    private ENiveauDEau niveauEau;
    private EDeck deck;
    private ArrayList< EJoueur> listeJoueurs;
    private EMain main;
    private EActions actions;

    public Vue() {
        window = new JFrame("L'Île interdite");
        this.configureWindow(window);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        window.setLayout(new GridBagLayout());

        listeJoueurs = new ArrayList<>();
        deck = new EDeck();
        actions = new EActions(this);
        main = new EMain(this);
        informations = new EInfo();

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.gridx = 2;
        constraints.gridy = 5;
        window.add(deck,constraints);
        deck.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        new AdaptativeDimension(
                window,
                ()->(int) (deck.getHeight()/DECK_SIZE_RATIO),
                null,
                deck);

        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        window.add(actions,constraints);
        actions.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));


        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.gridx = 1;
        constraints.gridy = 5;
        window.add(informations,constraints);
        informations.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));



        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 0;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        window.add(main,constraints);
        main.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));

        new AdaptativeDimension(window,
                null,
                ()-> (int)(CARD_SIZE_RATIO*(double) main.getWidth()*0.125),
                main);




    }

    private void configureWindow(JFrame window) {
        window.setBackground(Color.WHITE);
        window.setSize(getWINDOW_SIZE_X(), getWINDOW_SIZE_Y());
        window.getContentPane().setLayout(new java.awt.BorderLayout());
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new java.awt.event.WindowListener() {
            public void windowOpened(java.awt.event.WindowEvent e) {
            }

            public void windowClosed(java.awt.event.WindowEvent e) {
            }

            public void windowIconified(java.awt.event.WindowEvent e) {
            }

            public void windowDeiconified(java.awt.event.WindowEvent e) {
            }

            public void windowActivated(java.awt.event.WindowEvent e) {
            }

            public void windowDeactivated(java.awt.event.WindowEvent e) {
            }

            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static int getWINDOW_SIZE_X() {
        return WINDOW_SIZE_X;
    }

    public static int getWINDOW_SIZE_Y() {
        return WINDOW_SIZE_Y;
    }

    public static void main(String[] args) {
        Vue ihme = new Vue();
        //ihme.initialiserGrille(Controleur.getControleur().getGrille().getNames());
        ihme.grille.updateGrid(Controleur.getControleur().getGrille().getInnondee(), Controleur.getControleur().getGrille().getCoulee());
    }

    public void initialiserNiveauEau(int level) {
        niveauEau = new ENiveauDEau(level);
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.BOTH;
        window.add(niveauEau, constraints);
        niveauEau.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));

    }

    public void initialiserGrille(String[][] names, boolean[][] inondee, boolean[][] coulee) {
        grille = new EGrille(names[0].length, names.length, names, this);

        new AdaptativeDimension(window,
                ()-> (int)(window.getWidth()*0.5< window.getHeight()*0.85 ? window.getWidth()*0.5: window.getHeight()*0.85),
                ()->(int)grille.getSize().width,
                grille);
        constraints.gridwidth = 1;
        constraints.gridheight = 6;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        window.add(grille, constraints);
        grille.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));


        grille.setVisible(true);
        grille.paintComponents(window.getGraphics());

    }

    public void initialiserJoueurs(ArrayList<Aventurier> joueurs){
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < 4; i++) {
            EJoueur eJoueur = new EJoueur(this,joueurs.get(i));
            this.listeJoueurs.add(eJoueur);
            constraints.gridy = i;
            window.add(eJoueur,constraints);
            eJoueur.updateJoueur();
            eJoueur.setBorder(BorderFactory.createLineBorder(Color.PINK,2));
            new AdaptativeDimension(window, null ,()-> (int)(CARD_SIZE_RATIO*(double) eJoueur.getWidth()*0.125),eJoueur);
        }
    }

    public void initialiserVue() {
        window.setVisible(true);
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

    public void SetMode(IhmMode ihmMode) {
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
                    j.setEnabled(false);
                }
                break;

        }
    }

    public EInfo getInformations() {
        return informations;
    }
    
    public void updateJoueurs(){
        for (EJoueur j :
                listeJoueurs) {
            j.updateJoueur();
        }
    }
}