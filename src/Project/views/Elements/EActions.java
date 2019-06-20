package Project.views.Elements;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Utils;
import Project.views.Vue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EActions extends JPanel {

    /* CONSTANTES */
    private static final JPanel PANEL = new JPanel();
    public static final int ACTION_MODE = 1;
    public static final int PARAMETER_MODE = 2;
    public static final int STOP_MODE = 0;
    /* ATTRIBUTS */

    private final ArrayList<JButton> boutons;
    private final JButton actionSpecialButon;
    private final JButton annulerButon;

    private Vue vue;


    /* CONSTRUCTEUR */
    public EActions(Vue vue) {

        this.setLayout(new GridLayout(2,4));

        this.vue = vue;
        boutons = new ArrayList<>();

        JButton seDeplacerButon = new JButton("se deplacer");
        this.add(seDeplacerButon);
        seDeplacerButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.SE_DEPLACER;
                vue.notifierObserver(m);
            }
        });
        boutons.add(seDeplacerButon);

        JButton assecherButon = new JButton("assecher");
        this.add(assecherButon);
        assecherButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.ASSECHER;
                vue.notifierObserver(m);
            }
        });
        boutons.add(assecherButon);

        JButton donnerCarteButon = new JButton("donner carte");
        this.add(donnerCarteButon);
        donnerCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.DON_CARTE;
                vue.notifierObserver(m);
            }
        });
        boutons.add(donnerCarteButon);

        JButton prendreTresorButon = new JButton("prendre tresor");
        this.add(prendreTresorButon);
        prendreTresorButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.PRENDRE_TRESOR;
                vue.notifierObserver(m);
            }
        });
        boutons.add(prendreTresorButon);

        JButton finDeTourButon = new JButton("fin de tour");
        this.add(finDeTourButon);
        finDeTourButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.FIN_TOUR;
                vue.notifierObserver(m);
            }
        });
        boutons.add(finDeTourButon);

        JButton utiliserCarteButon = new JButton("utiliser carte");
        this.add(utiliserCarteButon);
        utiliserCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.UTILISER_CARTE;
                vue.notifierObserver(m);
            }
        });
        boutons.add(utiliserCarteButon);

        actionSpecialButon = new JButton("Action Special");
        this.add(actionSpecialButon);
        utiliserCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.ACTION_SPECIALE;
                vue.notifierObserver(m);
            }
        });

        annulerButon = new JButton("Annuler");
        this.add(annulerButon);
        utiliserCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ANNULER);
                vue.notifierObserver(m);
            }
        });

        for (JButton unBouton : boutons) {
            unBouton.setEnabled(true);

        }

        this.setVisible(true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JButton bouton:
             boutons) {
            bouton.setEnabled(enabled);
        }
    }

    public void setEnableActionSpecial(boolean enabled){
        actionSpecialButon.setEnabled(enabled);
    }

    public void setEnableAnnuler(boolean enabled){
        annulerButon.setEnabled(enabled);
    }

}
