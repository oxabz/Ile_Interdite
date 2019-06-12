package Project.views.Elements;

import Project.FactoryDeck;
import Project.Modele.Deck;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EDeck extends JPanel {

    /* CONSTANTES */
    private final static String IMAGE_PREFIXE_DECK = "src/images/decks/";
    private final static String IMAGE_PREFIXE_CARTE = "src/images/cartes/";
    private final static String IMAGE_EXTENSION = ".png";
    private final static JLabel LABEL_ITEM = new JLabel("Items");
    private final static JLabel LABEL_INNONDATION = new JLabel("Innondation");
    private final static int NOMBRE_FRAMES_ANIMATION = 30;
    /* ATTRIBUTS */
    private Deck deckInnondation;
    private Deck deckItems;
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
    private BufferedImage imageItem;

    private JLabel labelImagePiocheItem;
    private JLabel labelImageDefausseItem;
    private JLabel labelImagePiocheInnondation;
    private JLabel labelImageDefausseInnondation;

    /* CONSTRUCTEURS */
    public EDeck() throws IOException {
        //deckInnondation = FactoryDeck.getDeckInondations();
        deckItems = FactoryDeck.getDeckItems();
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
        itemPiocheNombre = new JLabel(Integer.toString(deckItems.getPioche().size()));
        itemDefausseNombre = new JLabel(Integer.toString(deckItems.getDefausse().size()));
        innondationPiocheNombre = new JLabel("0");
        innondationDefausseNombre = new JLabel("0");
        // Aménagement des JLabels
        LABEL_ITEM.setHorizontalAlignment(JLabel.CENTER);
        LABEL_ITEM.setVerticalAlignment(JLabel.CENTER);
        LABEL_INNONDATION.setHorizontalAlignment(JLabel.CENTER);
        LABEL_INNONDATION.setVerticalAlignment(JLabel.CENTER);
        itemPiocheNombre.setHorizontalAlignment(JLabel.CENTER);
        itemPiocheNombre.setVerticalAlignment(JLabel.CENTER);
        itemDefausseNombre.setHorizontalAlignment(JLabel.CENTER);
        itemDefausseNombre.setVerticalAlignment(JLabel.CENTER);
        innondationPiocheNombre.setHorizontalAlignment(JLabel.CENTER);
        innondationPiocheNombre.setVerticalAlignment(JLabel.CENTER);
        innondationDefausseNombre.setHorizontalAlignment(JLabel.CENTER);
        innondationDefausseNombre.setVerticalAlignment(JLabel.CENTER);
        itemPioche.add(LABEL_ITEM, BorderLayout.NORTH);
        itemPioche.add(itemPiocheNombre, BorderLayout.SOUTH);
        itemDefausse.add(itemDefausseNombre, BorderLayout.SOUTH);
        innondationPioche.add(LABEL_INNONDATION, BorderLayout.NORTH);
        innondationPioche.add(innondationPiocheNombre, BorderLayout.SOUTH);
        innodationDefausse.add(innondationDefausseNombre, BorderLayout.SOUTH);
        // Création et chargement des images de base
        dosItem = ImageIO.read(new File(IMAGE_PREFIXE_DECK + "dosPioche" + IMAGE_EXTENSION));
        dosItemBleu = ImageIO.read(new File(IMAGE_PREFIXE_DECK + "dosPiocheBleu" + IMAGE_EXTENSION));
        dosInnondation = ImageIO.read(new File(IMAGE_PREFIXE_DECK + "dosDefausse" + IMAGE_EXTENSION));
        dosInnondationBleu = ImageIO.read(new File(IMAGE_PREFIXE_DECK + "dosDefausseBleu" + IMAGE_EXTENSION));

        imageItem = dosItem;
        // Création des JPanels contenant les images (& Override paintComponent)
        labelImagePiocheItem = new JLabel(new ImageIcon(imageItem)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageItem, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImageDefausseItem = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosItemBleu, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImagePiocheInnondation = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosInnondation, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImageDefausseInnondation = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dosInnondationBleu, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        // Aménagement des images
        itemPioche.add(labelImagePiocheItem);
        itemDefausse.add(labelImageDefausseItem);
        innondationPioche.add(labelImagePiocheInnondation);
        innodationDefausse.add(labelImageDefausseInnondation);
    }

    /* METHODES */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        itemPiocheNombre.setText(Integer.toString(deckItems.getPioche().size()));
        itemDefausseNombre.setText(Integer.toString(deckItems.getDefausse().size()));
        //innondationPiocheNombre.setText(Integer.toString(deckInnondation.getPioche().size()));
        //innondationDefausseNombre.setText(Integer.toString(deckInnondation.getPioche().size()));
    }

    /**
     * Animation qui retourne la première carte de la pioche Toujours du dos
     * vers la face
     *
     * @throws java.io.IOException
     */
    public void retournerCartePioche() throws IOException {
        imageItem = ImageIO.read(new File(IMAGE_PREFIXE_CARTE + deckItems.getPioche().get(0).getImage() + IMAGE_EXTENSION));
    }

    /**
     * Utilisé pour voir si les JLabels et les images se mettent bien à jour
     *
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public void test() throws IOException, InterruptedException {
        int nbTour = deckItems.getPioche().size();
        for (int i = 0; i < nbTour; i++) {
            this.retournerCartePioche();            
            deckItems.addCarteDefausseDebut(deckItems.getPioche().get(0));
            deckItems.retirerCartePioche(0);     
            this.repaint();
            TimeUnit.SECONDS.sleep(1);
            imageItem = dosItem;
            this.repaint();
            TimeUnit.SECONDS.sleep(1);

        }

    }
    /* GETTERS & SETTERS */
}
