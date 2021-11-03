package MenuSystem;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;

public class CheckBox {

    public CheckBox(Rectangle _rect){
        rect = _rect;
    }

    public void Update(CoreSystem.Mouse.EventType e){
        if (e == CoreSystem.Mouse.EventType.LPress && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
            bIsChecked = !bIsChecked;
        }
    }

    public void Draw() throws ProjectException{
        Draw(0);
    }
    public void Draw(int priority) throws ProjectException{
        GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, priority);
        GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, priority + 1);
        if (bIsChecked){
            GraphicsSystem.GetInstance().DrawLine(rect.getLocation(), new Point(rect.x + rect.width, rect.y + rect.height), Color.BLACK, priority + 2);
            GraphicsSystem.GetInstance().DrawLine(new Point(rect.x + rect.width, rect.y), new Point(rect.x, rect.y + rect.height), Color.BLACK, priority + 2);
        }
    }

    public void Clear(){
        bIsChecked = false;
    }

    public boolean IsChecked(){
        return bIsChecked;
    }
    
    private Rectangle rect;
    private boolean bIsChecked = false;
}
