package Project.views.Elements;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import Project.views.VueFormulaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public final class EFormulaire extends JPanel {

    /* CONSTANTES */
    private final static JLabel tableauJoueurs[] = {new JLabel("Joueur 1") , new JLabel("Joueur 2") , new JLabel("Joueur 3") , new JLabel("Joueur 4") };
    private final static String tableauDifficulté [] = {"novice" , "normale" , "élite" , "légendaire" } ;
    private final static String tableauNbJoueur [] = {"2" , "3" , "4"} ;

    /* ATTRIBUTS */
    private JTextField tableauNomJoueurs[] = {new JTextField("J1") , new JTextField("J2") ,new JTextField("J3") , new JTextField("J4")} ;
    private JComboBox choixNbJoueurs ;
    private JSlider choixNBJOUEURS ;
    private JComboBox choixDifficulté ;
    private JSlider choixDIFFICULTE ;
    private JButton valider ;
    private ArrayList<JPanel> listeFormulaire ; // contient les formulaire de nom pour les joueurs et aussi le panel qui contient le choix de difficulté et de nombre de joueurs
    private Observe observe;

    /*CONSTRUCTEUR*/

    /**
     * c'est l'observateur qui est mit en paramètre.
     * @param observe
     */
    public EFormulaire (Observe observe) {

        this.observe = observe;

        this.setLayout(new GridLayout(1,2));
        JPanel formulaire = new JPanel( new GridLayout(5,1)) ;

        listeFormulaire = new ArrayList<>() ;
        listeFormulaire.add(new JPanel (new GridLayout (2,2))) ;

        listeFormulaire.get(0).add(new JLabel("nombre de Joueurs")) ;

        listeFormulaire.get(0).add(new JLabel("niveau de difficulté")) ;

        choixNbJoueurs = new JComboBox() ;  // initialisation des valeurs du choix du nombre de joueurs
        choixNBJOUEURS = new JSlider(JSlider.HORIZONTAL  , 2 , 4 , 4) ;
        choixNBJOUEURS.setMajorTickSpacing(1);
        choixNBJOUEURS.setMinorTickSpacing(1);
        choixNBJOUEURS.setPaintTicks(true);
        choixNBJOUEURS.setPaintLabels(true);
        Hashtable labelTable = new Hashtable();

        for (int i = 0 ; i<3 ; i++){
            labelTable.put( new Integer( i ), new JLabel(tableauNbJoueur[i]) );
        }
        choixNBJOUEURS.setLabelTable( labelTable );
        choixNBJOUEURS.setPaintLabels(true);


        for (int i = 0 ; i<3 ; i++){
            choixNbJoueurs.addItem(tableauNbJoueur[i]);
        }
        listeFormulaire.get(0).add(choixNBJOUEURS);
        choixNbJoueurs.setSelectedIndex(2);

        choixNbJoueurs.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dessinFormulaireNom(choixNbJoueurs.getSelectedIndex()+2); // si on change le nombre de joueurs appelle la méthode dessinFormualaireNom ayant en paramètre le nombre de joueurs
            }
        });

        choixDifficulté = new JComboBox() ; // initialsation des valeurs du choix de difficulté
        for (int i = 0 ; i<4 ; i++){
            choixDifficulté.addItem(tableauDifficulté[i]);
        }
        listeFormulaire.get(0).add(choixDifficulté);
        choixDifficulté.setSelectedIndex(0);

        for (int i = 0 ; i<4 ; i++){    // création de tout les formulaires pour les joueurs
            JPanel panel = new JPanel(new GridLayout(2,1)) ;
            panel.add(tableauJoueurs[i]) ;
            panel.add(tableauNomJoueurs[i]) ;
            listeFormulaire.add(panel) ;
        }

        dessinFormulaireNom(choixNbJoueurs.getSelectedIndex()+2);

        for (int i = 0 ; i<listeFormulaire.size() ; i++) { // ajout de tout les élements sur la partie visuel
            formulaire.add(listeFormulaire.get(i)) ;
        }
        this.add(formulaire) ;
        JPanel bouttons = new JPanel (new BorderLayout()) ;
        valider = new JButton("Valider") ;
        valider.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.VALIDER_FOMULAIRE) ;  // au moment de la validation, envoi un message avec le nombre de joueurs, leur nom ainsi que la difficulté choisi
                ArrayList<String> noms = new ArrayList<>() ;
                for (int i = 1 ; i<listeFormulaire.size() ; i++) {
                    noms.add(tableauNomJoueurs[i-1].getText()) ;
                }

                m.nomDesJoueurs = noms;
                m.nbJoueurs = choixNbJoueurs.getSelectedIndex() + 2 ;
                m.difficulte = choixDifficulté.getSelectedIndex() +1 ;
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
