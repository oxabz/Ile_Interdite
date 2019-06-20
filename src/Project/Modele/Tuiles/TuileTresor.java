package Project.Modele.Tuiles;

import Project.Modele.Tuile;
import Project.util.Utils.Tresor;

public class TuileTresor extends Tuile {

    /* ATTRIBUTS */
    private Tresor tresor;

    /* CONSTRUCTEUR */
    /**
     *
     * @param nom le nom de la tuile
     * @param tresor le trésor lié à la tuile
     */
    public TuileTresor(String nom, Tresor tresor) {
        super(nom);
        setTresor(tresor);
    }

    /* GETTERS & SETTERS */
    public Tresor getTresor() {
        return tresor;
    }

    private void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
}
