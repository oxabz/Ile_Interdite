package Project.views.Elements;

import Project.Controleur;
import Project.Modele.Aventurier;
import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public final class EEcranVictoire extends JPanel {

    private JButton rejouer ;
    private JButton quitter ;
    private Observe observe;
    private JLabel bravo ; // label contenant le message de félicitation
    private GridBagConstraints contrainteText ; // containte pour l'affichage des différents messages, pour facilité de réutilisabilité
    private JLabel score ; // label contenant le message des scores
    private JLabel histoire ; // label contenant le message d'histoire de fin de jeux
    private JPanel text; // panel contenant la partie d'affichage des messages
    private GridBagConstraints cVide ; // contrainte d'un panel vide
    private JPanel pVide ; // panel vide

    public EEcranVictoire (Observe observe) {

        this.observe = observe ;

        Controleur controleur = Controleur.getControleur() ;

        rejouer = new JButton("rejouer") ;
        quitter = new JButton("quitter") ;

        this.setLayout(new BorderLayout());

        GridBagConstraints contrainte = new GridBagConstraints();

        text = new JPanel( new GridBagLayout()) ;

        contrainte.weightx = 0.5;
        contrainte.weighty = 0.5 ;

        pVide = new JPanel() ;
        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 0 ;
        contrainte.gridy = 0 ;
        text.add(pVide , contrainte) ;


        bravo = new JLabel();
        contrainte.fill = GridBagConstraints.BOTH;
        contrainte.gridx = 1;
        contrainte.gridy = 0;
        contrainte.weightx = 3.0 ;


        // création du label de bravo
        bravo.setText("<html> <p style=\"text-align:center;\"> FÉLICITATION ! <br> <br> VOUS AVEZ SURVECU  <br> À L'ÎLE INTERDITE <p></html>");
        bravo.setFont(new Font("Arial",Font.BOLD,72));
        bravo.setHorizontalAlignment(JLabel.CENTER);
        bravo.setVerticalAlignment(JLabel.CENTER);
        contrainteText =  contrainte;

        // création du label de score
        score = new JLabel( );
        ArrayList<Aventurier> av = controleur.getAventuriers() ;
        String presentationJoueurs = "" ;
        String niveauDifficulté ;
        if (controleur.getGameState().getNiveauEau()-1 == 0){
            niveauDifficulté = "novice" ;
        }
        else if (controleur.getGameState().getNiveauEau()-1 == 1) {
            niveauDifficulté = "normal" ;
        }
        else if (controleur.getGameState().getNiveauEau()-1 == 2) {
            niveauDifficulté = "expert" ;
        }
        else {
            niveauDifficulté = "légendaire" ;
        }
        for (int i = 0 ; i<av.size() ; i++) {
            presentationJoueurs = presentationJoueurs + av.get(i).getJoueur() + " en tant que " + av.get(i).getNom() + ". <br> <br>" ;
        }
        score.setText("<html> Votre équipe était constituée de : <br><br>" + presentationJoueurs+
                "a survécue à l'Île Interdite au niveau de difficulté : " + niveauDifficulté + "<br>" +
                "<br> avec " + (100-controleur.getRateTuilesNonInondees()) + "% de l'île inondée</html>");

        score.setFont(new Font("Arial",Font.BOLD,30));
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setVerticalAlignment(JLabel.CENTER);

        // création du label de histoire
        histoire = new JLabel( );

        histoire.setText("<html>Après avoir passé toutes leurs épreuves en ayant réussi à survivre à la nature et ramené tous les trésors de l'Île Interdite : \"La Pierre sacrée\", \"La Statue du zéphyr\", \"Le Cristal ardent\" et \"Le Calice de l'onde\" représentant les 4 éléments, soit respectivement la terre, le vent, le feu et l'eau. <br><br>" +
                "Même si les rassembler n'a pas été facile sur cette île déchaînée, évitant l'inondation totale afin de pouvoir accéder aux temples contenant les diverses reliques mais aussi en protégeant leur seule issue, l'héliport, grâce auquel nos " + controleur.getAventuriers().size() + " héros sont tous revenus vivant de leur expédition. <br><br> " +
                "Nos aventuriers ne sont certes pas sortis indemne de leur expérience cependant les liens qui les unissent, après une telle épreuve, se retrouvèrent renforcés et plus solides que jamais.</html>");

        histoire.setFont(new Font("Arial",Font.BOLD,20));
        histoire.setHorizontalAlignment(JLabel.LEFT);
        histoire.setVerticalAlignment(JLabel.CENTER);

        cVide = new GridBagConstraints();
        pVide = new JPanel();
        cVide.fill = GridBagConstraints.BOTH;
        cVide.gridx = 2;
        cVide.gridy = 0;
        cVide.weightx = 0.5;
        text.add(pVide, cVide);
        text.add(bravo , contrainteText);

        this.add(text , BorderLayout.CENTER) ;

        contrainte = new GridBagConstraints() ;
        contrainte.weightx = 1;
        contrainte.weighty = 1 ;

        // ajout de la partie boutton

        pVide = new JPanel( );
        JPanel panelBouttons = new JPanel( new GridBagLayout()) ;
        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 0 ;
        contrainte.gridy = 0 ;
        panelBouttons.add(pVide , contrainte) ; // ajout de panel vide pour la mise en page des bouttons

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 1 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 0.75 ;
        panelBouttons.add(rejouer , contrainte) ;

        pVide = new JPanel( );
        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 2 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 2 ;
        panelBouttons.add(pVide , contrainte) ;

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 3 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 0.75 ;
        panelBouttons.add(quitter , contrainte) ;

        pVide = new JPanel( );
        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 4 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 1 ;
        panelBouttons.add(pVide , contrainte) ;

        pVide = new JPanel( );

        this.add(panelBouttons , BorderLayout.SOUTH) ;

        // actions des boutons quand clicer

        rejouer = new JButton("Rejouer") ; // envoi un message de type rejouer
        rejouer.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.REJOUER) ;
                observe.notifierObserver(m);
            }
        }) ;

        quitter = new JButton("Quitter") ; // envoi un message de type quitter
        quitter.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.QUITTER) ;
                observe.notifierObserver(m);
            }
        }) ;



    }

    public void afficherText () { // méthode pour afficher les différents texte avec un intervalle de 15 secondes

//        text.add(pVide, cVide);

        try {
            Thread.sleep(10000);        // attente de 15 seconde avant le changement de texte
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        text.remove(bravo); // supression de l'ancien texte
        text.remove(pVide) ;

        text.add(score , contrainteText) ; // ajout du nouveaux text
        text.add(pVide , cVide) ;

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        text.remove(score);
        text.remove(pVide) ;

        text.add(histoire , contrainteText) ;
        text.add(pVide , cVide) ;
    }

}
