package MenuSystem;

import java.awt.*;

import Exceptions.ProjectException;

public class UserMessage {
    public UserMessage(Point _pos){
        pos = _pos;
    }

    public void Update(){
        timer -= CoreSystem.Timer.GetInstance().DeltaTime();
    }

    public void Draw() throws ProjectException{
        if (timer > 0.0f){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, pos, color);
        }
    }

    public void SetMessage(String _text, Color _color, float _timer){
        text = _text;
        color = _color;
        timer = _timer;
    }

    private String text = "";
    private Color color = Color.WHITE;
    private float timer = 0.0f;
    private Point pos;
}
