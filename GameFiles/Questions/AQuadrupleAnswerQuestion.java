package GameFiles.Questions;

import java.awt.*;
import Exceptions.ProjectException;

public abstract class AQuadrupleAnswerQuestion extends AQuestion {

    public enum AnswerType{
        ANSWER_A,
        ANSWER_B,
        ANSWER_C,
        ANSWER_D,
        ANSWER_AB,
        ANSWER_AC,
        ANSWER_AD,
        ANSWER_BC,
        ANSWER_BD,
        ANSWER_CD,
        ANSWER_ABC,
        ANSWER_ABD,
        ANSWER_ACD,
        ANSWER_BCD,
        ANSWER_ABCD,
        NONE
    }

    public AQuadrupleAnswerQuestion(String _question, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) {
        super(_question);
        //TODO Auto-generated constructor stub
        answerA = _anwserA;
        answerB = _answerB;
        answerC = _answerC;
        answerD = _answerD;
        type = _type;
    }

    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A - " + answerA, new Point(50, 525), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B - " + answerB, new Point(450, 525), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C - " + answerC, new Point(50, 545), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("D - " + answerD, new Point(450, 545), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
    }
    
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final String answerD;
    private final AnswerType type;
}
