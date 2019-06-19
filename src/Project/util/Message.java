package Project.util;

import java.util.ArrayList;
import Project.Modele.Aventurier;
import Project.Modele.Carte;

public class Message {
    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType type;

    public Vector2 position;
    public Utils.Action action;
    public String parametre;
    public ArrayList<String> nomDesJoueurs ;
    public int nbJoueurs ;
    public int difficulte ;
    public Carte carte;
    public Aventurier av;
}

