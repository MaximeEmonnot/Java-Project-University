package GameFiles.Scenes;

import java.sql.SQLException;
import Exceptions.ProjectException;
import GameFiles.Questions.*;

public class QuizzScene extends AScene {

    public QuizzScene() throws Exception{
        testQuestion = new ConcreteQuadrupleQuestion("Question Title", "Answer A", "Answer B", "Answer C", "Answer D", AQuadrupleAnswerQuestion.AnswerType.ANSWER_A);
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        testQuestion.Update();
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        testQuestion.Draw();
    }
    
    private AQuestion testQuestion;
}
