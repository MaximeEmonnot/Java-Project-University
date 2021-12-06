package GameFiles.Questions.InteractiveItems;

import java.util.List;
import java.util.ArrayList;

import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;

import GraphicsEngine.Sprite;

/**
 * @author Lansana Diakite
 * @author Alhousseiny Diallo
 * Mini jeu de cadeau, affichage de deuc cadeaux, ensuite des bulles vertes ou rouges
 * selon si le joueur a trouv� ou non
 * @see La classe Animation pour plus de d�tails
 */
public class Cadeau {
	
	public Cadeau(Rectangle _rect, boolean _bIsRight) {
		rect = _rect; //La position du cadeau
		bIsRight = _bIsRight;
		//Je fais de switch case car c'est le m�me cadeau partout
		//D�ja, on affiche les cadeaux
		giftBox = new Animation(new Rectangle(0, 0, 26, 32), 7, new Sprite("Images/giftBox.png"), 0.1);
		
		for (int i = 0; i < NBUBBLES; i++){
			bubbles.add(new Bubble(rect, bIsRight));
		}
	}
	
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
	
	//Afficher le truc en foonction de la reussite ou non
	public void Draw() throws ProjectException{
		
		//Si il clique sur un des cadeaux
		if(bIsRevealed) {
			for (Bubble b : bubbles){
				b.Draw();
			}
		}
		//SI il clique sur rien, on affiche les cadeaux
		else {
			giftBox.Draw(rect);
		}
		
	}
	
	public boolean isbIsRevealed() {
		return bIsRevealed;
	}
	
	public void setbIsRevealed(boolean bIsRevealed) {
		this.bIsRevealed = bIsRevealed;
	}
	
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
    }
	public boolean HasLost(){
		return !bIsRight && bIsRevealed;
    }
	
	private Animation giftBox;
	private final Rectangle rect;
	private final boolean bIsRight;
	private boolean bIsRevealed = false;
	
	private List<Bubble> bubbles = new ArrayList<Bubble>();
	private static final int NBUBBLES = 200;
}