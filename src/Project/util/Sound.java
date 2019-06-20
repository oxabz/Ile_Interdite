/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.util;

import java.io.File;
import java.io.IOException;
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
    public static synchronized void jouer(final String nomFichier)
    {
        // Note : fichier .wav !            
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(nomFichier));
                    clip.open(inputStream);
                    clip.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.out.println("Erreur son : " + e.getMessage() + " pour " + nomFichier);
                }
            }
        }).start();
    }
    
    public static synchronized void jouerMusique(final String nomFichier)
    {
        // Note : fichier .wav !     
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(nomFichier));
                    clip.open(inputStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    clip.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    System.out.println("Erreur son : " + e.getMessage() + " pour " + nomFichier);
                }
            }
        }).start();
    }
}
