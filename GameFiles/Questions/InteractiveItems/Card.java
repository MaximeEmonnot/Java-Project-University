package GameFiles.Questions.InteractiveItems;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Sprite;
/**
 * Carte utilisee dans CardQuestion
 * <p>Possede 2 phases, passant de l'une a l'autre apres un clic.
 * <p>Le contenu, apres les 2 phases, indique sur le succes ou l'echec
 * @author Thierno Amadou Diallo
 * @see CardQuestion
 * @version 1.4.0
 */
public class Card{ 
	/**
     * Constructeur Card
     * <p>Choix 4 des cartes et de leur couleurs aleatoirement dans le spritesheet 
     * <p>Initialisation du rectangle de la croix
     * @author Thierno Amadou Diallo
     * @param _destRect Rectangle indicant le type de la carte apr�s le clic sur la carte
     * @param _destCross Rectangle de la croix d'echec ou de validation
     * @param _bIsRight Valeur indicant s'il s'agit de la bonne reponse
     */
	public Card(Rectangle _destRect, Rectangle _destCross, boolean _bIsRight){
		destRect = _destRect;
		destCross = _destCross;
		bIsRight = _bIsRight;
		    
		int value = (int)(Math.random() * 13);
		int color = 1;
		if (!bIsRight){
		    int[] roulette = {0, 2, 3};
		    color = roulette[(int)(Math.random() * 3)];
		}
		srcRectValue = new Rectangle(0 + value * 71, 0 + color * 96, 71, 96);
		if (bIsRight) {
		    srcCross = new Rectangle(0, 0,64, 64);
		}
		else { 
		    srcCross = new Rectangle(64, 0, 64, 64);
		}
	}
	  
	/**
	 * Mise a jour de l'oeuf, selon une entree souris donnee
	 * @author Thierno Amadou Diallo
	 * @param e Entree souris enregistree dans CardQuestion
	 */
	public void Update(CoreSystem.Mouse.EventType e){
		if (e == CoreSystem.Mouse.EventType.LRelease && destRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
		    bIsRevealed = true;
		}
	}
		  
	/**
	 * Affichage des cartes de dos dans un premier temps
	 * <p>Affichage des cartes de face, ainsi que de la croix d'echec ou de validation
	 * @author Thierno Amadou Diallo
	 * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
	 */
	public void Draw() throws ProjectException{ 
		if (bIsRevealed) {
			GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(card, destRect, srcRectValue);
		    GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(cross, destCross, srcCross);
		}
		else GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(card, destRect, srcRectBack);
	}

	/**
	 * Recuperation de l'etat de succes
	 * @author Thierno Amadou Diallo
	 * @return Vrai si la mauvaise reponse est selectionnee, Faux sinon
	 */
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
	}
		  
	/**
	 * Recuperation de l'etat d'echec
	 * @author Thierno Amadou Diallo
	 * @return Vrai si la mauvaise reponse est selectionnee, Faux sinon
	 */
	public boolean HasLost(){
		return !bIsRight && bIsRevealed;
	}

	private static Sprite card = new Sprite("Images/cards.png");
	private static Sprite cross = new Sprite("Images/rightWrong.png");
	private Rectangle srcRectBack = new Rectangle(142, 384, 71, 96);
	private Rectangle srcRectValue;
	private Rectangle destRect;
	private Rectangle srcCross;
	private Rectangle destCross;
	private boolean bIsRight;
	private boolean bIsRevealed = false;
}