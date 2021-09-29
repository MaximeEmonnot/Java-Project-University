package SoundEngine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundSystem {

    private enum SongStatus{
        PLAY,
        PAUSE,
        STOP,
        NONE
    }

    private SoundSystem() throws LineUnavailableException{
        clip = AudioSystem.getClip();
    }
    
    public synchronized static SoundSystem GetInstance() throws LineUnavailableException{
        if (INSTANCE == null)
            INSTANCE = new SoundSystem();
        return INSTANCE;
    }

    public void SelectNewSong(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        long beginDelay = System.currentTimeMillis();
        while(System.currentTimeMillis() - beginDelay < 2){}
        currentSong = path;
        ais = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void PlaySong(){
        clip.start();
        sStatus = SongStatus.PLAY;
    }
    public void PauseSong(){
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
        sStatus = SongStatus.PAUSE;
    }
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
    public void StopSong(){
        currentFrame = 0L;
        clip.stop();
        clip.close();
        sStatus = SongStatus.STOP;
    }
    public void RestartSong() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        clip.stop();
        clip.close();
        ResetAudio();
        currentFrame = 0L;
        PlaySong();
    }

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
