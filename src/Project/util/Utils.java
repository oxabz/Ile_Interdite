/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import Project.Modele.Aventurier;

/**
 *
 * @author Eric
 */
public class Utils {

    public enum Tresor {
        CRISTAL,
        COUPE,
        PIERRE,
        STATUE
    }

    public static enum EtatTuile {
        ASSECHEE("Asséchée"),
        INONDEE("Inondée"),
        COULEE("Coulée");

        String libelle;

        EtatTuile(String libelle) {
            this.libelle = libelle;
        }

        @Override
        public String toString() {
            return this.libelle;
        }
    }

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0)),
        VERT("Vert", new Color(0, 195, 0)),
        BLEU("Bleu", new Color(55, 194, 198)),
        // ORANGE("Orange", new Color(255, 148, 0)), inutilisé
        // VIOLET("Violet", new Color(204, 94, 255)), inutilisé
        JAUNE("Jaune", new Color(255, 255, 0)),
        GRIS("Gris", new Color(100, 100, 100)),
        NOIR("Noir", new Color(0, 0, 0));

        private final String libelle;
        private final Color couleur;
        private BufferedImage image;

        Pion(String libelle, Color couleur) {
            this.libelle = libelle;
            this.couleur = couleur;
            try {
                image = ImageBuffer.getImage(Utils.View.getImagePrefixePion() + libelle + Utils.View.getImageExtension());
            } catch (IOException e) {
                System.err.println("Erreur : Echec du chargement de l'image du pion " + libelle);
                image = null;
            }

        }

        @Override
        public String toString() {
            return this.libelle;
        }

        public Color getCouleur() {
            return this.couleur;
        }

        static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) {
                return ROUGE;
            }
            if (VERT.name().equals(name)) {
                return VERT;
            }
            if (BLEU.name().equals(name)) {
                return BLEU;
            }/*
            if (ORANGE.name().equals(name)) {
                return ORANGE;
            }
            if (VIOLET.name().equals(name)) {
                return VIOLET;
            }*/
            if (JAUNE.name().equals(name)) {
                return JAUNE;
            }
            return null;
        }

        public BufferedImage getImage() {
            return image;
        }
    }

    /**
     * Permet d'afficher un message d'information avec un bouton OK
     *
     * @param message Message à afficher
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }

    public enum Action {
        SE_DEPLACER,
        ASSECHER,
        PRENDRE_TRESOR,
        DON_CARTE,
        UTILISER_CARTE,
        ACTION_SPECIALE,
        FIN_TOUR
    }

    public static class View {

        private final static String IMAGE_PREFIXE_CARTE = "src/images/cartes/";
        private final static String IMAGE_EXTENSION = ".png";
        private final static String IMAGE_PREFIXE_TRESOR = "src/images/tresors/";
        private final static String IMAGE_PREFIXE_PION = "src/images/pions/pion";
        private final static int NOMBRE_FRAMES_ANIMATION = 30;

        public static String getImagePrefixeCarte() {
            return IMAGE_PREFIXE_CARTE;
        }

        public static String getImageExtension() {
            return IMAGE_EXTENSION;
        }

        public static int getNombreFramesAnimation() {
            return NOMBRE_FRAMES_ANIMATION;
        }

        public static String getImagePrefixeTresor() {
            return IMAGE_PREFIXE_TRESOR;
        }

        public static String getImagePrefixePion() {
            return IMAGE_PREFIXE_PION;
        }
    }

    public static class Son {

        private final static String CHEMIN_SON = "src/sons/";
        private final static String CHEMIN_HELICOPTERE = "src/sons/effets/helicoptere.wav";
        private final static String CHEMIN_INONDATION = "src/sons/carte/inondation.wav";
        private final static String CHEMIN_SABLE = "src/sons/effets/sable.wav";

        public static String getCHEMIN_SON() {
            return CHEMIN_SON;
        }

        public static String getCHEMIN_SABLE() {
            return CHEMIN_SABLE;
        }

        public static String getCHEMIN_HELICOPTERE() {
            return CHEMIN_HELICOPTERE;
        }

        public static String getCHEMIN_INONDATION() {
            return CHEMIN_INONDATION;
        }

    }
}
