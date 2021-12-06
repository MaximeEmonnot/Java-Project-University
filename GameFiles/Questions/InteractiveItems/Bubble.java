package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

public class Bubble {
    public Bubble(Rectangle startingDest, boolean bIsRight){
        if (bIsRight){
            anim = new Animation(new Rectangle(0, 32, 32, 32), 4, sprite, 0.2f);
        }
        else{
            anim = new Animation(new Rectangle(0, 0, 32, 32), 4, sprite, 0.2f);
        }

        int[] sizes = {16, 24, 32, 48};
        int[] dir = {-1, 1};

        vel = new Point(((int)(Math.random() * 4) + 1) * dir[(int)(Math.random() * 2)], ((int)(Math.random() * 4) + 1) * dir[(int)(Math.random() * 2)]);
        destRect.x = startingDest.x + (int)(Math.random() * startingDest.width);
        destRect.y = startingDest.y + (int)(Math.random() * startingDest.height); 
        destRect.width = sizes[(int)(Math.random() * 4)];
        destRect.height = destRect.width;
    }

    public void Update(){
        destRect.x += (int)vel.getX();
        destRect.y += (int)vel.getY();
    }

    public void Draw() throws ProjectException{
        anim.Draw(destRect);
    }

    private static final Sprite sprite = new Sprite("Images/bubbles.png");
    private final Animation anim;
    private Rectangle destRect = new Rectangle(0, 0, 0, 0);
    private final Point vel;
}
