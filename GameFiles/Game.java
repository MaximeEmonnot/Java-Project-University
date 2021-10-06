package GameFiles;

import java.awt.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Exceptions.ProjectException;

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

    private void UpdateFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException, ProjectException{
        //Must be called to update the DeltaTime value
        CoreSystem.Timer.GetInstance().Update();
        kirby.Update();
    }

    private void RenderFrame() throws Exceptions.ProjectException {
        kirby.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(10, 10), CoreSystem.Mouse.GetInstance().GetMousePos(), Color.BLUE);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(500, 10), CoreSystem.Mouse.GetInstance().GetMousePos(), Color.RED);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(10, 500), CoreSystem.Mouse.GetInstance().GetMousePos(), Color.GREEN);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(500, 500), CoreSystem.Mouse.GetInstance().GetMousePos(), Color.YELLOW);
    }

    private Character kirby;
}
