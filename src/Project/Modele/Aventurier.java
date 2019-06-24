package Project.Modele;

import Project.Controleur;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteBonus;
import Project.Modele.Cartes.CartesItem.CarteTresor;
import Project.Modele.Tuiles.TuileTresor;
import Project.util.IdentifiedElement;
import Project.util.Sound;
import Project.util.Utils;
import Project.util.Vector2;
import Project.util.Utils.Tresor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier extends IdentifiedElement {


    /* CONSTANT */
    protected static final boolean ACTION_SPECIAL_DISPONIBLE = false;

    /* ATTRIBUTS */
    protected Vector2 position;
    private ArrayList<CarteItem> carteItems;
    private String joueur;
    private int nbAction;

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
    public void initialiserTour() {
        nbAction = 3;
    }

    /**
     * Déplace le joueur
     * @param annulable true si l'action est annulable
     * @return true si l'action a été effectuée
     */
    public boolean seDeplacer(boolean annulable) {

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
        Vector2 p = c.getPosClic(pos, annulable);
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
        return getPosCroix(this);
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

        Vector2 aAssecher = c.getPosClic(pos, true);
        if (aAssecher != null) {
            Sound.jouer(Utils.Son.getCHEMIN_SABLE());
            g.getTuile(aAssecher).setInnondee(false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permet de donner une carte d'un joueur à l'autre
     *
     * @return true si l'action a réussi
     */
    public boolean donnerCarte() {
        Controleur c = Controleur.getControleur();
        Aventurier av;
        do {
            av = c.getSelectedAventurier();
        } while (av != null && !av.getPosition().equals(this.getPosition()));
        if (av != null) {
            Carte carte;
            do {
                carte = c.getCarteSelectionne();
            } while (carte != null && !(carte instanceof CarteTresor));
            if (carte != null) {
                this.removeCarteItem((CarteItem) carte);
                av.getCarteItems().add((CarteItem) carte); // le déclancement de la mettre à la défose est à 6 auto ?
                if (carte != null && this.getCarteItems().contains((CarteItem) carte) == false && av.getCarteItems().contains((CarteItem) carte) == true) {
                    return true;
                } else {
                    return false;
                }
            }

        }
        return false;
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
    public boolean actionSpeciale() {
        return false;
    }

    /*
    GETTER SETTER
     */
    public boolean utiliserCarte() {
        Controleur c = Controleur.getControleur();

        if (this.getCarteItems().size() > 0) {

            // On récupère la carte séléctionné par le joueur dans la main du joueur.
            Carte carte = c.getCarteSelectionne();

            if (carte != null && carte instanceof CarteBonus) {

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

    public static ArrayList<Vector2> getPosCroix(Aventurier av) {
        return getPosCroix(av.getPosition());
    }
    public static ArrayList<Vector2> getPosCroix(Vector2 center) {
        ArrayList<Vector2> pos = new ArrayList<>();
        pos.add(center.add(-1, 0));
        pos.add(center.add(1, 0));
        pos.add(center.add(0, -1));
        pos.add(center.add(0, 1));
        return pos;
    }

    public void defausserCarteItem(CarteItem carteItem) {
        carteItems.remove(carteItem);
        Controleur controleur = Controleur.getControleur();
        controleur.getCartesItem().getDefausse().add(carteItem);
    }

    public void utiliserAction() {
        nbAction--;
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
            this.defausserCarteItem((CarteItem) controleur.getCarteSelectionne(this));
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

    public int getNbAction() {
        return nbAction;
    }

    public boolean isActionSpecialDisponible() {
        return ACTION_SPECIAL_DISPONIBLE;
    }
}
