package GameFiles.Questions;

import java.awt.Color;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Apple;
import GameFiles.Questions.InteractiveItems.Knife;
import GameFiles.Questions.InteractiveItems.Apple.AppleType;

/**
 * Mini jeu des pommes.
 * <p>Quizz a trois reponses possibles
 * @author Alhousseiny Diallo 
 * @author Lansana Diakite
 * @version 1.4.0
 */
public class AppleQuestion extends ATripleAnswerQuestion {

	/**
	 * Constructeur AppleQuestion
	 * <p>Reprend le construceur de ATripleAnswerQuestion et initialise les pommes
	 * @author Alhousseiny Diallo 
 	 * @author Lansana Diakite
	 * @param _question Intitule question
	 * @param _timer Temps necessaire pour repondre
	 * @param _answerA Proposition A
	 * @param _answerB Proposition B
	 * @param _answerC Proposition C
	 * @param _type Reponse correcte
	 */
	public AppleQuestion(String _question, float _timer, String _answerA, String _answerB, String _answerC, AnswerType _type) {
		super(_question, _timer, _answerA, _answerB, _answerC, _type);
		switch(_type) {
		case ANSWER_A:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), true, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), false, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), false, AppleType.GREEN);
			break;
		case ANSWER_B:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), false, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), true, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), false, AppleType.GREEN);
			break;
		case ANSWER_C:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), false, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), false, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), true, AppleType.GREEN);
			break;
		case ANSWER_AB:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), true, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), true, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), false, AppleType.GREEN);
			break;
		case ANSWER_AC:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), true, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), false, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), true, AppleType.GREEN);
			break;
		case ANSWER_BC:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), false, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), true, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), true, AppleType.GREEN);
			break;
		case ANSWER_ABC:
			appleA = new Apple(new Rectangle(280, 150, 78, 78), true, AppleType.RED);
			appleB = new Apple(new Rectangle(600, 150, 78, 78), true, AppleType.YELLOW);
			appleC = new Apple(new Rectangle(920, 150, 78, 78), true, AppleType.GREEN);
			break;
		default:
			break;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * <p>Mise � jour des diff�rents affichages
	 * @author Alhousseiny Diallo 
 	 * @author Lansana Diakite
	 */
	@Override
	public void Update() {
		// TODO Auto-generated method stub
        super.Update();
		
		knife.Update();
        
		appleA.Update(knife.GetCuttingRectangle());
		appleB.Update(knife.GetCuttingRectangle());
		appleC.Update(knife.GetCuttingRectangle());
		
        bIsWon = (appleA.HasWon() || appleB.HasWon() || appleC.HasWon());
        bIsLost = (bIsLost || appleA.HasLost() || appleB.HasLost() || appleC.HasLost());
    }
	
	/**
	 * {@inheritDoc}
	 * <p>S'occupe des diff�rents affichages
	 * @author Alhousseiny Diallo 
 	 * @author Lansana Diakite
	 * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
	 */
	@Override
	public void Draw() throws ProjectException {
		super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        appleA.Draw();
        appleB.Draw();
        appleC.Draw();
        knife.Draw();
	}

	private Apple appleA;
	private Apple appleB;
	private Apple appleC;
	private Knife knife = new Knife(new Rectangle(950, 450, 256, 64));
}	
