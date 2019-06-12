package Project.views.Elements;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EDeck extends JPanel {

    /* CONSTANTES */
    private final static String IMAGE_PREFIXE = "src/images/decks/";
    private final static String IMAGE_EXTENSION = ".png";
    private final static JLabel LABEL_ITEM = new JLabel("Items");
    private final static JLabel LABEL_INNONDATION = new JLabel("Innondation");
    private final static int NOMBRE_FRAME_ANIMATION = 30;
    /* ATTRIBUTS */
    //private Deck deckInnondation = FactoryDeck.getDeckInondations();
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
    private final BufferedImage dosItem;
    private final BufferedImage dosInnondation;
    private final BufferedImage dosItemBleu;
    private final BufferedImage dosInnondationBleu;

    /* CONSTRUCTEURS */
    public EDeck() throws IOException {
        // Définition des paramètres propre à l'EDeck
        this.setLayout(new GridLayout(1, 2, 5, 0));
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
        /*
        this.setBackground(Color.DARK_GRAY);
        sideInondation.setBackground(Color.RED);
        sideItem.setBackground(Color.BLUE);
        itemPioche.setBackground(Color.YELLOW);
        itemDefausse.setBackground(Color.ORANGE);
        innondationPioche.setBackground(Color.PINK);
        innodationDefausse.setBackground(Color.MAGENTA);
         */
        // Création des JLabels
        itemPiocheNombre = new JLabel("1");
        itemDefausseNombre = new JLabel("2");
        innondationPiocheNombre = new JLabel("3");
        innondationDefausseNombre = new JLabel("4");
        // Aménagement des JLabels
        itemPioche.add(LABEL_ITEM, BorderLayout.NORTH);
        itemPioche.add(itemPiocheNombre, BorderLayout.SOUTH);
        itemDefausse.add(itemDefausseNombre, BorderLayout.SOUTH);
        innondationPioche.add(LABEL_INNONDATION, BorderLayout.NORTH);
        innondationPioche.add(innondationPiocheNombre, BorderLayout.SOUTH);
        innodationDefausse.add(innondationDefausseNombre, BorderLayout.SOUTH);
        // Création des images
        dosItem = ImageIO.read(new File(IMAGE_PREFIXE + "dosPioche" + IMAGE_EXTENSION));
        dosItemBleu = ImageIO.read(new File(IMAGE_PREFIXE + "dosPiocheBleu" + IMAGE_EXTENSION));
        dosInnondation = ImageIO.read(new File(IMAGE_PREFIXE + "dosDefausse" + IMAGE_EXTENSION));
        dosInnondationBleu = ImageIO.read(new File(IMAGE_PREFIXE + "dosDefausseBleu" + IMAGE_EXTENSION));
        // Création des JPanels contenant les images (& Override paintComponent)
        JPanel imagePiocheItem = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosItem, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        JPanel imageDefausseItem = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D graphics2D = (Graphics2D) g;
                super.paintComponent(g);
                g.drawImage(dosItemBleu, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);

            }
        };
        JPanel imagePiocheInnondation = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosInnondation, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        JPanel imageDefausseInnondation = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosInnondationBleu, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);

            }
        };
        // Aménagement des images
        itemPioche.add(imagePiocheItem);
        itemDefausse.add(imageDefausseItem);
        innondationPioche.add(imagePiocheInnondation);
        innodationDefausse.add(imageDefausseInnondation);
    }

    /* METHODES */
    private void flipCarte(JPanel dosCarte, JPanel faceCarte) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < NOMBRE_FRAME_ANIMATION; i++) {
                    // A TERMINER, FAIRE UN FONDU SI PAS POSSIBLE / PAS LE TEMPS
                }
            }
        };
    }
    /* GETTERS & SETTERS */
}
