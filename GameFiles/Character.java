package GameFiles;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Character {

    private enum AnimationList{
        WalkingRight,
        WalkingLeft,
        WalkingUp,
        WalkingDown,
        IdleRight,
        IdleLeft,
        Count
    }
    
    public Character(Rectangle _rect, String spriteFile) {
        rect = _rect;
        sprite = new GraphicsEngine.Sprite(spriteFile);

        for (int i = AnimationList.WalkingRight.ordinal(); i < AnimationList.IdleRight.ordinal(); i++){
            animations.add(new GraphicsEngine.Animation(new Rectangle(0, 25 * i, 25, 25), 10, sprite, 0.075f));
        }
        for (int i = AnimationList.IdleRight.ordinal(); i < AnimationList.Count.ordinal(); i++){
            animations.add(new GraphicsEngine.Animation(new Rectangle(250, 21 * (i - AnimationList.IdleRight.ordinal()), 21, 21), 10, sprite, 0.075f));
        }
        iCurSequence = AnimationList.IdleRight.ordinal();
    }

    public void Update() {
        Move();
        animations.get(iCurSequence).Update();
    }
    private void Move(){
        
        Point dir = new Point(0, 0);

        if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_LEFT)) dir.x--;
        if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_RIGHT)) dir.x++;
        if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_UP)) dir.y--;
        if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_DOWN)) dir.y++;

        
        if (rect.x + dir.x * speed > 0 && rect.x + rect.width + dir.x * speed< 800){
            rect.x += dir.x * speed;;
        }
        if (rect.y + dir.y * speed > 0 && rect.y + rect.height + dir.y * speed < 600){
            rect.y += dir.y * speed;
        }

        if (dir.x != 0 || dir.y != 0){
            if (dir.y != 0){
                if (dir.y > 0){
                    iCurSequence = AnimationList.WalkingDown.ordinal();
                }
                else{
                    iCurSequence = AnimationList.WalkingUp.ordinal();
                }
            }
            else{
                if (dir.x > 0){
                    iCurSequence = AnimationList.WalkingRight.ordinal();
                }
                else{
                    iCurSequence = AnimationList.WalkingLeft.ordinal();
                }
            }
        }
        else{
            if (vel.x > 0){
                iCurSequence = AnimationList.IdleRight.ordinal();
            }
            else{
                iCurSequence = AnimationList.IdleLeft.ordinal();
            }
        }
        vel = dir;
    }

    public void Draw() throws Exceptions.ProjectException{
        animations.get(iCurSequence).Draw(rect);
    }

    private Point vel = new Point();
    private Rectangle rect;
    private GraphicsEngine.Sprite sprite;
    private ArrayList<GraphicsEngine.Animation> animations = new ArrayList<GraphicsEngine.Animation>();
    private int iCurSequence = 0;
    private float speed = 20.0f;
}
