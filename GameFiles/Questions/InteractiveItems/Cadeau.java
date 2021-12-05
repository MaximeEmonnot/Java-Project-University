package GameFiles.Questions.InteractiveItems;

import java.awt.Color;

/**
 * @author Lansana Diakite & Alhousseiny Diallo
 * Mini jeu de cadeau, affichage de deuc cadeaux, ensuite des bulles vertes ou rouges
 * selon si le joueur a trouvé ou non
 * @see La classe Animation pour plus de détails
 */
import java.awt.Rectangle;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

public class Cadeau {
	private Sprite sprite;
	private Animation inside;
	private Animation giftBox;
	private final Rectangle rect;
	//private final Rectangle insideRect; je pense que j'en ai pas besoin finalement
	private final boolean bIsRight;
	private boolean bIsRevealed = false;
	
	public Cadeau(Rectangle _rect, boolean _bIsRight) {
		rect = _rect; //La position du cadeau
		bIsRight = _bIsRight;
		//Je fais de switch case car c'est le même cadeau partout
		//Déja, on affiche les cadeaux
		giftBox = new Animation(new Rectangle(0, 0, 26, 32), 2, new Sprite("Images/giftBox.png"), 0.1);

		
		//on teste en fonction du clic la variable qui nous indique si c'est la bonne réponse
		if(bIsRight) {
			inside = new Animation(new Rectangle(0, 32, 32, 32), 2, new Sprite("Images/bubbles.png"), 0.1f);
            //Au cas ou il trouve
		}
		else {
			inside = new Animation(new Rectangle(0, 0, 32, 32), 2, new Sprite("Images/bubbles.png"), 0.1f);
            //animation des bulles rouges au cas ou le joueur trouve pas
			 
		}
	}
	
	public void Update(CoreSystem.Mouse.EventType e){
		
		//return true ou false selon si le joueur clique sur un cadeau ou pas
        if(e == CoreSystem.Mouse.EventType.LRelease && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())) 
        {
        	bIsRevealed = true;
        }
        
        if (bIsRevealed){ 
        	
			inside.Update();
			
    	}
			
			

	}
	
	//Afficher le truc en foonction de la reussite ou non
	public void Draw() throws ProjectException{
		
		//Si il clique sur un des cadeaux
		if(bIsRevealed) {
			int x =10;
			int y =0;
			//Pour le nombre de colonnes et la largeur, dimensions manuelles
			for(int i=0;i<26;i++) {
				
				//Pour le nombre de lignes et la longueur, dimensions manuelles
				for(int j = 0; j<24;j++) {
					inside.Draw(new Rectangle(x, y, 20, 20));
					y=y+20;
				}
				x=x+30;
				y=0;
				
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
	
		
	
}