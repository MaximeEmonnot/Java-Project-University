package GameFiles;

import java.awt.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game {
    public Game() throws Exceptions.ProjectException, Exception{
        //Graphics initialization
        GraphicsEngine.GraphicsSystem.GetInstance();
        
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

        menuTest.Update();
        kirby.Update();
    }

    private void RenderFrame() throws Exceptions.ProjectException {
        kirby.Draw();
        menuTest.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(150, 150, 50, 50), Color.WHITE);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(250, 150, 50, 50), Color.WHITE);

        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(150, 250, 50, 50), Color.WHITE);

        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(250, 250, 50, 50), Color.WHITE);


    }

    private Character kirby;
    private MenuSystem.IMenu menuTest = new MenuSystem.MenuTest(new MenuSystem.BasicMenu());
}
