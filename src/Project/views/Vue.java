package Project.views;

import Project.views.Elements.EActions;
import Project.views.Elements.EDeck;
import Project.views.Elements.EGrille;
import Project.views.Elements.EInfo;
import Project.views.Elements.EJoueur;
import Project.views.Elements.EMain;
import Project.views.Elements.ENiveauDEau;
import java.util.HashMap;
import javax.swing.JFrame;

public class Vue {

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

    public Vue() {
        window = new JFrame("L'Île interdite");
        this.configureWindow(window);
        window.setVisible(true);
    }

    private void configureWindow(JFrame window) {
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
    }

}
