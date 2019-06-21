package Project.views.Elements;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Project.util.Message;
import Project.util.MessageType;

import Project.Controleur;
import Project.util.Observe;

public class EGameOver extends JPanel {

	/**
	 * ATTRIBUTES
	 */
	// 
	private Observe observe;
    private JPanel texte;
    private JLabel title;
    private JLabel description;
    private JPanel groupe_boutons; 
    private JButton rejouer;
    private JButton quitter;
	
	/**
	 * CONSTRUCTOR
	 */
	// 
	public EGameOver(Observe observe) {
		this.observe = observe;

		this.observe = observe ;

        Controleur c = Controleur.getControleur() ;

        rejouer = new JButton("Rejouer") ;
        quitter = new JButton("Quitter") ;

        this.setLayout(new BorderLayout());

        texte = new JPanel( new GridBagLayout()) ;

        texte.setLayout(new GridLayout(2, 1));

        title = new JLabel();

        // création du label de game over
        title.setText("<html> <p style=\"text-align:center;\"> GAME OVER <p></html>");
        title.setFont(new Font("Arial",Font.BOLD,72));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        texte.add(title);

        description = new JLabel();

        // création du label de la description de la défaite
        // Si l'héliport a coulé
        if(c.isHeliportCoule()) {
            description.setText("<html><p style=\"text-align:justify;\"> L'héliport étant coulé, il n'est plus possible de s'enfuir. </p></html>");
        }
        // Si une des tuiles trésors a coulé alors que le trésor n'a pas été récupéré
        else if(c.isTuilesTresorCoince()) {
            description.setText("<html><p style=\"text-align:justify;\"> Les trésors étant irrécupérables, il n'est plus possible de s'enfuir. </p></html>");
        } 
        // Si un ou plusieurs joueurs ont coulés
        else if(c.isJoueursCoince()) {
            description.setText("<html><p style=\"text-align:justify;\"> Un des joueurs a coulé, vous avez donc perdu. </p></html>");
        }
        // Si le niveau d'eau a atteint le dernier pallier
        else if(c.isTeteDeMort()) {
            description.setText("<html><p style=\"text-align:justify;\"> Le niveau d'eau a atteint le seuil mortel, vous avez donc perdu. </p></html>");
        }
        // Sinon on affiche un message par défaut
        else {
            description.setText("<html><p style=\"text-align:justify;\"> Vous avez perdu </p></html>");
        }
        description.setFont(new Font("Arial",Font.BOLD,24));
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setVerticalAlignment(JLabel.TOP);
        texte.add(description);

        this.add(texte);

        groupe_boutons = new JPanel( new GridBagLayout()) ;

        GridBagConstraints contrainte = new GridBagConstraints();

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 0 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 1;
        groupe_boutons.add(new JLabel() , contrainte) ; // ajout de panel vide pour la mise en page des bouttons

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 1 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 0.75 ;
        groupe_boutons.add(rejouer , contrainte) ;

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 2 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 2 ;
        groupe_boutons.add(new JLabel() , contrainte) ;

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 3 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 0.75 ;
        groupe_boutons.add(quitter , contrainte) ;

        contrainte.fill = GridBagConstraints.BOTH ;
        contrainte.gridx = 4 ;
        contrainte.gridy = 0 ;
        contrainte.weightx = 1 ;
        groupe_boutons.add(new JLabel(), contrainte) ;

        this.add(groupe_boutons , BorderLayout.SOUTH) ;
        


        rejouer.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.REJOUER) ;
                observe.notifierObserver(m);
            }
        }) ;

        quitter.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(MessageType.QUITTER) ;
                observe.notifierObserver(m);
            }
        });
	}

	/**
	 * GETTERS/SETTERS
	 */
	// 
	
}