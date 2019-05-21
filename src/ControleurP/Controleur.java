package ControleurP;

public class Controleur {
    private Controleur() {

    }

    private final static Controleur controleur = new Controleur();

    public static Controleur getControleur() {
        return controleur;
    }
}
