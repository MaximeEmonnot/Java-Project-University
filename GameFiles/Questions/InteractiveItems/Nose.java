package GameFiles.Questions.InteractiveItems;

import GraphicsEngine.*;
import java.awt.*;

import Exceptions.ProjectException;

/**
 * Nez utilise dans FingerNoseQuestion
 * <p>Chaque narine correspond a une proposition de reponse
 * @author Maxime Emonnot
 * @see FingerNoseQuestion
 * @version 1.1.0
 */
public class Nose {
    
    /**
     * Constructeur Nose
     * <p>Initialise la zone de repone selon un code de question donne
     * @author Maxime Emonnot
     * @param questionCode Code de question correspondant a la bonne repone
     */
    public Nose(int questionCode){
        sprite = new Sprite("Images/nose.png");
        gold = new Sprite("Images/gold.png");
        droplet = new Animation(new Rectangle(0, 0, 16, 12), 4, new Sprite("Images/droplet.png"), 0.25);

        switch(questionCode){
        case 1:
            bLeftIsWin = true;
            bRightIsWin = false;
            destDroplet = new Rectangle(651, 84, 32, 24);
            break;
        case 2:
            bLeftIsWin = false;
            bRightIsWin = true;
            destDroplet = new Rectangle(606, 84, 32, 24);
            break;
        case 3:
            bLeftIsWin = true;
            bRightIsWin = true;
            destDroplet = new Rectangle(0, 0, 0, 0);
            break;
        default:
            bLeftIsWin = false;
            bRightIsWin = false;
            destDroplet = new Rectangle(0, 0, 0, 0);
            break;
        }

        leftZone = new Rectangle(608, 58, 28, 28);
        rightZone = new Rectangle(659, 58, 28, 28);
    }

    /**
     * Mise a jour de l'etat de succes et d'echec, ainsi que de l'animation de succes et d'echec
     * @author Maxime Emonnot
     */
    public void Update(){

        if (!bIsWon && !bIsLost){
            if (leftZone.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bIsWon = bLeftIsWin;
                bIsLost = !bLeftIsWin;
            }
            else if (rightZone.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                bIsWon = bRightIsWin;
                bIsLost = !bRightIsWin;
            }
        }
        else if (bIsLost){
            droplet.Update();
        }
        else if (bIsWon){
            goldRect.x = CoreSystem.Mouse.GetInstance().GetMousePosX() - 8;
            goldRect.y = CoreSystem.Mouse.GetInstance().GetMousePosY() - 14;
        }
    }
    
    /**
     * Affichage du nez, ainsi que du resultat (succes ou echec)
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(600, 85, 85, 8), Color.BLACK);
        GraphicsSystem.GetInstance().DrawSprite(sprite, new Rectangle(592, 0, 104, 96), 2);
        if (bIsLost){
            droplet.Draw(destDroplet, 1);
        }
        else if (bIsWon){
            GraphicsSystem.GetInstance().DrawSprite(gold, goldRect, 1);
        }
    }

    /**
     * Recuperation de l'etat de succes
     * @author Maxime Emonnot
     * @return Vrai si la bonne reponse est selectionnee, Faux sinon
     */
    public boolean HasWon(){
        return bIsWon;
    }
    /**
     * Recuperation de l'etat d'echec
     * @author Maxime Emonnot
     * @return Vrai si la mauvaise reponse est selectionnee, Faux sinon
     */
    public boolean HasLost(){
        return bIsLost;
    }

    private Sprite sprite;
    private Animation droplet;
    private Sprite gold;
    private final boolean bLeftIsWin;
    private final boolean bRightIsWin;
    private final Rectangle leftZone;
    private final Rectangle rightZone;
    private final Rectangle destDroplet;
    private Rectangle goldRect = new Rectangle(-100, -100, 16, 16);

    private boolean bIsWon = false;
    private boolean bIsLost = false;
}
