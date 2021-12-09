package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

/**
 * initialise l'animation d'un couteau qui va servir à couper la pomme
 * @author ALhouseeiny Diallo @ Lansana Dakite
 * @see Apple 
 *
 */
public class Knife {
	//Creation d'un sprite de couteau
	private Sprite sprite = new Sprite("Images/bigKnife.png");
	private Animation knifeAnimation;
	//Rectangle de decoupage
	private Rectangle rect = new Rectangle(0,0,704,64);
	private Rectangle moveRect = new Rectangle(100, 100, 100, 40);
	
	/**
	 * Creation d'une instance d'animation de knife à travers un rectangle et un sprite
	 * @see #Sprite
	 */
	public Knife() {
		knifeAnimation = new Animation(rect,7,sprite,0.1);
	}
	
	/**
	 * Déplace le couteau en même temps que la souris
	 */
	public void Update(){
        moveRect.x = CoreSystem.Mouse.GetInstance().GetMousePosX();
        moveRect.y = CoreSystem.Mouse.GetInstance().GetMousePosY();
    }
	
	 public void Draw() throws ProjectException{
	        knifeAnimation.Draw(moveRect);
	    }

}
