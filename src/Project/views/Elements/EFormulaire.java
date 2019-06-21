package Project.views.Elements;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import Project.views.VueFormulaire;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public final class EFormulaire extends JPanel {

    /* CONSTANTES */
    private final static JLabel tableauJoueurs[] = {new JLabel("Joueur 1") , new JLabel("Joueur 2") , new JLabel("Joueur 3") , new JLabel("Joueur 4") };
    private final static String tableauDifficulté [] = {"Novice" , "Normal" , "Élite" , "Légendaire" } ;

    /* ATTRIBUTS */
    private JTextField tableauNomJoueurs[] = {new JTextField("Joueur 1") , new JTextField("Joueur 2") ,new JTextField("Joueur 3") , new JTextField("Joueur 4")} ;
    private JSlider choixNbJoueurs ;
    private JSlider choixDifficulté ;
    private JButton valider ;
    private ArrayList<JPanel> listeFormulaire ; // contient les formulaire de nom pour les joueurs et aussi le panel qui contient le choix de difficulté et de nombre de joueurs
    private Observe observe;
    private ENiveauDEau niveauEau ;

    /*CONSTRUCTEUR*/

    /**
     * c'est l'observateur qui est mit en paramètre.
     * @param observe
     */
    public EFormulaire (Observe observe) {

        this.observe = observe;

        this.setLayout(new GridLayout(1,2, 5 , 0)) ;
        JPanel formulaire = new JPanel( new GridLayout(6,1)) ;

        listeFormulaire = new ArrayList<>() ;
        listeFormulaire.add(new JPanel (new GridBagLayout ())) ;

        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridwidth = 1;
        contrainte.gridheight = 1;
        contrainte.gridx = 1;
        contrainte.gridy = 1;
        contrainte.ipadx = 20;
        contrainte.ipady = 10;
        
        JLabel nbJoueurs = new JLabel("Nombre de joueurs");
        nbJoueurs.setHorizontalAlignment(JLabel.CENTER);
        nbJoueurs.setVerticalAlignment(JLabel.CENTER);
        listeFormulaire.get(0).add(nbJoueurs, contrainte) ;

        JLabel nivDif = new JLabel("Niveau de difficulté");
        contrainte.gridx = 2;
        contrainte.gridy = 1;
        nbJoueurs.setHorizontalAlignment(JLabel.CENTER);
        nbJoueurs.setVerticalAlignment(JLabel.CENTER);
        listeFormulaire.get(0).add(nivDif, contrainte) ;

        choixNbJoueurs = new JSlider(JSlider.HORIZONTAL  , 2 , 4 , 4) ; // création du slider du nombre de joeurs
        choixNbJoueurs.setMajorTickSpacing(1);
        choixNbJoueurs.setMinorTickSpacing(1);
        choixNbJoueurs.setPaintTicks(true);
        choixNbJoueurs.setPaintLabels(true);
        choixNbJoueurs.setSnapToTicks(true) ;
        contrainte.gridx = 1;
        contrainte.gridy = 2;

        listeFormulaire.get(0).add(choixNbJoueurs, contrainte);

        choixNbJoueurs.addChangeListener (new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dessinFormulaireNom(choixNbJoueurs.getValue()); // si on change le nombre de joueurs appelle la méthode dessinFormualaireNom ayant en paramètre le nombre de joueurs
            }
        });

        choixDifficulté = new JSlider(JSlider.VERTICAL,1, 4, 1); // création du slider de niveau de difficulté
        choixDifficulté.setSnapToTicks(true) ;
        choixDifficulté.setMajorTickSpacing(1);
        choixDifficulté.setPaintTicks(true);

        Hashtable labelTable = new Hashtable();

        for (int i = 0 ; i<4 ; i++){
            labelTable.put(  (Integer) i+1 , new JLabel((tableauDifficulté[i])) );;
        }
        
        // listeFormulaire.get(0).add(choixDifficulté, contrainte);
        choixDifficulté.setLabelTable( labelTable );

        contrainte.gridx = 2;
        contrainte.gridy = 2;

        choixDifficulté.setPaintLabels(true);
        listeFormulaire.get(0).add(choixDifficulté, contrainte);

        choixDifficulté.addChangeListener (new ChangeListener() { // changement de l'affichage si niveau de difficulté changer
            @Override
            public void stateChanged(ChangeEvent e) {
                niveauEau.setNiveau(choixDifficulté.getValue());
            }
        });

        JPanel panelRandom = new JPanel();
        JCheckBox choixRandom = new JCheckBox("Génération aléatoire");

        contrainte.gridx = 1;
        contrainte.gridy = 3;
        contrainte.gridwidth = 2;

        listeFormulaire.get(0).add(choixRandom, contrainte);

        for (int i = 0 ; i<4 ; i++){    // création de tout les formulaires pour les joueurs
            JPanel panel = new JPanel(new GridLayout(2,1)) ;
            panel.add(tableauJoueurs[i]) ;
            panel.add(tableauNomJoueurs[i]) ;
            listeFormulaire.add(panel) ;
        }
        dessinFormulaireNom(choixNbJoueurs.getValue());

        for (int i = 0 ; i<listeFormulaire.size() ; i++) { // ajout de tout les élements sur la partie visuel
            formulaire.add(listeFormulaire.get(i)) ;
        }

        this.add(formulaire) ;

        JPanel bouttons = new JPanel (new BorderLayout()) ;

        niveauEau = new ENiveauDEau(1) ;
        bouttons.add(niveauEau , BorderLayout.CENTER) ;

        valider = new JButton("Valider") ;
        valider.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.VALIDER_FOMULAIRE) ;  // au moment de la validation, envoi un message avec le nombre de joueurs, leur nom ainsi que la difficulté choisi
                ArrayList<String> noms = new ArrayList<>() ;
                for (int i = 1 ; i<listeFormulaire.size() ; i++) {
                    noms.add(tableauNomJoueurs[i-1].getText()) ;
                }

                m.nomDesJoueurs = noms;
                m.nbJoueurs = choixNbJoueurs.getValue() ;
                m.difficulte = choixDifficulté.getValue() ;
                m.random = choixRandom.isSelected();
                observe.notifierObserver(m);

                ((VueFormulaire)observe).getFrame().dispose();
            }
        }) ;
        
        bouttons.add(valider , BorderLayout.SOUTH) ;
        this.add(bouttons) ;
    }




    private void dessinFormulaireNom (int nbJ) { // met visible le nombre de formulaire nécessaire aux nombre de joueurs

        for (int i = 0 ; i<listeFormulaire.size() ; i++) {
            if (i <= nbJ){
                listeFormulaire.get(i).setVisible(true);
            }
            else {
                listeFormulaire.get(i).setVisible(false);
            }

        }


    }


}
