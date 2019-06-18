package Project.views.Elements;

import Project.Modele.Aventurier;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ECarteAventurier extends JPanel {

    private static final String IMAGE_PREFIX = "images/personnages/";
    private static final String IMAGE_EXTENTION = ".png";

    private Aventurier av;

    public ECarteAventurier() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        if(av != null){
            try {
                image = ImageIO.read(new File(IMAGE_PREFIX + av.getNom().toLowerCase() + IMAGE_EXTENTION));
            } catch (IOException e) {
                System.out.println("Erreur chargement carte");
            }
        }
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(), null );
    }

    public void setAv(Aventurier av) {
        this.av = av;
        repaint();
    }
}
