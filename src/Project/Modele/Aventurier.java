package Project.Modele;

import Project.Controleur;
import Project.Modele.Cartes.CarteItem;
import Project.util.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier extends ObjetIdentifie {
    /*
    ATTRIBUTES
     */

    protected Vector2 position;
    private ArrayList<CarteItem> carteItems;
    private String joueur;

    /*
    CONSTRUCTOR
     */

    public Aventurier() {
        position = new Vector2(0,0);
        carteItems = new ArrayList<>();
    }

    public Aventurier(Vector2 position) {
        this.position = position;
        carteItems = new ArrayList<>();
    }

    /*
    METHODS
     */

    public void seDeplacer(){

        //Declaration
        Controleur c = Controleur.getControleur();
        ArrayList<Vector2> pos;
        Grille grille = c.getGrille();

        pos = getPosDeplacement();

        Iterator<Vector2> iterator = pos.iterator();
        while(iterator.hasNext()){
            Vector2 p = iterator.next();
            if (grille.getTuile(p)==null){
                iterator.remove();
            }
        }
        Vector2 p = c.getPosClick(pos);
        if (p!=null){
            position = p;
        }
    }

    //Retourne les position de deplacemnt du joueur
    protected ArrayList<Vector2> getPosDeplacement(){
        ArrayList<Vector2> pos = new ArrayList<>();
        pos.add( position.add(-1,0));
        pos.add( position.add(1,0));
        pos.add( position.add(0,-1));
        pos.add( position.add(0,1));
        return pos;
    }

    public void assecher(){
        Controleur c = Controleur.getControleur();
        Grille g = c.getGrille();
        ArrayList<Vector2> pos = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            if (g.getTuile(position.add(i,0))!=null&&g.getTuile(position.add(i,0)).isInnondee()){//non fini
                pos.add(position.add(i,0));
            }
            if (g.getTuile(position.add(0,i))!=null&&g.getTuile(position.add(0,i)).isInnondee()){//non fini
                pos.add(position.add(0,i));
            }
        }
        Vector2 aAssecher = c.getPosClick(pos);
        if(aAssecher != null){

            g.getTuile(aAssecher).setInnondee(false);
        }
    }

    public void donnerCarte(){
        System.out.println("Choisissezun aventurier");
        Controleur c = Controleur.getControleur();

        Aventurier av = c.getAventurier(c.getCurrentAventurier());

        Carte carte = c.getCarteSelectionne(c.getCurrentAventurier());

    }

    /*
    GETTER SETTER
     */

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public ArrayList<CarteItem> getCarteItems() {
        return carteItems;
    }

    public void addCarteItem(CarteItem carte){
        Controleur controleur = Controleur.getControleur();

        carteItems.add(carte);
        if(carteItems.size()>5){
            this.carteItems.remove(controleur.getCarteSelectionne(controleur.getCurrentAventurier()));
        }
    }

    public void removeCarteItem(CarteItem carteItem){
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
}
