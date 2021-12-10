package GameFiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;

/**
 * Gestion des entites animees
 * <p>Classe abstraite possedant les methodes d'affichages et d'Update necessaires pour l'animation
 * @author Maxime Emonnot
 * @version 1.0.0
 * @since 1.1.0
 */
public abstract class Character {
    
    /**
     * Constructeur de l'entitee. Initialise l'entite selon un rectangle de position et un fichier JSON
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de l'entite
     * @param jsonFile Chemin du fichierJSON pour l'initialisation des animations
     * @throws Exception Erreur de lecture du fichier JSON
     */
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

    /**
     * Mise a jour de l'animation de l'entite
     * @author Maxime Emonnot
     */
    public void Update() {
        animations.get(iCurSequence).Update();
    }

    /**
     * Affichage de l'entite
     * @author Maxime Emonnot
     * @throws Exceptions.ProjectException Erreur d'instanciation de GraphicsSystem
     */
    public void Draw() throws Exceptions.ProjectException{
        animations.get(iCurSequence).Draw(rect);
    }

    protected Rectangle rect;
    private GraphicsEngine.Sprite sprite;
    private ArrayList<GraphicsEngine.Animation> animations = new ArrayList<GraphicsEngine.Animation>();
    protected int iCurSequence = 0;
    protected Point vel = new Point();
}
