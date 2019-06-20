package Project.views.Elements;

import Project.Main;
import Project.Modele.Aventurier;
import Project.util.Message;
import Project.util.MessageType;
import Project.views.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EJoueur extends JPanel {
    private EMain main;
    private Vue vue;
    private Aventurier av;

    public EJoueur(Vue vue, Aventurier av) {
        this.setLayout(new BorderLayout());
        this.vue = vue;
        this.av = av;
        this.main = new EMain(vue);
        this.main.removeListeners();
        this.add(main,BorderLayout.CENTER);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EJoueur ths = (EJoueur) e.getComponent();
                if (ths.isEnabled()){
                    Message m = new Message(MessageType.AVENTURIER);
                    m.av = ths.av;
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
        main.setAventurier(av);
    }


}
