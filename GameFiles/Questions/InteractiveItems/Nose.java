package GameFiles.Questions.InteractiveItems;

import GraphicsEngine.*;
import java.awt.*;

import Exceptions.ProjectException;

public class Nose {
    
    public Nose(int questionCode){
        sprite = new Sprite("Images/nose.png");
        gold = new Sprite("Images/gold.png");
        droplet = new Animation(new Rectangle(0, 0, 16, 12), 4, new Sprite("Images/droplet.png"), 0.75);

        switch(questionCode){
        case 1:
            bLeftIsWin = true;
            bRightIsWin = false;
            destDroplet = new Rectangle(407, 84, 32, 24);
            break;
            case 2:
            bLeftIsWin = false;
            bRightIsWin = true;
            destDroplet = new Rectangle(362, 84, 32, 24);
            break;
        case 3:
            bLeftIsWin = true;
            bRightIsWin = true;
            destDroplet = new Rectangle(0, 0, 0, 0);
            break;
        default:
            bLeftIsWin = false;
            bRightIsWin = false;
            destDroplet = new Rectangle(0, 0, 0, 0);
            break;
        }

        leftZone = new Rectangle(364, 58, 28, 28);
        rightZone = new Rectangle(415, 58, 28, 28);
    }

    public void Update(){

        if (!bIsWon && !bIsLost){
            if (leftZone.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bIsWon = bLeftIsWin;
                bIsLost = !bLeftIsWin;
            }
            else if (rightZone.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bIsWon = bRightIsWin;
                bIsLost = !bRightIsWin;
            }
        }
        else if (bIsLost){
            droplet.Update();
        }
        else if (bIsWon){
            goldRect.x = CoreSystem.Mouse.GetInstance().GetMousePosX() - 8;
            goldRect.y = CoreSystem.Mouse.GetInstance().GetMousePosY() - 14;
        }
    }
    
    public void Draw() throws ProjectException{
        GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(355, 85, 85, 8), Color.BLACK);
        GraphicsSystem.GetInstance().DrawSprite(sprite, new Rectangle(348, 0, 104, 96), 2);
        if (bIsLost){
            droplet.Draw(destDroplet, 1);
        }
        else if (bIsWon){
            GraphicsSystem.GetInstance().DrawSprite(gold, goldRect, 1);
        }
    }

    private Sprite sprite;
    private Animation droplet;
    private Sprite gold;
    private final boolean bLeftIsWin;
    private final boolean bRightIsWin;
    private final Rectangle leftZone;
    private final Rectangle rightZone;
    private final Rectangle destDroplet;
    private Rectangle goldRect = new Rectangle(0, 0, 16, 16);

    private boolean bIsWon = false;
    private boolean bIsLost = false;
}
