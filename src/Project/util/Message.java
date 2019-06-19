package Project.util;

import Project.Modele.Aventurier;
import Project.Modele.Carte;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Message {
    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType type;

    public Vector2 position;
    public Utils.Action action;
    public String parametre;
    public Carte carte;
    public Aventurier av;
    public ArrayList<String> nomDesJoueurs ;
    public int nbJoueurs ;
    public int difficulte ;
}
