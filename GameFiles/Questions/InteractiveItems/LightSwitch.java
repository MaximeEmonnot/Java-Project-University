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

    public void Update(CoreSystem.Mouse.EventType e){
        if (e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bLightIsOn = !bLightIsOn;
            }
        }
    }

    public void Draw() throws ProjectException{
        if (bLightIsOn){
            GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);
            GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.LIGHT_GRAY, 5);
            GraphicsSystem.GetInstance().DrawSprite(lightOn, rect, 6);
       }
       else{
           GraphicsSystem.GetInstance().SetBackgroundColor(Color.BLACK);
           GraphicsSystem.GetInstance().DrawSprite(lightOff, rect);
       }
    }

    public boolean GetState(){
        return bLightIsOn;
    }

    private final Rectangle rect;
    private Sprite lightOn;
    private Sprite lightOff;
    private boolean bLightIsOn = true;
}
