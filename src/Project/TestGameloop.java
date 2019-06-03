package Project;

public class TestGameloop {
    public static void main(String[] args) {
        Controleur c = Controleur.getControleur();
        c.initialiserPartie();
        c.gameLoop();
    }
}
