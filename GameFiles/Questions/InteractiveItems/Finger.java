package GameFiles.Questions.InteractiveItems;

import GraphicsEngine.Sprite;
import java.awt.*;

import Exceptions.ProjectException;

public class Finger {
    public Finger(){
        sprite = new Sprite("Images/finger.png");
    }

    public void Update(){
        rect.x = CoreSystem.Mouse.GetInstance().GetMousePosX() - 124;
        rect.y = CoreSystem.Mouse.GetInstance().GetMousePosY();
    }

    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(sprite, rect, 1);
    }

    private Rectangle rect = new Rectangle(0, 0, 132, 200);
    private Sprite sprite;
}
