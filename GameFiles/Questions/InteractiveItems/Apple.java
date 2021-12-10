package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import java.util.Queue;
import java.util.LinkedList;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

/**
 * Pomme utiliee dans AppleQuestion
 * <p>Possede deux etats : entiere et coupee.
 * <p>Deux sous etats pour la reponse : normale et pourrie
 * @author Alhousseiny Diallo
 * @see AppleQuestion
 * @version 1.4.0
 */
public class Apple {
	
	/**
	 * Type de pomme.
	 * <p>Utilise pour initialiser la couleur
	 * @author Alhousseiny Diallo
	 */
	public enum AppleType{
		RED,
		YELLOW,
		GREEN
	}

	/**
	 * Constructeur Apple
	 * <p>Initialise la position de la pomme, sa validite et sa couleur
	 * @author Alhousseiny Diallo
	 * @param _rect le rectangle d'affichage
	 * @param _bIsRight definit si c'est la bonne pomme
	 * @param type pour definir la couleur de la pomme
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
	
	/**
	 * Met a jour la pomme en fonction du rectangle de coupe du couteau.
	 * <p>Animation de chute de la moitie de la pomme si elle est coupee
	 * @author Alhousseiny Diallo
	 * @param knifeRectangle Rectangle de decoupe du couteau
	 */
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
	
	/**
	 * Affichage de la pomme
	 * @author Alhousseiny Diallo
	 * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
	 */
	public void Draw() throws ProjectException {
		if (points.isEmpty()) GraphicsSystem.GetInstance().DrawSprite(spriteApple, destRectInside, srcInside);
		GraphicsSystem.GetInstance().DrawSprite(spriteApple, destRectApple, srcApple, 1);
	}
		
	/**
     * Recuperation de l'etat de succes
	 * @author Alhousseiny Diallo
     * @return Vrai si la reponse choisie est correcte et que la pomme est coupee, Faux sinon
     */
	public boolean HasWon(){
		return bIsRight && points.isEmpty();
	}
	/**
     * Recuperation de l'etat d'echec
	 * @author Alhousseiny Diallo
     * @return Vrai si la reponse choisie est fausse et que la pomme est coupee, Faux sinon
     */
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
	