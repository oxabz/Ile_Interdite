package Project.Modele.Cartes.CartesItem;

import java.util.ArrayList;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.util.Vector2;

public class CarteHelicoptere extends CarteBonus {

    /* CONSTRUCTEURS */
    public CarteHelicoptere(String nom) {
        super(nom);
    }

    public CarteHelicoptere(String nom, String image) {
        super(nom, image);
    }

    /**
     * Permet de téléporter tous les joueurs d'une tuile à n'importe quelle
     * tuile de la grille
     */
    @Override
    public void actionCarte() {
        Controleur c = Controleur.getControleur();
        Grille grille = c.getGrille();

        // On récupère la position du joueur en train de joueur.
        Vector2 pos_joueur = c.getAventuriers().get(c.getCurrentAventurier()).getPosition();

        ArrayList<Aventurier> aventuriers = new ArrayList<>();

        for (Aventurier aventurier : c.getAventuriers()) {
            // On récupère tous les joueurs qui sont sur la même tuile que le joueur courant
            if (aventurier.getPosition() == pos_joueur) {
                aventuriers.add(aventurier);
            }
        }

        // Vecteur des positions à afficher pour une sélection
        ArrayList<Vector2> pos = new ArrayList<>();

        // Pour toutes les tuiles de la grille
        for (int i = 0; i < grille.getSizeX(); i++) {
            for (int j = 0; j < grille.getSizeY(); j++) {

                // On récupère la tuile
                Tuile t = grille.getTuile(i, j);
                // On vérifie qu'elle existe et on ajoute la position au vecteur
                if (t != null && (pos_joueur.x != i || pos_joueur.y != j)) {
                    pos.add(new Vector2(i, j));
                }
            }
        }

        // On récupère quelle position a été cliqué en donnant le vecteur de positions
        Vector2 tuile_pos = c.getPosClic(pos, true);

        // S'il la tuile existe, on s'y téléporte
        if (tuile_pos != null) {
            for (Aventurier aventurier : aventuriers) {
                aventurier.setPosition(tuile_pos);
            }
        }
    }
}
