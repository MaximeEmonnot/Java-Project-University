package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

/**
 * initialise l'animation d'un couteau qui va servir � couper la pomme
 * @author ALhouseeiny Diallo @ Lansana Dakite
 * @see Apple 
 *
 */
public class Knife {
	/**
	 * Creation d'une instance d'animation de knife � travers un rectangle et un sprite
	 * @see #Sprite
	 */
	public Knife(Rectangle _startingRect) {
		startingRect = _startingRect;
		destRect = startingRect;
	}
	
	/**
	 * D�place le couteau en m�me temps que la souris
	 */
	public void Update(){
		if (CoreSystem.Mouse.GetInstance().LeftIsPressed()){
			if (!bIsHold){
				if (startingRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
					bIsHold = true;
				}
			}
			else{
				destRect.x = CoreSystem.Mouse.GetInstance().GetMousePosX() - 182;
				destRect.y = CoreSystem.Mouse.GetInstance().GetMousePosY() - 24;
				cuttingRectangle.x = destRect.x;
				cuttingRectangle.y = destRect.y + 36;
			}
		}
		else{
			destRect = startingRect;
		}
    }
	
	public void Draw() throws ProjectException{
		GraphicsSystem.GetInstance().DrawSprite(sprite, destRect);
	}

	public Rectangle GetCuttingRectangle(){
		return cuttingRectangle;
	}

	//Creation d'un sprite de couteau
	private Sprite sprite = new Sprite("Images/bigKnife.png");
	//Rectangle de decoupage
	private final Rectangle startingRect;
	private Rectangle cuttingRectangle = new Rectangle(0, 0, 85, 16);
	private Rectangle destRect;
	private boolean bIsHold = false;
}

