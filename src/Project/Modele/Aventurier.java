package Project.Modele;

import Project.Controleur;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteBonus;
import Project.Modele.Cartes.CartesItem.CarteTresor;
import Project.Modele.Tuiles.TuileTresor;
import Project.util.Utils;
import Project.util.Vector2;
import Project.util.Utils.Tresor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier {

    /* ATTRIBUTS */
    protected Vector2 position;
    private ArrayList<CarteItem> carteItems;
    private String joueur;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier en position 0,0 et initialise sa main
     *
     * @deprecated utiliser {@link #Aventurier(Vector2)} à la place
     */
    @Deprecated
    public Aventurier() {
        position = new Vector2(0, 0);
        carteItems = new ArrayList<>();
    }

    /**
     * Créer un aventurier à la position donnée
     *
     * @param position la position de départ de l'aventurier
     */
    public Aventurier(Vector2 position) {
        this.position = position;
        carteItems = new ArrayList<>();
    }

    /* MÉTHODES */
    /**
     * Déplace le joueur
     *
     * @return true si l'action a été effectuée
     */
    public boolean seDeplacer() {

        //Declaration
        Controleur c = Controleur.getControleur();
        ArrayList<Vector2> pos;
        Grille grille = c.getGrille();
        pos = getPosDeplacement();
        Iterator<Vector2> iterator = pos.iterator();
        while (iterator.hasNext()) {
            Vector2 p = iterator.next();
            if (grille.getTuile(p) == null) {
                iterator.remove();
            }
        }
        Vector2 p = c.getPosClic(pos);
        if (p != null) {
            position = p;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return Les position de déplacement de l'aventurier de base
     */
    public ArrayList<Vector2> getPosDeplacement() {
        ArrayList<Vector2> pos = new ArrayList<>();
        pos.add(position.add(-1, 0));
        pos.add(position.add(1, 0));
        pos.add(position.add(0, -1));
        pos.add(position.add(0, 1));
        return pos;
    }

    /**
     * permet d'assécher une case
     *
     * @return true si l'action a été effectuée
     */
    public boolean assecher() {
        Controleur c = Controleur.getControleur();
        Grille g = c.getGrille();
        ArrayList<Vector2> pos = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            if (g.getTuile(position.add(i, 0)) != null && g.getTuile(position.add(i, 0)).isInnondee()) {
                pos.add(position.add(i, 0));
            }
            if (g.getTuile(position.add(0, i)) != null && g.getTuile(position.add(0, i)).isInnondee()) {
                pos.add(position.add(0, i));
            }
        }
        Vector2 aAssecher = c.getPosClic(pos);
        if (aAssecher != null) {

            g.getTuile(aAssecher).setInnondee(false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * permet de rien faire
     *
     * @hidden
     * @since never
     * @deprecated fuyez
     */
    @Deprecated
    public void donnerCarte() {
        System.out.println("Choisissez un aventurier");
        Controleur c = Controleur.getControleur();
        Aventurier av = c.getSelectedAventurier(c.getCurrentAventurier());
        Carte carte = c.getCarteSelectionne();
    }

    /**
     * Permet de récupérer un tresor
     *
     * @return true si le joueur a pu prendre le trésor
     *
     */
    public boolean prendreTresor() {
        Controleur c = Controleur.getControleur();

        // Tuile actuelle sur laquelle le joueur est positionné
        Tuile tuile = c.getGrille().getTuile(getPosition());
        // Si le joueur est sur un tuile et qu'il s'agit d'une tuile Tresor
        if (tuile != null && tuile instanceof TuileTresor) {
            // On récupère le type de tresor de la tuile représentant un trésor
            Tresor tresor = ((TuileTresor) tuile).getTresor();

            // Si le tresor actuel n'a jamais été récupéré
            if (c.getGameState().getTresors().get(tresor) != true) {
                // Permet de faire le compte des cartes du tresor actuel possédé
                int nbCarteTresor = 0;

                // Pour chaque carte que le joueur possède
                for (CarteItem carte : this.carteItems) {
                    // Si la carte est une carte trésor et quelle correspond au trésor actuel
                    if (carte instanceof CarteTresor && ((CarteTresor) carte).getTresor().equals(tresor)) {
                        nbCarteTresor++;
                    }
                }

                if (nbCarteTresor >= 4) {
                    // On récupère le trésor
                    c.getGameState().recupererTresor(tresor);

                    // Collection permettant de récupérer les cartes à retirer
                    ArrayList<CarteItem> cartes_a_retirer = new ArrayList<>();

                    // Pour chaque carte que le joueur possède
                    for (CarteItem carte : this.carteItems) {
                        // Si la carte est une carte trésor et quelle correspond au trésor actuel
                        if (carte instanceof CarteTresor && ((CarteTresor) carte).getTresor().equals(tresor)) {
                            // On ajoute la carte aux cartes à retirer de la main du joueur
                            cartes_a_retirer.add(carte);

                            // On l'ajoute dans la défausse
                            c.getCartesItem().addCarteDefausseDebut(carte);
                        }
                    }

                    // On retire toutes les cartes à supprimer
                    this.getCarteItems().removeAll(cartes_a_retirer);

                    // On update la main du joueur dans la vue
                    c.updateMain();
                }
                // On a réussi à prendre le trésor donc on a fait une action
                return true;
            }
        }
        return false;
    }

    /**
     * Permet d'utiliser une carte bonus possédée par le joueur
     *
     * @return true si la carte a pu être utilisée
    */
    public boolean actionSpeciale () {
        return true ;
    }

    /*
    GETTER SETTER
     */
    public boolean utiliserCarte() {
        Controleur c = Controleur.getControleur();

        if (this.getCarteItems().size() > 0) {

            // On récupère la carte séléctionné par le joueur dans la main du joueur.
            Carte carte = c.getCarteSelectionne();

            if (carte instanceof CarteBonus) {

                // On retire la carte de la main du joueur
                this.getCarteItems().remove(carte);

                // On fait l'action spéciale de la carte
                ((CarteBonus) carte).actionCarte();

                c.updateMain();

                return true;
            }
        }

        return false;
    }

    /* GETTERS & SETTERS */
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public ArrayList<CarteItem> getCarteItems() {
        return carteItems;
    }

    public void addCarteItem(CarteItem carte) {
        Controleur controleur = Controleur.getControleur();

        carteItems.add(carte);
        if (carteItems.size() > 5) {
            this.carteItems.remove(controleur.getCarteSelectionne(this));
        }
    }

    public void removeCarteItem(CarteItem carteItem) {
        carteItems.remove(carteItem);
    }

    public void setCarteItems(ArrayList<CarteItem> carteItems) {
        this.carteItems = carteItems;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public abstract String getNom();

    public abstract Utils.Pion getPion();
}
