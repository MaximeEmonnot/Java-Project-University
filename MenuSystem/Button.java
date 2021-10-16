package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

public class Button {

    public Button(Rectangle _rect, String _text, int _textSize, Lambda _func)
    {
        rect = _rect;
        text = _text;
        textSize = _textSize;
        func = _func;
    }

    public boolean OnClick(){
        bIsClicked = false;
        if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos()) && CoreSystem.Mouse.GetInstance().LeftIsPressed()){
            bIsClicked = true;
        }
        return bIsClicked;
    }
    public boolean OnHover(){
        return false;
    }

    public void ComputeFunction(){
        func.func();
    }

    public void Draw(Color c) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, c, 10);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, new Point(rect.x + rect.width / 10, rect.y + rect.height / 3), Color.BLACK, 11);
    }

    public boolean IsClicked(){
        boolean out = bIsClicked;
        bIsClicked = false;
        return out;
    }

    private boolean bIsClicked = false;
    private final Rectangle rect;
    private final String text;
    private final int textSize;
    private Lambda func;
}
