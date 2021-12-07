package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

public class CardBoard{ 
	
	  public CardBoard(Rectangle _destRect, Rectangle _destRep, boolean _bIsRight){
		    destRect = _destRect;
		    destRep = _destRep;
		    bIsRight = _bIsRight;
		    if (bIsRight) {
		    	srcRect = new Rectangle(destRect.x, destRect.y, 40, 64); 
		    	inside = new Animation(new Rectangle(0, 0, 64, 64), 6, money, 0.1f);
		    	inside2  = new Animation(new Rectangle(40, 0, 40, 64), 6, cardBoard, 0.1f);
		    	}
				else {
					srcRect = new Rectangle(destRect.x, destRect.y, 40, 64); 
					inside  = new Animation(new Rectangle(0, 0, 78, 86), 6, scorpion, 0.1f);
					inside2  = new Animation(new Rectangle(40, 0, 40, 64), 6, cardBoard, 0.1f);
		    	}
		  }
	  
		  public void Update(CoreSystem.Mouse.EventType e){
		    if (e == CoreSystem.Mouse.EventType.LRelease && destRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
		      bIsRevealed = true;
		    }
		  }
		  
		  public void Draw() throws ProjectException{ 
		    if (bIsRevealed) {
		    	inside2.Draw(srcRect);
		    	inside.Draw(srcRect);
		    }
		    else GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(cardBoard, destRect, srcCardF);
		  }

		  public boolean HasWon(){
		    return bIsRight && bIsRevealed;
		  }
		  
		  public boolean HasLost(){
		    return !bIsRight && bIsRevealed;
		  }

		  private Sprite money = new Sprite("Images/money.png");
		  private Sprite scorpion = new Sprite("Images/scorpion.png");
		  private Sprite cardBoard = new Sprite("Images/greenBoxStates.png");
		  private Rectangle srcCardF = new Rectangle(0, 0, 40, 64);
		  private Animation inside;
		  private Animation inside2;
		  private Rectangle destRect;
		  private Rectangle srcRect;
		  private Rectangle destRep;
		  private boolean bIsRight;
		  private boolean bIsRevealed = false;
}
