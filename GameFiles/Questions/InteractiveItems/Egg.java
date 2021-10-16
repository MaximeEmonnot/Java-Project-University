package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import java.util.List;

import Exceptions.ProjectException;

import java.util.ArrayList;

import GraphicsEngine.Sprite;

public class Egg {
    public Egg(Rectangle _rect, Rectangle _insideRect, boolean bIsRight){
        rect = _rect;
        insideRect = _insideRect;

        for (int i = 0; i < 4; i++){
            states.add(new Rectangle(0 + 78 * i, 0, 78, 78));
        }
        states.add(new Rectangle(312, 0, 134, 78));

        if (bIsRight){
            insideSprite = new Sprite("Images/bird.png");
        }
        else{
            insideSprite = new Sprite("Images/panEgg.png");
        }
    }

    public void Update(CoreSystem.Mouse.EventType e){
        if (e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos()) && iCurState < 4){
                iCurState++;
                if (iCurState == 4){
                    rect.x -= 28;
                    rect.width += 56;
                }
            }
        }
    }
    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(eggSprite, rect, states.get(iCurState), 1);
        if (iCurState == 4){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(insideSprite, insideRect, 2);
        }
    }

    public boolean IsFinished(){
        return iCurState == 4;
    }

    private Rectangle rect;
    private final Rectangle insideRect;
    private Sprite eggSprite = new Sprite("Images/egg.png");
    private Sprite insideSprite;
    private List<Rectangle> states = new ArrayList<Rectangle>();
    private int iCurState = 0;
}
