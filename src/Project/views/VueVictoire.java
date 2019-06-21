package Project.views;

import Project.Controleur;
import Project.util.Observe;
import Project.util.Sound;
import Project.util.Utils;
import Project.views.Elements.EEcranVictoire;
import javax.swing.*;

public class VueVictoire extends Observe {

    private JFrame f;

    public VueVictoire() {
        f = new JFrame("Victoire");
        f.setSize(500, 500);
        Sound.jouer(Utils.Son.getCHEMIN_HELICOPTERE());
        Sound son = new Sound();
        son.jouerBoucle(Utils.Son.getCHEMIN_SON() + "/musique/musique_fin.wav", Controleur.getControleur());
        EEcranVictoire ecranVictoire = new EEcranVictoire(this);
        f.add(ecranVictoire);
        f.setVisible(true);
        this.f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ecranVictoire.afficherText(); // pour apeler la fonction de présentation des messages

    }


	public void cacher() {
		this.f.setVisible(false);
	}

    public static void main(String[] args) {
        VueVictoire v = new VueVictoire();
    }
}
