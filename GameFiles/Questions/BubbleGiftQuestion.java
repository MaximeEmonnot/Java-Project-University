package GameFiles.Questions;

import java.awt.Color;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Gift;

/**
 * 
 * @author Lansana Diakite
 * Le mini jeu des cadeaux, en concret
 * H�rite de ADOubleAnswer
 * Mini jeu valable pour les quizzs � deux r�ponses
 * 
 *
 */
public class BubbleGiftQuestion extends ADoubleAnswerQuestion {
	private Gift cadGauche;
	private Gift cadDroite;

	/**
	 * Mini jeux des cadeaux, 02 r�ponses possibles.
	 * Initialise l'affichage des cadeaux.
	 * @param _question
	 * @param _answerA
	 * @param _answerB
	 * @param _type
	 */
	public BubbleGiftQuestion(String _question, float _timer, String _answerA, String _answerB, AnswerType _type) {
		super(_question, _timer, _answerA, _answerB, _type);
		//Determiner quel cadeau est le bon
		switch(_type) {
		case ANSWER_A:
			cadGauche = new Gift(new Rectangle(440, 150, 78, 78),true);
			cadDroite = new Gift(new Rectangle(740,150,78,78),false);
			break;
		case ANSWER_B:
			cadGauche = new Gift(new Rectangle(440, 150, 78, 78),false);
			cadDroite = new Gift(new Rectangle(740,150,78,78),true);
			break;
		case BOTH:
			cadGauche = new Gift(new Rectangle(440, 150, 78, 78),true);
			cadDroite = new Gift(new Rectangle(740,150,78,78),true);
			break;
		default:
			cadGauche = new Gift(new Rectangle(440, 150, 78, 78),false);
			cadDroite = new Gift(new Rectangle(740,150,78,78),false);
			break;
			
		}

	}
	
	/**
	 * Actualise l'affichage des cadeaux au fur et � mesure.
	 * Se base de la position de la souris
	 * @see CoreSystem#Mouse
	 */
	public void Update() {
		super.Update();
		CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        cadGauche.Update(e);
        cadDroite.Update(e);
        bIsWon = (cadDroite.HasWon() || cadGauche.HasWon());
        bIsLost = (bIsLost || cadDroite.HasLost() || cadGauche.HasLost());
        
	}
	
	/**
	 * Se charge d'afficher les diff�rents cadeaux, celui de gauche et de droite
	 */
	public void Draw() throws ProjectException {
        super.Draw();
        
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        cadGauche.Draw();
        cadDroite.Draw();
        
    }
	

}	
