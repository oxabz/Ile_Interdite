package Project.views.Elements;

import Project.Modele.Aventurier;
import Project.util.ImageBuffer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ECarteAventurier extends JPanel {

    private static final String IMAGE_PREFIX = "src/images/personnages/";
    private static final String IMAGE_EXTENTION = ".png";

    private String nomAventurier;

    public ECarteAventurier() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        if(nomAventurier != null){
            try {
                image = ImageBuffer.getImage(IMAGE_PREFIX + nomAventurier.toLowerCase() + IMAGE_EXTENTION);
            } catch (IOException e) {
                System.err.println("Erreur chargement carte aventurier");
            }
        }
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(), null );
    }

    public void setAv(Aventurier av) {
        this.nomAventurier = av.getNom();
        repaint();
    }
}
