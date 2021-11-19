package SoundEngine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Gestion des fichiers audios.
 * Permet d'ouvrir un fichier audio, et de le jour a n'importe quel moment.
 * Utilise le patron de conception Singleton pour qu'un seul systeme audio existe dans toute l'application
 * @author Maxime Emonnot
 */
public class SoundSystem {

    /**
     * Differents etat du systeme selon l'action la plus recente
     * @author Maxime Emonnot
     */
    private enum SongStatus{
        PLAY,
        PAUSE,
        STOP,
        NONE
    }

    /**
     * Constructeur prive dans le cadre du patron de conception Singleton. 
     * Initialise l'environnement de lecture de fichiers audios.
     * @author Maxime Emonnot
     * @throws LineUnavailableException Lors de l'initialisation du systeme audio.
    */
    private SoundSystem() throws LineUnavailableException{
        clip = AudioSystem.getClip();
    }
    
    /**
     * Recuperation d'instance, dans le cadre du patron de conception Singleton.
     * @author Maxime Emonnot
     * @return L'instance SoundSystem de l'application
     * @throws LineUnavailableException Lors de l'initialisation du systeme audio.
     */
    public synchronized static SoundSystem GetInstance() throws LineUnavailableException{
        if (INSTANCE == null)
            INSTANCE = new SoundSystem();
        return INSTANCE;
    }

    /**
     * Selectionne un nouveau son a partir de son chemin.
     * @author Maxime Emonnot
     * @param path Chemin du fichier audio
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le system de lecture est indisponible
     */
    public void SelectNewSong(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        long beginDelay = System.currentTimeMillis();
        while(System.currentTimeMillis() - beginDelay < 2){}
        currentSong = path;
        ais = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Joue le son selectionne.
     * Passe le systeme en etat PLAY.
     * @author Maxime Emonnot
     * @see SoundSystem#SelectNewSong(String)
     */
    public void PlaySong(){
        clip.start();
        sStatus = SongStatus.PLAY;
    }
    /**
     * Met en pause le son selectionne
     * Passe le systeme en etat PAUSE
     * @author Maxime Emonnot
     * @see SoundSystem#SelectNewSong(String)
     */
    public void PauseSong(){
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
        sStatus = SongStatus.PAUSE;
    }
    /**
     * Si le system est en pause, relance le son en pause.
     * Met le systeme en etat PLAY.
     * @author Maxime Emonnot
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le system de lecture est indisponible
     * @see SoundSystem#ResetAudio()
     * @see SoundSystem#PlaySong()
     */
    public void ResumeSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if (sStatus == SongStatus.PAUSE){
            clip.close();
            ResetAudio();
            PlaySong();
            long beginDelay = System.currentTimeMillis();
            while(System.currentTimeMillis() - beginDelay < 2){}
            clip.setMicrosecondPosition(currentFrame);
        }
    }
    /**
     * Arrete le son selectionne.
     * Met le system en etat STOP
     * @author Maxime Emonnot
     * @see SoundSystem#SelectNewSong(String)
     */
    public void StopSong(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
        sStatus = SongStatus.STOP;
    }
    /**
     * Relance le son selectionne.
     * Met le systeme en etat PLAY
     * @author Maxime Emonnot
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le system de lecture est indisponible
     * @see SoundSystem#ResetAudio()
     * @see SoundSystem#PlaySong()
     */
    public void RestartSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        clip.stop();
        clip.close();
        ResetAudio();
        currentFrame = 0L;
        PlaySong();
    }

    /**
     * Reinitialise le son selectionne.
     * @author Maxime Emonnot
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le system de lecture est indisponible
     * @see SoundSystem#SelectNewSong(String)
     */
    private void ResetAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        ais = AudioSystem.getAudioInputStream(new File(currentSong).getAbsoluteFile());
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private static SoundSystem INSTANCE = null;

    private String currentSong;
    private SongStatus sStatus = SongStatus.NONE;

    private AudioInputStream ais;
    private long currentFrame;
    private Clip clip;
}
