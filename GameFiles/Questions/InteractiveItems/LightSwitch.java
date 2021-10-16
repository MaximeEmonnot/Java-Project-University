package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.*;

public class LightSwitch {
    
    public LightSwitch(Rectangle _rect){
        lightOn = new Sprite("Images/switchOn.png");
        lightOff = new Sprite("Images/switchOff.png");
        rect = _rect;
    }

    public void Update(){
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        if (e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bLightIsOn = !bLightIsOn;
            }
        }
    }

    public void Draw() throws ProjectException{
        if (bLightIsOn){
            GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
            GraphicsSystem.GetInstance().DrawSprite(lightOn, rect);
       }
       else{
           GraphicsSystem.GetInstance().SetBackgroundColor(Color.BLACK);
           GraphicsSystem.GetInstance().DrawSprite(lightOff, rect);
       }
    }

    private final Rectangle rect;
    private Sprite lightOn;
    private Sprite lightOff;
    private boolean bLightIsOn = true;
}
