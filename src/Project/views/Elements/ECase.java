package Project.views.Elements;

import Project.util.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ECase extends JPanel {
    public enum Etat{
        SEC,
        INNONDEE,
        COULEE
    }

    /*
    CONSTANTS
     */

    private static final String IMAGE_PREFIX = "src/images/tuiles/";
    private static final String IMAGE_EXTENTION = ".png";
    private static final String IMAGE_INNONDEE_SUFIX = "_Inonde";

    /*
    ATTRIBUTES
     */
    private String name;
    private BufferedImage imageNormale;
    private BufferedImage imageInnondee;

    private Vector2 position;
    private Etat etat;

    public ECase(String name, Vector2 position) {
        this.name = name;
        this.position = position;
        this.etat = Etat.SEC;
        try {
            imageNormale = ImageIO.read(new File(IMAGE_PREFIX+name.replaceAll(" ", "")+IMAGE_EXTENTION));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageInnondee = ImageIO.read(new File(IMAGE_PREFIX+name.replaceAll(" ", "")+IMAGE_INNONDEE_SUFIX+IMAGE_EXTENTION));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (etat){
            case SEC:
                g.drawImage(imageNormale,0,0,(int)this.getSize().getWidth(),(int)this.getSize().getHeight(),null);
                break;
            case COULEE:
                break;
            case INNONDEE:
                g.drawImage(imageInnondee,0,0,(int)this.getSize().getWidth(),(int)this.getSize().getHeight(),null);
                break;
        }
    }
}
