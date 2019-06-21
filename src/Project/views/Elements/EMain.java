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

    private final JLabel nomJoueur;
    private final JLabel nbAction;
    private final JLabel nombreCarte;
    private final ECarteAventurier carteAventurier;
    private final ArrayList<JPanel> panels;
    private ArrayList<CarteItem> cartesItems;
    private Aventurier aventurier;
    private final Vue vue;

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
        panelJoueur.add(new JLabel("Nom : "));
        panelJoueur.add(nomJoueur);
        panelJoueur.add(nbAction);
        panelJoueur.add(new JLabel("Cartes : "));
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
        nombreCarte.setText(Integer.toString(aventurier.getCarteItems().size()) + " / 5");
        carteAventurier.setAv(aventurier);
    }

    public void removeListeners(){
        for (JPanel jp :
                panels) {
            if (jp instanceof ECarte) {
                jp.removeMouseListener(jp.getMouseListeners()[0]);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JPanel jp :
                panels) {
            if (jp instanceof ECarte) {
                jp.setEnabled(enabled);
            }
        }

    }
}
