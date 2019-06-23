package Project.views.Elements;

import Project.Modele.Aventurier;
import Project.util.IdentifiedElement;
import Project.util.Message;
import Project.util.MessageType;
import Project.views.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class EJoueur extends JPanel {
    private EMain main;
    private Vue vue;
    private int avId;

    public EJoueur(Vue vue, Aventurier av) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHEAST;
        this.vue = vue;
        this.avId = av.getId();
        this.main = new EMain(vue);
        this.add(main, c);
        this.main.removeListeners();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EJoueur ths = (EJoueur) e.getComponent();
                if (ths.isEnabled()){
                    Message m = new Message(MessageType.AVENTURIER);
                    m.aventurier = ths.avId;
                    ths.vue.notifierObserver(m);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void updateJoueur(){
        main.setAventurier((Aventurier) IdentifiedElement.getIdentifiedElement(this.avId));
    }


}
