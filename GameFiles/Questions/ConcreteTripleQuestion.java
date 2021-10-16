package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Egg;

public class ConcreteTripleQuestion extends ATripleAnswerQuestion {

    public ConcreteTripleQuestion(String _question, String _answerA, String _answerB, String _answerC,
            AnswerType _type) {
        super(_question, _answerA, _answerB, _answerC, _type);
        //TODO Auto-generated constructor stub
        
        switch (_type){
        case ANSWER_A:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), false);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), false);
            break;
        case ANSWER_B:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), false);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), false);
            break;
        case ANSWER_C:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), false);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), false);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), true);
            break;
        case ANSWER_AB:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), false);
            break;
        case ANSWER_AC:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), false);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), true);
            break;
        case ANSWER_BC:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), false);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), true);
            break;
        case ANSWER_ABC:
            eggA = new Egg(new Rectangle(100, 150, 78, 78), new Rectangle(100, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(250, 150, 78, 78), new Rectangle(250, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(400, 150, 78, 78), new Rectangle(400, 150, 78, 78), true);
            break;
        default:
            break;
        }
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        if (!eggA.IsFinished() && !eggB.IsFinished() && !eggC.IsFinished()){
            eggA.Update(e);
            eggB.Update(e);
            eggC.Update(e);
        }
    }
    @Override
    public void Draw() throws ProjectException {
        super.Draw();
        eggA.Draw();
        eggB.Draw();
        eggC.Draw();
    }

    private Egg eggA;
    private Egg eggB;
    private Egg eggC;
}