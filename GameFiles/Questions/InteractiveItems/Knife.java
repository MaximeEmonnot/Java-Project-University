package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

/**
 * Couteau utilise dans AppleQuestion
 * <p>Permet de couper les pommes du mini-jeux
 * @author ALhousseiny Diallo 
 * @author Lansana Diakite
 * @see AppleQuestion
 * @version 1.4.0
 */
public class Knife {
	/**
	 * Constructeur de knife
	 * @author ALhousseiny Diallo 
 	 * @author Lansana Diakite
	 * @param _startingRect rectangle de depart du couteau
	 */
	public Knife(Rectangle _startingRect) {
		startingRect = _startingRect;
		destRect = new Rectangle(_startingRect);
	}
	
	/**
	 * Si l'on clique sur le couteau, il se deplace en meme temps que la souris, deplacant ainsi la zone de decoupe du couteau
	 * @author ALhousseiny Diallo 
 	 * @author Lansana Diakite
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
			bIsHold = false;
		}
    }
	
	/**
	 * Affichage du coutau
	 * @author ALhousseiny Diallo 
 	 * @author Lansana Diakite
	 * @throws ProjectException Erreur lors de l'initialisation de GraphicsSystem
	 */
	public void Draw() throws ProjectException{
		GraphicsSystem.GetInstance().DrawSprite(sprite, destRect);
	}

	/**
	 * Retourne le rectangle de decoupe du couteau. Utilise dans la classe Apple
	 * @author ALhousseiny Diallo 
 	 * @author Lansana Diakite
	 * @return Rectangle de decoupe
	 * @see Apple#Update(Rectangle)
	 */
	public Rectangle GetCuttingRectangle(){
		return cuttingRectangle;
	}

	//Creation d'un sprite de couteau
	private Sprite sprite = new Sprite("Images/knife.png");
	//Rectangle de decoupage
	private final Rectangle startingRect;
	private Rectangle cuttingRectangle = new Rectangle(0, 0, 85, 16);
	private Rectangle destRect;
	private boolean bIsHold = false;
}

