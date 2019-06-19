package Project.views.Elements;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import Project.util.Utils;
import Project.views.VueFormulaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public final class EFormulaire extends JPanel {

    // get pour les labels  (prénom, nom  / psedo) nb joueurs, dificulté
        // nom vide refusé

    private final static JLabel tableauJoueurs[] = {new JLabel("Joueur 1") , new JLabel("Joueur 2") , new JLabel("Joueur 3") , new JLabel("Joueur 4") };
    private final static JTextField tableauNomJoueurs[] = {new JTextField(15) , new JTextField(15) ,new JTextField(15) , new JTextField(15)} ;
    private final static String tableauDifficulté [] = {"novice" , "normale" , "élite" , "légendaire" } ;

    // A MODIFIER POUR MORTEL DIFICULTY ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    private final static String tableauNbJoueur [] = {"2" , "3" , "4"} ;

    private JComboBox choixNbJoueurs ;
    private JComboBox choixDifficulté ;
    private JButton valider ;
    private VueFormulaire formulaire ;

    private ArrayList<JPanel> listeFormulaire ;

    Observe observe;

    public EFormulaire (Observe observe) {

        this.observe = observe;

        this.setLayout(new GridLayout(1,2));
        JPanel p = new JPanel( new GridLayout(5,1)) ;

        tableauNomJoueurs[0].setText("J1");
        tableauNomJoueurs[1].setText("J2");
        tableauNomJoueurs[2].setText("J3");
        tableauNomJoueurs[3].setText("J4");

        listeFormulaire = new ArrayList<>() ;
        listeFormulaire.add(new JPanel (new GridLayout (2,2))) ;

        listeFormulaire.get(0).add(new JLabel("nombre de Joueurs")) ;

        listeFormulaire.get(0).add(new JLabel("niveau de difficulté")) ;

        choixNbJoueurs = new JComboBox() ;
        for (int i = 0 ; i<3 ; i++){
            choixNbJoueurs.addItem(tableauNbJoueur[i]);
        }
        listeFormulaire.get(0).add(choixNbJoueurs);
        choixNbJoueurs.setSelectedIndex(2);

        choixNbJoueurs.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dessinFormulaireNom(choixNbJoueurs.getSelectedIndex()+2);
            }
        });

        choixDifficulté = new JComboBox() ;
        for (int i = 0 ; i<4 ; i++){   // mettre à max 5 à la place de 4 pour difficulté mortel
            choixDifficulté.addItem(tableauDifficulté[i]);
        }
        listeFormulaire.get(0).add(choixDifficulté);
        choixDifficulté.setSelectedIndex(0);

        for (int i = 0 ; i<4 ; i++){
            JPanel panel = new JPanel(new GridLayout(2,1)) ;
            panel.add(tableauJoueurs[i]) ;
            panel.add(tableauNomJoueurs[i]) ;
            listeFormulaire.add(panel) ;
        }

        dessinFormulaireNom(choixNbJoueurs.getSelectedIndex()+2);

        for (int i = 0 ; i<listeFormulaire.size() ; i++) {
            p.add(listeFormulaire.get(i)) ;
        }
        this.add(p) ;
        JPanel panel = new JPanel (new BorderLayout()) ;
        valider = new JButton("Valider") ;
        valider.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.VALIDER_FOMULAIRE) ;
                ArrayList<String> noms = new ArrayList<>() ;
                for (int i = 1 ; i<listeFormulaire.size() ; i++) {
                    noms.add(tableauNomJoueurs[i-1].getName()) ;
                }

                m.nomDesJoueurs = noms;
                m.nbJoueurs = choixNbJoueurs.getSelectedIndex() + 2 ;
                m.difficulte = choixDifficulté.getSelectedIndex() +1 ;
                observe.notifierObserver(m);
            }
        }) ;
        panel.add(valider , BorderLayout.SOUTH) ;
        this.add(panel) ;
    }




    private void dessinFormulaireNom (int nbJ) {

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
