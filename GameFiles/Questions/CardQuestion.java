package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Card;

/**
 * Mini-jeu a quatre propositions de reponse
 * Quatre Cartes de dos representent les qautres possibilites, l'utilisateur doit cliquer sur la carte correspondante a la bonne reponse
 * @author Ahmad
 */
public class CardQuestion extends AQuadrupleAnswerQuestion{

	/**
     * Constructeur CardQuestion
     * Reprend le constructeur de AQuadrupleAnswerQuestion et initialise les differentes cartes pour la bonne reponse
     * @author Ahmad
     * @param _question Intitule question
     * @param _answerA Proposition A
     * @param _answerB Proposition B
     * @param _answerC Proposition C
     * @param _answerD Proposition D
     * @param _type Reponse correcte
     */
	    public CardQuestion(String _question, float _timer, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) {
	        super(_question, _timer, _anwserA, _answerB, _answerC, _answerD, _type);
	       
	        switch (_type){
	        case ANSWER_A:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64),false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64),false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64),false);
	            break;
	        case ANSWER_B:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64),false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64),false);
	            break;
	        case ANSWER_C:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64),true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), false);
	            break;
	        case ANSWER_D:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	            break;
	        case ANSWER_AB:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), false);
	            break;
	        case ANSWER_AC:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), false);
	            break;
	        case ANSWER_BC:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), false);
	            break;
	        case ANSWER_BD:
                cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	            break;
	        case ANSWER_CD:
	        	cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	            break;
	        case ANSWER_ABC:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), false);
	            break;
	        case ANSWER_ABD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	            break;
	        case ANSWER_ACD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64),false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	        case ANSWER_BCD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	            break;
	        case ANSWER_ABCD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(115, 200, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(290, 200, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(465, 200, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(640, 200, 64, 64), true);
	            break;
	        default:
	            break;
	        }
	      

	    }

	    /**
	     * {@inheritDoc}
	     * Mise a jour du mini-jeu
	     * Mise a jour des etats de la carte, ainsi que des etats de d'echec et de succes
	     * @author Ahmad
	     */
	    @Override
	    public void Update() {
	        // TODO Auto-generated method stub
	        super.Update();
	        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
	        
	        if (!bIsWon && !bIsLost){   
	        cardA.Update(e);
	        cardB.Update(e);
	        cardC.Update(e);
	        cardD.Update(e);
	        }

	        bIsWon = (cardA.HasWon() || cardB.HasWon() || cardC.HasWon() || cardD.HasWon());
	        bIsLost = bIsLost || (cardA.HasLost() || cardB.HasLost() || cardC.HasLost() || cardD.HasLost());
	    }

	    /**
	     * {@inheritDoc}
	     * Affichage des differentes cartes et du text A,B,C et D correspondant a chaque cartes
	     * @author Ahmad
	     */
	    @Override
	    public void Draw() throws ProjectException{
	        super.Draw();
	        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
		      //Drawing cards
	        cardA.Draw();
	        cardB.Draw();
	        cardC.Draw();
	        cardD.Draw();

	      //Drawing text
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A", new Point(130, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B", new Point(305, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C", new Point(480, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("D", new Point(655, 120), Color.BLACK);
	    }
	    
	    private Card cardA;
	    private Card cardB;
	    private Card cardC;
	    private Card cardD;

}