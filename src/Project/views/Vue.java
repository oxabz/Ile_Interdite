package Project.views;

import Project.Controleur;
import Project.Modele.Grille;
import Project.util.Observe;
import Project.util.Vector2;
import Project.views.Elements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Vue extends Observe {

    private final static int WINDOW_SIZE_X = 980;
    private final static int WINDOW_SIZE_Y = 600;
    private JFrame window;

    //Elements
    private EGrille grille;
    private EInfo informations;
    private ENiveauDEau niveauEau;
    private EDeck deck;
    private HashMap<String, EJoueur> listeJoueurs;
    private EMain main;
    private EActions actions;

    public Vue()  {
        window = new JFrame("L'Île interdite");
        this.configureWindow(window);
        deck = new EDeck();
        window.add(deck, BorderLayout.CENTER);
        window.setVisible(true);
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
        ihme.initialiserGrille(Controleur.getControleur().getGrille().getNames());
        ihme.grille.updateGrid(Controleur.getControleur().getGrille().getInnondee(),Controleur.getControleur().getGrille().getCoulee());
    }

    public void initialiserGrille(String [][] names){
        grille = new EGrille(names[0].length, names.length,names,this);
        window.add(grille);

        grille.paint(window.getGraphics());
    }


    public EGrille getGrille(){
        return grille;
    }



    public enum IhmMode{
        POSITION,
        AVENTURIER,
        ACTION,
        MAIN,
    }

    public void SetMode(IhmMode ihmMode){
        switch (ihmMode){
            case ACTION:
                EActions.
                break;
            case POSITION:
                break;
            case MAIN:
                break;
            case AVENTURIER:
                break;

        }
    }
}
