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
    public Carte(String nom) {
        this.nom = nom;
    }

    public Carte(String nom, String image) {
        this.nom = nom;
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

    public void setImage(String image) {
        this.image = image;
    }

}
