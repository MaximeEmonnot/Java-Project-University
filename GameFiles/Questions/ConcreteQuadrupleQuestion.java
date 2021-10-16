package GameFiles.Questions;

import GameFiles.Questions.InteractiveItems.*;
import java.awt.*;

import Exceptions.ProjectException;

public class ConcreteQuadrupleQuestion extends AQuadrupleAnswerQuestion {

    public ConcreteQuadrupleQuestion(String _question, String _anwserA, String _answerB, String _answerC,
            String _answerD, AnswerType _type) {
        super(_question, _anwserA, _answerB, _answerC, _answerD, _type);
        //TODO Auto-generated constructor stub
        ls = new LightSwitch(new Rectangle(0, 0, 136, 200));
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        ls.Update();   
    }

    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        ls.Draw();
    }
    
    private LightSwitch ls;
}
