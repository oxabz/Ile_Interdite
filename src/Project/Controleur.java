package Project;

import Project.Modele.*;
import Project.Modele.Aventuriers.*;
import Project.Modele.Cartes.CarteItem;
import Project.util.*;
import Project.views.VueAventurier;
import Project.views.VueGrille;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Controleur implements Observeur {
    /*
    ATTRIBUTES
     */

    private Grille grille;
    private Deck cartesItem;
    private Deck cartesInnondation;
    private ArrayList<Aventurier> aventuriers;
    private GameState gameState;

    private VueGrille vueGrille;
    private ArrayList<VueAventurier> vuesAventurier;

    private Deque<Message> messages = new ArrayDeque<>();

    private int currentAventurier;


    /*
    SINGLETON THINGY
     */

    private Controleur() {
        grille = new Grille();
        aventuriers  = new ArrayList<>();
        boolean[][] testVal = {
                {false,false,true,true,false,false},
                {false,true,true,true,true,false},
                {true,true,true,true,true,true},
                {true,true,true,true,true,true},
                {false,true,true,true,true,false},
                {false,false,true,true,false,false}
        };
        vueGrille=new VueGrille(grille.getSizeX(),grille.getSizeY(),testVal);
        vueGrille.setObserveur(this);

        vuesAventurier = new ArrayList<>();
    }

    private final static Controleur controleur = new Controleur();

    public static Controleur getControleur() {
        return controleur;
    }

    /*
    METHODS
     */


    //Retourne une position selectioné dans les clickable
    public Vector2 getPosClick(ArrayList<Vector2> clickables){
        //placeholder

        System.out.println("Choisissez une case (dans la fenetre):");

        if (clickables.size()!=0){
            vueGrille.allumerTuiles(clickables);

            Vector2 pos = new Vector2(0,0);
            boolean done = false;
            while (!done){
                System.out.print("");
                while (!messages.isEmpty()){
                    Message m = messages.poll();
                    if(m.type == MessageType.POSITION){
                        done = true;
                        pos =  m.position;
                    }
                }
            }

            vueGrille.etteindreTuiles();
            return pos;
        }
        return null;


        //Console based I/O deprecated
        /*

        Scanner scanner = new Scanner(System.in);

        for (Vector2 c :
                clickables) {
            System.out.print('{'+Integer.toString(c.x)+','+Integer.toString(c.y)+'}');
        }

        System.out.print('\n');


        while (clickables.size() != 0) {
            int x, y;
            System.out.print("x : ");
            x = scanner.nextInt();
            System.out.print("y : ");
            y = scanner.nextInt();

            for (Vector2 c : clickables) {
                if (c.x == x && c.y == y) {
                    return new Vector2(x, y);
                }
            }
        }


         */
        //return null;
    }


    //Retourne une action selectioné par l'aventurier d'index aventurierIndex
    Utils.Action getSelectedAction(int aventurierIndex){

        vuesAventurier.get(aventurierIndex).setMode(1,"");

        Utils.Action act = Utils.Action.SE_DEPLACER;
        boolean done = false;
        while (!done){
            System.out.print("");
            while (!messages.isEmpty()){
                Message m = messages.poll();
                if(m.type == MessageType.ACTION){
                    done = true;
                    act =  m.action;
                }
            }
        }

        vuesAventurier.get(aventurierIndex).setMode(0,"");

        return act;

        //Console based I/O deprecated
        /*
        Scanner s  = new Scanner(System.in);
        System.out.print("choisissez  une action a faire ((d)se deplacer,(a)assecher, (e)donner carte, (t)prendre tresor, (f)fin de tour, (c)utiliser carte"+(currentAventurier instanceof Navigateur ? "(s)action speciale" : "" ));
        switch (s.nextLine()){
            default:
            case "d":
                return Utils.Action.SE_DEPLACER;
            case "a":
                return Utils.Action.ASSECHER;
            case "e":
                return Utils.Action.DON_CARTE;
            case "t":
                return Utils.Action.PRENDRE_TRESOR;
            case "f":
                return Utils.Action.FIN_TOUR;
            case "c":
                return Utils.Action.UTILISER_CARTE;
            case "s":
                return Utils.Action.ACTION_SPECIALE;
        }*/
    }

    //Permet de selectionner un aventurier un aventurier
    public Aventurier getAventurier(int indexAventurier){
        vuesAventurier.get(indexAventurier).setMode(2,"veuillez entrer un nom d'aventurier : ");

        String s = "";
        boolean done = false;
        while (!done){
            System.out.print("");
            while (!messages.isEmpty()){
                Message m = messages.poll();
                if(m.type == MessageType.PARAMETRE){
                    done = true;
                    s = m.parametre;
                }
            }
        }

        vuesAventurier.get(indexAventurier).setMode(0,"");
        for (Aventurier av :
                aventuriers) {
            if (av.getNom() == s) {
                return av;
            }
        }


        return null;
    }

    //Permet de selectinner une carte
    public Carte getCarteSelectionne(int currentAventurier) {

        vuesAventurier.get(currentAventurier).setMode(2,"veuillez entrer le nom d'une carte : ");

        String s = "";
        boolean done = false;
        while (!done){
            System.out.print("");
            while (!messages.isEmpty()){
                Message m = messages.poll();
                if(m.type == MessageType.PARAMETRE){
                    done = true;
                    s = m.parametre;
                }
            }
        }


        ArrayList<CarteItem> cartes = aventuriers.get(currentAventurier).getCarteItems();
        vuesAventurier.get(currentAventurier).setMode(0,"");
        for (CarteItem c :
                cartes) {
            if (c.getNom() == s) {
                return c;
            }
        }

        return null;

         
    }

    /*
    GAME LOOP
     */

    //Gere le tour de jeu
    void gameLoop(){
        while(true){//Berk
            for (int i = 0;i<aventuriers.size();i++) {
                Aventurier av = aventuriers.get(i);
                currentAventurier = i;

                //Actions Tour

                //Initialisation du tour
                int nbAction = 0;
                boolean finT = false;
                if(av instanceof Pilot){
                    ((Pilot)av).setDeplacemntSpecial(true);
                }

                //Phase d'action
                System.out.println("Tour de : " + av.getJoueur());
                while(nbAction<3&&!finT){
                    switch (getSelectedAction(i)){
                        case SE_DEPLACER:
                            nbAction++;
                            av.seDeplacer();
                            break;
                        case ASSECHER:
                            nbAction++;
                            av.assecher();
                            break;
                        case DON_CARTE:
                            nbAction++;
                            av.donnerCarte();
                            break;
                        case FIN_TOUR:
                            finT = true;
                            break;
                        case PRENDRE_TRESOR:
                            nbAction++;
                            break;
                        case UTILISER_CARTE:
                            break;
                        case ACTION_SPECIALE:
                            nbAction++;
                            break;
                    }
                }

                //phase de pioche

                //phase d'innondation
            }            
        }


    }

    //Permet de demarer une partie
    void initialiserPartie(){

        //Ajout des joueurs
        ArrayList<Aventurier> dispoAventuriers = new ArrayList<>();
        dispoAventuriers.add(new Explorateur());
        dispoAventuriers.add(new Ingenieur());
        dispoAventuriers.add(new Messager());
        dispoAventuriers.add(new Navigateur());
        dispoAventuriers.add(new Pilot());
        dispoAventuriers.add(new Plongeur());

        Scanner s = new Scanner(System.in);

        System.out.print("nb de joueur :");
        int nbJ = s.nextInt();
        s.nextLine();

        for (int i = 0; i < nbJ; i++) {
            //Initialiser aventurier
            System.out.print("nom joueur "+i+" : ");
            String nomJ = s.nextLine();
            int r = ThreadLocalRandom.current().nextInt(dispoAventuriers.size()-1);
            Aventurier av = dispoAventuriers.get(r);
            aventuriers.add(av);
            dispoAventuriers.remove(r);
            av.setJoueur(nomJ);
            System.out.println(av.getJoueur()+" sera "+av.getNom());

            //initialisation des interface
            VueAventurier vueAventurier =new VueAventurier(nomJ,av.getNom());
            vueAventurier.setObserveur(this);
            vuesAventurier.add(vueAventurier);

        }

        //Initialisation de la grille
        grille = new Grille();

        //Initialiser decks
        cartesItem = new Deck();
        cartesInnondation = new Deck();

        //Initialisation du gamestate
        System.out.println("Choisissez un niveau de jeu");
        int lvl = s.nextInt();
        gameState = new GameState(lvl);

    }

    @Override
    public void recevoirMessage(Message m) {
        messages.add(m);
    }
    

    /*
    GETTER SETTER
     */

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public Deck getCartesItem() {
        return cartesItem;
    }

    public void setCartesItem(Deck cartesItem) {
        this.cartesItem = cartesItem;
    }

    public Deck getCartesInnondation() {
        return cartesInnondation;
    }

    public void setCartesInnondation(Deck cartesInnondation) {
        this.cartesInnondation = cartesInnondation;
    }

    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public void setAventuriers(ArrayList<Aventurier> aventuriers) {
        this.aventuriers = aventuriers;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getCurrentAventurier() {
        return currentAventurier;
    }


}
