/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.views.Elements;

import Project.Modele.Aventurier;
import Project.Modele.Cartes.CarteItem;
import Project.views.Vue;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author seiglebq
 */
public class EMain extends JPanel {

    private JLabel nomJoueur;
    private JLabel nbAction;
    private JLabel nombreCarte;
    private ECarteAventurier carteAventurier;
    private ArrayList<JPanel> panels;
    private ArrayList<CarteItem> cartesItems;
    private Aventurier aventurier;
    private Vue vue;

    public EMain(Vue vue) {
        this.vue = vue;
        panels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            panels.add(new ECarte(vue));
        }
        cartesItems = new ArrayList<>();

        this.setLayout(new GridLayout(1, 8));

        for (JPanel p : panels) {
            this.add(p);
        }

        carteAventurier = new ECarteAventurier();

        this.add(carteAventurier);
        carteAventurier.paint(this.getGraphics());

        nomJoueur = new JLabel("");
        nbAction = new JLabel("");
        nombreCarte = new JLabel("");

        JPanel panelJoueur = new JPanel();
        panelJoueur.add(nomJoueur);
        panelJoueur.add(nbAction);
        panelJoueur.add(nombreCarte);
        this.add(panelJoueur);
    }

    public void setCartesItems(ArrayList<CarteItem> cartesItems) {
        this.cartesItems = cartesItems;
        for (int i = 0; i < 6; i++) {
            ((ECarte) panels.get(i)).setCarte((i < cartesItems.size() ? cartesItems.get(i) : null));

        }
    }

    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
        setCartesItems(aventurier.getCarteItems());
        nomJoueur.setText(aventurier.getJoueur());
        nombreCarte.setText(Integer.toString(aventurier.getCarteItems().size()));
        carteAventurier.setAv(aventurier);
    }
}
