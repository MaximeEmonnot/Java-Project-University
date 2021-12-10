package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

/**
 * Classe Hand
 * <p>Main utilisee dans HandCoinQuestion
 * <p>Contient une piece si bonne reponse, rien sinon
 * @author Godfree Akakpo
 * @see HandCoinQuestion
 * @version 1.4.0
 */
public class Hand {
    
    /**
     * Constructeur Hand
     * <p>Definit un sprite, differentes positions et la validite de la main
     * @author Godfree Akakpo
     * @param spritePath Emplacement du sprite de la main
     * @param _rect Rectangle de osition de la main
     * @param _insideRect Rectangle de position du contenu de la main
     * @param _bIsRight Validite de la main
     */
    public Hand(String spritePath, Rectangle _rect, Rectangle _insideRect, boolean _bIsRight){
        bIsRight = _bIsRight;
        rect = _rect;
        insideRect = _insideRect;
        hand = new Sprite(spritePath);
    }

    /**
     * Mise a jour de la main.
     * <p>Ouverture de la main si un clic a eu lieu sur l'une des main
     * @author Godfree Akakpo
     * @param e Entree souris
     * @param bRevealedState Etat de la question (repondu ou non)
     */
    public void Update(CoreSystem.Mouse.EventType e, boolean bRevealedState){
        if (!bRevealedState){
            if (e == CoreSystem.Mouse.EventType.LRelease && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bIsRevealed = true;
            }
        }
        else{
            coin.Update();
        }
    }


    /**
     * Affichage de la main et de son contenu si revele
     * @author Godfree Akakpo
     * @param bRevealedState Etat de la question (repondu ou non)
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(boolean bRevealedState) throws ProjectException{
        if (!bRevealedState){
            GraphicsSystem.GetInstance().DrawSprite(hand, rect, new Rectangle(0, 0, 32, 32));
        }
        else{
            GraphicsSystem.GetInstance().DrawSprite(hand, rect, new Rectangle(32, 0, 32, 32));
            if (bIsRight) coin.Draw(insideRect, 1);
        }
    }
    
    /**
     * Recuperation de l'etat de succes
     * @author Godfree Akakpo
     * @return Vrai si c'est la bonne reponse et que la main est ouverte, Faux sinon
     */
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
    }
    /**
     * Recuperation de l'etat d'echec
     * @author Godfree Akakpo
     * @return Vrai si c'est la mauvaise reponse et que la main est ouverte, Faux sinon
     */
	public boolean HasLost(){
		return !bIsRight && bIsRevealed;
    }

    private Animation coin = new Animation(new Rectangle(0, 0, 32, 32), 8, new Sprite("Images/coin.png"), 0.1f);
    private Sprite hand;
    private final boolean bIsRight;
    private boolean bIsRevealed = false;
    private final Rectangle rect;
    private final Rectangle insideRect;
}
