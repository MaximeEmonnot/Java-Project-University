package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Questions.InteractiveItems.CardBoard;

public class CardBoardQuestion extends AQuadrupleAnswerQuestion{

	    public CardBoardQuestion(String _question, float _timer, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) {
	        super(_question, _timer, _anwserA, _answerB, _answerC, _answerD, _type);
	       
	        switch (_type){
	        case ANSWER_A:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(463, 190, 78, 86),false);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(739, 190, 78, 86),false);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86),false);
	            break;
	        case ANSWER_B:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86),false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(739, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86),false);
	            break;
	        case ANSWER_C:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(463, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64),true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86), false);
	            break;
	        case ANSWER_D:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(463, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(739, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
	            break;
	        case ANSWER_AB:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(739, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86), false);
	            break;
	        case ANSWER_AC:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(463, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86), false);
	            break;
	        case ANSWER_BC:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86), false);
	            break;
	        case ANSWER_BD:
                CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(739, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
	            break;
	        case ANSWER_CD:
	        	CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(463, 190, 78, 86), false);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
	            break;
	        case ANSWER_ABC:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1015, 190, 78, 86), false);
	            break;
	        case ANSWER_ABD:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(739, 190, 78, 86), false);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
	            break;
	        case ANSWER_ACD:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(463, 190, 78, 86),false);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
	        case ANSWER_BCD:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(187, 190, 78, 86), false);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
	            break;
	        case ANSWER_ABCD:
	            CardBoardA = new CardBoard(new Rectangle(176, 150, 100, 160), new Rectangle(194, 200, 64, 64), true);
	            CardBoardB = new CardBoard(new Rectangle(452, 150, 100, 160), new Rectangle(470, 200, 64, 64), true);
	            CardBoardC = new CardBoard(new Rectangle(728, 150, 100, 160), new Rectangle(746, 200, 64, 64), true);
	            CardBoardD = new CardBoard(new Rectangle(1004, 150, 100, 160), new Rectangle(1022, 200, 64, 64), true);
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
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A", new Point(225, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B", new Point(495, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C", new Point(770, 120), Color.BLACK);
	        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("D", new Point(1045, 120), Color.BLACK);
	    }
	    
	    private CardBoard CardBoardA;
	    private CardBoard CardBoardB;
	    private CardBoard CardBoardC;
	    private CardBoard CardBoardD;

}