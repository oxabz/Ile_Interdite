package Project.views.Elements;

import Project.Modele.Carte;
import Project.util.Message;
import Project.util.MessageType;
import Project.views.Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ECarte extends JPanel {

    private static final String IMAGE_PREFIX = "src/images/cartes/";
    private static final String IMAGE_EXTENTION = ".png";
    private static final String IMAGE_INNONDEE_SUFIX = "_Inonde";

    Vue vue;
    BufferedImage image;
    Carte carte;

    public ECarte(Vue vue) {
        this.vue = vue;

        try {
            image = ImageIO.read(new File(IMAGE_PREFIX + "fondrouge" + IMAGE_EXTENTION));
        } catch (IOException e) {
            System.out.println("Erreur chargement carte ");
        }
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ECarte ths = (ECarte) mouseEvent.getComponent();
                if (ths.isEnabled()){
                    Message m = new Message(MessageType.CARTE);
                    m.carte = ths.carte;
                    ths.vue.notifierObserver(m);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte){
        this.carte=carte;
        String name = (carte!=null ? carte.getImage() : "");
        name = name.replaceAll("\\s+","").replaceAll("\'","").replaceAll("Carte","").toLowerCase();
        try {
            image = ImageIO.read(new File(IMAGE_PREFIX + name + IMAGE_EXTENTION));
        } catch (IOException e) {
            if(!name.equals("")){
                System.out.println("not found image"+name);
            }
            try {
                image = ImageIO.read(new File(IMAGE_PREFIX + "fondrouge" + IMAGE_EXTENTION));
            } catch (IOException f) {
                System.out.println("Erreur chargement fallback");
            }
        }
        repaint();
    }
}
