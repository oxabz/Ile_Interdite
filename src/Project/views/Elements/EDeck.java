package Project.views.Elements;

import Project.FactoryDeck;
import Project.Modele.Deck;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EDeck extends JPanel {

    /* ATTRIBUTS */
    //private Deck deckInnondation = FactoryDeck.getDeckInondations();
    private final Deck deckItems = FactoryDeck.getDeckItems();
    private final JPanel frame;
    private final JPanel sideInondation;
    private final JPanel sideItem;
    private final JPanel itemPioche;
    private final JPanel itemDefausse;
    private final JPanel innondationPioche;
    private final JPanel innodationDefausse;
    private final JLabel itemPiocheNombre;
    private final JLabel itemDefausseNombre;
    private final JLabel innondationPiocheNombre;
    private final JLabel innondationDefausseNombre;

    /* CONSTRUCTEURS */
    public EDeck() {
        // Création des JPanels
        frame = new JPanel(new GridLayout(1, 2));
        sideInondation = new JPanel(new GridLayout(2, 1));
        sideItem = new JPanel(new GridLayout(2, 1));
        innondationPioche = new JPanel(new BorderLayout());
        innodationDefausse = new JPanel(new BorderLayout());
        itemPioche = new JPanel(new BorderLayout());
        itemDefausse = new JPanel(new BorderLayout());
        // Aménagement des JPanels
        this.add(frame);
        frame.add(sideInondation);
        frame.add(sideItem);
        sideInondation.add(innondationPioche);
        sideInondation.add(innodationDefausse);
        sideItem.add(itemPioche);
        sideItem.add(itemDefausse);
        // Couleur d'arrière plan pour tests
        frame.setBackground(Color.DARK_GRAY);
        sideInondation.setBackground(Color.RED);
        sideItem.setBackground(Color.BLUE);
        itemPioche.setBackground(Color.YELLOW);
        itemDefausse.setBackground(Color.ORANGE);
        innondationPioche.setBackground(Color.PINK);
        innodationDefausse.setBackground(Color.MAGENTA);
        // Création des JLabels
        itemPiocheNombre = new JLabel();
        itemDefausseNombre = new JLabel();
        innondationPiocheNombre = new JLabel();
        innondationDefausseNombre = new JLabel();
        // Aménagement des JLabels
        itemPioche.add(itemPiocheNombre, BorderLayout.SOUTH);
        itemDefausse.add(itemDefausseNombre, BorderLayout.SOUTH);
        innondationPioche.add(innondationPiocheNombre, BorderLayout.SOUTH);
        innodationDefausse.add(innondationDefausseNombre, BorderLayout.SOUTH);

    }

    /* METHODES */

 /* GETTERS & SETTERS */
}
