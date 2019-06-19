package Project.views.Elements;

import Project.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EPion extends JPanel {

    private Utils.Pion pion;

    public EPion() {
        setBackground(new Color(0,0,0,0));
        setEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(pion!=null){g.drawImage(pion.getImage(),0,0,getWidth(),getHeight(),null);}
    }

    public void setPion(Utils.Pion pion) {
        this.pion = pion;
        repaint();
    }
}
