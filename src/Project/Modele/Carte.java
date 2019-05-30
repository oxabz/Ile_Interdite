package Project.Modele;

public abstract class Carte {
    /*
    ATTRIBUTES
     */

    private String nom;
    private String image;

    /*
    CONSTRUCTOR
     */

    public Carte(String image) {
        this.image = image;
    }

    /*
    METHODS
     */

    /*
    GETTER SETTER
     */

    public String getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
