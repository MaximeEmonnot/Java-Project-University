package GraphicsEngine;

import java.util.ArrayList;
import java.awt.*;

/**
 * Gestion des animations.
 * <p>Decoupe un Sprite en plusieurs rectangles pour realiser une animation.
 * @author Maxime Emonnot
 * @version 1.0.0
 */
public class Animation {
    /**
     * Constructeur de l'Animation. Realise le decoupage des differentes frames selon un rectangle, un nombre d'images, un Sprite et une duree
     * @author Maxime Emonnot
     * @param rect Rectangle de decoupage d'image (taille d'une image)
     * @param count Nombre d'images dans l'animation
     * @param _s Sprite de l'animation
     * @param _holdTime Duree d'une image pour le deroulement de l'animation
     */
    public Animation(Rectangle rect, int count, Sprite _s, double _holdTime){
        s = _s;
        holdTime = _holdTime;

        for (int i = 0; i < count; i++){
            frames.add(new Rectangle(rect.x + rect.width * i, rect.y, rect.width, rect.height));
        }
    }

    /**
     * Affichage de l'animation, selon un rectangle de destination donne
     * @author Maxime Emonnot
     * @param dest Rectangle de destination
     * @throws Exceptions.ProjectException Erreur d'instanciation de GraphicsSystem
     */
    public void Draw(Rectangle dest) throws Exceptions.ProjectException {
        Draw(dest, 0);
    }
    /**
     * Surcharge fonctionnelle. Affichage de l'animation, selon un rectangle de destination et une priorite donnes
     * @author Maxime Emonnot
     * @param dest Rectangle de destination
     * @param priority Priorite d'affichage de l'animation
     * @throws Exceptions.ProjectException Erreur d'instanciation de GraphicsSystem
     */
    public void Draw (Rectangle dest, int priority) throws Exceptions.ProjectException{
        GraphicsSystem.GetInstance().DrawSprite(s, dest, frames.get(iCurFrame), priority);

    }

    /**
     * Mise a jour du timer de l'animation. Methode appelee a chaque frame
     * @author Maxime Emonnot
     */
    public void Update(){
        float dt = CoreSystem.Timer.GetInstance().DeltaTime();
        curFrameTime += dt;
        if (curFrameTime >= holdTime){
            Advance();
            curFrameTime -= holdTime;
        }
    }

    /**
     * Avancement de l'animation. Permet de passer a l'image suivante. 
     * @author Maxime Emonnot
     * @see Animation#Update()
     */
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
