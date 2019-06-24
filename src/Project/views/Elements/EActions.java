package Project.views.Elements;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Utils;
import Project.views.Vue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
    private final JButton seDeplacerButon;
    private final JButton actionSpecialButon;
    private final JButton assecherButon;
    private final JButton donnerCarteButon;
    private final JButton prendreTresorButon;
    private final JButton finDeTourButon;
    private final JButton utiliserCarteButon;
    private final JButton annulerButon;
    private final static String CHEMIN_ICONES = "src/images/icones/";
    private Vue vue;

    private final static ImageIcon MOVE = new ImageIcon(CHEMIN_ICONES + "iconMove.png");
    private final static ImageIcon ASSECHER = new ImageIcon(CHEMIN_ICONES + "iconDry.png");
    private final static ImageIcon DONNER = new ImageIcon(CHEMIN_ICONES + "iconGive.png");
    private final static ImageIcon RECUPERER = new ImageIcon(CHEMIN_ICONES + "iconReceive.png");
    private final static ImageIcon FIN = new ImageIcon(CHEMIN_ICONES + "iconDone.png");
    private final static ImageIcon UTILISER = new ImageIcon(CHEMIN_ICONES + "iconTarget.png");
    private final static ImageIcon SPECIALE = new ImageIcon(CHEMIN_ICONES + "iconGet.png");
    private final static ImageIcon ANNULER = new ImageIcon(CHEMIN_ICONES + "iconShift.png");

    private final static ImageIcon MOVE_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconMove_disabled.png");
    private final static ImageIcon ASSECHER_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconDry_disabled.png");
    private final static ImageIcon DONNER_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconGive_disabled.png");
    private final static ImageIcon RECUPERER_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconReceive_disabled.png");
    private final static ImageIcon FIN_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconDone_disabled.png");
    private final static ImageIcon UTILISER_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconTarget_disabled.png");
    private final static ImageIcon SPECIALE_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconGet_disabled.png");
    private final static ImageIcon ANNULER_DISABLED = new ImageIcon(CHEMIN_ICONES + "iconShift_disabled.png");

    private ArrayList<ImageIcon> icones = new ArrayList<>();
    private ArrayList<ImageIcon> icones_disabled = new ArrayList<>();

    /* CONSTRUCTEUR */
    public EActions(Vue vue) {

        icones.add(MOVE);
        icones.add(ASSECHER);
        icones.add(DONNER);
        icones.add(RECUPERER);
        icones.add(FIN);
        icones.add(UTILISER);
        icones.add(SPECIALE);
        icones.add(ANNULER);

        icones_disabled.add(MOVE_DISABLED);
        icones_disabled.add(ASSECHER_DISABLED);
        icones_disabled.add(DONNER_DISABLED);
        icones_disabled.add(RECUPERER_DISABLED);
        icones_disabled.add(FIN_DISABLED);
        icones_disabled.add(UTILISER_DISABLED);
        icones_disabled.add(SPECIALE_DISABLED);
        icones_disabled.add(ANNULER_DISABLED);

        this.setLayout(new GridLayout(2, 4));

        this.vue = vue;
        boutons = new ArrayList<>();

        seDeplacerButon = new JButton("Se déplacer", icones.get(0));
        seDeplacerButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.SE_DEPLACER;
                vue.notifierObserver(m);
            }
        });
        boutons.add(seDeplacerButon);

        assecherButon = new JButton("Assécher", icones.get(1));
        assecherButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.ASSECHER;
                vue.notifierObserver(m);
            }
        });
        boutons.add(assecherButon);

        donnerCarteButon = new JButton("Donner carte", icones.get(2));
        donnerCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.DON_CARTE;
                vue.notifierObserver(m);
            }
        });
        boutons.add(donnerCarteButon);

        prendreTresorButon = new JButton("Récupérer trésor", icones.get(3));
        prendreTresorButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.PRENDRE_TRESOR;
                vue.notifierObserver(m);
            }
        });
        boutons.add(prendreTresorButon);

        finDeTourButon = new JButton("Fin de tour", icones.get(4));
        finDeTourButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.FIN_TOUR;
                vue.notifierObserver(m);
            }
        });
        boutons.add(finDeTourButon);

        utiliserCarteButon = new JButton("Utiliser carte", icones.get(5));
        utiliserCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.UTILISER_CARTE;
                vue.notifierObserver(m);
            }
        });
        boutons.add(utiliserCarteButon);

        actionSpecialButon = new JButton("Action spéciale", icones.get(6));
        actionSpecialButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.ACTION_SPECIALE;
                vue.notifierObserver(m);
            }
        });
        boutons.add(actionSpecialButon);

        annulerButon = new JButton("Annuler", icones.get(7));
        annulerButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ANNULER);
                vue.notifierObserver(m);
            }
        });

        this.add(seDeplacerButon);
        this.add(assecherButon);
        this.add(prendreTresorButon);
        this.add(finDeTourButon);
        this.add(utiliserCarteButon);
        this.add(donnerCarteButon);
        this.add(actionSpecialButon);
        this.add(annulerButon);

        for (JButton unBouton : boutons) {
            unBouton.setEnabled(true);

        }

        this.setVisible(true);
    }

    @Override
    /**
     *
     * @enabled etat des boutons
     */
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < boutons.size(); i++) {
            boutons.get(i).setEnabled(enabled);

            if (enabled) {
                boutons.get(i).setIcon(icones.get(i));
            } else {
                boutons.get(i).setIcon(icones.get(i));
            }
        }

        setEnableAnnuler(!enabled);
    }

    /**
     *
     * @param b etat du bouton annulé
     */
    public void setEnableAnnuler(boolean b) {
        annulerButon.setEnabled(b);
        annulerButon.setIcon(icones.get(7));
    }

    /**
     *
     * @param enabled etat des boutons
     * @param actions boutons a update
     */
    public void setEnabled(boolean enabled, ArrayList<Utils.Action> actions) {
        setEnabled(false);
        super.setEnabled(true);
        for (Utils.Action ac
                : actions) {
            switch (ac) {
                default:
                case ACTION_SPECIALE:
                    actionSpecialButon.setEnabled(enabled);
                    if (enabled) {
                        actionSpecialButon.setIcon(icones.get(6));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(6));
                    }
                    break;
                case UTILISER_CARTE:
                    utiliserCarteButon.setEnabled(enabled);
                    if (enabled) {
                        utiliserCarteButon.setIcon(icones.get(5));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(5));
                    }
                    break;
                case PRENDRE_TRESOR:
                    prendreTresorButon.setEnabled(enabled);
                    if (enabled) {
                        prendreTresorButon.setIcon(icones.get(3));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(3));
                    }
                    break;
                case FIN_TOUR:
                    finDeTourButon.setEnabled(enabled);
                    if (enabled) {
                        finDeTourButon.setIcon(icones.get(4));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(4));
                    }
                    break;
                case SE_DEPLACER:
                    seDeplacerButon.setEnabled(enabled);
                    if (enabled) {
                        seDeplacerButon.setIcon(icones.get(0));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(0));
                    }
                    break;
                case DON_CARTE:
                    donnerCarteButon.setEnabled(enabled);
                    if (enabled) {
                        donnerCarteButon.setIcon(icones.get(2));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(2));
                    }
                    break;
                case ASSECHER:
                    assecherButon.setEnabled(enabled);
                    if (enabled) {
                        assecherButon.setIcon(icones.get(1));
                    } else {
                        actionSpecialButon.setIcon(icones_disabled.get(1));
                    }
                    break;
            }
        }
    }

}
