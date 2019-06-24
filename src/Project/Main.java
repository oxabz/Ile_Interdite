package Project;

public class Main {

    public static void main(String[] args) {
        // On créer le contrôleur pour la première partie
        Controleur c = Controleur.getControleur();
        // On lance le jeu
        c.gameLoop();

        // Si le joueur choisit de rejouer
        while (!c.isPartieFinie()) {
            // On recréer un contrôleur
            c = Controleur.resetControleur();
            c.gameLoop();
        }
        System.exit(0);
    }
}
