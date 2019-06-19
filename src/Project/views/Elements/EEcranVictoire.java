package Project.views.Elements;

import Project.Controleur;
import Project.Modele.Aventurier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public final class EEcranVictoire extends JPanel {

    private JButton rejouer ;
    private JButton quitter ;

    public EEcranVictoire () {

        Controleur controleur = Controleur.getControleur() ;

        rejouer = new JButton("rejouer") ;
        quitter = new JButton("quitter") ;

        this.setLayout(new BorderLayout());

        GridBagConstraints c = new GridBagConstraints();

        JPanel p = new JPanel( new GridBagLayout()) ;


        c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5 ;

        JPanel panel = new JPanel() ;
        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 0 ;
        c.gridy = 0 ;
        p.add(panel , c) ;







        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.CYAN);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 3.0 ;

        /* label.setText("<html> <p style=\"text-align:center;\"> FÉLICITATION ! <br> <br> VOUS AVEZ SURVECU  <br> À L'ILE INTERDITE <p></html>");
        label.setFont(new Font("Arial",Font.BOLD,72));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER); */

        ArrayList<Aventurier> av = controleur.getAventuriers() ;
        String s = "" ;
        String s2 ;
        if (controleur.getGameState().getNiveauEau()-1 == 0){
            s2 = "novice" ;
        }
        else if (controleur.getGameState().getNiveauEau()-1 == 1) {
            s2 = "normal" ;
        }
        else if (controleur.getGameState().getNiveauEau()-1 == 2) {
            s2 = "expert" ;
        }
        else {
            s2 = "légendaire" ;
        }
        for (int i = 0 ; i<av.size() ; i++) {
            s = s + av.get(i).getJoueur() + " en tant que " + av.get(i).getNom() + ". <br> <br>" ;
        }
        label.setText("<html> Votre équipe était constituer de : <br><br>" + s +
                "a survécue à l'Ile Interdite au niveau de difficulté : " + s2 + "<br>" +
                "<br> avec " + (100-controleur.getRateTuilesNonInondees()) + "% de la cate inondée</html>");

        label.setFont(new Font("Arial",Font.BOLD,30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        p.add(label, c);



//        thread  pour le délé sans niké les bouttons






        panel = new JPanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        p.add(panel, c);

        this.add(p , BorderLayout.CENTER) ;


        c = new GridBagConstraints() ;
        c.weightx = 1;
        c.weighty = 1 ;

        JPanel f = new JPanel( new GridBagLayout()) ;
        label = new JLabel() ;
        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 0 ;
        c.gridy = 0 ;
        f.add(label , c) ;

        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 1 ;
        c.gridy = 0 ;
        c.weightx = 0.75 ;
        f.add(rejouer , c) ;

        label = new JLabel() ;
        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 2 ;
        c.gridy = 0 ;
        c.weightx = 2 ;
        f.add(label , c) ;


        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 3 ;
        c.gridy = 0 ;
        c.weightx = 0.75 ;
        f.add(quitter , c) ;

        label = new JLabel() ;
        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 4 ;
        c.gridy = 0 ;
        c.weightx = 1 ;
        f.add(label , c) ;

        this.add(f , BorderLayout.SOUTH) ;


    }

}
