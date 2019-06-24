package Project.views.Elements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Project.util.AdaptativeDimension;
import Project.util.ImageBuffer;
import Project.util.Utils;

public class EInfo extends JPanel {

    /**
     * CONSTANTES
     */
    private static final double TRESOR_SIZE_RATIO = 1.2;

    /**
     * ATTRIBUTES
     */
    // Panel contenant les 4 tresors
    private JPanel panel_groupe_tresor;
    // Panel de chaque trésor individuellement
    private JPanel[] panel_tresor;
    // Image de chaque tresor
    private BufferedImage[] image_tresor;
    // Tableau des tresors contenant leur nom pour chaque index
    private static final String[] NOM_TRESOR = {"cristal", "calice", "pierre", "zephyr"};
    // Tableau des Tresor correspondant à chaque index
    private static final Utils.Tresor[] INDEX_TRESOR = {Utils.Tresor.CRISTAL,
        Utils.Tresor.COUPE, Utils.Tresor.PIERRE, Utils.Tresor.STATUE};
    // Panel contenant tous les messages
    private JPanel panel_groupe_messages;
    // Label contenant le nombre de tours restant pour le joueur courant
    private JLabel label_tours;
    // Label contenant le pourcentage de tuiles non-inondée
    private JLabel label_tuiles_inondees;
    // Label contenant le pourcentage de tuiles restantes
    private JLabel label_tuiles_restantes;
    // Label contenant le message important
    private JLabel label_alerte;

    /**
     * Nombre de tours Nombre de tuiles immergées/Nombre de tuiles total : %
     * nombre de tuiles non-inondée Nombre de tuiles restantes/Nombre de tuiles
     * total : % nombre de tuiles restantes Message
     * impolabel_tuiles_inondeesrtant : Heliport inondé, joueur bloqué, tresor
     * avec une case restante
     */
    /**
     * CONSTRUCTOR
     */
    public EInfo() {

        this.setLayout(new GridLayout(1, 2));

        this.panel_tresor = new JPanel[4];

        this.image_tresor = new BufferedImage[4];

        this.panel_groupe_tresor = new JPanel(new GridLayout(2, 2));
        new AdaptativeDimension(
                this,
                () -> 10,
                () -> (int) (panel_groupe_tresor.getWidth() * TRESOR_SIZE_RATIO),
                panel_groupe_tresor);

        this.panel_groupe_messages = new JPanel(new GridLayout(4, 1));

        for (int i = 0; i < this.panel_tresor.length; i++) {
            // On récupère l'index courant
            int index = i;

            try {
                // Lecture de l'image selon le nom du tresor
                this.image_tresor[i] = ImageBuffer.getImage(Utils.View.getImagePrefixeTresor() + NOM_TRESOR[i] + Utils.View.getImageExtension()
                );

            } catch (IOException e) {

                // Gestion de l'exception IOException (en cas de fichier non trouvé).
                System.out.println("File " + Utils.View.getImagePrefixeTresor() + NOM_TRESOR[i] + Utils.View.getImageExtension() + " not found");
            }

            // Panel du tresor actuel de la boucle
            this.panel_tresor[i] = new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    // On dessine l'image ensuite
                    g.drawImage(
                            // Image grisée si tresor non-récupéré
                            image_tresor[index],
                            // Position (0, 0)
                            0, 0,
                            // Prend la largeur du panel et la hauteur du panel
                            (int) this.getSize().getWidth(), (int) this.getSize().getHeight(),
                            // Ne prend pas de listener
                            null
                    );
                }
            };

            this.panel_groupe_tresor.add(this.panel_tresor[i]);
        }

        this.add(this.panel_groupe_tresor);

        this.label_tours = new JLabel("Nombre de tours restants : 3");

        this.panel_groupe_messages.add(this.label_tours);

        this.label_tuiles_inondees = new JLabel("Tuiles non-inondées : 100%");

        this.panel_groupe_messages.add(this.label_tuiles_inondees);

        this.label_tuiles_restantes = new JLabel("Tuiles restantes : 100%");

        this.panel_groupe_messages.add(this.label_tuiles_restantes);

        this.label_alerte = new JLabel("");

        this.panel_groupe_messages.add(this.label_alerte);

        this.add(this.panel_groupe_messages);
    }

    /**
     * Met à jour cette vue
     */
    public void update(HashMap<Utils.Tresor, Boolean> tresors, int nbTours, int tuiles_non_inondees, int tuiles_restantes, String message_alerte) {
        // Mise à jour des images
        for (int i = 0; i < this.panel_tresor.length; i++) {
            try {
                // Lecture de l'image selon le nom du tresor
                this.image_tresor[i] = ImageBuffer.getImage(
                        Utils.View.getImagePrefixeTresor() + NOM_TRESOR[i] + Utils.View.getImageExtension()
                );

            } catch (IOException e) {

                // Gestion de l'exception IOException (en cas de fichier non trouvé).
                System.out.println("File " + Utils.View.getImagePrefixeTresor() + NOM_TRESOR[i] + Utils.View.getImageExtension() + " not found");
            } finally {
                // Si le Trésor n'est pas récupéré, on grise l'image du tresor
                if (!tresors.get(INDEX_TRESOR[i]).booleanValue()) {
                    ImageGrisee(this.image_tresor[i]);
                }
            }
        }

        // Repeindre chaque panel permettant de mettre à jour les images
        for (JPanel panel : this.panel_tresor) {
            panel.repaint();
        }

        label_tours.setText("Nombre de tours restants : " + nbTours);

        label_tuiles_inondees.setText("Tuiles non-inondées : " + tuiles_non_inondees + "%");

        label_tuiles_restantes.setText("Tuiles restantes : " + tuiles_restantes + "%");

        label_alerte.setText("<HTML><p>" + message_alerte + "</p></HTML>");
        // Si le message est en majuscule, alors on affiche le texte en rouge
        if (message_alerte.equals(message_alerte.toUpperCase())) {
            label_alerte.setForeground(Color.RED);
            // Sinon on le met en orange tel un avertissement
        } else {
            label_alerte.setForeground(Color.ORANGE);
        }
    }

    /**
     * Converti une image en image grisée
     *
     * @param {BufferedImage} L'image d'entrée
     */
    private BufferedImage ImageGrisee(BufferedImage image) {
        if (image == null) {
            return null;
        }
        // On récupère la largeur de l'image
        int width = image.getWidth();
        // On récupère la hauteur de l'image
        int height = image.getHeight();

        // Pour chaque pixel (largeur, hauteur)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // On récupère la valeur RGB de la couleur du pixel
                Color c = new Color(image.getRGB(j, i));
                // On multiplie pour le rouge, le vert et le bleu, une valeur permettant de convertir en gris
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                // Puis on recréer une couleur avec les nouvelles valeurs RGB
                Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                // On affecte cette couleur au pixel
                image.setRGB(j, i, newColor.getRGB());
            }
        }

        return image;
    }
}
