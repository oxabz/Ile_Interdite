/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.views.Elements;

import Project.Modele.Carte;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteTresor;
import Project.util.Utils;
import Project.views.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author seiglebq
 */
public class EMain extends JPanel {
    private ArrayList<JPanel> panels;
    private ArrayList<CarteItem> cartesItems;
    private Vue vue;

    public EMain(Vue vue) {
        this.vue = vue;
        panels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            panels.add(new ECarte(vue));
        }
        cartesItems = new ArrayList<>();

        this.setLayout(new GridLayout(1,8));

        for (JPanel p :
                panels) {
            this.add(p);
        }


        JPanel panelJoueur = new JPanel();
        panelJoueur.add(new JLabel("test"));
        panelJoueur.add(new JLabel("test"));
        this.add(panelJoueur);
    }

    public void setCartesItems(ArrayList<CarteItem> cartesItems) {
        this.cartesItems = cartesItems;
        for (int i = 0; i < cartesItems.size(); i++) {
            ((ECarte)panels.get(i)).setCarte(cartesItems.get(i));
        }
    }
}
