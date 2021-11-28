package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

public class Boite {
	private Sprite sprite;
	private Animation inside;
	private final Rectangle rect;
	private final Rectangle insideRect;
	private final boolean bIsRight;
	private boolean bIsRevealed = false;
	    public Boite(Rectangle _rect, boolean _bIsRight, int type){
	    	   rect = _rect;
	    	   bIsRight = _bIsRight;
	    	   switch(type){
	    	     case 1:
	    	      sprite = new Sprite("Images/mysteryBox.png");
	    	      break;
	    	     case 2:
	    	      sprite = new Sprite("Images/mysteryBox_Blue.png");
	    	      break;
	    	     case 3:
	    	      sprite = new Sprite("Images/mysteryBox_Red.png");
	    	      break;
	    	     default:
	    	      break;
	    	   }
	    	   if (bIsRight){
	    	      inside = new Animation(new Rectangle(0, 0, 31, 32), 4, new Sprite("Images/cake.png"), 0.1f);
	    	      insideRect = new Rectangle(rect.x, rect.y, 60, 64);
	    	   }
	    	   else{
	    	      inside = new Animation(new Rectangle(0, 0, 32, 64), 14, new Sprite("Images/bomb.png"), 0.1f);
	    	      insideRect = new Rectangle(rect.x, rect.y, 64, 80);
	    	   }
	    	}
  public void Update(CoreSystem.Mouse.EventType e){
	      
	        if (e == CoreSystem.Mouse.EventType.LRelease && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
	        	  bIsRevealed = true;  
	        	}

	        	if (bIsRevealed){  
	        	 inside.Update();
	        	}
    }

	  public void Draw() throws ProjectException{
		  if (!bIsRevealed) GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(sprite, rect, 0);
		  else inside.Draw(insideRect);
	       
	    }
	  public boolean HasWon(){
		  return bIsRight && bIsRevealed;
	    }
	  public boolean HasLost(){
	        return !bIsRight && bIsRevealed;
	    }
	  

}
