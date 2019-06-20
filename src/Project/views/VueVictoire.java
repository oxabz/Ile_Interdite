package Project.views;

import Project.util.Observe;
import Project.views.Elements.EEcranVictoire;
import javax.swing.*;

public class VueVictoire extends Observe {

    private JFrame f ;

    public VueVictoire () {
        f = new JFrame("Victoire") ;
        f.setSize(500   , 500);

        EEcranVictoire ecranVictoire = new EEcranVictoire(this) ;
        f.add(ecranVictoire) ;
        f.setVisible(true);
        ecranVictoire.afficherText(); // pour apeler la fonction de présentation des messages
    }

    public static void main (String[] args) { VueVictoire v = new VueVictoire() ; }
}

