package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class TypingBox {
    public TypingBox(Rectangle _rect, String _description){
        rect = _rect;
        description = _description;
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
        Draw(0);
    }
    
    public void Draw(int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, priority + 1);

        if (bIsPassword){
            String password = "";
            for (int i = 0; i < text.length(); i++){
                if (text.charAt(i) == '|'){
                    password += "|";
                }
                else{
                    password += "*";
                }
            }
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(password, new Point(rect.x + rect.width / 10, rect.y + rect.height / 3), Color.BLACK, priority + 2);
        }
        else{
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, new Point(rect.x + rect.width / 10, rect.y + rect.height / 3), Color.BLACK, priority + 2);
        }   
        if (text.replace("|", "").length() == 0){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(description, new Point(rect.x + rect.width / 10 + 2, rect.y + rect.height / 3), new Font("Arial Bold", Font.ITALIC, 16), Color.GRAY, priority + 2);
        }
    }

    public void SetPasswordMode(boolean bMode){
        bIsPassword = bMode;
    }

    public String GetText(){
        return text.replace("|", "");
    }

    public void Clear(){
        text = "";
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
        if (c != 0){
            text = text.replace("|", "");
            if (authorizedChar.contains(Character.toString(c))){
                text += c;
            }
            else if (c == 8 && text.length() > 0){
                text = text.substring(0, text.length() -1);
            }
        }
    }

    private Rectangle rect;

    private final float timer = 0.75f;
    private float currentTimer = 0.0f; 
    private String text = "";
    private final String description;
    private boolean bIsFocused = false;
    private boolean bIsPassword = false;

    private final static String authorizedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?!,;.:/\\\'\"°+-*@&éèêàùç²{([|])}=_><%$€#~ ";
}
