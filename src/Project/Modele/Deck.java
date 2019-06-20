package Project.Modele;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

    /* ATTRIBUTS */
    private final LinkedList<Carte> pioche;
    private final LinkedList<Carte> defausse;

    /* CONSTRUCTEUR */
    public Deck() {
        this.pioche = new LinkedList<>();
        this.defausse = new LinkedList<>();
    }

    /* MÉTHODES */
    /**
     * *
     * @param carte la carte à rajouter au DÉBUT De la PIOCHE
     */
    public void addCartePiocheDebut(Carte carte) {
        this.pioche.addFirst(carte);
    }

    /**
     *
     * @param cartes la LinkedList des cartes à rajouter au DÉBUT de la PIOCHE
     */
    public void addCartesPiocheDebut(LinkedList<Carte> cartes) {
        for (int i = cartes.size() - 1; i >= 0; i--) {
            pioche.addFirst(cartes.get(i));
        }
    }

    /**
     *
     * @param carte la carte à rajouter à la FIN De la PIOCHE
     */
    public void addCartePiocheFin(Carte carte) {
        this.pioche.addLast(carte);
    }

    /**
     *
     * @param carte la carte à rajouter au DÉBUT de la DÉFAUSSE
     */
    public void addCarteDefausseDebut(Carte carte) {
        this.defausse.addFirst(carte);
    }

    /**
     *
     * @param carte la carte à rajouter à la FIN de la DÉFAUSSE
     */
    public void addCarteDefausseFin(Carte carte) {
        this.defausse.addLast(carte);
    }

    /**
     * shuffle la DÉFAUSSE
     */
    public void melangerCartesDefausse() {
        Collections.shuffle(this.defausse);
    }

    /**
     * shuffe la PIOCHE
     */
    public void melangerCartesPioche() {
        Collections.shuffle(this.pioche);
    }

    /**
     *
     * @param positionCarte la position dans la PIOCHE de la carte à retirer
     */
    public void retirerCartePioche(int positionCarte) {
        pioche.remove(positionCarte);
    }

    /**
     *
     * @param positionCarte la postion dans la DÉFAUSSE de la carte à retirer
     */
    public void retirerCarteDefausse(int positionCarte) {
        defausse.remove(positionCarte);
    }

    /**
     *
     * @return La carte a piocher Permet de piocher ainsi que de mélanger la
     * défausse et de remplacer la pioche par la défausse si elle est vide
     */
    public Carte piocher() {
        // S'il n'y a plus de cartes dans la pioche
        if (this.getPioche().isEmpty()) {
            this.melangerCartesDefausse();
            // On récupère la défausse
            for (Carte carte : this.getDefausse()) {
                // On place la défausse à la place de la pioche
                addCartePiocheDebut(carte);
            }
            // On vide la défausse pour ne pas avoir de doublon
            this.defausse.clear();
        }
        // Tire la première carte de la pioche
        return this.getPioche().poll();
    }

    /**
     * GETTERS
     */
    public LinkedList<Carte> getPioche() {
        return pioche;
    }

    public LinkedList<Carte> getDefausse() {
        return defausse;
    }
}
