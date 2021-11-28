package GameFiles.Questions;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Boite;

public class BoiteQuestion extends ATripleAnswerQuestion {
    private Boite boiteA;
    private Boite boiteB;
    private Boite boiteC;
	public BoiteQuestion(String _question, String _answerA, String _answerB, String _answerC, AnswerType _type) {
		super(_question, _answerA, _answerB, _answerC, _type);
		// TODO Auto-generated constructor stub
		 switch (_type){
	        case ANSWER_A:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78), true,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78),false,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),false,3);
	            break;
	        case ANSWER_B:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78),false,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78), true,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),false,3);
	            break;
	        case ANSWER_C:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78),false,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78),false,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),true,3);
	            break;
	        case ANSWER_AB:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78),true,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78),true,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),false,3);
	            break;
	        case ANSWER_AC:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78),true,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78),false,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),true,3);
	            break;
	        case ANSWER_BC:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78),false,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78),true,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),true,3);
	            break;
	        case ANSWER_ABC:
	            boiteA = new Boite(new Rectangle(140, 150, 78, 78),true,1);
	            boiteB = new Boite(new Rectangle(360, 150, 78, 78),true,2);
	            boiteC = new Boite(new Rectangle(580, 150, 78, 78),true,3);
	            break;
	        default:
	            break;
	        }
	}
	public void Update() {
        // TODO Auto-generated method stub
        super.Update();
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        
            boiteA.Update(e);
            boiteB.Update(e);
            boiteC.Update(e);
        

        bIsWon = (boiteA.HasWon() || boiteB.HasWon() || boiteC.HasWon());
        bIsLost = (bIsLost || boiteA.HasLost() || boiteB.HasLost() || boiteC.HasLost());
    }
	  public void Draw() throws ProjectException {
	        super.Draw();
	        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
	        //Drawing eggs
	        boiteA.Draw();
	        boiteB.Draw();
	        boiteC.Draw();

	        //Drawing text
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A", new Point(175, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B", new Point(395, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C", new Point(612, 120), Color.BLACK);
	    }
}
