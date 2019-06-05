package Project.views;

import Project.util.Message;
import Project.util.MessageType;
import Project.util.Observe;
import Project.util.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VueGrille extends Observe {

    private boolean opened;
    private JFrame fenetre;
    private JButton tuiles[][];

    public VueGrille(int sizeX, int sizeY, boolean[][] coulee, boolean[][] innondee) {
        fenetre = new JFrame();
        opened = true;
        fenetre.setSize(600, 600);
        fenetre.setTitle("Plateau");
        fenetre.setLayout(new GridLayout(sizeX, sizeY));
        fenetre.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                opened = false;
                System.exit(0);
            }
        });

        tuiles = new JButton[sizeX][sizeY];

        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                JButton b = new JButton(i + "," + j);
                b.setEnabled(false);
                fenetre.add(b);

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JButton b = ((JButton) actionEvent.getSource());
                        for (int i = 0; i < tuiles.length; i++) {
                            for (int j = 0; j < tuiles[i].length; j++) {
                                if (tuiles[i][j] == b) {
                                    Message message = new Message(MessageType.POSITION);
                                    message.position = new Vector2(i, j);
                                    notifierObserver(message);
                                }
                            }
                        }
                    }
                });

                tuiles[i][j] = b;

            }
        }
        resetColors(innondee, coulee);

        fenetre.setVisible(true);
    }

    public void resetColors(boolean[][] inondee, boolean[][] coulee) {
        for (int i = 0; i < tuiles.length; i++) {
            for (int j = 0; j < tuiles.length; j++) {
                if (coulee[i][j]) {
                    tuiles[i][j].setBackground(Color.BLACK);
                } else if (inondee[i][j]) {
                    tuiles[i][j].setBackground(Color.BLUE);
                } else {
                    tuiles[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void allumerTuiles(ArrayList<Vector2> positions) {
        for (Vector2 p
                : positions) {
            tuiles[p.x][p.y].setEnabled(true);
            tuiles[p.x][p.y].setForeground(Color.RED);
        }
    }

    public void eteindreTuiles() {
        for (JButton[] ligne
                : tuiles) {
            for (JButton b
                    : ligne) {
                b.setEnabled(false);
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        Dimension dimension = new Dimension().getSize();
        System.out.println(dimension.getWidth()+"+"+dimension.getHeight());
    }

    public boolean isOpen() {
        return opened;
    }
}
