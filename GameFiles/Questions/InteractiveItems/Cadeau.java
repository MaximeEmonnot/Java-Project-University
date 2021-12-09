package GameFiles.Questions.InteractiveItems;

import java.util.List;
import java.util.ArrayList;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;

import GraphicsEngine.Sprite;

/**
 * @author Lansana Diakite
 * 
 * Mini jeu de cadeau, affichage de deuc cadeaux, ensuite des bulles vertes ou rouges
 * selon si le joueur a trouvïou non
 * @see La classe Animation pour plus de dï¿½tails
 */
public class Cadeau {
	
	/**
	 * Initailise un cadeau, sa position, son contenu et si c'est le bon
	 * @param _rect le rectangle de destination
	 * @param _bIsRight savvoir si c'est le bon cadeau ou pas
	 */
	public Cadeau(Rectangle _rect, boolean _bIsRight) {
		rect = _rect; //La position du cadeau
		bIsRight = _bIsRight;
		//Je fais pas de switch case car c'est le mï¿½me cadeau partout
		//Dï¿½ja, on affiche les cadeaux
		giftBox = new Animation(new Rectangle(0, 0, 26, 32), 7, new Sprite("Images/giftBox.png"), 0.1);
		
		//Affichage des bulles
		for (int i = 0; i < NBUBBLES; i++){
			bubbles.add(new Bubble(rect, bIsRight));
		}
	}
	
	/**
	 * Mise à jour des cadeaux en fonctions des clics de la souris
	 * @param e represente la position actuelle de la souris
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
	 * 	Afficher le cadeau ainsi que son contenu en foonction de la reussite ou non.
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

	public boolean HasWon(){
		return bIsRight && bIsRevealed;
    }
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