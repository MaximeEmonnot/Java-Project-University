package GameFiles.Questions.InteractiveItems;

import java.util.List;
import java.util.ArrayList;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;

import GraphicsEngine.Sprite;

/**
 * Cadeau utilise dans BubbleGiftQuestion
 * <p>Des bulles vertes ou rouges s'affichent selon la validite du choix du joueur
 * @author Lansana Diakite
 * @see BubbleGiftQuestion
 * @version 1.4.0
 */
public class Gift {
	
	/**
	 * Initailise un cadeau, sa position, son contenu et si c'est le bon
	 * @author Lansana Diakite
	 * @param _rect le rectangle de destination
	 * @param _bIsRight definit si c'est le bon cadeau ou pas
	 */
	public Gift(Rectangle _rect, boolean _bIsRight) {
		rect = _rect; //La position du cadeau
		bIsRight = _bIsRight;
		//Je fais pas de switch case car c'est le m�me cadeau partout
		//Deja, on initialise l'animation du cadeau
		giftBox = new Animation(new Rectangle(0, 0, 26, 32), 7, new Sprite("Images/giftBox.png"), 0.1);
		
		//Affichage des bulles
		for (int i = 0; i < NBUBBLES; i++){
			bubbles.add(new Bubble(rect, bIsRight));
		}
	}
	
	/**
	 * Mise a jour des cadeaux en fonctions des clics de la souris
	 * @author Lansana Diakite
	 * @param e Evenement souris capture
	 */
	public void Update(CoreSystem.Mouse.EventType e){
		
		//return true ou false selon si le joueur clique sur un cadeau ou pas
		giftBox.Update();
		
        if(e == CoreSystem.Mouse.EventType.LRelease && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())) 
        {
			bIsRevealed = true;
        }
        
        if (bIsRevealed){ 
			bubbles.forEach((bubble) -> bubble.Update());
    	}
	}
	
	/**
	 * 	Afficher le cadeau ainsi que son contenu en fonction de la reussite ou non.
	 * @author Lansana Diakite
	 * @throws ProjectException
	 */
	public void Draw() throws ProjectException{
		
		//Si il clique sur un des cadeaux
		if(bIsRevealed) {
			for (Bubble b : bubbles){
				b.Draw();
			}
		}
		//SI il clique sur rien, on continue d'afficher les cadeaux
		else {
			giftBox.Draw(rect);
		}
		
	}

	/**
	 * Recuperation de l'etat de succes
	 * @author Lansana Diakite
	 * @return Vrai si c'est la bonne reponse et que le cadeau est ouvert, Faux sinon
	 */
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
    }
	/**
	 * Recuperation de l'etat d'echec
	 * @author Lansana Diakite
	 * @return Vrai si c'est la mauvaise reponse et que le cadeau est ouvert, Faux sinon
	 */
	public boolean HasLost(){
		return !bIsRight && bIsRevealed;
    }
	//les differents attributs
	private Animation giftBox;
	private final Rectangle rect;
	private final boolean bIsRight;
	private boolean bIsRevealed = false;
	
	private List<Bubble> bubbles = new ArrayList<Bubble>();
	private static final int NBUBBLES = 200;
}