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

    private static final double CARD_SIZE_RATIO = 1.39191919192;

    private final JLabel nomJoueur;
    private final JLabel nbAction;
    private final JLabel nombreCarte;
    private final ECarteAventurier carteAventurier;
    private final ArrayList<JPanel> panels;
    private final Vue vue;

    public EMain(Vue vue) {
        this.vue = vue;
        panels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            panels.add(new ECarte(vue));
        }

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
        for (int i = 0; i < 6; i++) {
            ((ECarte) panels.get(i)).setCarte((i < cartesItems.size() ? cartesItems.get(i) : null));

        }
    }

    public void setAventurier(Aventurier aventurier) {
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                (int)(getParent().getWidth()*0.125*CARD_SIZE_RATIO < getParent().getHeight() ? getParent().getWidth() : getParent().getHeight()*8/CARD_SIZE_RATIO),
                (int)(getParent().getWidth()*0.125*CARD_SIZE_RATIO < getParent().getHeight() ? getParent().getWidth()*0.125*CARD_SIZE_RATIO : getParent().getHeight()));
    }
}
