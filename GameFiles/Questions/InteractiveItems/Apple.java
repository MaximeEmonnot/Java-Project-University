package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

/**
 * Permet l'affichage d'une pomme ayant une couleur sur l'ecran du jeu.
 * Mini jeu de pommes
 * @author Alhousseiny Diallo
 * 
 *
 */
public class Apple {

	private Animation inside;
	private Animation afterInside;
	private Sprite spriteApple;
	private Rectangle rect;
	private Rectangle srcInside;
	private boolean bIsRight;
	private boolean bIsRevealed;
	
	/**
	 * 
	 * @param _rect le rectangle d'affichage
	 * @param _bIsRight determine si c'est la bonne pomme
	 * @param type pour trouver la couleur de la pomme
	 */
	public Apple(Rectangle _rect, boolean _bIsRight, int type) {
		//Création de la pomme
		spriteApple = new Sprite("Images/appleStates.png");
		switch(type){
		//Pomme rouge, jaune , ou verte
		case 1:
			inside = new Animation(new Rectangle(0,0,128,128),7,spriteApple,0.1);
		break;
		case 2:
			inside = new Animation(new Rectangle(128,0,128,128),7,spriteApple,0.1);
		break;
		case 3:
			inside = new Animation(new Rectangle(256,0,128,128),7,spriteApple,0.1);
		break;
    	     default:
			 break;
			}
		rect = _rect;
		bIsRight = _bIsRight;
		
		//Si c'est la bonne pomme
	    if(bIsRight) {
	    	srcInside = new Rectangle(0,128,128,128);
	    	afterInside = new Animation(srcInside,4,spriteApple,0.1f);
	    }
	    else {
	    	srcInside = new Rectangle(128,128,128,128);
	    	afterInside = new Animation(srcInside,4,spriteApple,0.1f);
	    }
	    
	    
	}
	
	/**
	 * affiche le contenu de la pomme si l'user clique dessus
	 * @param e position de la souris
	 */
	public void Update(CoreSystem.Mouse.EventType e){
		  
        if (e == CoreSystem.Mouse.EventType.LRelease && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
			bIsRevealed = true;  
        	}

        	if (bIsRevealed){  
				inside.Update();
        	}
	}
	
	/**
	 * Dessin de la pomme et de son contenu après clic de l'user.
	 * @throws ProjectException
	 */
	public void Draw() throws ProjectException {
		if (!bIsRevealed) {
			//GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(spriteApple, rect, 0);
			inside.Draw(rect);
		}
		  else afterInside.Draw(rect);
	}
	
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
    }
	public boolean HasLost(){
		return !bIsRight && bIsRevealed;
    }
}
