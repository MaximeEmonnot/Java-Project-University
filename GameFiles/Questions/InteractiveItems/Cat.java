package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Character;

public class Cat extends Character {

    private enum AnimationList{
        IdleBottomLeft,
        IdleTopLeft,
        IdleTopRight,
        IdleBottomRight,
        SleepingBottomLeft,
        SleepingTopLeft,
        SleepingTopRight,
        SleepingBottomRight,
        WalkingBottomLeft,
        WalkingTopLeft,
        WalkingTopRight,
        WalkingBottomRight
    }

    public Cat(Rectangle _rect, String jsonFile) throws Exception {
        //TODO Auto-generated constructor stub
        super(_rect, jsonFile);
        iCurSequence = AnimationList.IdleBottomLeft.ordinal();
    }

    @Override
    public void Update(CoreSystem.Mouse.EventType e){
        super.Update(e);

        if (bIsVisible && e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                if (bIsRightCat){
                    switch(iCurSequence){
                    case 0:
                        iCurSequence = AnimationList.SleepingBottomLeft.ordinal();
                        break;
                    case 1:
                        iCurSequence = AnimationList.SleepingTopLeft.ordinal();
                        break;
                    case 2:
                        iCurSequence = AnimationList.SleepingTopRight.ordinal();
                        break;
                    case 3:
                        iCurSequence = AnimationList.SleepingBottomRight.ordinal();
                        break;
                    default:
                        break;
                    }
                }
                else{
                    switch(iCurSequence){
                    case 0:
                        iCurSequence = AnimationList.WalkingBottomLeft.ordinal();
                        break;
                    case 1:
                        iCurSequence = AnimationList.WalkingTopLeft.ordinal();
                        break;
                    case 2:
                        iCurSequence = AnimationList.WalkingTopRight.ordinal();
                        break;
                    case 3:
                        iCurSequence = AnimationList.WalkingBottomRight.ordinal();
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        
        if (iCurSequence >= 8){
            rect.x += (int)(vel.x * speed);
            rect.y += (int)(vel.y * speed);
        }
    }

    public void SetCatRightness(boolean bValue){
        bIsRightCat = bValue;
    }

    public void SetCatVisibility(boolean bValue){
        bIsVisible = bValue;
    }

    public void ChangePosition(){
        rect.x = (int)(Math.random() * (660 - rect.width)) + 140;
        rect.y = (int)(Math.random() * (480 - rect.height));

        bIsVisible = (Math.random() < 0.5) ? false : true;

        iCurSequence = (int)(Math.random() * 4);
        switch(iCurSequence){
        case 0:
            vel = new Point(-1, 1);
            break;
        case 1:
            vel = new Point(-1, -1);
            break;
        case 2:
            vel = new Point(1, -1);
            break;
        case 3:
            vel = new Point(1, 1);
            break;
        default:
            break;
        }
    }

    @Override
    public void Draw() throws ProjectException{
        if (bIsVisible){
            super.Draw();
        }
    }

    public boolean HasWon() {
        return iCurSequence >= 4 && iCurSequence < 8;
    }

    public boolean HasLost(){
        return iCurSequence > 8;
    }

    private final double speed = 5.0f;
    private boolean bIsRightCat = false;
    private boolean bIsVisible = false;
}
