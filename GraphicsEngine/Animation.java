package GraphicsEngine;

import java.util.ArrayList;
import java.awt.*;

public class Animation {
    public Animation(Rectangle rect, int count, Sprite _s, double _holdTime){
        s = _s;
        holdTime = _holdTime;

        for (int i = 0; i < count; i++){
            frames.add(new Rectangle(rect.x + rect.width * i, rect.y, rect.width, rect.height));
        }
    }

    public void Draw(Rectangle dest) throws Exceptions.ProjectException {
        GraphicsSystem.GetInstance().DrawSprite(s, dest, frames.get(iCurFrame));
    }

    public void Update(){
        float dt = CoreSystem.Timer.GetInstance().DeltaTime();
        curFrameTime += dt;
        if (curFrameTime >= holdTime){
            Advance();
            curFrameTime -= holdTime;
        }
    }

    private void Advance(){
        iCurFrame++;
        if (iCurFrame >= frames.size()){
            iCurFrame = 0;
        }
    }

    private Sprite s;
    private ArrayList<Rectangle> frames = new ArrayList<Rectangle>();
    private double curFrameTime = 0.0f;
    private double holdTime;
    private int iCurFrame = 0;
}
