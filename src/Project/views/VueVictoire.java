package Project.views;

import Project.util.Observe;
import Project.views.Elements.EEcranVictoire;

import javax.swing.*;

public class VueVictoire extends Observe {

    private JFrame f ;

    public VueVictoire () {
        f = new JFrame("meow") ;
        f.setSize(500   , 500);

        f.add(new EEcranVictoire()) ;
        f.setVisible(true);
    }

    public static void main (String[] args) { VueVictoire v = new VueVictoire() ; }
}

