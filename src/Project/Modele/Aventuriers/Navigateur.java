package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Utils;
import Project.util.Vector2;
import java.util.ArrayList;
import java.util.Iterator;

public class Navigateur extends Aventurier {

    private static final boolean ACTION_SPECIAL_DISPONIBLE = true;

    /* ATTRIBUTS */
    private static final String NOM = "Navigateur";
    private static final Utils.Pion PION = Utils.Pion.JAUNE;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier de type navigateur
     *
     * @deprecated utiliser {@link #Navigateur(Vector2) à la place
     */
    @Deprecated
    public Navigateur() {
        super();
    }

    /**
     * Créer un aventurier de type pilote
     *
     * @param position la position de départ du navigateur
     */
    public Navigateur(Vector2 position) {
        super(position);
    }

    @Override
    public String getNom() {
        return NOM;
    }

    @Override
    public Utils.Pion getPion() {
        return PION;
    }

    @Override
    public boolean actionSpeciale() {
        Controleur controleur = Controleur.getControleur();

        Aventurier av = controleur.getSelectedAventurier();
        if (av != null) {
            ArrayList<Vector2> pos;
            Grille grille = controleur.getGrille();
            pos = Aventurier.getPosCroix(av);
            Iterator<Vector2> iterator = pos.iterator();
            while (iterator.hasNext()) {
                Vector2 posPossible = iterator.next();
                if (grille.getTuile(posPossible) == null) {
                    iterator.remove();
                }
            }
            Vector2 p1 = controleur.getPosClic(pos, true);
            if (p1 == null) {
                return false;
            } else {
                av.setPosition(p1);
            }

            // premier déplacement fini
            grille = controleur.getGrille();
            pos = Aventurier.getPosCroix(av);
            iterator = pos.iterator();
            while (iterator.hasNext()) {
                Vector2 posPossible = iterator.next();
                if (grille.getTuile(posPossible) == null) {
                    iterator.remove();
                }
            }
            Vector2 p2 = controleur.getPosClic(pos, true);
            if (p2 != null) {
                av.setPosition(p2);
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    @Override
    public boolean isActionSpecialDisponible() {
        return ACTION_SPECIAL_DISPONIBLE;
    }
}
