package Project.views.Elements;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import Project.util.Utils;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EActions extends Observe {

    /* CONSTANTES */
    private static final JPanel PANEL = new JPanel();
    public static final int ACTION_MODE = 1;
    public static final int PARAMETER_MODE = 2;
    public static final int STOP_MODE = 0;
    /* ATTRIBUTS */

    private final ArrayList<JButton> boutons;

    private final JButton button_Deplacer = new JButton("Se déplacer");
    private final JButton button_Assecher = new JButton("Assécher");
    private final JButton button_DonnerCarte = new JButton("Donner une carte");
    private final JButton button_RecupererTresor = new JButton("Récupérer un trésor");
    private final JButton button_Special = new JButton("Action spéciale");
    private final JButton button_FinTour = new JButton("Fin du tour");

    /* CONSTRUCTEUR */
    public EActions() {

        PANEL.setLayout(new GridLayout(2, 3));
        PANEL.add(button_Deplacer);
        PANEL.add(button_Assecher);
        PANEL.add(button_DonnerCarte);
        PANEL.add(button_Special);
        PANEL.add(button_RecupererTresor);
        PANEL.add(button_FinTour);
        boutons = new ArrayList<>();
        boutons.add(button_Deplacer);
        boutons.add(button_Assecher);
        boutons.add(button_DonnerCarte);
        boutons.add(button_Special);
        boutons.add(button_RecupererTresor);
        boutons.add(button_FinTour);

        for (JButton unBouton : boutons) {
            unBouton.setEnabled(true);

        }

        PANEL.setVisible(true);
    }

}
