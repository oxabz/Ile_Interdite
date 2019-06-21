package Project.views;

import Project.util.Observe;
import Project.views.Elements.EFormulaire;

import javax.swing.*;

public class VueFormulaire extends Observe {

    private final JFrame f ;

    public VueFormulaire () {
        f = new JFrame("Formulaire") ;
        f.setSize(700 , 600);

        f.add (new EFormulaire (this)) ;
        f.setVisible(true);
        this.f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        VueFormulaire v = new VueFormulaire() ;
    }

    public JFrame getFrame() {
        return f;
    }
}
