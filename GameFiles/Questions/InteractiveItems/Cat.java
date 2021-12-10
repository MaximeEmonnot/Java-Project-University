package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.Character;

/**
 * Chat utilise dans CatsLightQuestion
 * <p>Herite de Character pour les animations.
 * <p>Chaque chat correspond a une proposition de reponse, cliquer sur un chat entraine une animation differente en fonction de la validite de la reponse
 * @author Maxime Emonnot
 * @see CatsLightQuestion
 * @version 1.1.0
 */
public class Cat extends Character {

    /**
     * Liste des animations des chats (dans l'ordre inscrit dans les fichiers JSON)
     * @author Maxime Emonnot
     */
    private enum AnimationList{
        IdleBottomLeft,
        IdleTopLeft,
        IdleTopRight,
        IdleBottomRight,
        SleepingBottomLeft,
        SleepingTopLeft,
        SleepingTopRight,
        SleepingBottomRight,
        WalkingBottomLeft,
        WalkingTopLeft,
        WalkingTopRight,
        WalkingBottomRight
    }

    /**
     * Constructeur Cat
     * <p>Initialise le chat selon un rectangle de position et un fichier JSON pour l'animation 
     * @author Maxime Emonnot
     * @param _rect Rectangle de position du chat
     * @param jsonFile Ficher JSON pour l'initialisation de l'animation du chat
     * @throws Exception Erreurs lors de l'acces au fichier JSON
     */
    public Cat(Rectangle _rect, String jsonFile) throws Exception {
        //TODO Auto-generated constructor stub
        super(_rect, jsonFile);
        iCurSequence = AnimationList.IdleBottomLeft.ordinal();
    }

    /**
     * Mise a jour de l'animation du chat, ainsi que de son apparition et de sa disparition.
     * <p>Mise a jour de l'etat de succes et d'echec
     * @author Maxime Emonnot
     * @param e Entree souris enregistree dans ConcreteQuadrupleQuestion
     */
    public void Update(CoreSystem.Mouse.EventType e){
        super.Update();

        if (bIsVisible && e == CoreSystem.Mouse.EventType.LRelease){
            if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                if (bIsRightCat){
                    switch(iCurSequence){
                    case 0:
                        iCurSequence = AnimationList.SleepingBottomLeft.ordinal();
                        break;
                    case 1:
                        iCurSequence = AnimationList.SleepingTopLeft.ordinal();
                        break;
                    case 2:
                        iCurSequence = AnimationList.SleepingTopRight.ordinal();
                        break;
                    case 3:
                        iCurSequence = AnimationList.SleepingBottomRight.ordinal();
                        break;
                    default:
                        break;
                    }
                }
                else{
                    switch(iCurSequence){
                    case 0:
                        iCurSequence = AnimationList.WalkingBottomLeft.ordinal();
                        break;
                    case 1:
                        iCurSequence = AnimationList.WalkingTopLeft.ordinal();
                        break;
                    case 2:
                        iCurSequence = AnimationList.WalkingTopRight.ordinal();
                        break;
                    case 3:
                        iCurSequence = AnimationList.WalkingBottomRight.ordinal();
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        
        if (iCurSequence >= 8){
            rect.x += (int)(vel.x * speed);
            rect.y += (int)(vel.y * speed);
        }
    }

    /**
     * Initialisation de la validite de la reponse liee au chat
     * @author Maxime Emonnot
     * @param bValue Valeur concernant la validite de la reponse
     */
    public void SetCatRightness(boolean bValue){
        bIsRightCat = bValue;
    }

    /**
     * Initialisation de la visibilite du chat
     * @author Maxime Emonnot
     * @param bValue Valeur concernant la visibilite du chat dans la scene
     */
    public void SetCatVisibility(boolean bValue){
        bIsVisible = bValue;
    }

    /**
     * Methode changement de position du chat, appelee dans ConcreteQuadrupleQuestion lorsque la lumiere se rallume
     * @author Maxime Emonnot
     */
    public void ChangePosition(){
        rect.x = (int)(Math.random() * (1140 - rect.width)) + 140;
        rect.y = (int)(Math.random() * (550 - rect.height));

        bIsVisible = (Math.random() < 0.5) ? false : true;

        iCurSequence = (int)(Math.random() * 4);
        switch(iCurSequence){
        case 0:
            vel = new Point(-1, 1);
            break;
        case 1:
            vel = new Point(-1, -1);
            break;
        case 2:
            vel = new Point(1, -1);
            break;
        case 3:
            vel = new Point(1, 1);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     * <p>Affichage du chat, s'il est visible
     * @author Maxime Emonnot
     */
    @Override
    public void Draw() throws ProjectException{
        if (bIsVisible){
            super.Draw();
        }
    }

    /**
     * Recuperation de l'etat de succes
     * @author Maxime Emonnot
     * @return Vrai si la reponse selectionnee est correcte, Faux sinon
     */
    public boolean HasWon() {
        return iCurSequence >= 4 && iCurSequence < 8;
    }

    /**
     * Recuperation de l'etat d'echec
     * @author Maxime Emonnot
     * @return Vrai si la reponse selectionnee est fausse, Faux sinon
     */
    public boolean HasLost(){
        return iCurSequence >= 8;
    }

    private final double speed = 5.0f;
    private boolean bIsRightCat = false;
    private boolean bIsVisible = false;
}
