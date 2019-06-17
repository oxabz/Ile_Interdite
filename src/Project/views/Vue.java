package Project.views;

import Project.views.Elements.EActions;
import Project.views.Elements.EDeck;
import Project.views.Elements.EGrille;
import Project.views.Elements.EInfo;
import Project.views.Elements.EJoueur;
import Project.views.Elements.EMain;
import Project.views.Elements.ENiveauDEau;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Vue {

    private final static int WINDOW_SIZE_X = 500;
    private final static int WINDOW_SIZE_Y = 600;
    private final JFrame window = new JFrame("L'Île interdite");

    //Elements
    private EGrille grille;
    private EInfo informations;
    private ENiveauDEau niveauEau;
    private EDeck deck;
    private HashMap<String, EJoueur> listeJoueurs;
    private EMain main;
    private EActions actions;

    public Vue() throws IOException, InterruptedException {        
        this.configureWindow(window);
        //deck = new EDeck();    
        actions = new EActions();
        window.add(actions, BorderLayout.CENTER);
        window.setVisible(true);       
        //deck.test();
        
    }

    private void configureWindow(JFrame window) {
        window.setBackground(Color.WHITE);
        window.setSize(getWINDOW_SIZE_X(), getWINDOW_SIZE_Y());
        window.getContentPane().setLayout(new java.awt.BorderLayout());
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new java.awt.event.WindowListener() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowIconified(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowDeiconified(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowActivated(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowDeactivated(java.awt.event.WindowEvent e) {
            }

            @Override
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
 
    public static void main(String[] args) throws IOException {
        try {
            Vue fenetre = new Vue();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
