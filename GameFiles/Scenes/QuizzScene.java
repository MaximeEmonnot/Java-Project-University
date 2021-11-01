package GameFiles.Scenes;

import java.sql.SQLException;
import Exceptions.ProjectException;
import java.awt.*;

public class QuizzScene extends AScene {

    public QuizzScene() throws Exception{
        super();
        nextSceneIndex = 1;
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        if (lives <= 0){
            bChangeScene = true;
        }
        
        questions.get(iCurQuestion).Update();
        if (questions.get(iCurQuestion).IsLost() || questions.get(iCurQuestion).IsWon()){
            timerNextQuestion -= CoreSystem.Timer.GetInstance().DeltaTime();
            if (timerNextQuestion <= 0.0f){
                if (questions.get(iCurQuestion).IsLost()){
                    lives--;
                }
                iCurQuestion++;
                if (iCurQuestion >= questions.size()){
                    bChangeScene = true;
                }
                timerNextQuestion = 1.0f;
            }
        }
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        questions.get(iCurQuestion).Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("LIVES : " + lives, new Point(10, 500), Color.BLACK, 15);
    }
    
    private float timerNextQuestion = 1.0f;
}
