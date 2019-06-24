package Project.views;

import Project.Modele.Aventurier;
import Project.Modele.Deck;
import Project.util.CalculatedVector2;
import Project.util.Observe;
import java.awt.*;

import Project.util.Vector2;
import Project.views.Elements.EActions;
import Project.views.Elements.EDeck;
import Project.views.Elements.EGrille;
import Project.views.Elements.EInfo;
import Project.views.Elements.EJoueur;
import Project.views.Elements.EMain;
import Project.views.Elements.ENiveauDEau;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class Vue extends Observe {

    private static final Color TRANSPARENT = new Color(0,0,0,0);
    private static final Color HIGHLIGHT_COLOR = new Color(0,255,0);
    private final static int WINDOW_SIZE_X = 1680;
    private final static int WINDOW_SIZE_Y = 1000;
    private final static int POPUP_OFFSET = 50;
    private final JFrame window;
    private final GridBagConstraints constraints;

    //Panels
    private final JPanel componentPanel;
    private final JPanel grilleMainPanel;
    private JPanel joueursPanel;
    private final JPanel bottomRightPanel;

    //Elements
    private EGrille grille;
    private final EInfo informations;
    private ENiveauDEau niveauEau;
    private final EDeck deck;
    private final ArrayList< EJoueur> listeJoueurs;
    private final EMain main;
    private final EActions actions;

    //JLayerUI

    /**
     * Class  permettant l'affichage d'un highlight sur la fenetre
     */
    class HighlighLayerUI extends LayerUI<JComponent>{

        private ArrayList<CalculatedVector2> corner = new ArrayList<>();
        private ArrayList<CalculatedVector2> dimensions = new ArrayList<>();
        private Color highlightColor;
        private float opacity;
        private boolean doHighlight;

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            if (doHighlight) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2.setColor(highlightColor);
                for (int i = 0; i < corner.size(); i++) {
                    g2.fillRect(corner.get(i).get().x, corner.get(i).get().y, dimensions.get(i).get().x , dimensions.get(i).get().y);
                }
            }
        }

        public void setHighlight(CalculatedVector2 corner, CalculatedVector2 dimension, Color color, float opacity){
            this.corner.clear();
            this.corner.add(corner);
            this.dimensions.clear();
            this.dimensions.add(dimension);
            this.highlightColor = color;
            this.opacity = opacity;
            doHighlight = true;
        }

        public void setHighlights(ArrayList<CalculatedVector2 > corner, ArrayList<CalculatedVector2 > dimension, Color color, float opacity){
            this.corner = corner;
            this.dimensions = dimension;
            this.highlightColor = color;
            this.opacity = opacity;
            this.doHighlight = true;
        }

        public void disableHighlight(){
            this.doHighlight = false;
        }
    }
    private final HighlighLayerUI highlighLayerUI;
    private final JLayer highlighLayer;

    public Vue(Deck deckInondation, Deck deckItem) {
        window = new JFrame("L'Île interdite");
        componentPanel = new JPanel();
        this.configureWindow(window);

        highlighLayerUI = new HighlighLayerUI();
        highlighLayer = new JLayer<>(componentPanel,highlighLayerUI);
        //highlighLayerUI.setHighlight(Vector2.ZERO,new Vector2(500,500),Color.RED,0.5f);

        window.add(highlighLayer);


        grilleMainPanel = new JPanel();
        grilleMainPanel.setLayout(new BorderLayout());
        bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        componentPanel.setLayout(new GridBagLayout());

        listeJoueurs = new ArrayList<>();
        deck = new EDeck(deckInondation, deckItem);
        actions = new EActions(this);
        main = new EMain(this);
        informations = new EInfo();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new GridBagLayout());
        deckPanel.add(deck);
        bottomRightPanel.add(deckPanel, constraints);

        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        bottomRightPanel.add(actions, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        bottomRightPanel.add(informations, constraints);

        grilleMainPanel.add(main, BorderLayout.SOUTH);

    }

    private void configureWindow(JFrame window) {
        window.setBackground(Color.WHITE);
        window.setSize(getWINDOW_SIZE_X(), getWINDOW_SIZE_Y());
        window.getContentPane().setLayout(new java.awt.BorderLayout());
        window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);

    }

    public static int getWINDOW_SIZE_X() {
        return WINDOW_SIZE_X;
    }

    public static int getWINDOW_SIZE_Y() {
        return WINDOW_SIZE_Y;
    }    

    public void initialiserNiveauEau(int level) {
        niveauEau = new ENiveauDEau(level);
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        bottomRightPanel.add(niveauEau, constraints);

    }

    public void initialiserGrille(String[][] names, boolean[][] inondee, boolean[][] coulee) {
        grille = new EGrille(names[0].length, names.length, names, this);

        JPanel grillePanel = new JPanel();
        grillePanel.setLayout(new GridBagLayout());
        grillePanel.add(grille);
        grilleMainPanel.add(grillePanel, BorderLayout.CENTER);

        grille.setVisible(true);
        grille.paintComponents(window.getGraphics());

    }

    public void initialiserJoueurs(ArrayList<Aventurier> joueurs) {
        joueursPanel = new JPanel();
        joueursPanel.setLayout(new GridLayout(4, 1));
        for (int i = 0; i < joueurs.size(); i++) {
            EJoueur eJoueur = new EJoueur(this, joueurs.get(i));
            this.listeJoueurs.add(eJoueur);
            joueursPanel.add(eJoueur, constraints);
            eJoueur.updateJoueur();

        }
    }

    public void initialiserVue() {
        constraints.weightx=1.2;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        componentPanel.add(grilleMainPanel, constraints);
        constraints.weightx=0.8;
        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        componentPanel.add(joueursPanel, constraints);
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        componentPanel.add(bottomRightPanel, constraints);
        window.setVisible(true);
    }

    public EDeck getDeck() {
        return deck;
    }

    public EGrille getGrille() {
        return grille;
    }

    public ENiveauDEau getNiveauEau() {
        return niveauEau;
    }

    public EMain getMain() {
        return main;
    }

    public enum IhmMode {
        POSITION,
        AVENTURIER,
        ACTION,
        MAIN,
    }

    /**
     * Permet d'indiquer a la vue quel est l'entré attendu
     * @param ihmMode indique l'entré attendu
     */
    public void setMode(IhmMode ihmMode) {
        switch (ihmMode) {
            case ACTION:
                actions.setEnabled(true);
                grille.setEnabled(false);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(false);
                }
                //actions.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                grille.setBorder(null);
                main.setBorder(null);
                joueursPanel.setBorder(null);
                highlightComponent(actions);
                break;
            case POSITION:
                actions.setEnabled(false);
                grille.setEnabled(true);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(false);
                }

                //grille.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                actions.setBorder(null);
                main.setBorder(null);
                joueursPanel.setBorder(null);
                //highlightComponent(grille);
                break;
            case MAIN:

                actions.setEnabled(false);
                grille.setEnabled(false);
                main.setEnabled(true);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(false);
                }
                //main.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                grille.setBorder(null);
                actions.setBorder(null);
                joueursPanel.setBorder(null);
                highlightComponent(main);
                break;
            case AVENTURIER:

                actions.setEnabled(false);
                grille.setEnabled(false);
                main.setEnabled(false);
                for (EJoueur j
                        : listeJoueurs) {
                    j.setEnabled(true);
                }
                //joueursPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                grille.setBorder(null);
                main.setBorder(null);
                actions.setBorder(null);
                highlightComponent(joueursPanel);
                break;

        }
    }

    public EInfo getInformations() {
        return informations;
    }

    /**
     * Fonction permetant de mettre a jour l'affichage des joueur
     */
    public void updateJoueurs() {
        for (EJoueur j
                : listeJoueurs) {
            j.updateJoueur();
        }
    }

    public JFrame getWindow() {
        return window;
    }

    public EActions getActions() {
        return actions;
    }

    public JPanel getComponentPanel() {
        return componentPanel;
    }

    /**
     * Fonction permetant de mettre en evidence un composent
     * @param c le composent mis en evidence
     */
    public void highlightComponent(JComponent c){
        highlighLayerUI.setHighlight(
                ()->(new Vector2(SwingUtilities.convertPoint(c,0,0,componentPanel).x,SwingUtilities.convertPoint(c,0,0,componentPanel).y)),
                ()->(new Vector2(c.getWidth(),c.getHeight())),
                HIGHLIGHT_COLOR,
                0.5f);
        highlighLayer.updateUI();
    }

    /**
     * Fonction permettant de mettre en evidence des composants
     * @param cs les composants mis en evidence
     */
    public void highlightComponents(ArrayList<JComponent> cs){
        ArrayList<CalculatedVector2> dimensions = new ArrayList<>();
        ArrayList<CalculatedVector2> positions = new ArrayList<>();

        for (JComponent c :
                cs) {
            positions.add(()->new Vector2(SwingUtilities.convertPoint(c,0,0,componentPanel).x,SwingUtilities.convertPoint(c,0,0,componentPanel).y));
            dimensions.add(()->new Vector2(c.getWidth(),c.getHeight()));
        }

        highlighLayerUI.setHighlights(
                positions,
                dimensions,
                HIGHLIGHT_COLOR,
                0.5f);
        highlighLayer.updateUI();
    }

    /**
     * Permet d'afficher une pop up centré à {@link Vue#POPUP_OFFSET} px du haut de la fenetre
     * @param text texte affiché par la popup
     * @param duration duré d'affichage de la popup
     */
    public void popUpMessage(String text, int duration){
        JLabel textLabel = new JLabel(text);
        Thread messageThread = new Thread(){
            @Override
            public void run() {
                super.run();
                JDialog frame = new JDialog(window);
                frame.setUndecorated(true);
                frame.add(textLabel);
                int x = (int)(window.getWidth()/2-text.length()*textLabel.getFont().getSize()/3.2);

                frame.setBounds(x,POPUP_OFFSET,(int)(text.length()*textLabel.getFont().getSize()/1.6f),textLabel.getFont().getSize());
                frame.getRootPane().setOpaque(false);
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    try {
                        frame.setOpacity(((float) 100-i)/100);
                    }
                    catch (UnsupportedOperationException e){
                        System.err.println("transparency not suported");
                        i = 100;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(duration/100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frame.dispose();
            }
        };
        messageThread.start();
    }
}
