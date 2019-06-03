package Project.util;

public class Observe {
    private Observeur observeur;

    public void notifierObserver(Message m){
        observeur.recevoirMessage(m);
    }

    public void setObserveur(Observeur observeur) {
        this.observeur = observeur;
    }
}
