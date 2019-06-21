package Project.views;

import Project.util.Observe;
import Project.views.Elements.EEcranVictoire;
import javax.swing.*;

public class VueVictoire extends Observe {

    private JFrame window ;

    public VueVictoire () {
        this.window = new JFrame("Victoire") ;
        this.window.setSize(500   , 500);

        EEcranVictoire ecranVictoire = new EEcranVictoire(this) ;
        this.window.add(ecranVictoire) ;
        this.window.setVisible(true);
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ecranVictoire.afficherText(); // pour apeler la fonction de présentation des messages

    }


	public void cacher() {
		this.window.setVisible(false);
	}

    public static void main (String[] args) { VueVictoire v = new VueVictoire() ; }
}

