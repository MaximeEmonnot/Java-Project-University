package GameFiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {
    public Game() throws Exceptions.ProjectException, Exception{
        //Graphics initialization
        GraphicsEngine.GraphicsSystem.GetInstance();
        
        SoundEngine.SoundSystem.GetInstance().SelectNewSong("Audio/test.wav");
        SoundEngine.SoundSystem.GetInstance().PlaySong();
        
        kirby = new Character(new Rectangle(150, 150, 64, 64), "json/kirby.json");
    }

    public void Go() throws Exceptions.ProjectException, LineUnavailableException, UnsupportedAudioFileException, IOException{
        UpdateFrame();
        RenderFrame();
        GraphicsEngine.GraphicsSystem.GetInstance().Render();
    }

    private void UpdateFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
        //Must be called to update the DeltaTime value
        CoreSystem.Timer.GetInstance().Update();
        if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_F1)){
            SoundEngine.SoundSystem.GetInstance().PauseSong();
        }
        else if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_F2)){
            SoundEngine.SoundSystem.GetInstance().ResumeSong();
        }
        else if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_F3)){
            SoundEngine.SoundSystem.GetInstance().StopSong();
        }
        else if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_F4)){
            SoundEngine.SoundSystem.GetInstance().PlaySong();
        }
        else if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_F5)){
            SoundEngine.SoundSystem.GetInstance().RestartSong();
        }

        kirby.Update();
    }

    private void RenderFrame() throws Exceptions.ProjectException {
        kirby.Draw();
    }

    private Character kirby;
}
