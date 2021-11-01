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
        Draw(textColor, boxColor, backgroundColor, 0);
    }
    public void Draw(Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException{
        Draw(text, textColor, boxColor, backgroundColor, priority);
    }
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        Draw(_text, textColor, boxColor, backgroundColor, 0);
    }
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, backgroundColor, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, boxColor, priority + 1);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(_text, new Point(rect.x + rect.width / 5, rect.y + rect.height / 3), textColor, priority + 2);
    }

    private final Rectangle rect;
    private String text;
}
