package MenuSystem;

import CoreSystem.Mouse.EventType;
import Exceptions.ProjectException;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Rectangle;
import java.awt.Color;

public class MenuDecorator implements IMenu {

    public MenuDecorator(IMenu _decoratedMenu){
        decoratedMenu = _decoratedMenu;
    }   
    
    
    @Override
    public void Update() {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        if (e == CoreSystem.Mouse.EventType.LPress){
            decoratedMenu.Update(e);
            for (Button b : buttons){
                if (b.OnClick()){
                    b.ComputeFunction();
                    break;
                }
            }
        }
    }
    
    @Override
    public void Update(EventType e) {
        // TODO Auto-generated method stub
        if (e == CoreSystem.Mouse.EventType.LPress){
            decoratedMenu.Update(e);
            for (Button b : buttons){
                if (b.OnClick()){
                    b.ComputeFunction();
                    break;
                }
            } 
        }
    }
    
    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        decoratedMenu.Draw();
        for(Button b : buttons){
            if (b.IsClicked()){
                b.Draw(Color.GREEN);
            }
            else{
                b.Draw(Color.RED);
            }
        }
        for (TextBox tb : textBoxes){
            tb.Draw(Color.BLACK, Color.GRAY, Color.WHITE);
        }
    }

    protected void InitializeButtonsAndTextBoxes(HashMap<Rectangle, Map.Entry<String, Lambda>> _buttons, HashMap<Rectangle, String> _textBoxes){
        Iterator<Map.Entry<Rectangle, Map.Entry<String, Lambda>>> itrButtons = _buttons.entrySet().iterator();
        while(itrButtons.hasNext()){
            Map.Entry<Rectangle, Map.Entry<String, Lambda>> val = itrButtons.next();
            buttons.add(new Button(val.getKey(), val.getValue().getKey(), val.getValue().getValue()));
        }
        Iterator<Map.Entry<Rectangle, String>> itrTextBoxes = _textBoxes.entrySet().iterator();
        while(itrTextBoxes.hasNext()){
            Map.Entry<Rectangle, String> val = itrTextBoxes.next();
            textBoxes.add(new TextBox(val.getKey(), val.getValue()));
        }
    }
    
    protected IMenu decoratedMenu;

    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();
}
