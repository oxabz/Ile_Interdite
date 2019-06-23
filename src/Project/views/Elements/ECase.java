package Project.views.Elements;

import Project.Modele.Grille;
import Project.util.ImageBuffer;
import Project.util.Utils;
import Project.util.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ECase extends JPanel {

    public enum Etat {
        SEC,
        INNONDEE,
        COULEE
    }

    public class FiltreCouleur extends LayerUI<JComponent> {

        private Color color;

        public FiltreCouleur(Color color) {
            this.color = color;
        }

        public FiltreCouleur(Color color, float opacity) {
            this.color = new Color(color.getRed() / 256, color.getGreen() / 256, color.getBlue() / 256, opacity);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            setBackground(color);
        }
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
    private JLayer filter;
    private EPion[] pions = new EPion[4];

    private Vector2 position;
    private Etat etat;
    private EGrille grille;
    private boolean clickable;

    public ECase(String name, Vector2 position, EGrille grille) {
        this.name = name;
        name = name.replaceAll(" d\u0027", "d");
        name = name.replaceAll(" d", "D");
        name = name.replaceAll(" l\u0027", "L");
        name = name.replaceAll(" ", "");
        this.position = position;
        this.etat = Etat.SEC;
        if (!name.equals("")) {
            String location = "JE SUIS VIDE C'EST PAS NORMAL";
            try {
                location = IMAGE_PREFIX + name.replaceAll(" ", "") + IMAGE_EXTENTION;
                imageNormale = ImageBuffer.getImage(location);
            } catch (IOException e) {
                System.out.println("Project.views.Elements.ECase.<init>()");
                System.out.println(e.getMessage() + " pour " + location);
            }
            try {
                location = IMAGE_PREFIX + name.replaceAll(" ", "") + IMAGE_INNONDEE_SUFIX + IMAGE_EXTENTION;
                imageInnondee = ImageBuffer.getImage(location);
            } catch (IOException e) {
                System.out.println("Project.views.Elements.ECase.<init>() INONDEE");
                System.out.println(e.getMessage() + " pour " + location);
            }
        }


        /*
        *Ajout des pion
         */

        this.setLayout(new GridLayout(2,2));

        pions[0] = new EPion();
        pions[0].setVisible(false);
        this.add(pions[0]);
        pions[1] = new EPion();
        pions[1].setVisible(false);
        this.add(pions[1]);
        pions[2] = new EPion();
        pions[2].setVisible(false);
        this.add(pions[2]);
        pions[3] = new EPion();
        pions[3].setVisible(false);
        this.add(pions[3]);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ECase eCase = ((ECase) mouseEvent.getComponent());
                if (eCase.isEnabled()) {
                    eCase.getGrille().messageCase(eCase.getPosition());
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
        this.grille = grille;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (etat) {
            case SEC:
                g.drawImage(imageNormale, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
                break;
            default:
            case COULEE:
                break;
            case INNONDEE:
                g.drawImage(imageInnondee, 0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight(), null);
                break;
        }
    }

    public void changeEtat(Etat newEtat) {
        this.etat = newEtat;
        this.repaint();
    }

    public EGrille getGrille() {
        return grille;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
        if (clickable) {
            setEnabled(true);
            /*
            filter.setVisible(true);
            filter.paint(this.getGraphics());*/
            //setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));

        } else {
            setEnabled(false);
//            filter.setVisible(false);
            //setBorder(null);
        }
    }


    public void setPions(ArrayList<Utils.Pion> pions){
        for (int i = 0; i < this.pions.length; i++) {
            this.pions[i].setVisible(i<pions.size());
            if (i<pions.size()){
                this.pions[i].setPion(pions.get(i));
            }
        }
    }
}
