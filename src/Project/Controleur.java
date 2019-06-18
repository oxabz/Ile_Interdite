package Project;

import Project.Modele.*;
import Project.Modele.Aventuriers.*;
import Project.Modele.Cartes.CarteInondation;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteMEau;
import Project.util.*;
import Project.views.Vue;
import Project.views.VueAventurier;
import Project.views.VueGrille;

import javax.naming.InitialContext;
import java.util.*;
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

    private Vue vue;

    private Deque<Message> messages = new ArrayDeque<>();

    private int currentAventurier;


    /*
    SINGLETON THINGY
     */
    private Controleur() {
        grille = FactoryGrille.getGrilleTest();
        aventuriers = new ArrayList<>();

        cartesItem = FactoryDeck.getDeckItems();
        cartesInnondation = FactoryDeck.getDeckInondations();

        initialiserPartie();

        vue = new Vue();
        vue.setObserveur(this);
        vue.initialiserNiveauEau(gameState.getNiveauEau());
        vue.initialiserGrille(grille.getNames(),grille.getInnondee(),grille.getCoulee());
        vue.initialiserVue();
        vue.getGrille().updateGrid(grille.getInnondee(),grille.getCoulee());
    }

    private final static Controleur controleur = new Controleur();

    public static Controleur getControleur() {
        return controleur;
    }

    /*
    METHODS
     */
    //Retourne une position selectioné dans les clickable
    public Vector2 getPosClick(ArrayList<Vector2> clickables) {
        //placeholder

        vue.SetMode(Vue.IhmMode.POSITION);

        System.out.println("Choisissez une case (dans la fenetre):");

        if (!clickables.isEmpty()) {
            vue.getGrille().setClickables(clickables,true);

            Vector2 pos = new Vector2(0, 0);
            boolean done = false;
            while (!done) {
                System.out.print("");
                while (!messages.isEmpty()) {
                    Message m = messages.poll();
                    if (m.type == MessageType.POSITION) {
                        done = true;
                        pos = m.position;
                    }
                }
            }

            vue.getGrille().setClickables(clickables,false);
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
    Utils.Action getSelectedAction(int aventurierIndex) {

        vue.SetMode(Vue.IhmMode.ACTION);

        Utils.Action act = Utils.Action.SE_DEPLACER;
        boolean done = false;
        while (!done) {
            System.out.print("");
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.ACTION) {
                    done = true;
                    act = m.action;
                }
            }
        }
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
    public Aventurier getAventurier(int indexAventurier) {
        vue.SetMode(Vue.IhmMode.AVENTURIER);

        String s = "";
        boolean done = false;
        while (!done) {
            System.out.print("");
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.PARAMETRE) {
                    done = true;
                    s = m.parametre;
                }
            }
        }

        for (Aventurier av
                : aventuriers) {
            if (av.getNom().equals(s)) {
                return av;
            }
        }

        return null;
    }

    //Permet de selectinner une carte
    public Carte getCarteSelectionne(int currentAventurier) {
        vue.SetMode(Vue.IhmMode.MAIN);

        String s = "";
        boolean done = false;
        while (!done) {
            System.out.print("");
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.PARAMETRE) {
                    done = true;
                    s = m.parametre;
                }
            }
        }

        ArrayList<CarteItem> cartes = aventuriers.get(currentAventurier).getCarteItems();
        for (CarteItem c
                : cartes) {
            if (c.getNom().equals(s)) {
                return c;
            }
        }

        return null;

    }

    /*
    GAME LOOP
     */
    //Gere le tour de jeu
    void gameLoop() {
        while (true) {//Berk
            for (int i = 0; i < aventuriers.size(); i++) {
                Aventurier av = aventuriers.get(i);
                currentAventurier = i;

                //Actions Tour
                //Initialisation du tour
                int nbAction = 0;
                boolean finT = false;
                if (av instanceof Pilote) {
                    ((Pilote) av).setDeplacemntSpecial(true);
                }

                //Phase d'action
                System.out.println("Tour de : " + av.getJoueur());
                System.out.println("Position du pion : " + av.getPosition().toString());
                vue.getMain().setAventurier(av);
                while (nbAction < 3 && !finT) {
                    switch (getSelectedAction(i)) {
                        case SE_DEPLACER:
                            if(av.seDeplacer()){
                                nbAction++;
                            }
                            break;
                        case ASSECHER:
                            if (av.assecher()){
                                nbAction++;
                            }
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
                    vue.getGrille().updateGrid(grille.getInnondee(),grille.getCoulee());
                }



                //phase de pioche
                CarteItem cIt1 = (CarteItem) cartesItem.getPioche().poll();
                CarteItem cIt2 = (CarteItem) cartesItem.getPioche().poll();

                if (cIt1 instanceof CarteMEau && cIt2 instanceof CarteMEau) {
                    faireMonteDesEau();
                    gameState.incrementNiveau();
                    cartesItem.addCarteDefausseDebut(cIt1);
                    cartesItem.addCarteDefausseDebut(cIt2);
                } else if (cIt1 instanceof CarteMEau || cIt2 instanceof CarteMEau) {
                    faireMonteDesEau();
                    if (cIt1 instanceof CarteMEau) {
                        av.addCarteItem(cIt2);
                        cartesItem.addCarteDefausseDebut(cIt1);
                    } else {
                        av.addCarteItem(cIt1);
                        cartesItem.addCarteDefausseDebut(cIt2);
                    }
                    vue.getNiveauEau().setNiveau(gameState.getNiveauEau());
                } else {
                    av.addCarteItem(cIt1);
                    av.addCarteItem(cIt2);
                }

                //phase d'innondation
                faireInnondation();

                vue.getGrille().updateGrid(grille.getInnondee(),grille.getCoulee());
            }
        }

    }

    private void faireMonteDesEau() {
        cartesInnondation.melangerCartesDefausse();
        LinkedList<Carte> defausse = cartesInnondation.getDefausse();
        cartesInnondation.addCartesPiocheDebut(defausse);
        defausse.clear();
        faireInnondation();
        gameState.incrementNiveau();
    }

    private void faireInnondation() {
        int nbCarteInnondation = gameState.getNbDeCarte();
        LinkedList<Carte> cartesInnondationPioche = cartesInnondation.getPioche();

        for (int j = 0; j < nbCarteInnondation; j++) {
            CarteInondation cIn = (CarteInondation) cartesInnondationPioche.poll();
            if ( cIn.getTuile().isInnondee()) {
                grille.removeTuile(cIn.getTuile());
            } else {
                cIn.getTuile().setInnondee(true);
            }
            cartesInnondation.addCarteDefausseDebut(cIn);
        }
    }

    //Permet de demarer une partie
    public void initialiserPartie() {

        //Ajout des joueurs

        ArrayList<Aventurier> dispoAventuriers = FactoryAventurier.getAventuriers(grille);

        Scanner s = new Scanner(System.in);

        int nbJ;
        do{
            System.out.print("nb de joueur :");
            nbJ = s.nextInt();
            s.nextLine();
        } while (!(nbJ >= 2 && nbJ <= 4));

        for (int i = 0; i < nbJ; i++) {
            //Initialiser aventurier
            System.out.print("nom joueur " + (i + 1) + " : ");
            String nomJ = s.nextLine();
            int r = ThreadLocalRandom.current().nextInt(dispoAventuriers.size());
            Aventurier av = dispoAventuriers.get(r);
            aventuriers.add(av);
            dispoAventuriers.remove(r);
            av.setJoueur(nomJ);
            System.out.println(av.getJoueur() + " sera " + av.getNom());



        }

        //Initialisation du gamestate

        int lvl;
        do {
            System.out.println("Choisissez un niveau de jeu");
            lvl = s.nextInt();
        }while (!(lvl>=1 && lvl<=4));
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
