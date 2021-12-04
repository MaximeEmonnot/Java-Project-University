package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Sprite;

public class Card{ 
	  public Card(Rectangle _destRect, Rectangle _destCross, boolean _bIsRight){
		    destRect = _destRect;
		    destCross = _destCross;
		    bIsRight = _bIsRight;
		    
		    int value = (int)(Math.random() * 13);
		    int color = 1;
		    if (!bIsRight){
		      int[] roulette = {0, 2, 3};
		      color = roulette[(int)(Math.random() * 3)];
		    }
		    srcRectValue = new Rectangle(0 + value * 71, 0 + color * 96, 71, 96);
		    if (bIsRight) {
		    	srcCross = new Rectangle(0, 0,64, 64);
		    	}
				else { 
		    	srcCross = new Rectangle(64, 0, 64, 64);
		    	}
		  }

		  public void Update(CoreSystem.Mouse.EventType e){
		    if (e == CoreSystem.Mouse.EventType.LRelease && destRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
		      bIsRevealed = true;
		    }
		  }

		  public void Draw() throws ProjectException{ 
		    if (bIsRevealed) {
		      GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(card, destRect, srcRectValue);
		      GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(cross, destCross, srcCross);
		    }
		    else GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(card, destRect, srcRectBack);
		  }

		  public boolean HasWon(){
		    return bIsRight && bIsRevealed;
		  }
		  public boolean HasLost(){
		    return !bIsRight && bIsRevealed;
		  }

		  private static Sprite card = new Sprite("Images/cards.png");
		  private static Sprite cross = new Sprite("Images/rightWrong.png");
		  private Rectangle srcRectBack = new Rectangle(142, 384, 71, 96);
		  private Rectangle srcRectValue;
		  private Rectangle destRect;
		  private Rectangle srcCross;
		  private Rectangle destCross;
		  private boolean bIsRight;
		  private boolean bIsRevealed = false;
}
