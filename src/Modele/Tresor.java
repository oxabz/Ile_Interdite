package Modele;

public enum Tresor {
    CRISTAL("La Statue du zéphyr"),
    STATUE("Le Cristal ardent"),
    CALICE("Le calice de l'onde"),
    PIERRE("La pierre sacrée");

    Tresor(String nom) {
        this.nom = nom;
    }

    String nom;
}
