package GameFiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;

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
    
    public Character(Rectangle _rect, String jsonFile) throws Exception {
        rect = _rect;
        CoreSystem.JSONReader jr = new CoreSystem.JSONReader(jsonFile);
        String fileName = jr.GetValue("filename");
        sprite = new GraphicsEngine.Sprite(fileName);
        
        TreeMap<String, JSONArray> anim = jr.GetObjectMember("animations");

        Iterator<Map.Entry<String, JSONArray>> itr = anim.entrySet().iterator();
        while(itr.hasNext()){
            Object val = itr.next().getValue();
            JSONArray arr = (JSONArray)val;
            animations.add(new GraphicsEngine.Animation(new Rectangle(Math.toIntExact((Long)arr.get(0)), Math.toIntExact((Long)arr.get(1)), Math.toIntExact((Long)arr.get(2)), Math.toIntExact((Long)arr.get(3))), Math.toIntExact((Long)arr.get(4)), sprite, (Double)arr.get(5)));
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
    private float speed = 5.0f;
}
