package Project.views.Elements;

import Project.FactoryDeck;
import Project.Modele.Carte;
import Project.Modele.Cartes.CartesItem.CarteMEau;
import Project.Modele.Deck;
import Project.util.Sound;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class EDeck extends JPanel {

    /* CONSTANTES */
    private static final BufferedImage NULL = null;
    private final static String IMAGE_PREFIXE_CARTE = "src/images/cartes/";
    private final static String IMAGE_EXTENSION = ".png";
    private final static String SON_CARTE_FLIP_CHEMIN = "src/sons/carte/carteFlip.wav";
    private final static JLabel LABEL_ITEM = new JLabel("Items");
    private final static JLabel LABEL_INONDATION = new JLabel("Inondation");
    private final static int NOMBRE_FRAMES_ANIMATION = 24;
    private final static int DELAI_ANIMATION = 500;
    /* ATTRIBUTS */
    private final Deck deckInondation;
    private final Deck deckItems;
    private final JPanel sideInondation;
    private final JPanel sideItem;
    private final JPanel itemPioche;
    private final JPanel itemDefausse;
    private final JPanel inondationPioche;
    private final JPanel inondationDefausse;
    private final JLabel itemPiocheNombre;
    private final JLabel itemDefausseNombre;
    private final JLabel inondationPiocheNombre;
    private final JLabel inondationDefausseNombre;
    private final JLabel labelImagePiocheItem;
    private final JLabel labelImageDefausseItem;
    private final JLabel labelImagePiocheInondation;
    private final JLabel labelImageDefausseInondation;
    private BufferedImage dosItem;
    private BufferedImage dosInondation;
    private BufferedImage imageItemDefausse;
    private BufferedImage imageItemPioche;
    private BufferedImage imageInondationPioche;
    private BufferedImage imageInondationDefausse;

    /* CONSTRUCTEURS */
    public EDeck(Deck deckInondation, Deck deckItems) {
        this.deckInondation = deckInondation;
        this.deckItems = deckItems;
        this.setDoubleBuffered(true);
        // Définition des paramètres propre à l'EDeck
        this.setLayout(new GridLayout(1, 2, 5, 0));
        // Création des JPanels
        sideInondation = new JPanel(new GridLayout(2, 1));
        sideItem = new JPanel(new GridLayout(2, 1));
        inondationPioche = new JPanel(new BorderLayout());
        inondationDefausse = new JPanel(new BorderLayout());
        itemPioche = new JPanel(new BorderLayout());
        itemDefausse = new JPanel(new BorderLayout());
        // Aménagement des JPanels
        this.add(this.getSideInondation());
        this.add(this.getSideItem());
        this.getSideInondation().add(this.getInondationPioche());
        this.getSideInondation().add(this.getInondationDefausse());
        this.getSideItem().add(this.getItemPioche());
        this.getSideItem().add(this.getItemDefausse());
        // Création des JLabels
        itemPiocheNombre = new JLabel();
        itemDefausseNombre = new JLabel();
        inondationPiocheNombre = new JLabel();
        inondationDefausseNombre = new JLabel();
        // Aménagement des JLabels
        EDeck.getLABEL_ITEM().setHorizontalAlignment(JLabel.CENTER);
        EDeck.getLABEL_ITEM().setVerticalAlignment(JLabel.CENTER);
        EDeck.getLABEL_INONDATION().setHorizontalAlignment(JLabel.CENTER);
        EDeck.getLABEL_INONDATION().setVerticalAlignment(JLabel.CENTER);
        this.getItemPiocheNombre().setHorizontalAlignment(JLabel.CENTER);
        this.getItemPiocheNombre().setVerticalAlignment(JLabel.CENTER);
        this.getItemDefausseNombre().setHorizontalAlignment(JLabel.CENTER);
        this.getItemDefausseNombre().setVerticalAlignment(JLabel.CENTER);
        this.getInondationPiocheNombre().setHorizontalAlignment(JLabel.CENTER);
        this.getInondationPiocheNombre().setVerticalAlignment(JLabel.CENTER);
        this.getInondationDefausseNombre().setHorizontalAlignment(JLabel.CENTER);
        this.getInondationDefausseNombre().setVerticalAlignment(JLabel.CENTER);
        this.getItemPioche().add(LABEL_ITEM, BorderLayout.NORTH);
        this.getItemPioche().add(itemPiocheNombre, BorderLayout.SOUTH);
        this.getItemDefausse().add(itemDefausseNombre, BorderLayout.SOUTH);
        this.getInondationPioche().add(LABEL_INONDATION, BorderLayout.NORTH);
        this.getInondationPioche().add(inondationPiocheNombre, BorderLayout.SOUTH);
        this.getInondationDefausse().add(inondationDefausseNombre, BorderLayout.SOUTH);
        // Création et chargement des images de base
        dosItem = null;
        dosInondation = null;
        try {
            dosItem = ImageIO.read(new File(IMAGE_PREFIXE_CARTE + "fondrouge" + IMAGE_EXTENSION));
            dosInondation = ImageIO.read(new File(IMAGE_PREFIXE_CARTE + "fondbleu" + IMAGE_EXTENSION));
        } catch (IOException ex) {
            System.err.println("Project.views.Elements.EDeck.<init>()");
            System.err.println("Erreur fichier : " + ex.getMessage() + " pour dos des cartes");

        }
        this.setImageItemPioche(this.getDosItem());
        this.setImageItemDefausse(EDeck.getNULL());
        this.setImageInondationPioche(this.getDosInondation());
        this.setImageInondationDefausse(EDeck.getNULL());
        // Création des JPanels contenant les images (& Override paintComponent)
        labelImagePiocheItem = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImageItemPioche(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImageDefausseItem = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getImageItemDefausse() == null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2d.setStroke(dashed);
                    g2d.setColor(Color.RED);
                    g2d.drawRoundRect(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), 20, 20);
                    g2d.dispose();
                } else {
                    g.drawImage(getImageItemDefausse(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
                }

            }
        };
        labelImagePiocheInondation = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImageInondationPioche(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);

            }
        };
        labelImageDefausseInondation = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getImageInondationDefausse() == null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2d.setStroke(dashed);
                    g2d.setColor(Color.BLUE);
                    g2d.drawRoundRect(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), 20, 20);
                    g2d.dispose();
                } else {
                    g.drawImage(getImageInondationDefausse(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
                }

            }
        };
        // Aménagement des images
        this.getItemPioche().add(getLabelImagePiocheItem());
        this.getItemDefausse().add(getLabelImageDefausseItem());
        this.getInondationPioche().add(getLabelImagePiocheInondation());
        this.getInondationDefausse().add(getLabelImageDefausseInondation());
    }


    /* METHODES */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.getItemPiocheNombre().setText(Integer.toString(this.getDeckItems().getPioche().size()));
        this.getItemDefausseNombre().setText(Integer.toString(this.getDeckItems().getDefausse().size()));
        this.getInondationPiocheNombre().setText(Integer.toString(this.getDeckInondation().getPioche().size()));
        this.getInondationDefausseNombre().setText(Integer.toString(this.getDeckInondation().getDefausse().size()));
    }

    /**
     * Animation qui retourne la carte, du dos vers la face et joue un son en
     * même temps
     *
     * @param carte la carte qui est piochée
     */
    private void retournerCartePioche(Carte carte) {
        String location = IMAGE_PREFIXE_CARTE + this.cleanString(carte.getImage()) + IMAGE_EXTENSION;
        try {
            this.setImageItemPioche(ImageIO.read(new File(location)));
            Sound.jouer(SON_CARTE_FLIP_CHEMIN);
        } catch (IOException ex) {
            System.err.println("Project.views.Elements.EDeck.retournerCartePioche()");
            System.err.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
        }
    }

    /**
     * Animation qui retourne la carte, du dos vers la face et joue un son en
     * même temps
     *
     * @param carte la carte qui est piochée
     */
    private void retournerCarteInondation(Carte carte) {
        String location = IMAGE_PREFIXE_CARTE + this.cleanString(carte.getNom()) + IMAGE_EXTENSION;
        try {
            this.setImageInondationPioche(ImageIO.read(new File(location)));
            Sound.jouer(SON_CARTE_FLIP_CHEMIN);
        } catch (IOException ex) {
            System.err.println("Project.views.Elements.EDeck.retournerCarteInondation()");
            System.err.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
        }
    }

    /**
     * Animation qui fait piocher dans le deck Item
     *
     * @param carte la carte qui est piochée
     */
    public void piocherItem(Carte carte) {
        try {
            this.retournerCartePioche(carte);
            this.repaint();
            TimeUnit.MILLISECONDS.sleep(DELAI_ANIMATION);
            this.setImageItemPioche(dosItem);
            if (carte instanceof CarteMEau) {
                String location = IMAGE_PREFIXE_CARTE + this.cleanString(carte.getImage()) + IMAGE_EXTENSION;
                try {
                    this.setImageItemDefausse(ImageIO.read(new File(location)));
                } catch (IOException ex) {
                    System.err.println("Project.views.Elements.EDeck.piocherItem()");
                    System.err.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
                }
            }
            repaint();
            TimeUnit.MILLISECONDS.sleep(DELAI_ANIMATION);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }

    }

    /**
     * Animation qui fait piocher dans le deck
     *
     * @param carte la carte qui est piochée
     */
    public void piocherInondation(Carte carte) {
        try {
            this.retournerCarteInondation(carte);
            this.repaint();
            TimeUnit.MILLISECONDS.sleep(DELAI_ANIMATION);
            this.setImageInondationPioche(dosInondation);
            String location = IMAGE_PREFIXE_CARTE + this.cleanString(carte.getNom()) + IMAGE_EXTENSION;
            try {
                this.setImageInondationDefausse(ImageIO.read(new File(location)));
            } catch (IOException ex) {
                System.err.println("Project.views.Elements.EDeck.piocherInondation()");
                System.err.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
            }
            TimeUnit.MILLISECONDS.sleep(DELAI_ANIMATION);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
        repaint();
    }

    /**
     * Utilisé pour récupérer le nom de l'image associé à une carte
     *
     * @param string La chaîne de caractère à nettoyer
     * @return la chaîne de caractère sans espace ni apostrophe
     */
    private String cleanString(String string) {
        return string.toLowerCase().replaceAll("\\s+", "").replaceAll("\'", "");
    }

    /* GETTERS & SETTERS */
    private Deck getDeckItems() {
        return deckItems;
    }

    private Deck getDeckInondation() {
        return deckInondation;
    }

    public BufferedImage getImageInondationPioche() {
        return imageInondationPioche;
    }

    public BufferedImage getImageInondationDefausse() {
        return imageInondationDefausse;
    }

    public BufferedImage getImageItemPioche() {
        return imageItemPioche;
    }

    public JLabel getItemDefausseNombre() {
        return itemDefausseNombre;
    }

    public JLabel getItemPiocheNombre() {
        return itemPiocheNombre;
    }

    public JLabel getInondationDefausseNombre() {
        return inondationDefausseNombre;
    }

    public JLabel getInondationPiocheNombre() {
        return inondationPiocheNombre;
    }

    public JPanel getSideInondation() {
        return sideInondation;
    }

    public JPanel getSideItem() {
        return sideItem;
    }

    public JPanel getItemPioche() {
        return itemPioche;
    }

    public JPanel getItemDefausse() {
        return itemDefausse;
    }

    public JPanel getInondationPioche() {
        return inondationPioche;
    }

    public JPanel getInondationDefausse() {
        return inondationDefausse;
    }

    public BufferedImage getDosItem() {
        return dosItem;
    }

    public BufferedImage getDosInondation() {
        return dosInondation;
    }

    public JLabel getLabelImagePiocheItem() {
        return labelImagePiocheItem;
    }

    public JLabel getLabelImageDefausseItem() {
        return labelImageDefausseItem;
    }

    public JLabel getLabelImagePiocheInondation() {
        return labelImagePiocheInondation;
    }

    public JLabel getLabelImageDefausseInondation() {
        return labelImageDefausseInondation;
    }

    public static String getIMAGE_PREFIXE_CARTE() {
        return IMAGE_PREFIXE_CARTE;
    }

    public static String getIMAGE_EXTENSION() {
        return IMAGE_EXTENSION;
    }

    public static JLabel getLABEL_ITEM() {
        return LABEL_ITEM;
    }

    public static JLabel getLABEL_INONDATION() {
        return LABEL_INONDATION;
    }

    public static int getNOMBRE_FRAMES_ANIMATION() {
        return NOMBRE_FRAMES_ANIMATION;
    }

    public static String getSON_CARTE_FLIP_CHEMIN() {
        return SON_CARTE_FLIP_CHEMIN;
    }

    public static int getDELAI_ANIMATION() {
        return DELAI_ANIMATION;
    }

    public void setImageInondationPioche(BufferedImage imageInondationPioche) {
        this.imageInondationPioche = imageInondationPioche;
    }

    public void setImageItemPioche(BufferedImage imageItemPioche) {
        this.imageItemPioche = imageItemPioche;
    }

    public void setImageInondationDefausse(BufferedImage imageInondationDefausse) {
        this.imageInondationDefausse = imageInondationDefausse;
    }

    public void setImageItemDefausse(BufferedImage imageItemDefausse) {
        this.imageItemDefausse = imageItemDefausse;
    }

    public void setItemPiocheNombre(int nbCartes) {
        this.getItemPiocheNombre().setText(Integer.toString(nbCartes));
    }

    public void setItemDefausseNombre(int nbCartes) {
        this.getItemDefausseNombre().setText(Integer.toString(nbCartes));
    }

    public void setInondationPiocheNombre(int nbCartes) {
        this.getInondationPiocheNombre().setText(Integer.toString(nbCartes));
    }

    public void setInondationDefausseNombre(int nbCartes) {
        this.getInondationDefausseNombre().setText(Integer.toString(nbCartes));
    }

    public EDeck getEDeck() {
        return this;
    }

    public static BufferedImage getNULL() {
        return NULL;
    }

    public BufferedImage getImageItemDefausse() {
        return imageItemDefausse;
    }

}
