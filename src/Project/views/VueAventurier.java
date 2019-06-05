package Project.views;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import Project.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VueAventurier extends Observe {

    public static final int ACTION_MODE = 1;
    public static final int PARAMETER_MODE = 2;
    public static final int STOP_MODE = 0;

    private JFrame frame;
    private ArrayList<JButton> actionButtons;
    private JButton okButton;
    private JLabel parametreLabel;

    private JButton seDeplacer;

    public VueAventurier(String nomJoueur, String nomAventurier) {
        this.frame = new JFrame();
        frame.setSize(400,200);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });


        JPanel topPanel = new JPanel();
        frame.add(topPanel,BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(3,1));

        JLabel label = new JLabel(nomJoueur+" : "+nomAventurier);
        parametreLabel = label;
        topPanel.add(label);

        JTextField textField = new JTextField();
        topPanel.add(textField);
        JButton buton = new JButton("valider");
        topPanel.add(buton);
        buton.setEnabled(false);
        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.PARAMETRE);
                m.parametre = textField.getText();
                notifierObserver(m);
            }
        });
        okButton =buton;

        JPanel bottomPanel = new JPanel();
        frame.add(bottomPanel,BorderLayout.SOUTH);
        bottomPanel.setLayout(new GridLayout(3,2));

        actionButtons = new ArrayList<>();

        JButton seDeplacerButon = new JButton("se deplacer");
        bottomPanel.add(seDeplacerButon);
        seDeplacerButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.SE_DEPLACER;
                notifierObserver(m);
            }
        });
        actionButtons.add(seDeplacerButon);
        seDeplacer = seDeplacerButon;

        JButton assecherButon = new JButton("assecher");
        bottomPanel.add(assecherButon);
        assecherButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.ASSECHER;
                notifierObserver(m);
            }
        });
        actionButtons.add(assecherButon);

        JButton donnerCarteButon = new JButton("donner carte");
        bottomPanel.add(donnerCarteButon);
        donnerCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.DON_CARTE;
                notifierObserver(m);
            }
        });
        actionButtons.add(donnerCarteButon);

        JButton prendreTresorButon = new JButton("prendre tresor");
        bottomPanel.add(prendreTresorButon);
        prendreTresorButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.PRENDRE_TRESOR;
                notifierObserver(m);
            }
        });
        actionButtons.add(prendreTresorButon);

        JButton finDeTourButon = new JButton("fin de tour");
        bottomPanel.add(finDeTourButon);
        finDeTourButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.FIN_TOUR;
                notifierObserver(m);
            }
        });
        actionButtons.add(finDeTourButon);

        JButton utiliserCarteButon = new JButton("utiliser carte");
        bottomPanel.add(utiliserCarteButon);
        utiliserCarteButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Message m = new Message(MessageType.ACTION);
                m.action = Utils.Action.UTILISER_CARTE;
                notifierObserver(m);
            }
        });
        actionButtons.add(utiliserCarteButon);

        for (JButton b :
                actionButtons) {
            b.setEnabled(false);
        }

        frame.setVisible(true);
    }

    public void setMode(int mode,String labelText){
        switch (mode){
            case ACTION_MODE:
                okButton.setEnabled(false);
                for (JButton b :
                        actionButtons) {
                    b.setEnabled(true);
                }
                break;
            case PARAMETER_MODE:
                okButton.setEnabled(true);
                for (JButton b :
                        actionButtons) {
                    b.setEnabled(false);
                }
            default:
            case STOP_MODE:
                okButton.setEnabled(false);
                for (JButton b :
                        actionButtons) {
                    b.setEnabled(false);
                }
        }
    }
}
