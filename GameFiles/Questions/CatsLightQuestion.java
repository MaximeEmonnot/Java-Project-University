package GameFiles.Questions;

import GameFiles.Questions.InteractiveItems.*;
import GraphicsEngine.Sprite;

import java.awt.*;

import Exceptions.ProjectException;

/**
 * Mini-jeu a quatre possibilites de reponse
 * L'utilisateur doit cliquer sur l'interrupteur, faisant apparaitre differents chats
 * Chaque chat correspond a une proposition de reponse, comme indique sur le cote gauche
 * @author Maxime Emonnot
 */
public class CatsLightQuestion extends AQuadrupleAnswerQuestion {

    /**
     * Constructeur ConcreteQuadrupleQuestion
     * Reprend le constructeur de AQuadrupleAnswerQuestion et initialise les differents chats pour la bonne reponse
     * @author Maxime Emonnot
     * @param _question Intitule de la question
     * @param _anwserA Proposition A
     * @param _answerB Proposition B 
     * @param _answerC Proposition C
     * @param _answerD Proposition D
     * @param _type Reponse correcte
     * @throws Exception Erreurs lors de l'acces aux fichiers JSON pour les animations des chats
     */
    public CatsLightQuestion(String _question, float _timer, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) throws Exception {
        super(_question, _timer, _anwserA, _answerB, _answerC, _answerD, _type);
        //TODO Auto-generated constructor stub
        ls = new LightSwitch(new Rectangle(0, 0, 136, 200));
        catA = new Cat(new Rectangle(0, 0, 64, 64), "json/blackCat.json");
        catB = new Cat(new Rectangle(0, 0, 64, 64), "json/whiteCat.json");
        catC = new Cat(new Rectangle(0, 0, 64, 64), "json/orangeCat.json");
        catD = new Cat(new Rectangle(0, 0, 64, 64), "json/tricolorCat.json");

        catAIcon = new Sprite("Images/blackCatIcon.png");
        catBIcon = new Sprite("Images/whiteCatIcon.png");
        catCIcon = new Sprite("Images/orangeCatIcon.png");
        catDIcon = new Sprite("Images/tricolorCatIcon.png");

        switch(_type){
            case ANSWER_A:
                catA.SetCatRightness(true);
                break;
            case ANSWER_B:
                catB.SetCatRightness(true);
                break;
            case ANSWER_C:
                catC.SetCatRightness(true);
                break;
            case ANSWER_D:
                catD.SetCatRightness(true);
                break;
            case ANSWER_AB:
                catA.SetCatRightness(true);
                catB.SetCatRightness(true);
                break;
            case ANSWER_AC:
                catA.SetCatRightness(true);
                catC.SetCatRightness(true);
                break;
            case ANSWER_AD:
                catA.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            case ANSWER_BC:
                catB.SetCatRightness(true);
                catC.SetCatRightness(true);
                break;
            case ANSWER_BD:
                catB.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            case ANSWER_CD:
                catC.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            case ANSWER_ABC:
                catA.SetCatRightness(true);
                catB.SetCatRightness(true);
                catC.SetCatRightness(true);
                break;
            case ANSWER_ABD:
                catA.SetCatRightness(true);
                catB.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            case ANSWER_ACD:
                catA.SetCatRightness(true);
                catB.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            case ANSWER_BCD:
                catB.SetCatRightness(true);
                catC.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            case ANSWER_ABCD:
                catA.SetCatRightness(true);
                catB.SetCatRightness(true);
                catC.SetCatRightness(true);
                catD.SetCatRightness(true);
                break;
            default:
                break;
        }

    }

    /**
     * {@inheritDoc}
     * Mise a jour du mini-jeu
     * Mise a jour de l'animation des chats, de l'etat de l'interrupteur, ainsi que de l'etat de succes et d'echec
     * @author Maxime Emonnot
     */
    @Override
    public void Update() {
        // TODO Auto-generated method stub
        super.Update();
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.EventType.None;
        
        if (!bIsWon && !bIsLost){
            e = CoreSystem.Mouse.GetInstance().Read();
        }

        boolean lastState = ls.GetState();
        ls.Update(e); 
        if (!lastState && lastState != ls.GetState()){
            catA.ChangePosition();
            catB.ChangePosition();
            catC.ChangePosition();
            catD.ChangePosition();
        }
        else if(!ls.GetState()){
            catA.SetCatVisibility(false);
            catB.SetCatVisibility(false);
            catC.SetCatVisibility(false);
            catD.SetCatVisibility(false);
        }
        catA.Update(e);
        catB.Update(e);
        catC.Update(e);
        catD.Update(e);

        bIsWon = (catA.HasWon() || catB.HasWon() || catC.HasWon() || catD.HasWon());
        bIsLost = bIsLost || (catA.HasLost() || catB.HasLost() || catC.HasLost() || catD.HasLost());
    }

    /**
     * {@inheritDoc}
     * Affichage des chats et de l'interrupteur
     * @author Maxime Emonnot
     */
    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        ls.Draw();
        catA.Draw();
        catB.Draw();
        catC.Draw();
        catD.Draw();

    //Legend Drawing
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(0, 200, 136, 280), Color.WHITE, 5);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(0, 200, 136, 280), Color.BLACK, 6);

        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(catAIcon, new Rectangle(70, 205, 64, 64), 6);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(catBIcon, new Rectangle(70, 275, 64, 64), 6);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(catCIcon, new Rectangle(70, 345, 64, 64), 6);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(catDIcon, new Rectangle(70, 415, 64, 64), 6);

        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A = ", new Point(30, 245), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 6);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B = ", new Point(30, 315), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 6);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C = ", new Point(30, 385), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 6);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("D = ", new Point(30, 455), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 6);
    }
    
    private LightSwitch ls;
    private Cat catA;
    private Cat catB;
    private Cat catC;
    private Cat catD;

    private Sprite catAIcon;
    private Sprite catBIcon;
    private Sprite catCIcon;
    private Sprite catDIcon;
}
