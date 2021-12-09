package GameFiles.Questions;

import java.awt.Color;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Apple;
import GameFiles.Questions.InteractiveItems.Knife;

/**
 * Mini jeu des pommes.
 * Quizz à trois réponses possibles
 * @author Alhousseiny Diallo @ Lansana DIakite
 *
 */
public class AppleQuestion extends ATripleAnswerQuestion {
	
	private Apple appleA;
	private Apple appleB;
	private Apple appleC;
	private Knife knife = new Knife();
	

	/**
	 * 
	 * @param _question
	 * @param _answerA
	 * @param _answerB
	 * @param _answerC
	 * @param _type
	 */
	public AppleQuestion(String _question, String _answerA, String _answerB, String _answerC, AnswerType _type) {
		super(_question, _answerA, _answerB, _answerC, _type);
		switch(_type) {
			case ANSWER_A:
		        appleA = new Apple(new Rectangle(140, 150, 78, 78), true,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),false,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),false,3);
		        break;
		    case ANSWER_B:
		    	appleA = new Apple(new Rectangle(140, 150, 78, 78), false,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),true,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),false,3);
		        break;
		    case ANSWER_C:
		    	appleA = new Apple(new Rectangle(140, 150, 78, 78), false,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),false,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),true,3);
		        break;
		    case ANSWER_AB:
		    	appleA = new Apple(new Rectangle(140, 150, 78, 78), true,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),true,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),false,3);
		        break;
		    case ANSWER_AC:
		    	appleA = new Apple(new Rectangle(140, 150, 78, 78), true,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),false,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),true,3);
		        break;
		    case ANSWER_BC:
		    	appleA = new Apple(new Rectangle(140, 150, 78, 78), false,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),true,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),true,3);
		        break;
		    case ANSWER_ABC:
		    	appleA = new Apple(new Rectangle(140, 150, 78, 78), true,1);
		        appleB = new Apple(new Rectangle(360, 150, 78, 78),true,2);
		        appleC = new Apple(new Rectangle(580, 150, 78, 78),true,3);
		        break;
		    default:
		        break;
		    }
		
		
	}
	
	/**
	 * Mise à jour des différents affichages
	 */
	public void Update() {
        // TODO Auto-generated method stub
        super.Update();
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        
            appleA.Update(e);
            appleB.Update(e);
            appleC.Update(e);
            knife.Update();
        

        bIsWon = (appleA.HasWon() || appleB.HasWon() || appleC.HasWon());
        bIsLost = (bIsLost || appleA.HasLost() || appleB.HasLost() || appleC.HasLost());
    }
	
	/**
	 * S'occupe des différents affichages
	 */
	public void Draw() throws ProjectException {
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        appleA.Draw();
        appleB.Draw();
        appleC.Draw();
        knife.Draw();
	}

}
