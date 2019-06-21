package Project.Modele.Cartes.CartesItem;

import java.util.ArrayList;

import Project.Controleur;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.util.Vector2;

public class CarteSacSable extends CarteBonus {

    /* CONSTRUCTEURS */
    /**
     *
     * @param nom le nom de la carte à créer
     * @deprecated utiliser {@link #CarteSacSable(String, String)} à la place
     */
    @Deprecated
    public CarteSacSable(String nom) {
        super(nom);
    }

    /**
     *
     * @param nom le nom de la carte à créer
     * @param image le nom de l'image correspondante
     */
    public CarteSacSable(String nom, String image) {
        super(nom, image);
    }


    /* MÉTHODES */
    /**
     * Permet d'assécher une carte sur n'importe quelle tuile de la grille
     */
    @Override
    public void actionCarte() {
        Controleur c = Controleur.getControleur();
        Grille grille = c.getGrille();

        // Vecteur des positions à afficher pour une sélection
        ArrayList<Vector2> pos = new ArrayList<>();

        // Pour toutes les tuiles de la grille
        for (int i = 0; i < grille.getSizeX(); i++) {
            for (int j = 0; j < grille.getSizeY(); j++) {

                // On récupère la tuile
                Tuile t = grille.getTuile(i, j);
                // On vérifie qu'elle existe et on ajoute la position au vecteur
                if (t != null) {
                    pos.add(new Vector2(i, j));
                }
            }
        }

        // On récupère quelle position a été cliqué en donnant le vecteur de positions
        Vector2 aAssecher = c.getPosClic(pos, true);

        // S'il la tuile existe, on l'asseche
        if (aAssecher != null) {
            grille.getTuile(aAssecher).setInnondee(false);
        }
    }

}
