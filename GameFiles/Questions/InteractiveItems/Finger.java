package GameFiles.Questions.InteractiveItems;

import GraphicsEngine.Sprite;
import java.awt.*;

import Exceptions.ProjectException;

/**
 * Doigt utilise dans FingerNoseQuestion
 * <p>Reste toujours a la position du curseur
 * @author Maxime Emonnot
 * @see FingerNoseQuestion
 * @version 1.1.0
 */
public class Finger {
    /**
     * Constructeur Finger
     * <p>Initialisation du Sprite de doigt
     * @author Maxime Emonnot
     */
    public Finger(){
        sprite = new Sprite("Images/finger.png");
    }

    /**
     * Mise a jour de la position du doigt (position de la souris)
     * @author Maxime Emonnot
     */
    public void Update(){
        rect.x = CoreSystem.Mouse.GetInstance().GetMousePosX() - 124;
        rect.y = CoreSystem.Mouse.GetInstance().GetMousePosY();
    }

    /**
     * Affichage du Sprite de doigt
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem 
     */
    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(sprite, rect, 1);
    }

    private Rectangle rect = new Rectangle(-200, -200, 132, 200);
    private Sprite sprite;
}
