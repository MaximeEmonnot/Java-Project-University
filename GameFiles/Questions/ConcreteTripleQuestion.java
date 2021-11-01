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
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), false);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), false);
            break;
        case ANSWER_B:
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), false);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), false);
            break;
        case ANSWER_C:
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), false);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), false);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), true);
            break;
        case ANSWER_AB:
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), false);
            break;
        case ANSWER_AC:
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), false);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), true);
            break;
        case ANSWER_BC:
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), false);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), true);
            break;
        case ANSWER_ABC:
            eggA = new Egg(new Rectangle(140, 150, 78, 78), new Rectangle(140, 150, 78, 78), true);
            eggB = new Egg(new Rectangle(360, 150, 78, 78), new Rectangle(360, 150, 78, 78), true);
            eggC = new Egg(new Rectangle(580, 150, 78, 78), new Rectangle(580, 150, 78, 78), true);
            break;
        default:
            break;
        }
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        super.Update();
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        if (!bIsWon && !bIsLost){
            eggA.Update(e);
            eggB.Update(e);
            eggC.Update(e);
        }

        bIsWon = (eggA.HasWon() || eggB.HasWon() || eggC.HasWon());
        bIsLost = (bIsLost || eggA.HasLost() || eggB.HasLost() || eggC.HasLost());
    }
    @Override
    public void Draw() throws ProjectException {
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        //Drawing eggs
        eggA.Draw();
        eggB.Draw();
        eggC.Draw();

        //Drawing text
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A", new Point(175, 120), Color.BLACK);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B", new Point(395, 120), Color.BLACK);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C", new Point(612, 120), Color.BLACK);
    }

    private Egg eggA;
    private Egg eggB;
    private Egg eggC;
}