package Project;

import Project.Modele.*;
import Project.Modele.Aventuriers.Navigateur;
import Project.Modele.Cartes.CarteInondation;
import Project.Modele.Cartes.CarteItem;
import Project.Modele.Cartes.CartesItem.CarteHelicoptere;
import Project.Modele.Cartes.CartesItem.CarteMEau;
import Project.Modele.Tuiles.Heliport;
import Project.Modele.Tuiles.TuileTresor;
import Project.util.*;
import Project.util.Utils.Tresor;
import Project.views.Vue;
import Project.views.VueFormulaire;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Controleur implements Observeur {

    /* ATTRIBUTS */
    private Grille grille;
    private Deck cartesItem;
    private Deck cartesInondation;
    private ArrayList<Aventurier> aventuriers;
    private GameState gameState;
    private int currentAventurier;
    private final Vue vue;
    private final VueFormulaire vueFormulaire;
    private final Deque<Message> messages = new ArrayDeque<>();

    /*
    SINGLETON THINGY (CONSTRUCTEUR)
     */
    private Controleur() {
        grille = FactoryGrille.getGrilleTest();
        aventuriers = new ArrayList<>();

        cartesItem = FactoryDeck.getDeckItems();
        cartesInondation = FactoryDeck.getDeckInondations();

        vueFormulaire = new VueFormulaire();
        vueFormulaire.setObserveur(this);

        this.initialiserPartie();

        vue = new Vue();
        vue.setObserveur(this);
        vue.initialiserNiveauEau(gameState.getNiveauEau());
        vue.initialiserGrille(grille.getNames(), grille.getInnondee(), grille.getCoulee());
        vue.initialiserJoueurs(aventuriers);
        vue.initialiserVue();
        Sound.jouerMusique(Project.util.Utils.Son.getCheminSon() + "musique/musique_menu.wav");
        vue.initialiserAdaptativeSize();
        vue.getGrille().updateGrid(grille.getInnondee(), grille.getCoulee());
        vue.getGrille().updatePion(getPosPion());
        vue.updateJoueurs();
    }

    private final static Controleur controleur = new Controleur();

    public static Controleur getControleur() {
        return controleur;
    }

    /* MÉTHODES */
    /**
     *
     * @param clickables la liste des positions clickables
     * @return retourne une position sélectionée dans les clickables
     */
    public Vector2 getPosClic(ArrayList<Vector2> clickables) {

        vue.setMode(Vue.IhmMode.POSITION);

        if (!clickables.isEmpty()) {
            vue.getGrille().setClickables(clickables, true);

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
                    if(m.type == MessageType.ANNULER) {
                        done = true;
                        pos = null;
                    }
                }
            }

            vue.getGrille().setClickables(clickables, false);
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

    //
    /**
     *
     * @param aventurierIndex l'index de l'aventurier
     * @return Retourne une action sélectionée par l'aventurier d'index
     * aventurierIndex
     */
    Utils.Action getSelectedAction(int aventurierIndex) {

        vue.setMode(Vue.IhmMode.ACTION);

        ArrayList<Utils.Action> actions;
        if (aventuriers.get(aventurierIndex).getNbAction()>0){
            Utils.Action[] actionsList = {Utils.Action.SE_DEPLACER,Utils.Action.ASSECHER,Utils.Action.DON_CARTE,Utils.Action.FIN_TOUR,Utils.Action.UTILISER_CARTE,Utils.Action.PRENDRE_TRESOR};
            actions = new ArrayList<>(Arrays.asList(actionsList));
            if(aventuriers.get(aventurierIndex) instanceof Navigateur) actions.add(Utils.Action.ACTION_SPECIALE);
        }else{
            Utils.Action[] actionsList = {Utils.Action.FIN_TOUR,Utils.Action.UTILISER_CARTE};
            actions = new ArrayList<>(Arrays.asList(actionsList));
        }

        vue.getActions().setEnabled(true, actions);

        Utils.Action act = null;
        boolean done = false;
        while (!done) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.ACTION || m.type == MessageType.ANNULER) {
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

    /**
     *
     *
     * @return Retourne l'aventurier d'index indexAventurier
     */
    public Aventurier getSelectedAventurier() {
        vue.setMode(Vue.IhmMode.AVENTURIER);

        Aventurier av = null;
        boolean done = false;
        while (!done) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.AVENTURIER) {
                    done = true;
                    av = m.av;
                }
                if (m.type == MessageType.ANNULER) {
                    done = true;
                    av = null;
                }
            }
        }

        return av;
    }

    /**
     *
     * @return Retourne la carte que l'aventurier sélectionne
     */
    public Carte getCarteSelectionne() {

        vue.getMain().setAventurier(aventuriers.get(currentAventurier));
        vue.setMode(Vue.IhmMode.MAIN);

        Carte c = null;
        boolean done = false;
        while (!done) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.CARTE) {
                    done = true;
                    c = m.carte;
                }
                if(m.type == MessageType.ANNULER) {
                    done = true;
                    c = null;
                }
            }
        }

        return c;

    }

    /**
     *
     * @param av Qui doit sélectionner une carte à défausser
     * @return retourne la carte selectionné
     */
    public Carte getCarteSelectionne(Aventurier av) {

        vue.getMain().setAventurier(av);
        vue.setMode(Vue.IhmMode.MAIN);
        vue.getActions().setEnableAnnuler(false);

        Carte c = null;
        boolean done = false;
        while (!done) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.CARTE) {
                    done = true;
                    c = m.carte;
                }
            }
        }

        return c;

    }

    /*
    GAME LOOP
     */
 /*
    Gère la game loop du jeu
     */
    public void gameLoop() {
        currentAventurier = 0;
        boolean finDeTour = true;
        Aventurier av = null;
        while (!(isGameOver()||isVictoire())) {

            /*incrementer le joueur en fin de tour*/
            if(finDeTour) {
                av = aventuriers.get(currentAventurier);
                av.initialiserTour();
                System.out.println("Tour de : " + av.getJoueur());
                System.out.println("Position du pion : " + av.getPosition().toString());
                finDeTour = false;
            }

            //Phase d'action

            vue.getMain().setAventurier(av);


            // Update de la case d'affichage des informations
            vue.getInformations().update(
                    gameState.getTresors(),
                    av.getNbAction(),
                    getRateTuilesNonInondees(),
                    getRateTuilesRestantes(),
                    getAlerteMessage()
            );

            switch (getSelectedAction(currentAventurier)) {
                case SE_DEPLACER:
                    if (av.seDeplacer()) {
                        av.utiliserAction();
                    }
                    break;
                case ASSECHER:
                    if (av.assecher()) {
                        av.utiliserAction();
                    }
                    break;
                case DON_CARTE:
                    av.utiliserAction();
                    av.donnerCarte();
                    break;
                case FIN_TOUR:
                    finDeTour = true;
                    break;
                case PRENDRE_TRESOR:
                    // Si le joueur a peut prendre le trésor alors il a fait une action
                    if (av.prendreTresor()) {
                        av.utiliserAction();
                    }
                    break;
                case UTILISER_CARTE:
                    if (av.utiliserCarte()) {
                        av.utiliserAction();
                    }
                    break;
                case ACTION_SPECIALE:
                    if(av.actionSpeciale()){
                        av.utiliserAction();
                    }
                    break;
            }
            vue.getGrille().updateGrid(grille.getInnondee(), grille.getCoulee());
            vue.getGrille().updatePion(getPosPion());

            if(finDeTour){

                //phase de pioche
                CarteItem cIt1 = (CarteItem) cartesItem.piocher();
                CarteItem cIt2 = (CarteItem) cartesItem.piocher();

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

                currentAventurier = (currentAventurier!=aventuriers.size()-1?currentAventurier+1:0);
                //phase d'innondation
                faireInnondation();

                vue.getGrille().updateGrid(grille.getInnondee(), grille.getCoulee());
                vue.updateJoueurs();
            }

        }

    }

    /**
     * Incrémente le niveau de l'eau, l'appel d'innondation et mélange/vide la
     * défausse des cartes inondation
     */
    private void faireMonteDesEau() {
        cartesInondation.melangerCartesDefausse();
        LinkedList<Carte> defausse = cartesInondation.getDefausse();
        cartesInondation.addCartesPiocheDebut(defausse);
        defausse.clear();
        faireInnondation();
        gameState.incrementNiveau();
    }

    /**
     * Pioche x cartes inondation et met à jour les tuiles (inondées/coulées)
     */
    private void faireInnondation() {
        int nbCarteInondation = gameState.getNbDeCarte();

        for (int j = 0; j < nbCarteInondation; j++) {
            CarteInondation cIn = (CarteInondation) cartesInondation.piocher();

            if (cIn == null) {
                System.err.println("Pioche d'inondation vide.");
            } else {
                if (cIn.getTuile().isInnondee()) {
                    grille.removeTuile(cIn.getTuile());
                } else {
                    cartesInondation.addCarteDefausseDebut(cIn);
                    cIn.getTuile().setInnondee(true);
                }
            }

        }
    }

    /**
     * Méthode qui permet de démarrer une partie en récupérant les inputs de
     * l'utilisateur
     */
    public void initialiserPartie() {

        //Ajout des joueurs
        ArrayList<Aventurier> dispoAventuriers = FactoryAventurier.getAventuriers(grille);
        Message m = this.recevoirFormulaire();

        for (int i = 0; i < m.nbJoueurs; i++) {
            //Initialiser aventurier
            String nomJ = m.nomDesJoueurs.get(i);
            int r = ThreadLocalRandom.current().nextInt(dispoAventuriers.size());
            Aventurier av = dispoAventuriers.get(r);
            aventuriers.add(av);
            dispoAventuriers.remove(r);
            av.setJoueur(nomJ);

        }
        //Initialisation du gamestate

        gameState = new GameState(m.difficulte);

    }

    /* Méthodes liées aux conditions de défaite */
    /**
     *
     * @return la liste des aventuriers qui sont sur une case coulée
     */
    private ArrayList<Aventurier> listeJoueursCoule() {
        ArrayList<Aventurier> aventuriersCoule = new ArrayList<>();
        for (Aventurier unAventurier : this.getAventuriers()) {
            Vector2 positionJoueur = unAventurier.getPosition();
            Tuile tuileJoueur = this.getGrille().getTuile(positionJoueur);
            if (tuileJoueur == null) {
                aventuriersCoule.add(unAventurier);
            }
        }
        return aventuriersCoule;
    }

    /**
     *
     * @return true si le joueur est sur une case coulée et ne peut pas se
     * déplacer ailleurs
     */
    private boolean isJoueursCoince() {
        int i = 0;
        if (listeJoueursCoule().size() > 0) {
            for (Aventurier unAventurier : listeJoueursCoule()) {
                ArrayList<Vector2> listeDeplacements = unAventurier.getPosDeplacement();
                for (Vector2 unDeplacement : listeDeplacements) {
                    if (this.getGrille().getTuile(unDeplacement) == null) {
                        i++;
                    }
                }
                if (i == listeDeplacements.size()) {
                    return true;
                }
                i = 0;
            }

        }
        return false;
    }

    /**
     *
     * @param tresor le type de trésor que l'on veut vérifier les tuiles
     * @return true si les tuiles du trésor tresor sont coulées
     */
    private boolean isTuilesTresorCoule(Utils.Tresor tresor) {
        int i = 0;
        Grille grilleJeu = this.getGrille();
        Tuile[][] listeTuiles = grilleJeu.getTuiles();
        for (Tuile[] uneColonne : listeTuiles) {
            for (Tuile uneTuile : uneColonne) {
                if (uneTuile != null) {
                    if (uneTuile.isTuileTresor()) {
                        TuileTresor uneTuileTresor = (TuileTresor) uneTuile;
                        if (uneTuileTresor.getTresor() == tresor) {
                            i++;
                        }
                    }
                }
            }
        }
        return i == 0;
    }

    /**
     *
     * @return true si au moins l'un des trésors n'est plus récupérable (plus de
     * case & pas récupéré à temps)
     */
    private boolean isTuilesTresorCoince() {
        Utils.Tresor[] listeTresors = Utils.Tresor.values();
        for (Utils.Tresor unTresor : listeTresors) {
            if (isTuilesTresorCoule(unTresor) && !(this.getGameState().getTresors().get(unTresor))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return true si l'héliport est coulé
     */
    private boolean isHeliportCoule() {
        int i = 0;
        Tuile[][] listeTuiles = this.getGrille().getTuiles();
        for (Tuile[] uneColonne : listeTuiles) {
            for (Tuile uneTuile : uneColonne) {
                if (uneTuile instanceof Heliport) {
                    i++;
                }
            }
        }
        return i == 0;
    }

    /**
     *
     * @return true si le niveau d'eau dépasse le niveau 5 (>= 10 dans le code)
     */
    private boolean isTeteDeMort() {
        return this.getGameState().getNiveauEau() >= 10;
    }

    /**
     *
     * @return true si au moins l'une des conditions de défaite est respectée
     */
    private boolean isGameOver() {
        return isJoueursCoince() || isTuilesTresorCoince() || isHeliportCoule() || isTeteDeMort();
    }

    /* Méthodes liées aux conditions de victoire */
    /**
     *
     * @return true si tout les joueurs se situent sur la tuile hélicoptère
     */
    private boolean isToutLeMondeSurTuileHelicopetere() {
        int i = 0;
        ArrayList<Aventurier> listeAventuriers = this.getAventuriers();
        for (Aventurier unAventurier : listeAventuriers) {
            if (this.getGrille().getTuile(unAventurier.getPosition()) instanceof Heliport) {
                i++;
            }
        }
        return i == listeAventuriers.size();
    }

    /**
     *
     * @return true si tout les trésors ont été récupérés
     */
    private boolean isToutlestresorsrecuperes() {
        int i = 0;
        for (Map.Entry<Utils.Tresor, Boolean> entry : this.getGameState().getTresors().entrySet()) {
            boolean value = entry.getValue();
            if (value) {
                i++;
            }
        }
        return i == 4;
    }

    /**
     *
     * @return true si les conditions nécessaires à la victoire sont respectées
     */
    private boolean isVictoire() {
        boolean carteHelico = false;
        for (Carte uneCarte : this.getAventuriers().get(this.getCurrentAventurier()).getCarteItems()) {
            if (uneCarte instanceof CarteHelicoptere) {
                carteHelico = true;
            }
        }
        return this.isToutLeMondeSurTuileHelicopetere() && this.isToutlestresorsrecuperes() && carteHelico;
    }

    @Override
    public void recevoirMessage(Message m) {
        messages.add(m);
    }

    /**
     * Récupère le pourcentage de tuiles non inondées parmi les tuiles restantes
     *
     * @return Nombre entier correspondant au pourcentage de tuiles non inondées
     */
    public int getRateTuilesNonInondees() {
        // On initialise une variable cartes qui fera le cumul des cartes existantes
        int cartes = 0;
        // On initialise une variable cartes_non_inondees qui fera le cumul des cartes non inondées
        int cartes_non_inondees = 0;

        // Pour toutes les cartes
        for (int x = 0; x < grille.getSizeX(); x++) {
            for (int y = 0; y < grille.getSizeX(); y++) {
                // On récupère la tuile
                Tuile tuile = grille.getTuile(x, y);
                // Si la tuile existe alors on l'ajoute aux tuiles existentes
                if (tuile != null) {
                    cartes++;
                    // Si la tuile n'est pas inondée, on ajoute la tuile aux tuiles non inondées
                    if (!tuile.isInnondee()) {
                        cartes_non_inondees++;
                    }
                }
            }
        }

        // cartes non inondées/cartes restantes (ici 24) x 100 (pour l'avoir en pourcentage)
        return (int) Math.round(((double) cartes_non_inondees / cartes) * 100);
    }

    /**
     * Récupère le pourcentage de tuiles restantes dans la grille
     *
     * @return Nombre entier correspondant au pourcentage de tuiles restantes
     */
    public int getRateTuilesRestantes() {
        // On initialise une variable cartes qui fera le cumul des cartes existantes
        int cartes = 0;

        // Pour toutes les cases de la grille
        for (int x = 0; x < grille.getSizeX(); x++) {
            for (int y = 0; y < grille.getSizeX(); y++) {
                // On récupère la grille
                Tuile tuile = grille.getTuile(x, y);
                // Si elle existe, on ajoute la carte
                if (tuile != null) {
                    cartes++;
                }
            }
        }

        // cartes restantes/cartes totales (ici 24) x 100 (pour l'avoir en pourcentage)
        return (int) Math.round(((double) cartes / 24) * 100);
    }

    /**
     * Récupère des messages importants pour la partie dans cet ordre de priorité
     * * Tuile hélicoptère inondé
     * * Une seule tuile d'un type de trésor
     * * Joueur sur case inondé
     * * Niveau d'eau > 5 (>= 8 palliers)
     * @return Chaîne de caractère correspondant au message d'alerte
     */
    public String getAlerteMessage() {
        // Chaîne de caractère du message
        String msg = new String();
        // Liste des tresor où il ne reste plus qu'une tuile sur la grille
        Tresor[] tresors_critiques = new Tresor[4];

        // HashMap avec tous les tresors et leur nombre de tuile sur la grille
        HashMap<Tresor, Integer> tresors = new HashMap<>();
        for (Tresor tresor : Tresor.values()) {
            // On initialise à 0
            tresors.put(tresor, 0);
        }

        // On parcours toute la grille
        for (int x = 0; x < grille.getSizeX(); x++) {
            for (int y = 0; y < grille.getSizeX(); y++) {
                // On récupère chaque tuile
                Tuile tuile = grille.getTuile(x, y);
                if (tuile != null) {
                    // S'il s'agit de l'héliport et qu'il est inondé, on affiche un message
                    if (tuile instanceof Heliport && tuile.isInnondee()) {
                        msg = "HÉLIPORT INONDÉ";
                    }
                    // S'il s'agit d'une tuile tresor, on l'ajoute à l'HashMap
                    if (tuile instanceof TuileTresor) {
                        Tresor tresor = ((TuileTresor) tuile).getTresor();
                        tresors.put(tresor, tresors.get(tresor) + 1);
                    }
                }
            }
        }

        // S'il y a un message, on retourne
        if(msg.length() > 0) return msg;

        // On initialise une variable qui va récupérer le nombre de tresors critiques
        int nb_tresors = 0;

        // Pour tous les tresors
        for (Tresor tresor : Tresor.values()) {
            // Si il reste plus qu'une tuile trésor et qu'il n'a pas été récupéré, on envoie un message
            if (tresors.get(tresor) == 1 && !this.getGameState().getTresors().get(tresor).booleanValue()) {
                // On incrémente la variable cumule
                nb_tresors++;
            }
        }

        // S'il y a des trésors critiques, on va construire le message séquentiellement
        if (nb_tresors > 0) {
            // On initialise une variable qui permet de savoir combien de trésor nous reste
            int index = 0;

            // On refait la boucle permettant d'obtenir les tuiles trésors critiques non récupérées
            for (Tresor tresor : Tresor.values()) {
                if (tresors.get(tresor) == 1 && !this.getGameState().getTresors().get(tresor).booleanValue()) {
                    index++;
                    // Pour la première itération, on consuit le début de la boucle
                    if (index == 1) {
                        msg = "1 TUILE TRESOR " + tresor.toString();
                    } // Pour la dernière itération, on ajoute un & avant le dernier trésor et on finit la string
                    else if (index == nb_tresors) {
                        msg += " & " + tresor.toString();
                    } // Sinon, on sépare par des virgules
                    else {
                        msg += ", " + tresor.toString();
                    }
                }
            }

            // On ajoute la fin du message
            msg += " RESTANTE";

            // On retourne donc la chaîne créée
            return msg;
        }

        // On initialise une variable pour récupérer le nombre d'aventurier sur une tuile
        int nb_aventuriers_inondes = 0;

        for (Aventurier aventurier : getAventuriers()) {
            // On récupère la tuile de chaque aventurier
            Tuile tuile = grille.getTuile(aventurier.getPosition());
            // Si la tuile est inondé, on averti que le joueur est sur une tuile inondée
            if(tuile != null && tuile.isInnondee()) nb_aventuriers_inondes++;
        }

        // S'il y a des aventuriers sur une case inondé
        if (nb_aventuriers_inondes > 0) {

            int index = 0;

            // On répète la boucle précédente
            for (Aventurier aventurier : getAventuriers()) {
                Tuile tuile = grille.getTuile(aventurier.getPosition());
                if (tuile != null && tuile.isInnondee()) {

                    index++;
                    // Pour la première itération, on ajoute le joueur
                    if (index == 1) {
                        msg = aventurier.getJoueur();
                    } // Si c'est la fin, on met un & devant le joueur
                    else if (index == nb_aventuriers_inondes) {
                        msg += " & " + aventurier.getJoueur();
                    } // Sinon, on met des virgules
                    else {
                        msg += ", " + aventurier.getJoueur();
                    }
                }
            }
            // On ajoute la fin du message
            msg += " sur tuile inondé.";

            return msg;
        }

        // Si on est à deux palliers de finir la partie, on affiche un avertissement
        if (gameState.getNiveauEau() >= 8) {
            return "Niveau d'eau critique";
        }

        // Sinon on affiche rien
        return "";
    }

    private MultiMap<Vector2, Utils.Pion> getPosPion() {
        MultiMap<Vector2, Utils.Pion> posPion = new MultiMap<>();
        for (Aventurier av
                : aventuriers) {
            posPion.put(av.getPosition(), av.getPion());
        }
        return posPion;
    }

    public Message recevoirFormulaire() {
        Message message = null;
        boolean done = false;
        while (!done) {
            System.out.print("");
            while (!messages.isEmpty()) {
                Message m = messages.poll();
                if (m.type == MessageType.VALIDER_FOMULAIRE) {
                    done = true;
                    message = m;
                }
            }
        }
        return message;
    }

    /**
     * Permet de mettre à jour la vue de la main avec les cartes du joueur
     * courant
     */
    public void updateMain() {

        Aventurier aventurier = this.getAventuriers().get(this.getCurrentAventurier());

        this.vue.getMain().setCartesItems(aventurier.getCarteItems());

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

    public Deck getCartesInondation() {
        return cartesInondation;
    }

    public void setCartesInondation(Deck cartesInondation) {
        this.cartesInondation = cartesInondation;
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
