package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.Card;

public class CardQuestion extends AQuadrupleAnswerQuestion{

	    public CardQuestion(String _question, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) {
	        super(_question, _anwserA, _answerB, _answerC, _answerD, _type);
	       
	        switch (_type){
	        case ANSWER_A:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64),false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64),false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64),false);
	            break;
	        case ANSWER_B:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64),false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64),false);
	            break;
	        case ANSWER_C:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64),true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_D:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_AB:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_AC:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_BC:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_BD:
                cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_CD:
	        	cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_ABC:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_ABD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), false);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_ACD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64),false);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	        case ANSWER_BCD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), false);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
	            break;
	        case ANSWER_ABCD:
	            cardA = new Card(new Rectangle(100, 150, 71, 96), new Rectangle(100, 150, 64, 64), true);
	            cardB = new Card(new Rectangle(275, 150, 71, 96), new Rectangle(275, 150, 64, 64), true);
	            cardC = new Card(new Rectangle(450, 150, 71, 96), new Rectangle(450, 150, 64, 64), true);
	            cardD = new Card(new Rectangle(625, 150, 71, 96), new Rectangle(625, 150, 64, 64), false);
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
	        cardA.Update(e);
	        cardB.Update(e);
	        cardC.Update(e);
	        cardD.Update(e);
	        }

	        bIsWon = (cardA.HasWon() || cardB.HasWon() || cardC.HasWon() || cardD.HasWon());
	        bIsLost = bIsLost || (cardA.HasLost() || cardB.HasLost() || cardC.HasLost() || cardD.HasLost());
	    }

	   
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