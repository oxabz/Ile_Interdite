/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.views.Elements;

import Project.Modele.Carte;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author seiglebq
 */
public final class EMain extends JPanel {
    // Carte du perso, liseret de couleur autour du Panel
    // Sa main, son nom    

    /* CONSTANTES */
    private final static int NOMBRE_PANELS_MAIN = 7;

    /* ATTRIBUTS */
    private final Carte cartePersonnage;
    private JLabel label_nomJoueur;
    private JLabel label_nombreCarteMain;
    private final JPanel panel_variablesUtilisateur;
    private ArrayList<Carte> cartes;
    private ArrayList<JPanel> panels;

    /* CONSTRUCTEUR */
    public EMain() {
        cartes = new ArrayList<>();
        panels = new ArrayList<>();
        this.setLayout(new GridLayout(1, EMain.getNOMBRE_PANELS_MAIN()));
        for (int i = 0; i < EMain.getNOMBRE_PANELS_MAIN(); i++) {
            panels.add(new JPanel());
            this.add(panels.get(i));
        }
        this.cartePersonnage = null;
        // panels.get(NOMBRE_PANELS_MAIN).add(/* RAJOUTER L'iMAGE DE LA CARTE */);
        label_nomJoueur = new JLabel("bonjour");
        label_nombreCarteMain = new JLabel("0 / 5");
        panel_variablesUtilisateur = new JPanel(new GridLayout(2, 1));
        panel_variablesUtilisateur.add(label_nomJoueur);
        panel_variablesUtilisateur.add(label_nombreCarteMain);
        panels.get(EMain.getNOMBRE_PANELS_MAIN() - 1).add(getPanel_variablesUtilisateur());

    }

    /* METHODES */ // Une méthode pour mettre à jour avec les personnages
    /* GETTERS & SETTERS */
    public static int getNOMBRE_PANELS_MAIN() {
        return NOMBRE_PANELS_MAIN;
    }

    public Carte getCartePersonnage() {
        return cartePersonnage;
    }

    public JLabel getLabel_nomJoueur() {
        return label_nomJoueur;
    }

    public JLabel getLabel_nombreCarteMain() {
        return label_nombreCarteMain;
    }

    public JPanel getPanel_variablesUtilisateur() {
        return panel_variablesUtilisateur;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }
}
