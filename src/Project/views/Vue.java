package Project.views;

import Project.util.Observe;
import Project.views.Elements.EJeu;
import Project.views.Elements.EJoueur;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.*;

public class Vue extends Observe {

    private final static int WINDOW_SIZE_X = 1715;
    private final static int WINDOW_SIZE_Y = 1050;
    private JPanel card_initialisation;
    private JPanel card_jeu;
    private JPanel card_fin;
    private CardLayout card;
    private JFrame window;

    //Elements
    private EJeu ejeu;

    public Vue() {
        window = new JFrame("L'Île interdite");
        this.configureWindow(window);
        

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

    public void initialiserVue() {
        window.setVisible(true);
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
                ejeu.getActions().setEnabled(true);
                ejeu.getGrille().setEnabled(false);
                ejeu.getMain().setEnabled(false);
                for (EJoueur j : ejeu.getListeJoueurs().values()) {
                    j.setEnabled(false);
                }
                break;
            case POSITION:
                ejeu.getActions().setEnabled(false);
                ejeu.getGrille().setEnabled(true);
                ejeu.getMain().setEnabled(false);
                for (EJoueur j : ejeu.getListeJoueurs().values()) {
                    j.setEnabled(false);
                }
                break;
            case MAIN:

                ejeu.getActions().setEnabled(false);
                ejeu.getGrille().setEnabled(false);
                ejeu.getMain().setEnabled(true);
                for (EJoueur j : ejeu.getListeJoueurs().values()) {
                    j.setEnabled(false);
                }
                break;
            case AVENTURIER:

                ejeu.getActions().setEnabled(false);
                ejeu.getGrille().setEnabled(false);
                ejeu.getMain().setEnabled(false);
                for (EJoueur j : ejeu.getListeJoueurs().values()) {
                    j.setEnabled(false);
                }
                break;

        }

    }

    public EJeu getEjeu() {
        return ejeu;
    }
}
