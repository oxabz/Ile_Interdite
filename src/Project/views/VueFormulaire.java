package Project.views;

import Project.util.Observe;
import Project.views.Elements.EFormulaire;

import javax.swing.*;
import java.awt.*;

public class VueFormulaire extends Observe {

    private JFrame f ;

    public VueFormulaire () {
        f = new JFrame("meow") ;
        f.setSize(500 , 500);

        f.add (new EFormulaire (this)) ;
        f.setVisible(true);

    }

    public static void main(String[] args) {
        VueFormulaire v = new VueFormulaire() ;
    }

}
