package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.*;

/**
 * Interrupteur utilise dans CatsLightQuestion
 * <p>Passe d'un etat a l'autre suite a un clic utilisateur.
 * <p>Des chats apparaissent lorsque l'on rallume la lumiere
 * @author Maxime Emonnot
 * @see CatsLightQuestion
 * @version 1.1.0
 */
public class LightSwitch {
    
    /**
     * Constructeur LightSwitch
     * <p>Initialisation des Sprite selon un rectangle de position donne
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de l'interrupteur
     */
    public LightSwitch(Rectangle _rect){
        lightOn = new Sprite("Images/switchOn.png");
        lightOff = new Sprite("Images/switchOff.png");
        rect = _rect;
    }

    /**
     * Mise a jour de l'etat de l'interrupteur, selon une entree souris donnee (clic gauche sur l'interrupteur)
     * @author Maxime Emonnot
     * @param e Entree souris enregistree dans ConcreteQuadrupleQuestion
     */
    public void Update(CoreSystem.Mouse.EventType e){
        if (e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bLightIsOn = !bLightIsOn;
            }
        }
    }

    /**
     * Affichage de l'interrupteur, en fonction de son etat
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
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

    /**
     * Recuperation de l'etat de l'interrupteur
     * @author Maxime Emonnot
     * @return Vrai si la lumiere est allumee, Faux sinon
     */
    public boolean GetState(){
        return bLightIsOn;
    }

    private final Rectangle rect;
    private Sprite lightOn;
    private Sprite lightOff;
    private boolean bLightIsOn = true;
}
