package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

public class CardBoard{ 
	
	  public CardBoard(Rectangle _destRect, Rectangle _destRep, boolean _bIsRight){
		    destRect = _destRect;
		    destRep = _destRep;
		    bIsRight = _bIsRight;
		  }
	  
		  public void Update(CoreSystem.Mouse.EventType e){
		    if (e == CoreSystem.Mouse.EventType.LRelease && destRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
		      bIsRevealed = true;
		    }
			if (bIsRevealed){
				scorpion.Update();
			}
		  }
		  
		  public void Draw() throws ProjectException{ 
		    if (bIsRevealed) {
				if (bIsRight) GraphicsSystem.GetInstance().DrawSprite(money, destRep, new Rectangle(0, 0, 64, 64), 1);
		    	else scorpion.Draw(destRep, 1);
				GraphicsSystem.GetInstance().DrawSprite(cardBoard, destRect, new Rectangle(40, 0, 40, 64));
		    }
		    else GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(cardBoard, destRect, new Rectangle(0, 0, 40, 64));
		  }

		  public boolean HasWon(){
		    return bIsRight && bIsRevealed;
		  }
		  
		  public boolean HasLost(){
		    return !bIsRight && bIsRevealed;
		  }

		  private Sprite cardBoard = new Sprite("Images/greenBoxStates.png");
		  private Sprite money = new Sprite("Images/money.png");
		  private Animation scorpion = new Animation(new Rectangle(0, 0, 78, 86), 6, new Sprite("Images/scorpion.png"), 0.1f);
		  private Rectangle destRect;
		  private Rectangle destRep;
		  private boolean bIsRight;
		  private boolean bIsRevealed = false;
}
