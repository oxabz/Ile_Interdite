package Project.util;

import Project.Modele.Carte;

public class Message {
    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType type;

    public Vector2 position;
    public Utils.Action action;
    public String parametre;
    public Carte carte;
}
