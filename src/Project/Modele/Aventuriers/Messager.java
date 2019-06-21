package Project.Modele.Aventuriers;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.Modele.Carte;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteTresor;
import Project.util.Utils;
import Project.util.Vector2;

public class Messager extends Aventurier {

    /* ATTRIBUTS */
    private static final String NOM = "Messager";
    private static final Utils.Pion PION = Utils.Pion.GRIS;

    /* CONSTRUCTEURS */
    /**
     * Créer un aventurier de type message
     *
     * @deprecated utiliser {@link #Messager(Vector2)} à la place
     */
    @Deprecated
    public Messager() {
        super();
    }

    /**
     * Créer un aventurier de type messager
     *
     * @param position la position de départ du messager
     */
    public Messager(Vector2 position) {
        super(position);
    }

    @Override
    public boolean  donnerCarte() {
        Controleur c = Controleur.getControleur();
        Aventurier av;
        av = c.getSelectedAventurier();
        if(av!=null){
            Carte carte;
            do {
                carte = c.getCarteSelectionne();
                System.out.println("test");
            }while (carte != null&&!(carte instanceof CarteTresor));
            if(carte!=null){
                this.removeCarteItem( (CarteItem) carte);
                av.getCarteItems().add((CarteItem) carte) ; // le déclancement de la mettre à la défose est à 6 auto ?
                if (carte != null && this.getCarteItems().contains((CarteItem) carte) == false && av.getCarteItems().contains((CarteItem)carte) == true) {
                    return true ;
                }
                else {
                    return false;
                }
            }

        }
        return false;
    }

    @Override
    public String getNom() {
        return NOM;
    }

    @Override
    public Utils.Pion getPion() {
        return PION;
    }
}
