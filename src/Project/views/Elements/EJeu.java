package Project.views.Elements;

import Project.views.Vue;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class EJeu extends JPanel {

    private final GridBagConstraints constraints;

    //Elements
    private EGrille grille;
    private final EInfo informations;
    private ENiveauDEau niveauEau;
    private final EDeck deck;
    private final HashMap<String, EJoueur> listeJoueurs;
    private final EMain main;
    private final EActions actions;
    private final Vue vue;

    public EJeu(Vue vue) {
        this.vue = vue;        
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.setLayout(new GridBagLayout());

        listeJoueurs = new HashMap<>();
        deck = new EDeck();
        actions = new EActions(this.vue);
        main = new EMain();
        informations = new EInfo();

        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.gridx = 2;
        constraints.gridy = 4;
        this.add(deck, constraints);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(actions, constraints);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(informations, constraints);

    }

    public void initialiserNiveauEau(int level) {
        niveauEau = new ENiveauDEau(level);
        constraints.gridwidth = 1;
        constraints.gridheight = 4;
        constraints.gridx = 3;
        constraints.gridy = 0;
        this.add(niveauEau, constraints);
    }

    public void initialiserGrille(String[][] names, boolean[][] inondee, boolean[][] coulee) {
        grille = new EGrille(names[0].length, names.length, names, this.vue);

        constraints.gridwidth = 3;
        constraints.gridheight = 4;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(grille, constraints);

        grille.setVisible(true);
        grille.paintComponents(this.getGraphics());

    }

    public void initialiserPanel() {
        this.setVisible(true);
    }

    public EGrille getGrille() {
        return grille;
    }

    public ENiveauDEau getNiveauEau() {
        return niveauEau;
    }    

    public EInfo getInformations() {
        return informations;
    }

    public JPanel getPanel() {
        return this;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    public EDeck getDeck() {
        return deck;
    }

    public HashMap<String, EJoueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public EMain getMain() {
        return main;
    }

    public EActions getActions() {
        return actions;
    }
    
    
}
