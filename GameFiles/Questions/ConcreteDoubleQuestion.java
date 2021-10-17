package GameFiles.Questions;

import java.awt.Color;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.*;

public class ConcreteDoubleQuestion extends ADoubleAnswerQuestion {

    public ConcreteDoubleQuestion(String _question, String _answerA, String _answerB, AnswerType _type) throws ProjectException {
        super(_question, _answerA, _answerB, _type);
        //TODO Auto-generated constructor stub
        switch (_type){
            case ANSWER_A:
            nose = new Nose(1);
            break;
            case ANSWER_B:
            nose = new Nose(2);
            break;
            case BOTH:
            nose = new Nose(3);
            break;
            default:
            break;
        }
    }
    
    @Override
    public void Update() {
        // TODO Auto-generated method stub
        nose.Update();
        finger.Update();
        bIsWon = nose.HasWon();
        bIsLost = nose.HasLost();
    }
    
    @Override 
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        nose.Draw();
        finger.Draw();
    }
    
    private Nose nose;
    private Finger finger = new Finger();
}
