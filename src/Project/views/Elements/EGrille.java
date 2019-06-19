package Project.views.Elements;

import Project.util.*;
import Project.views.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EGrille extends JPanel {
    /*
    ATTRIBUTES
     */
    
    private ECase[][] eCases;
    private Vue vue;

    /*
    CONSTRUCTOR
     */

    public EGrille(int size_x, int size_y, String[][] tuillesNames,Vue vue) {
        this.setLayout(new GridLayout(size_y,size_x));
        
        eCases = new ECase[size_x][size_y];
        for (int i = 0; i < eCases.length; i++) {
            for (int j = 0; j < eCases[i].length; j++) {
                ECase c = new ECase(tuillesNames[j][i], new Vector2(j,i),this);
                eCases[j][i] = c;
                add(c);
                c.setClickable(false);
                c.setVisible(true);
            }
        }


        eCases[0][0].setPions(new ArrayList<>(Arrays.asList(Utils.Pion.ROUGE, Utils.Pion.BLEU)));

        this.vue = vue;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < eCases.length; i++) {
            for (int j = 0; j < eCases[i].length; j++) {
                eCases[i][j].repaint();
            }
        }
    }

    public void updateGrid(boolean[][] inondee, boolean[][] coulee) {
        for (int i = 0; i < eCases.length; i++) {
            for (int j = 0; j < eCases[i].length; j++) {
                if (coulee[i][j]) {
                    eCases[i][j].changeEtat(ECase.Etat.COULEE);
                } else if (inondee[i][j]) {
                    eCases[i][j].changeEtat(ECase.Etat.INNONDEE);
                } else {
                    eCases[i][j].changeEtat(ECase.Etat.SEC);
                }
                eCases[i][j].setClickable(false);
            }
        }
    }

    public void messageCase(Vector2 pos){
        System.out.println(pos);
        Message m = new Message(MessageType.POSITION);
        m.position = pos;
        vue.notifierObserver(m);
    }

    /**
     * permet de changer la clickabiltié de certaine case
     * @param positions positions a modifier
     * @param aBoolean new state
     */
    public void setClickables(ArrayList<Vector2> positions,boolean aBoolean){
        for (Vector2 pos:
             positions) {
            ECase tuile = eCases[pos.x][pos.y];
            tuile.setClickable(aBoolean);
        }
    }

    /**
     * Fonction permetant de metre a jour l'affichage des pions dans la grille
     * @param pions liste des pions associé avec les position des pions
     */
    public void updatePion(MultiMap<Vector2, Utils.Pion> pions){
        for (int i = 0; i < eCases.length; i++) {
            for (int j = 0; j < eCases[0].length; j++) {
                eCases[i][j].setPions(pions.get(new Vector2(i,j)));
            }
        }
    }


}
