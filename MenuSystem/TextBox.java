package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

public class TextBox {
    public TextBox(Rectangle _rect){
        rect = _rect;
    }
    public TextBox(String _text){
        text = _text;
    }
    public TextBox(Rectangle _rect, String _text){
        rect = _rect;
        text = _text;
    }
    
    public void Draw(Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        Draw(textColor, boxColor, backgroundColor, 0);
    }
    public void Draw(Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException{
        Draw(text, rect, textColor, boxColor, backgroundColor, priority);
    }  
    public void Draw(Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor)throws ProjectException {
        Draw(_rect, textColor, boxColor, backgroundColor, 0);
    }
    public void Draw(Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor, int priority)throws ProjectException {
        Draw(text, _rect, textColor, boxColor, backgroundColor, priority);
    }
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor) throws ProjectException {
        Draw(_text, textColor, boxColor, backgroundColor, 0);
    }
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException {
        Draw(_text, rect, textColor, boxColor, backgroundColor, priority);
    }
    public void Draw(String _text, Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        Draw(_text, _rect, textColor, boxColor, backgroundColor, 0);
    }
    public void Draw(String _text, Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(_rect, backgroundColor, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(_rect, boxColor, priority + 1);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(_text, new Point(_rect.x + (int)((_rect.width - _text.length() * 8) / 2), _rect.y + _rect.height / 2), textColor, priority + 2);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof TextBox){
            return text.equals(((TextBox)o).text);
        }
        return false;
    }
    @Override
    public int hashCode(){
        return text.hashCode();
    }

    private Rectangle rect;
    private String text;
}
