package GameFiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;

public class Character {
    
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
    }

    public void Update(CoreSystem.Mouse.EventType e) {
        animations.get(iCurSequence).Update();
    }

    public void Draw() throws Exceptions.ProjectException{
        animations.get(iCurSequence).Draw(rect);
    }

    protected Rectangle rect;
    private GraphicsEngine.Sprite sprite;
    private ArrayList<GraphicsEngine.Animation> animations = new ArrayList<GraphicsEngine.Animation>();
    protected int iCurSequence = 0;
    protected Point vel = new Point();
}
