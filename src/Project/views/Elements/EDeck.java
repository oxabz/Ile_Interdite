package Project.views.Elements;

import Project.FactoryDeck;
import Project.Modele.Deck;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EDeck extends JPanel {

    /* CONSTANTES */
    private final static String IMAGE_PREFIXE = "src/images/deck/";
    private final static String IMAGE_EXTENSION = ".png";
    /* ATTRIBUTS */
    //private Deck deckInnondation = FactoryDeck.getDeckInondations();
    private final Deck deckItems = FactoryDeck.getDeckItems();
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
    private final BufferedImage dosCarteDeckItem;

    /* CONSTRUCTEURS */
    public EDeck() throws IOException {
        this.setLayout(new GridLayout(1, 2));

        // Création des JPanels
        sideInondation = new JPanel(new GridLayout(2, 1));
        sideItem = new JPanel(new GridLayout(2, 1));
        innondationPioche = new JPanel(new BorderLayout());
        innodationDefausse = new JPanel(new BorderLayout());
        itemPioche = new JPanel(new BorderLayout());
        itemDefausse = new JPanel(new BorderLayout());
        // Aménagement des JPanels
        this.add(sideInondation);
        this.add(sideItem);
        sideInondation.add(innondationPioche);
        sideInondation.add(innodationDefausse);
        sideItem.add(itemPioche);
        sideItem.add(itemDefausse);
        // Couleur d'arrière plan pour tests
        this.setBackground(Color.DARK_GRAY);
        sideInondation.setBackground(Color.RED);
        sideItem.setBackground(Color.BLUE);
        itemPioche.setBackground(Color.YELLOW);
        itemDefausse.setBackground(Color.ORANGE);
        innondationPioche.setBackground(Color.PINK);
        innodationDefausse.setBackground(Color.MAGENTA);
        // Création des JLabels
        itemPiocheNombre = new JLabel("1");
        itemDefausseNombre = new JLabel("2");
        innondationPiocheNombre = new JLabel("3");
        innondationDefausseNombre = new JLabel("4");
        // Aménagement des JLabels
        itemPioche.add(itemPiocheNombre, BorderLayout.SOUTH);
        itemDefausse.add(itemDefausseNombre, BorderLayout.SOUTH);
        innondationPioche.add(innondationPiocheNombre, BorderLayout.SOUTH);
        innodationDefausse.add(innondationDefausseNombre, BorderLayout.SOUTH);
        // Création des images
        dosCarteDeckItem = ImageIO.read(new File(IMAGE_PREFIXE + "dosCarte" + IMAGE_EXTENSION));
        JPanel imagePioche = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosCarteDeckItem, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        // Aménagement des images
        itemPioche.add(imagePioche, BorderLayout.NORTH);
        

    }

    /* METHODES */

 /* GETTERS & SETTERS */
}
