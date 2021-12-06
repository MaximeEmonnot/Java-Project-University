package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.GraphicsSystem;
import GraphicsEngine.Sprite;

public class Hand {
    
    public Hand(String spritePath, Rectangle _rect, Rectangle _insideRect, boolean _bIsRight){
        bIsRight = _bIsRight;
        rect = _rect;
        insideRect = _insideRect;
        hand = new Sprite(spritePath);
    }

    public void Update(CoreSystem.Mouse.EventType e, boolean bRevealedState){
        bIsRevealed = bRevealedState;
        if (!bIsRevealed){
            if (e == CoreSystem.Mouse.EventType.LRelease && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bIsRevealed = true;
            }
        }
        else{
            coin.Update();
        }
    }

    public void Draw() throws ProjectException{
        if (!bIsRevealed){
            GraphicsSystem.GetInstance().DrawSprite(hand, rect, new Rectangle(0, 0, 32, 32));
        }
        else{
            GraphicsSystem.GetInstance().DrawSprite(hand, rect, new Rectangle(32, 0, 32, 32));
            if (bIsRight) coin.Draw(insideRect, 1);
        }
    }
    
	public boolean HasWon(){
		return bIsRight && bIsRevealed;
    }
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
