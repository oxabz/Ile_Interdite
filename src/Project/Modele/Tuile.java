package Project.Modele;

import Project.Modele.Tuiles.TuileTresor;

public class Tuile {

    /**
     * ATTRIBUTES
     */

    private String nom;
    private Boolean innondee;

    /**
     * CONSTRUCTOR
     * @param nom le nom de la tuile
     */
    public Tuile(String nom) {
        this.setInnondee(false);
        this.setNom(nom);
    }

    /**
     * METHODS
     *
     * @return true si la tuile est une tuile trésor
     */
    public boolean isTuileTresor() {
        return this instanceof TuileTresor;
    }

    /**
     * GETTERS/SETTERS
     */
    private void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Boolean isInnondee() {
        return innondee;
    }

    public void setInnondee(Boolean innondee) {
        this.innondee = innondee;
    }
}
