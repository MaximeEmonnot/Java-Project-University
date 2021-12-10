package GameFiles.Questions;

import java.awt.Color;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Gift;

/**
 * Le mini jeu des cadeaux, en concret
 * <p>Herite de ADOubleAnswer
 * <p>Mini jeu valable pour les quizzs � deux r�ponses
 * @author Lansana Diakite
 * @version 1.4.0
 */
public class BubbleGiftQuestion extends ADoubleAnswerQuestion {

	/**
	 * Mini jeux des cadeaux, 02 r�ponses possibles.
	 * <p> Initialise l'affichage des cadeaux.
	 * @author Lansana Diakite
	 * @param _question Intitule question
	 * @param _timer Temps necessaire pour repondre
	 * @param _answerA Proposition A
	 * @param _answerB Proposition B
	 * @param _type Reponse correcte
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
	 * {@inheritDoc}
	 * <p>Actualise l'affichage des cadeaux au fur et � mesure.
	 * @author Lansana Diakite
	 */
	@Override
	public void Update() {
		super.Update();
		CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        cadGauche.Update(e);
        cadDroite.Update(e);

        bIsWon = (cadDroite.HasWon() || cadGauche.HasWon());
        bIsLost = (bIsLost || cadDroite.HasLost() || cadGauche.HasLost());  
	}
	
	/**
	 * {@inheritDoc}
	 * <p>Se charge d'afficher les diff�rents cadeaux, celui de gauche et de droite. 
	 * <p>Affiche egalement les bulles apres selection du cadeau
	 * @author Lansana Diakite
	 */
	@Override
	public void Draw() throws ProjectException {
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        cadGauche.Draw();
        cadDroite.Draw(); 
    }
	
	private Gift cadGauche;
	private Gift cadDroite;
}	
