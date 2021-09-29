package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

public class TextBox {
    public TextBox(Rectangle _rect){
        rect = _rect;
    }
    public TextBox(Rectangle _rect, String _text){
        rect = _rect;
        text = _text;
    }

    public void Draw(Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        Draw(text, textColor, boxColor, backgroundColor);
    }
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, backgroundColor, 10);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, boxColor, 11);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(_text, new Point(rect.x + rect.width / 5, rect.y + rect.height / 3), textColor, 12);
    }

    private final Rectangle rect;
    private String text;
}
