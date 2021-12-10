package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

/**
 * Boite utilisee dans BoxQuestion.
 * <p>Cliquer sur une boite affiche son contenu : un gateau si c'est la bonne reponse, une bombe sinon
 * @author Mamadou Cire Camara
 * @version 1.4.0  
 */
public class Box {
	
	/**
	 * Constructeur Box
	 * <p>Definit la position de la boite, sa couleur et sa validite 
	 * @author Mamadou Cire Camara
	 * @param _rect Rectangle d'affichage de la boite
	 * @param _bIsRight Validite de la boite
	 * @param type Couleur de la boite
	 */
	public Box(Rectangle _rect, boolean _bIsRight, int type){
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
	        insideRect = new Rectangle(rect.x, rect.y - 32, 64, 128);
	    }
	}

	/**
	 * Mise a jour de la boiite
	 * <p>Si un clic a lieu sur la boite, on met a jour l'etat de revelation
	 * @author Mamadou Cire Camara
	 * @param e Entree souris
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
	 * Affichage de la boite et de son contenu si revele
	 * @author Mamadou Cire Camara
	 * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
	 */
	public void Draw() throws ProjectException{
		if (!bIsRevealed) GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(sprite, rect, 0);
		else inside.Draw(insideRect);
	}

	/**
	 * Recuperation de l'etat de succes
	 * @author Mamadou Cire Camara
     * @return Vrai si la boite est valide et si le contenu est revele, Faux sinon
	 */
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
	}
	/**
     * Recuperation de l'etat d'echec
	 * @author Mamadou Cire Camara
     * @return Vrai si la boite est non valide et si le contenu est revele, Faux sinon
     */
	public boolean HasLost(){
		return !bIsRight && bIsRevealed;
	}

	private Sprite sprite;
	private Animation inside;
	private final Rectangle rect;
	private final Rectangle insideRect;
	private final boolean bIsRight;
	private boolean bIsRevealed = false;
		
}
