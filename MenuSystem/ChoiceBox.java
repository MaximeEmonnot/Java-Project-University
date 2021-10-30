package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class ChoiceBox {
    
    public ChoiceBox(Rectangle _rect){
        rect = _rect;
    }

    public void Update(CoreSystem.Mouse.EventType e){
        if (bIsExpanding){
            Iterator<String> itr = choices.iterator();
            int i = 1;
            while(itr.hasNext()){
                String newChoice = itr.next();
                Rectangle choiceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
                if (rect.y < 150){
                   choiceRect.y += i * choiceRect.height;
                   }
                else if (rect.y > 450){
                   choiceRect.y -= i * choiceRect.height;
                }
                if (e == CoreSystem.Mouse.EventType.LPress && choiceRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                    choice = newChoice;
                    break;
                }
                i++;
            }
        }
        if(e == CoreSystem.Mouse.EventType.LPress){
            bIsExpanding = !bIsExpanding && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos());
        }
    }

    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, 20);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, 21);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(choice, new Point(rect.x + rect.width / 10, rect.y + rect.height / 3), Color.BLACK, 22);

        if (bIsExpanding){
            Iterator<String> itr = choices.iterator();
            int i = 1;
            while(itr.hasNext()){
                String newChoice = itr.next();
                Rectangle choiceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
                if (rect.y < 150){
                    choiceRect.y += i * choiceRect.height;
                }
                else if (rect.y > 450){
                    choiceRect.y -= i * choiceRect.height;
                }
                GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(choiceRect, Color.WHITE, 20);
                GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(choiceRect, Color.BLACK, 21);
                GraphicsEngine.GraphicsSystem.GetInstance().DrawText(newChoice, new Point(choiceRect.x + choiceRect.width / 10, choiceRect.y + choiceRect.height / 3), Color.BLACK, 22);
                i++;
            }
        }
    }

    public void SetChoices(Set<String> newChoices){
        choices.clear();
        choices.addAll(newChoices);
    }

    public String GetText(){
        return choice;
    }

    public void Reset(){
        choice = "";
    }

    public boolean IsExpanding(){
        return bIsExpanding;
    }

    private Rectangle rect;
    
    private boolean bIsExpanding = false;
    private Set<String> choices = new HashSet<String>();
    private String choice = "";
}
