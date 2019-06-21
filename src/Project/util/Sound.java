/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.util;

import Project.Controleur;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author seiglebq
 */
public class Sound {

    private volatile boolean etatMusiqueJeu = true;
    private volatile boolean etatAmbianceJeu = true;

    /**
     *
     * @param fichier le nom du fichier à jouer joue le son une fois par appel
     * de la méthode
     */
    public static synchronized void jouer(final String fichier) {
        // Note : fichier en .wav
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fichier));
                    clip.open(inputStream);
                    clip.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.out.println("Erreur son : " + e.getMessage() + " pour " + fichier);
                }
            }
        }).start();
    }

    /**
     *
     * @param fichier le nom du fichier à jouer une fois la méthode appelée, le
     * son se joue en boucle tant que la fenêtre de jeu est ouverte
     * @param c
     */
    public synchronized void jouerBoucle(final String fichier, Controleur c) {
        // Note : fichier en .wav
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fichier));
                    clip.open(inputStream);

                    while (!c.isClosed && getEtatAmbianceJeu()) {

                        clip.start();
                        while (clip.getFramePosition() < clip.getFrameLength() && getEtatAmbianceJeu()) {
                            Thread.yield();
                        }
                        clip.stop();
                        clip.setFramePosition(0);
                    }

                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.out.println("Erreur son : " + e.getMessage() + " pour " + fichier);
                }
            }
        }).start();
    }

    public synchronized void demarrerMusiqueJeu(Controleur c) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Clip> clips = new ArrayList<>();
                ArrayList<AudioInputStream> ais = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    try {
                        clips.add(AudioSystem.getClip());
                        ais.add(AudioSystem.getAudioInputStream(new File(Utils.Son.getCHEMIN_SON() + "musique/musiqueJeu" + i + ".wav")));
                    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                        System.err.print("Erreur son : " + ex.getMessage() + " pour " + i);
                    }
                }
                int i = 0;
                while (!c.isClosed && i < clips.size() && getEtatMusiqueJeu()) {
                    try {
                        clips.get(i).open(ais.get(i));
                    } catch (LineUnavailableException | IOException ex) {
                        System.err.print("Erreur son : " + ex.getMessage() + " pour " + i);
                    }
                    clips.get(i).start();
                    while (clips.get(i).getFramePosition() < clips.get(i).getFrameLength() && getEtatMusiqueJeu()) {
                        Thread.yield();
                    }
                    clips.get(i).stop();
                    clips.get(i).close();
                    i++;

                }
                demarrerMusiqueJeu(c);
            }

        }).start();
    }

    public void stopMusiqueJeu() {
        this.setEtatMusiqueJeu(false);
    }

    public void stopAmbianceJeu() {
        this.setEtatAmbianceJeu(false);
    }

    private void setEtatMusiqueJeu(boolean etatMusiqueJeu) {
        this.etatMusiqueJeu = etatMusiqueJeu;
    }

    private boolean getEtatMusiqueJeu() {
        return etatMusiqueJeu;
    }

    private boolean getEtatAmbianceJeu() {
        return etatAmbianceJeu;
    }

    private void setEtatAmbianceJeu(boolean etatAmbianceJeu) {
        this.etatAmbianceJeu = etatAmbianceJeu;
    }

}
