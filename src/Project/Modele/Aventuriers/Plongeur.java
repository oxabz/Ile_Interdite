package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Grille;
import Project.util.Utils;
import Project.util.Vector2;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Plongeur extends Aventurier {

    /* ATTRIBUTS */
    private static final String NOM = "Plongeur";
    private static final Utils.Pion PION = Utils.Pion.NOIR;

    /* CONSTRUCTEURS */
    
    /**
     * Créer un aventurier de type plongeur
     *
     * @deprecated utiliser {@link #Plongeur(Vector2) à la place
     */
    @Deprecated
    public Plongeur() {
        super();
    }

    /**
     * Créer un aventurier de type plongeur
     *
     * @param position la position de départ du plongeur
     */
    public Plongeur(Vector2 position) {
        super(position);
    }

    /*@Override
    /**
     * Permet de déplacer le plongeur
     * @param annulable true si on peut annuler l'action
     * @return true quand le déplacement a été effectuée
     *//*
    public boolean seDeplacer(boolean annulable) {

        // Déclaration
        Controleur c = Controleur.getControleur();
        ArrayList<Vector2> pos;
        Grille grille = c.getGrille();
        Vector2 oldPosition = position;

        boolean fini;
        do {
            pos = getPosDeplacement();
            pos.add(position);
            Iterator<Vector2> iterator = pos.iterator();
            // On fait la liste des déplacements possibles
            while (iterator.hasNext()) {
                Vector2 p = iterator.next();
                // On limite les déplacements (OOB)
                if (p.x < 0 || p.x > 5 || p.y < 0 || p.y > 5) {
                    iterator.remove();
                }
            }
            Vector2 positionSelected = c.getPosClic(pos, annulable);
            if(positionSelected != null) {
                fini = (positionSelected.x == position.x && positionSelected.y == position.y) && grille.getTuile(position) != null;
                position = positionSelected;
            } else {
                fini = true;
            }
        } while ((grille.getTuile(position) == null || grille.getTuile(position).isInnondee()) && !fini);
        return !(position.x == oldPosition.x && position.y == oldPosition.y);
    }*/

    @Override
    public ArrayList<Vector2> getPosDeplacement() {
        HashSet<Vector2> tuiles = new HashSet<>();
        ArrayList<Vector2> tuilesVisite = new ArrayList<>();
        getTuileAccessible(position,tuiles,tuilesVisite);
        return new ArrayList<>(tuiles);
    }

    private void getTuileAccessible(Vector2 position, HashSet<Vector2> tuiles, ArrayList<Vector2> tuilesVisite){
        Controleur c = Controleur.getControleur();
        Grille g = c.getGrille();
        for (Vector2 p :
                getPosCroix(position)) {
            if(p.within(Vector2.ZERO,new Vector2(g.getSizeX()-1,g.getSizeY()-1))&&!tuilesVisite.contains(p)){
                if(g.getTuile(p) == null){
                    tuilesVisite.add(p);
                    getTuileAccessible(p,tuiles,tuilesVisite);
                    tuilesVisite.remove(p);
                }else if(g.getTuile(p).isInnondee()){
                    tuiles.add(p);
                    tuilesVisite.add(p);
                    getTuileAccessible(p,tuiles,tuilesVisite);
                    tuilesVisite.remove(p);
                }else {
                    tuiles.add(p);
                }
            }
        }
    }

    @Override
    public String getNom() {
        return NOM;
    }

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
