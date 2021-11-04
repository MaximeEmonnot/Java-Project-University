package GameFiles;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Exceptions.ProjectException;
import GameFiles.Scenes.*;

public class Game {
    public Game() throws Exceptions.ProjectException, Exception{
        scenes.add(new ConnectionScene());
        //scenes.add(new SearchScene());
        scenes.add(new QuizzScene());
        scenes.add(new TeacherScene());
        scenes.add(new AdminScene());
    }

    public void Go() throws Exceptions.ProjectException, LineUnavailableException, UnsupportedAudioFileException, IOException, SQLException{
        UpdateFrame();
        RenderFrame();
        GraphicsEngine.GraphicsSystem.GetInstance().Render();
    }

    private void UpdateFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException, ProjectException, SQLException{
        //Must be called to update the DeltaTime value
        CoreSystem.Timer.GetInstance().Update();

        scenes.get(iCurScene).Update();
        if (scenes.get(iCurScene).ChangeScene()){
            iCurScene = scenes.get(iCurScene).GetNextSceneIndex();
        }
    }

    private void RenderFrame() throws Exceptions.ProjectException {
        scenes.get(iCurScene).Draw();
    }

    private List<AScene> scenes = new ArrayList<AScene>();
    private int iCurScene = 0;
}
