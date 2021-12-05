package GameFiles.Questions;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Cadeau;

/**
 * 
 * @author Lansana Diakite
 * Le mini jeu des cadeaux, en concret
 * Hérite de ADOubleAnswer
 * Mini jeu valable pour les quizzs à deux réponses
 * 
 *
 */
public class CadeauQuestion extends ADoubleAnswerQuestion {
	private Cadeau cadGauche;
	private Cadeau cadDroite;

	/**
	 * Initialise l'affichage des cadeaux
	 * @param _question
	 * @param _answerA
	 * @param _answerB
	 * @param _type
	 */
	public CadeauQuestion(String _question, String _answerA, String _answerB, AnswerType _type) {
		super(_question, _answerA, _answerB, _type);
		switch(_type) {
		case ANSWER_A:
			cadGauche = new Cadeau(new Rectangle(140, 150, 78, 78),true);
			cadDroite = new Cadeau(new Rectangle(500,150,78,78),false);
			break;
		case ANSWER_B:
			cadGauche = new Cadeau(new Rectangle(140, 150, 78, 78),false);
			cadDroite = new Cadeau(new Rectangle(500,150,78,78),true);
			break;
		case BOTH:
			cadGauche = new Cadeau(new Rectangle(140, 150, 78, 78),true);
			cadDroite = new Cadeau(new Rectangle(500,150,78,78),true);
			break;
		default:
			cadGauche = new Cadeau(new Rectangle(140, 150, 78, 78),false);
			cadDroite = new Cadeau(new Rectangle(500,150,78,78),false);
			break;
			
		}

	}
	
	public void Update() {
		super.Update();
		CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        cadGauche.Update(e);
        cadDroite.Update(e);
        bIsWon = (cadDroite.HasWon() || cadGauche.HasWon());
        bIsLost = (bIsLost || cadDroite.HasLost() || cadGauche.HasLost());
        
	}
	
	public void Draw() throws ProjectException {
        super.Draw();
        
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        cadGauche.Draw();
        cadDroite.Draw();
        
    }
	

}	
