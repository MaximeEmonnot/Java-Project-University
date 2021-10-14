package GameFiles;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Exceptions.ProjectException;
import GameFiles.Scenes.*;
import GraphicsEngine.GraphicsSystem;

public class Game {
    public Game() throws Exceptions.ProjectException, Exception{
        //Graphics initialization (MUST BE FIRST)
        GraphicsEngine.GraphicsSystem.GetInstance();

        testScene = new SearchScene();
    }

    public void Go() throws Exceptions.ProjectException, LineUnavailableException, UnsupportedAudioFileException, IOException, SQLException{
        UpdateFrame();
        RenderFrame();
        GraphicsEngine.GraphicsSystem.GetInstance().Render();
    }

    private void UpdateFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException, ProjectException, SQLException{
        //Must be called to update the DeltaTime value
        CoreSystem.Timer.GetInstance().Update();

        testScene.Update();
    }

    private void RenderFrame() throws Exceptions.ProjectException {
        testScene.Draw();

        GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(0, 0), CoreSystem.Mouse.GetInstance().GetMousePos(), Color.RED);
    }

    AScene testScene;
}
