package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import java.util.List;

import Exceptions.ProjectException;

import java.util.ArrayList;

import GraphicsEngine.Sprite;

/**
 * Oeuf utilise dans ConcreteTripleQuestion
 * Possede 4 phases, passant de l'une a l'autre apres un clic.
 * Le contenu, apres les 4 phases, indique sur le succes ou l'echec
 * @author Maxime Emonnot
 */
public class Egg {
    /**
     * Constructeur Egg
     * Initilisation de l'oeuf, ainsi que du contenu selon un rectangle de position de l'oeuf, un rectangle de position du contenu, et un booleen donnes
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de l'oeuf
     * @param _insideRect Rectangle de position du contenu de l'oeuf
     * @param _bIsRight Valeur indicant s'il s'agit de la bonne reponse
     */
    public Egg(Rectangle _rect, Rectangle _insideRect, boolean _bIsRight){
        rect = _rect;
        insideRect = _insideRect;

        for (int i = 0; i < 4; i++){
            states.add(new Rectangle(0 + 78 * i, 0, 78, 78));
        }
        states.add(new Rectangle(312, 0, 134, 78));

        bIsRight = _bIsRight;

        if (bIsRight){
            insideSprite = new Sprite("Images/bird.png");
        }
        else{
            insideSprite = new Sprite("Images/panEgg.png");
        }
    }

    /**
     * Mise a jour de l'oeuf, selon une entree souris donnee
     * @author Maxime Emonnot
     * @param e Entree souris enregistree dans ConcreteTripleQuestion.
     */
    public void Update(CoreSystem.Mouse.EventType e){
        if (e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos()) && iCurState < 4){
                iCurState++;
                if (iCurState == 4){
                    rect.x -= 28;
                    rect.width += 56;
                }
            }
        }
    }
    /**
     * Affichage de l'oeuf et du contenu de l'oeuf
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(eggSprite, rect, states.get(iCurState), 1);
        if (iCurState == 4){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(insideSprite, insideRect, 2);
        }
    }

    /**
     * Recuperation de l'etat de succes
     * @return Vrai si la reponse choisie est correcte et que l'oeuf est entierement casse, Faux sinon
     */
    public boolean HasWon(){
        return bIsRight && IsFinished();
    }
    /**
     * Recuperation de l'etat d'echec
     * @return Vrai si la reponse choisie est fausse et que l'oeuf est entierement casse, Faux sinon
     */
    public boolean HasLost(){
        return !bIsRight && IsFinished();
    }
    /**
     * Recuperation de l'etat de fin de l'oeuf
     * @return Vrai si les 4 phases sont passees, Faux sinon
     */
    private boolean IsFinished(){
        return iCurState == 4;
    }
    
    private Rectangle rect;
    private final Rectangle insideRect;
    private Sprite eggSprite = new Sprite("Images/egg.png");
    private Sprite insideSprite;
    private List<Rectangle> states = new ArrayList<Rectangle>();
    private int iCurState = 0;
    private final boolean bIsRight;
}
