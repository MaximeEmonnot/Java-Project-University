package SoundEngine;

import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Exceptions.ProjectException;

/**
 * Gestion des fichiers audios.
 * <p>Permet d'ouvrir un fichier audio, et de le jour a n'importe quel moment.
 * <p>Utilise le patron de conception Singleton pour qu'un seul systeme audio existe dans toute l'application
 * @author Maxime Emonnot
 * @version 1.0.0
 * @since 1.4.0
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
     * <p>Initialise l'environnement de lecture de fichiers audios.
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
     */
    public void AddNewSong(String path) throws UnsupportedAudioFileException, IOException{
        songs.add(path);
        ais.add(AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()));
        //clip.open(ais);
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Joue le son selectionne.
     * <p>Passe le systeme en etat PLAY.
     * @author Maxime Emonnot
     * @param index Index du son a jouer
     * @throws ProjectException Si l'index ne correspond a aucun son ajoute
     * @throws IOException Si une erreur d'entree/sortie a lieu durant la lecture de l'adresse du fichier correspondant a l'index
     * @throws LineUnavailableException Si le system audio est indisponible
     */
    public void PlaySong(int index) throws ProjectException, LineUnavailableException, IOException{
        if (index > ais.size())
            throw new ProjectException("Can't access a song that's not added !");
        long beginDelay = System.currentTimeMillis();
        while(System.currentTimeMillis() - beginDelay < 2){}
        clip.open(ais.get(index));
        clip.loop(0);
        clip.start();
        sStatus = SongStatus.PLAY;
    }
    /**
     * Met en pause le son en cours
     * <p>Passe le systeme en etat PAUSE
     * @author Maxime Emonnot
     */
    public void PauseSong(){
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
        sStatus = SongStatus.PAUSE;
    }
    /**
     * Si le system est en pause, relance le son en pause.
     * <p>Met le systeme en etat PLAY.
     * @author Maxime Emonnot
     * @param index Index du son a relancer
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le system de lecture est indisponible
     * @throws ProjectException Si l'index ne correpond a aucun son ajoute
     * @see SoundSystem#ResetAudio()
     * @see SoundSystem#PlaySong(int)
     */
    public void ResumeSong(int index) throws UnsupportedAudioFileException, IOException, LineUnavailableException, ProjectException{
        if (sStatus == SongStatus.PAUSE){
            clip.close();
            ResetAudio(index);
            PlaySong(index);
            long beginDelay = System.currentTimeMillis();
            while(System.currentTimeMillis() - beginDelay < 2){}
            clip.setMicrosecondPosition(currentFrame);
        }
    }
    /**
     * Arrete le son en cours.
     * <p>Met le system en etat STOP
     * @author Maxime Emonnot
     */
    public void StopSong(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
        sStatus = SongStatus.STOP;
    }
    /**
     * Relance le son selectionne.
     * <p>Met le systeme en etat PLAY
     * @author Maxime Emonnot
     * @param index Index du son a redemarrer
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le system de lecture est indisponible
     * @throws ProjectException Si l'index ne correspond a aucun son ajoute
     * @see SoundSystem#ResetAudio(int)
     * @see SoundSystem#PlaySong(int)
     */
    public void RestartSong(int index) throws UnsupportedAudioFileException, IOException, LineUnavailableException, ProjectException{
        clip.stop();
        clip.close();
        ResetAudio(index);
        currentFrame = 0L;
        PlaySong(index);
    }

    /**
     * Reinitialise le son selectionne.
     * @author Maxime Emonnot
     * @param index Index du son a reinitialiser
     * @throws UnsupportedAudioFileException Si le fichier audio ne peut pas etre lu
     * @throws IOException S'il ya une erreur d'acces au fichier
     * @throws LineUnavailableException Si le systeme de lecture est indisponible
     * @see SoundSystem#AddNewSong(String)
     */
    private void ResetAudio(int index) throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        ais.set(index, AudioSystem.getAudioInputStream(new File(songs.get(index)).getAbsoluteFile()));        
    }

    private static SoundSystem INSTANCE = null;

    private List<String> songs = new ArrayList<String>();
    private SongStatus sStatus = SongStatus.NONE;

    private List<AudioInputStream> ais = new ArrayList<AudioInputStream>();
    private long currentFrame;
    private Clip clip;
}
