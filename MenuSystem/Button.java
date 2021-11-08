package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

public class Button {

    public Button(Rectangle _rect, String _text, Lambda _func)
    {
        rect = _rect;
        text = _text;
        func = _func;
    }

    public boolean OnClick(CoreSystem.Mouse.EventType e){
        bIsClicked = false;
        if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos()) && e == CoreSystem.Mouse.EventType.LPress){
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
        Draw(c, 0);
    }
    public void Draw(Color c, int priority) throws ProjectException{
        Draw(c, rect, priority);
    }
    public void Draw(Color c, Rectangle _rect) throws ProjectException{
        Draw(c, _rect, 0);
    }
    public void Draw(Color c, Rectangle _rect, int priority) throws ProjectException{
        rect = _rect;
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRoundRect(_rect, 10, 10, c, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRoundRect(_rect, 10, 10, Color.BLACK, priority + 1);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, new Point(_rect.x + (int)((_rect.width - text.length() * 8) / 2), _rect.y + 3 *_rect.height / 5), Color.BLACK, priority + 2);
    }

    public boolean IsClicked(){
        boolean out = bIsClicked;
        bIsClicked = false;
        return out;
    }

    public String GetText(){
        return text;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Button){
            return text.equals(((Button)o).text);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return text.hashCode();
    }

    private boolean bIsClicked = false;
    private Rectangle rect;
    private final String text;
    private Lambda func;
}
