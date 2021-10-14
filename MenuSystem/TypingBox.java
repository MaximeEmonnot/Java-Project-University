package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

public class TypingBox {
    public TypingBox(Rectangle _rect, int _textSize){
        rect = _rect;
        textSize = _textSize;
    }  

    public void Update(){
        if(CoreSystem.Mouse.GetInstance().LeftIsPressed()){
            bIsFocused = rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos());
        }
        if (bIsFocused){
            Timer();
            Read();
        }
        else{
            text = text.replace("|", "");
        }
    }

    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, new Point(rect.x + rect.width / 5, rect.y + rect.height / 3),textSize, Color.BLACK, 12);
    }

    public String GetText(){
        return text.replace("|", "");
    }

    private void Timer(){
        currentTimer += CoreSystem.Timer.GetInstance().DeltaTime();
        if (currentTimer > timer){
            if (text.endsWith("|")){
                text = text.substring(0, text.length() - 1);
            }
            else{
                text += "|";
            }
            currentTimer = 0.0f;
        }
    }

    private void Read() {
        char c = CoreSystem.Keyboard.GetInstance().ReadChar();
        if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || c == ' '){
            text += c;
            text = text.replace("|", "");
        }
        else if (c == 8 && text.length() > 0){
            text = text.substring(0, text.length() -1);
        }
    }

    private Rectangle rect;
    private final int textSize;

    private final float timer = 0.75f;
    private float currentTimer = 0.0f; 
    private String text = "";
    private boolean bIsFocused = false;
}
