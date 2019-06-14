package Project.views.Elements;

import Project.FactoryDeck;
import Project.Modele.Deck;
import Project.util.Sound;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class EDeck extends JPanel {

    /* CONSTANTES */
    private final static String IMAGE_PREFIXE_CARTE = "src/images/cartes/";
    private final static String IMAGE_EXTENSION = ".png";
    private final static String SON_CARTE_FLIP_CHEMIN = "src/sons/carte/carteFlip.wav";
    private final static JLabel LABEL_ITEM = new JLabel("Items");
    private final static JLabel LABEL_INONDATION = new JLabel("Innondation");
    private final static int NOMBRE_FRAMES_ANIMATION = 30;
    private final static int DELAI_ANIMATION = 1;
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
    private final JLabel labelImagePiocheInnondation;
    private final JLabel labelImageDefausseInnondation;
    private final BufferedImage dosItem;
    private final BufferedImage dosInondation;
    private BufferedImage imageItem;
    private BufferedImage imageInondationPioche;
    private BufferedImage imageInondationDefausse;

    /* CONSTRUCTEURS */
    public EDeck() throws IOException {
        deckInondation = FactoryDeck.getDeckInondations();
        deckItems = FactoryDeck.getDeckItems();
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
        dosItem = ImageIO.read(new File(IMAGE_PREFIXE_CARTE + "Fond rouge" + IMAGE_EXTENSION));
        dosInondation = ImageIO.read(new File(IMAGE_PREFIXE_CARTE + "Fond bleu" + IMAGE_EXTENSION));
        this.setImageItem(this.getDosItem());
        this.setImageInondationPioche(this.getDosInondation());
        this.setImageInondationDefausse(this.getImageInondationPioche()); // La défausse est (normalement) vide à l'initialisation donc ça ne gène en rien, on fait ça par précaution
        // Création des JPanels contenant les images (& Override paintComponent)
        labelImagePiocheItem = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImageItem(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImageDefausseItem = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getDosItem(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImagePiocheInnondation = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImageInondationPioche(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        labelImageDefausseInnondation = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(getImageInondationDefausse(), 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
            }
        };
        // Aménagement des images
        this.getItemPioche().add(getLabelImagePiocheItem());
        this.getItemDefausse().add(getLabelImageDefausseItem());
        this.getInondationPioche().add(getLabelImagePiocheInnondation());
        this.getInondationDefausse().add(getLabelImageDefausseInnondation());
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
     * Animation qui retourne la première carte de la pioche (Items) Toujours du
     * dos vers la face et joue un son en même temps *
     */
    private void retournerCartePioche() {
        String location = IMAGE_PREFIXE_CARTE + this.getDeckItems().getPioche().get(0).getImage() + IMAGE_EXTENSION;
        try {
            this.setImageItem(ImageIO.read(new File(location)));
            Sound.play(SON_CARTE_FLIP_CHEMIN);
        } catch (IOException ex) {
            System.out.println("Project.views.Elements.EDeck.retournerCartePioche()");
            System.out.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
        }
    }

    /**
     * Animation qui retourne la première carte de la pioche (Inondation)
     * Toujours du dos vers la face et joue un son en même temps
     */
    private void retournerCarteInnondation() {
        String location = IMAGE_PREFIXE_CARTE + this.cleanString(this.getDeckInondation().getPioche().get(0).getNom()) + IMAGE_EXTENSION;
        try {
            this.setImageInondationPioche(ImageIO.read(new File(location)));
            Sound.play(SON_CARTE_FLIP_CHEMIN);
        } catch (IOException ex) {
            System.out.println("Project.views.Elements.EDeck.retournerCarteInnondation()");
            System.out.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
        }
    }

    /**
     * Animation qui fait piocher dans le deck Item
     */
    private void piocherItem() {
        try {
            this.retournerCartePioche();
            this.repaint();
            TimeUnit.SECONDS.sleep(DELAI_ANIMATION);
            this.getDeckItems().addCarteDefausseDebut(this.getDeckItems().getPioche().get(0));
            this.setImageItem(dosItem);
            this.animationDrag(this.getDeckItems().getDefausse().get(0).getImage(), this.getLabelImagePiocheItem().getLocationOnScreen(), this.getLabelImageDefausseItem().getLocationOnScreen(), this.getGraphics(), (int) this.getLabelImagePiocheItem().getSize().getWidth(), (int) this.getLabelImagePiocheItem().getSize().getHeight());
            this.getDeckItems().retirerCartePioche(0);
            this.repaint();
            TimeUnit.SECONDS.sleep(DELAI_ANIMATION);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
    }

    /**
     * Animation qui fait piocher dans le deck Inondation
     */
    private void piocherInondation() {
        try {
            this.retournerCarteInnondation();
            this.repaint();
            TimeUnit.SECONDS.sleep(DELAI_ANIMATION);
            this.getDeckInondation().addCarteDefausseDebut(this.getDeckInondation().getPioche().get(0));
            this.getDeckInondation().retirerCartePioche(0);
            this.setImageInondationPioche(dosInondation);
            String location = IMAGE_PREFIXE_CARTE + this.cleanString(this.getDeckInondation().getDefausse().get(0).getNom()) + IMAGE_EXTENSION;
            try {
                this.setImageInondationDefausse(ImageIO.read(new File(location)));
            } catch (IOException ex) {
                System.out.println("Project.views.Elements.EDeck.piocherInondation()");
                System.out.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
            }
            TimeUnit.SECONDS.sleep(DELAI_ANIMATION);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
    }

    /**
     * Utilisé pour récupérer le nom de l'image associé à une carte
     *
     * @param string La chaîne de caractère à nettoyer
     * @return la chaîne de caractère sans espace ni apostrophe
     */
    private String cleanString(String string) {
        return string.replaceAll("\\s", "").replaceAll("\'", "");
    }

    /**
     * Utilisé pour voir si les JLabels et les images se mettent bien à jour
     */
    public void test() {
        int nbTour = this.getDeckItems().getPioche().size();
        for (int i = 0; i < nbTour; i++) {
            this.piocherItem();
        }

        nbTour = this.getDeckInondation().getPioche().size();
        for (int i = 0; i < nbTour; i++) {
            this.piocherInondation();
        }

    }

    /**
     * Animation qui déplace l'image du premier point au second
     */
    private void animationDrag(String imageString, Point pointDepart, Point pointArrive, Graphics g, int imageWidth, int imageHeight) {
        double deplacementX;
        double deplacementY;
        double posX;
        double posY;
        BufferedImage image = null;
        String location = EDeck.getIMAGE_PREFIXE_CARTE() + imageString + EDeck.getIMAGE_EXTENSION();
        try {
            image = ImageIO.read(new File(location));
        } catch (IOException ex) {
            System.out.println("Project.views.Elements.EDeck.animationDrag()");
            System.out.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
        }

        deplacementX = pointArrive.getX() - pointDepart.getX();
        deplacementY = pointArrive.getY() - pointDepart.getY();
        posX = deplacementX / EDeck.getNOMBRE_FRAMES_ANIMATION();
        posY = deplacementY / EDeck.getNOMBRE_FRAMES_ANIMATION();

        for (int j = 0; j < EDeck.getNOMBRE_FRAMES_ANIMATION(); j++) { // Créer un JPanel qui override paintComponent() et qui se situe par dessus 2 autres jpanels sans les cacher ni modifier le comportement du LayoutManager
            g.drawImage(image, (int) (j * posX + pointDepart.getX()), (int) (j * posY + pointDepart.getY()), imageWidth, imageHeight, null);
            try {
                 TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ex);
            }
            this.repaint();
        }
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

    public BufferedImage getImageItem() {
        return imageItem;
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

    public JLabel getLabelImagePiocheInnondation() {
        return labelImagePiocheInnondation;
    }

    public JLabel getLabelImageDefausseInnondation() {
        return labelImageDefausseInnondation;
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

    public void setImageItem(BufferedImage imageItem) {
        this.imageItem = imageItem;
    }

    public void setImageInondationDefausse(BufferedImage imageInondationDefausse) {
        this.imageInondationDefausse = imageInondationDefausse;
    }

}
