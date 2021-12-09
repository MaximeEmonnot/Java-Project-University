package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import java.util.Queue;
import java.util.LinkedList;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

/**
 * Permet l'affichage d'une pomme ayant une couleur sur l'ecran du jeu.
 * Mini jeu de pommes
 * @author Alhousseiny Diallo
 */
public class Apple {
	
	public enum AppleType{
		RED,
		YELLOW,
		GREEN
	}

	/**
	 * 
	 * @param _rect le rectangle d'affichage
	 * @param _bIsRight determine si c'est la bonne pomme
	 * @param type pour trouver la couleur de la pomme
	 */
	public Apple(Rectangle _rect, boolean _bIsRight, AppleType type) {
		//Cr�ation de la pomme
		destRectApple = _rect;
		destRectInside = new Rectangle(_rect);
		bIsRight = _bIsRight;

		srcInside = (bIsRight) ? new Rectangle(0, 128, 128, 128) : new Rectangle(128, 128, 128, 128);

		switch(type){
		case RED:
			srcApple = new Rectangle(0, 0, 128, 128);
			break;
		case YELLOW:
			srcApple = new Rectangle(128, 0, 128, 128);
			break;
		case GREEN:
			srcApple = new Rectangle(256, 0, 128, 128);
			break;
		default:
			break;
		}

		points.add(new Point(destRectApple.x + (destRectApple.width / 2), destRectApple.y));
		points.add(new Point(destRectApple.x + (destRectApple.width / 2), destRectApple.y + destRectApple.height));
	}
	
	
	public void Update(Rectangle knifeRectangle){
		if (points.isEmpty()){
			destRectApple.y += 2;
		}
		else{
			if (knifeRectangle.contains(points.peek())){
				points.poll();
			}
		}
	}
	
	public void Draw() throws ProjectException {
		GraphicsSystem.GetInstance().DrawSprite(spriteApple, destRectInside, srcInside);
		GraphicsSystem.GetInstance().DrawSprite(spriteApple, destRectApple, srcApple, 1);
	}
		
	public boolean HasWon(){
		return bIsRight && points.isEmpty();
	}
	public boolean HasLost(){
		return !bIsRight && points.isEmpty();
	}
		
	private final static Sprite spriteApple = new Sprite("Images/appleStates.png");
	private final boolean bIsRight;
	private Queue<Point> points = new LinkedList<Point>();
	private Rectangle destRectApple;
	private Rectangle destRectInside;
	private Rectangle srcApple;
	private Rectangle srcInside;
}
	