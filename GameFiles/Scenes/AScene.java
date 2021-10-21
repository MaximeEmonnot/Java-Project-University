package GameFiles.Scenes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ProjectException;
import GameFiles.Questions.AQuestion;

public abstract class AScene {
    public AScene(){
    }

    public abstract void Update() throws SQLException;
    public abstract void Draw() throws ProjectException;

    public int GetNextSceneIndex(){
        return nextSceneIndex;
    }

    public boolean ChangeScene(){
        boolean out = bChangeScene;
        bChangeScene = false;
        return out;
    }

    protected static List<AQuestion> questions = new ArrayList<AQuestion>();
    protected static boolean bChangeScene = false;
    protected static int lives = 3;
    protected static int iCurQuestion = 0;
    protected int nextSceneIndex;
}
