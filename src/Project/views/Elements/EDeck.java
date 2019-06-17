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
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;

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
    private final LayerUI<JComponent> layerUIAnimationDrag;
    private final JLayer<JComponent> jlayerAnimationDrag;
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
        // Layer
        layerUIAnimationDrag = new AnimationDrag();
        jlayerAnimationDrag = new JLayer<>(this, layerUIAnimationDrag);
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
            /**
             *
             */
            Point pointDepart = new Point(getSideInondation().getWidth() + 5, getLABEL_ITEM().getHeight()); // On récupère les coordonnées comme ça car getLocationOnScreen() ne retourne pas les bonnes valeurs
            Point pointArrive = new Point(getSideInondation().getWidth() + 5, getItemPioche().getHeight() + getLABEL_ITEM().getHeight()); // Voir commentaire de dessus

            Double deplacementY = pointArrive.getY() - pointDepart.getY();
            Double posY = deplacementY / EDeck.getNOMBRE_FRAMES_ANIMATION();

            String location = EDeck.getIMAGE_PREFIXE_CARTE() + getDeckItems().getDefausse().get(0).getImage() + EDeck.getIMAGE_EXTENSION();

            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(location));
            } catch (IOException ex) {
                System.out.println("Project.views.Elements.EDeck.AnimationDrag.paint()");
                System.out.println("Erreur fichier : " + ex.getMessage() + " pour " + location);
            }
            /**
             *
             */
            AnimationDrag animation = new AnimationDrag(image, pointDepart, pointArrive);
            for (int j = 0; j < EDeck.getNOMBRE_FRAMES_ANIMATION(); j++) {
                animation.move(0, j * posY);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ex);
                }

            }

            this.getDeckItems().retirerCartePioche(0);
            this.repaint();
            TimeUnit.SECONDS.sleep(DELAI_ANIMATION);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ex);
        }
    }

    /**
     * Animation qui fait piocher dans le deck
     * InondationgetSideItem().getLocationOnScreen();
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
        return string.replaceAll(" d","D").replaceAll("\\s", "").replaceAll("\'", "");
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

    public EDeck getEDeck() {
        return this;
    }

    public LayerUI<JComponent> getLayerUIAnimationDrag() {
        return layerUIAnimationDrag;
    }

    public JLayer<JComponent> getJlayerAnimationDrag() {
        return jlayerAnimationDrag;
    }

    /* Inner class LayerUI<JComponent> pour dessiner animation de drag d'une carte */
    class AnimationDrag extends LayerUI<JComponent> {

        /* ATTRIBUTS */
        private double posY;
        private double posX;
        private Point pointDepart;
        private Point pointArrive;
        private BufferedImage image;

        /* CONSTRUCTEURS */
        /**
         *
         * @param image l'image qui doit être animée
         * @param pointDepart point en haut à gauche du départ de l'animation
         * @param pointArrive point en bas à gauche de la fin de l'animation         
         * 
         */
        public AnimationDrag(BufferedImage image, Point pointDepart, Point pointArrive) {
            this.pointDepart = pointDepart;
            this.pointArrive = pointArrive;
            this.image = image;
            
        }

        public AnimationDrag() {
        }

        /* METHODES */
        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            g.drawImage(image, (int) (pointDepart.getX()), (int) (posY + pointDepart.getY()), (int) getLabelImagePiocheItem().getSize().getWidth(), (int) getLabelImagePiocheItem().getSize().getHeight(), null);

        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            
        }

        @Override
        public void uninstallUI(JComponent c) {
            super.uninstallUI(c); 
            
        }
        
        
        /**
         * 
         * @param posX quantité de pixels à 'traverser' en X (à chaque itération)
         * @param posY quantité de pixels à 'traverser' en Y (à chaque itération)
         */
        public void move(double posX, double posY) {
            this.setPosX(posX);
            this.setPosY(posY);
            this.paint(getEDeck().getGraphics(), getEDeck());
        }

        /* GETTERS & SETTERS */
        
        private Double getPosY() {
            return this.posY;
        }
        
        private Double getPosX() {
            return this.posX;
        }

        private void setPosX(double posX) {
            this.posX = posX;
        }

        private void setPosY(double posY) {
            this.posY = posY;
        }
        
        

    }

}
