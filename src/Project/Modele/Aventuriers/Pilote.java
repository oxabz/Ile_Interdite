package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.Modele.Tuile;
import Project.util.Utils;
import Project.util.Vector2;

import java.util.ArrayList;

public class Pilote extends Aventurier {

    /* ATTRIBUTS */
    private boolean deplacementSpecial = true;
    private static final String NOM = "Pilote";
    private static final Utils.Pion PION = Utils.Pion.BLEU;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier de type pilote
     *
     * @deprecated utiliser {@link #Pilote(Vector2) à la place
     */
    @Deprecated
    public Pilote() {
        super();
    }

    /**
     * Créer un aventurier de type pilote
     *
     * @param position la position de départ du pilote
     */
    public Pilote(Vector2 position) {
        super(position);
    }

    /* MÉTHODES */

    @Override
    public void initialiserTour() {
        super.initialiserTour();
        setDeplacementSpecial(true);
    }

    /**
     * Permet de déplacer un aventurier
     *
     * @return true si le déplacement a été effectuée
     *
     */
    @Override
    public boolean seDeplacer() {
        if (deplacementSpecial) {
            Controleur c = Controleur.getControleur();
            ArrayList<Vector2> pos = new ArrayList<>();
            Grille grille = c.getGrille();
            for (int i = 0; i < grille.getSizeX(); i++) {
                for (int j = 0; j < grille.getSizeY(); j++) {
                    Tuile t = grille.getTuile(i, j);
                    if (t != null && (position.x != i || position.y != j)) {
                        pos.add(new Vector2(i, j));
                    }
                }
            }
            Vector2 p = c.getPosClic(pos);
            if (p != null) {
                boolean b = false;
                for (Vector2 posNormal
                        : getPosDeplacement()) {
                    b = b || posNormal.x == p.x && posNormal.y == p.y;
                }
                position = p;
                deplacementSpecial = b;
                return true;
            } else {
                return false;
            }
        } else {
            return super.seDeplacer();

        }
    }

    @Override
    public String getNom() {
        return NOM;
    }

    public void setDeplacementSpecial(boolean deplacementSpecial) {
        this.deplacementSpecial = deplacementSpecial;
    }

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
