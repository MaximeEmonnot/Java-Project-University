package GameFiles.Questions;

import Exceptions.ProjectException;

import java.awt.*;

public abstract class ADoubleAnswerQuestion extends AQuestion {

    public enum AnswerType{
        ANSWER_A,
        ANSWER_B,
        BOTH
    }

    public ADoubleAnswerQuestion(String _question, String _answerA, String _answerB, AnswerType _type) {
        super(_question);
        //TODO Auto-generated constructor stub
        answerA = _answerA;
        answerB = _answerB;
        type = _type;
    }

    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A - " + answerA, new Point(50, 525), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B - " + answerB, new Point(450, 525), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
    }
    
    private final String answerA;
    private final String answerB;
    private final AnswerType type;
}
