package Project.views.Elements;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EActions extends JPanel {

    /* CONSTANTES */
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

        this.setLayout(new GridLayout(2, 3));
        this.add(button_Deplacer);
        this.add(button_Assecher);
        this.add(button_DonnerCarte);
        this.add(button_Special);
        this.add(button_RecupererTresor);
        this.add(button_FinTour);
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

        this.setVisible(true);
    }

}
