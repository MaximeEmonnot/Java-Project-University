package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.CardBoard;

public class CardBoardQuestion extends AQuadrupleAnswerQuestion{

	    public CardBoardQuestion(String _question, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) {
	        super(_question, _anwserA, _answerB, _answerC, _answerD, _type);
	       
	        switch (_type){
	        case ANSWER_A:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 190, 78, 86),false);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 190, 78, 86),false);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86),false);
	            break;
	        case ANSWER_B:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86),false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86),false);
	            break;
	        case ANSWER_C:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64),true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86), false);
	            break;
	        case ANSWER_D:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
	            break;
	        case ANSWER_AB:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86), false);
	            break;
	        case ANSWER_AC:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86), false);
	            break;
	        case ANSWER_BC:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86), false);
	            break;
	        case ANSWER_BD:
                CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
	            break;
	        case ANSWER_CD:
	        	CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
	            break;
	        case ANSWER_ABC:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 190, 78, 86), false);
	            break;
	        case ANSWER_ABD:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
	            break;
	        case ANSWER_ACD:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 190, 78, 86),false);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
	        case ANSWER_BCD:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
	            break;
	        case ANSWER_ABCD:
	            CardBoardA = new CardBoard(new Rectangle(100, 150, 100, 160), new Rectangle(115, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(275, 150, 100, 160), new Rectangle(290, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(450, 150, 100, 160), new Rectangle(465, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(625, 150, 100, 160), new Rectangle(625, 200, 64, 64), true);
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
	        if (bIsWon || bIsLost) e = CoreSystem.Mouse.EventType.None; 

	        CardBoardA.Update(e);
	        CardBoardB.Update(e);
	        CardBoardC.Update(e);
	        CardBoardD.Update(e);

	        bIsWon = (CardBoardA.HasWon() || CardBoardB.HasWon() || CardBoardC.HasWon() || CardBoardD.HasWon());
	        bIsLost = bIsLost || (CardBoardA.HasLost() || CardBoardB.HasLost() || CardBoardC.HasLost() || CardBoardD.HasLost());
	    }

	    @Override
	    public void Draw() throws ProjectException{
	        super.Draw();
	        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
		      //Drawing CardBoards
	        CardBoardA.Draw();
	        CardBoardB.Draw();
	        CardBoardC.Draw();
	        CardBoardD.Draw();

	      //Drawing text
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A", new Point(130, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B", new Point(305, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C", new Point(480, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("D", new Point(655, 120), Color.BLACK);
	    }
	    
	    private CardBoard CardBoardA;
	    private CardBoard CardBoardB;
	    private CardBoard CardBoardC;
	    private CardBoard CardBoardD;

}