package MenuSystem;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;

/**
 * Checkbox interactive.
 * <p>Utilisable dans des formulaires
 * @author Maxime Emonnot
 * @version 1.2.0
 */
public class CheckBox {

    /**
     * Constructeur de la CheckBox
     * <p>Initialisation a partir d'un rectangle de position
     * @author Maxime Emonnot
     * @param _rect Position de la CheckBox
     */
    public CheckBox(Rectangle _rect){
        rect = _rect;
    }

    /**
     * Mise a jour de l'etat de la CheckBox, si l'evenement est bien un clic sur la CheckBox.
     * @author Maxime Emonnot
     * @param e Evenement souris, qui sera analyse pour determiner l'etat de la CheckBox
     */
    public void Update(CoreSystem.Mouse.EventType e){
        if (e == CoreSystem.Mouse.EventType.LPress && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
            bIsChecked = !bIsChecked;
        }
    }

    /**
     * Affichage de la CheckBox
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        Draw(0);
    }
    /**
     * Surcharge fonctionnelle. Affichage de la CheckBox selon une priorite donnee.
     * @author Maxime Emonnot
     * @param priority Priorite d'affichage de la CheckBox.
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(int priority) throws ProjectException{
        GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, priority);
        GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, priority + 1);
        if (bIsChecked){
            GraphicsSystem.GetInstance().DrawLine(rect.getLocation(), new Point(rect.x + rect.width, rect.y + rect.height), Color.BLACK, priority + 2);
            GraphicsSystem.GetInstance().DrawLine(new Point(rect.x + rect.width, rect.y), new Point(rect.x, rect.y + rect.height), Color.BLACK, priority + 2);
        }
    }

    /**
     * Reinitialisation de l'etat du CheckBox
     * @author Maxime Emonnot
     */
    public void Clear(){
        bIsChecked = false;
    }

    /**
     * Accesseur de l'etat du CheckBox
     * @author Maxime Emonnot
     * @return Etat du CheckBox
     */
    public boolean IsChecked(){
        return bIsChecked;
    }
    
    private Rectangle rect;
    private boolean bIsChecked = false;
}
