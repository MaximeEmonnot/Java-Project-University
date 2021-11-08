package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class ChoiceBox {
    
    public ChoiceBox(Rectangle _rect, String _description){
        rect = _rect;
        description = _description;
    }

    public void Update(CoreSystem.Mouse.EventType e){
        if (bIsExpanding){
            Iterator<String> itr = choices.iterator();
            int i = 1;
            while(itr.hasNext()){
                String newChoice = itr.next();
                Rectangle choiceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
                if (rect.y < 300){
                   choiceRect.y += i * choiceRect.height;
                   }
                else if (rect.y >= 300){
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
        Draw(0);
    }
    public void Draw(int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, priority + 1);
        if (choice.length() != 0){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(choice, new Point(rect.x + rect.width / 10, rect.y + rect.height / 2), Color.BLACK, priority + 2);
        }
        else{
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(description, new Point(rect.x + rect.width / 10, rect.y + rect.height / 2), new Font("Arial Bold", Font.ITALIC, 16), Color.GRAY, priority + 2);
        }
        if (bIsExpanding){
            Iterator<String> itr = choices.iterator();
            int i = 1;
            while(itr.hasNext()){
                String newChoice = itr.next();
                Rectangle choiceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
                if (rect.y < 300){
                    choiceRect.y += i * choiceRect.height;
                }
                else if (rect.y >= 300){
                    choiceRect.y -= i * choiceRect.height;
                }
                if (choiceRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                    GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(choiceRect, Color.GRAY, priority);
                }
                else{
                    GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(choiceRect, Color.WHITE, priority);
                }
                GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(choiceRect, Color.BLACK, priority + 1);
                GraphicsEngine.GraphicsSystem.GetInstance().DrawText(newChoice, new Point(choiceRect.x + choiceRect.width / 10, choiceRect.y + choiceRect.height / 2), Color.BLACK, priority + 2);
                i++;
            }
        }
    }

    public void AddChoice(String choice){
        choices.add(choice);
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
    private final String description;
}
