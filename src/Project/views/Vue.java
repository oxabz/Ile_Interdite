package Project.views;

import Project.Controleur;
import Project.util.Observe;
import java.awt.*;
import Project.views.Elements.EActions;
import Project.views.Elements.EDeck;
import Project.views.Elements.EGrille;
import Project.views.Elements.EInfo;
import Project.views.Elements.EJoueur;
import Project.views.Elements.EMain;
import Project.views.Elements.ENiveauDEau;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.*;

public class Vue extends Observe {

    private final static int WINDOW_SIZE_X = 1715;
    private final static int WINDOW_SIZE_Y = 1050;
    private JFrame window;
    private GridBagConstraints constraints;

    //Elements
    private EGrille grille;
    private EInfo informations;
    private ENiveauDEau niveauEau;
    private EDeck deck;
    private HashMap<String, EJoueur> listeJoueurs;
    private EMain main;
    private EActions actions;

    public Vue() {
        window = new JFrame("L'Île interdite");
        this.configureWindow(window);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        window.setLayout(new GridBagLayout());

        listeJoueurs = new HashMap<>();
        deck = new EDeck();
        actions = new EActions(this);
        main = new EMain();
        informations = new EInfo();

        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.gridx = 2;
        constraints.gridy = 4;
        window.add(deck,constraints);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 4;
        window.add(actions,constraints);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 4;
        window.add(informations,constraints);




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
        constraints.gridwidth = 1;
        constraints.gridheight = 4;
        constraints.gridx = 3;
        constraints.gridy = 0;
        window.add(niveauEau, constraints);
    }

    public void initialiserGrille(String[][] names, boolean[][] inondee, boolean[][] coulee) {
        grille = new EGrille(names[0].length, names.length, names, this);

        constraints.gridwidth = 3;
        constraints.gridheight = 4;
        constraints.gridx = 0;
        constraints.gridy = 0;
        window.add(grille, constraints);

        grille.setVisible(true);
        grille.paintComponents(window.getGraphics());

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

    public void SetMode(IhmMode ihmMode) {
        switch (ihmMode) {
            case ACTION:
                actions.setEnabled(true);
                grille.setEnabled(false);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs.values()) {
                    j.setEnabled(false);
                }
                break;
            case POSITION:
                actions.setEnabled(false);
                grille.setEnabled(true);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs.values()) {
                    j.setEnabled(false);
                }
                break;
            case MAIN:

                actions.setEnabled(false);
                grille.setEnabled(false);
                main.setEnabled(true);
                for (EJoueur j
                        : listeJoueurs.values()) {
                    j.setEnabled(false);
                }
                break;
            case AVENTURIER:

                actions.setEnabled(false);
                grille.setEnabled(false);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs.values()) {
                    j.setEnabled(false);
                }
                break;

        }
    }

    public EInfo getInformations() {
        return informations;
    }
}