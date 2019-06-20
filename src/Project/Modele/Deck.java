package Project.Modele;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {

    /**
     * ATTRIBUTES
     */
    private LinkedList<Carte> pioche;
    private LinkedList<Carte> defausse;

    /**
     * CONSTRUCTOR
     */
    public Deck() {
        this.pioche = new LinkedList<>();
        this.defausse = new LinkedList<>();
    }

    /**
     * METHODS
     */
    public void addCartePiocheDebut(Carte carte) {
        this.pioche.addFirst(carte);
    }

    public void addCartesPiocheDebut(LinkedList<Carte> cartes) {
        for (int i = cartes.size() - 1; i >= 0; i--) {
            pioche.addFirst(cartes.get(i));
        }
    }

    public void addCartePiocheFin(Carte carte) {
        this.pioche.addLast(carte);
    }

    public void addCarteDefausseDebut(Carte carte) {
        this.defausse.addFirst(carte);
    }

    public void addCarteDefausseFin(Carte carte) {
        this.defausse.addLast(carte);
    }

    public void melangerCartesDefausse() {
        Collections.shuffle(this.defausse);
    }

    public void melangerCartesPioche() {
        Collections.shuffle(this.pioche);
    }
    
    public void retirerCartePioche(int positionCarte) {
        pioche.remove(positionCarte);
    }
    
    public void retirerCarteDefausse(int positionCarte) {
        defausse.remove(positionCarte);
    }

    /**
     * Permet de piocher ainsi que de mélanger la défausse et de remplacer la pioche par la défausse si elle est vide
     * @return La carte a piocher
     */
    public Carte piocher() {
        // S'il n'y a plus de cartes dans la pioche
        if(this.getPioche().size() == 0) {
            this.melangerCartesDefausse();
            // On récupère la défausse
            for(Carte carte : this.getDefausse()) {

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
