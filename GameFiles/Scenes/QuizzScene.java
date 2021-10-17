package GameFiles.Scenes;

import java.sql.SQLException;
import Exceptions.ProjectException;
import GameFiles.Questions.*;

public class QuizzScene extends AScene {

    public QuizzScene() throws Exception{
        nextSceneIndex = 0;
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub

        questions.get(iCurQuestion).Update();
        if (questions.get(iCurQuestion).IsLost() || questions.get(iCurQuestion).IsWon()){
            timerNextQuestion -= CoreSystem.Timer.GetInstance().DeltaTime();
            if (timerNextQuestion <= 0.0f){
                iCurQuestion++;
                timerNextQuestion = 1.0f;
                if (questions.get(iCurQuestion).IsLost()){
                    lives--;
                }
            }
        }

        if (lives <= 0){
            bChangeScene = true;
            lives = 3;
        }

    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        questions.get(iCurQuestion).Draw();
    }
    

    private int lives = 3;
    private float timerNextQuestion = 1.0f;
    private int iCurQuestion = 0;
}
