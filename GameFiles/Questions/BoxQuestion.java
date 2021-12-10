package GameFiles.Questions;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Box;

/**
 * Mini-jeu a trois propositions de reponse
 * <p>Trois boites representent les trois possibilites, l'utilisateur doit cliquer sur la boite correspondant a la bonne reponse
 * @author Mamadou Cire Camara
 * @version 1.4.0
 */
public class BoxQuestion extends ATripleAnswerQuestion {
    /**
     * Constructeur BoxQuestion
     * <p>Reprend le constructeur de ATripleAnswerQuestion et initialise les differentes boites
     * @author Mamadou Cire Camara
     * @param _question Intitule question
     * @param _timer Temps necessaire pour repondre
     * @param _answerA Proposition A
     * @param _answerB Proposition B
     * @param _answerC Proposition C
     * @param _type Reponse correcte
     */
	public BoxQuestion(String _question, float _timer, String _answerA, String _answerB, String _answerC, AnswerType _type) {
		super(_question, _timer, _answerA, _answerB, _answerC, _type);
		// TODO Auto-generated constructor stub
		switch (_type){
		case ANSWER_A:
			boiteA = new Box(new Rectangle(280, 150, 78, 78), true,1);
			boiteB = new Box(new Rectangle(600, 150, 78, 78),false,2);
			boiteC = new Box(new Rectangle(920, 150, 78, 78),false,3);
			break;
        case ANSWER_B:
			boiteA = new Box(new Rectangle(280, 150, 78, 78),false,1);
            boiteB = new Box(new Rectangle(600, 150, 78, 78), true,2);
            boiteC = new Box(new Rectangle(920, 150, 78, 78),false,3);
            break;
		case ANSWER_C:
            boiteA = new Box(new Rectangle(280, 150, 78, 78),false,1);
            boiteB = new Box(new Rectangle(600, 150, 78, 78),false,2);
            boiteC = new Box(new Rectangle(920, 150, 78, 78),true,3);
            break;
		case ANSWER_AB:
            boiteA = new Box(new Rectangle(280, 150, 78, 78),true,1);
            boiteB = new Box(new Rectangle(600, 150, 78, 78),true,2);
            boiteC = new Box(new Rectangle(920, 150, 78, 78),false,3);
            break;
		case ANSWER_AC:
            boiteA = new Box(new Rectangle(280, 150, 78, 78),true,1);
            boiteB = new Box(new Rectangle(600, 150, 78, 78),false,2);
            boiteC = new Box(new Rectangle(920, 150, 78, 78),true,3);
            break;
		case ANSWER_BC:
            boiteA = new Box(new Rectangle(280, 150, 78, 78),false,1);
            boiteB = new Box(new Rectangle(600, 150, 78, 78),true,2);
            boiteC = new Box(new Rectangle(920, 150, 78, 78),true,3);
            break;
		case ANSWER_ABC:
            boiteA = new Box(new Rectangle(280, 150, 78, 78),true,1);
            boiteB = new Box(new Rectangle(600, 150, 78, 78),true,2);
            boiteC = new Box(new Rectangle(920, 150, 78, 78),true,3);
            break;
		default:
            break;
        }
	}

    /**
     * {@inheritDoc}
     * <p> Mise a jour des boites
     * @author Mamadou Cire Camara
     */
    @Override
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
    
    /**
     * {@inheritDoc}
     * <p>Affichage des boites
     * @author Mamadou Cire Camara
     */
    @Override
    public void Draw() throws ProjectException {
	    super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
        //Drawing eggs
        boiteA.Draw();
        boiteB.Draw();
        boiteC.Draw();
		
        //Drawing text
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A", new Point(312, 120), Color.BLACK);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B", new Point(632, 120), Color.BLACK);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C", new Point(952, 120), Color.BLACK);
    }

	private Box boiteA;
	private Box boiteB;
	private Box boiteC;
}