package GameFiles.Questions;

import java.awt.*;
import Exceptions.ProjectException;

public abstract class ATripleAnswerQuestion extends AQuestion{

    public enum AnswerType{
        ANSWER_A,
        ANSWER_B,
        ANSWER_C,
        ANSWER_AB,
        ANSWER_AC,
        ANSWER_BC,
        ANSWER_ABC
    }

    public ATripleAnswerQuestion(String _question, String _answerA, String _answerB, String _answerC, AnswerType _type) {
        super(_question);
        //TODO Auto-generated constructor stub
        answerA = _answerA;
        answerB = _answerB;
        answerC = _answerC;
        type = _type;
    }

    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A - " + answerA, new Point(50, 525), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B - " + answerB, new Point(450, 525), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C - " + answerC, new Point(300, 545), new Font("Arial Bold", Font.BOLD, 16), Color.BLACK, 16);
    }
    
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final AnswerType type;
}
