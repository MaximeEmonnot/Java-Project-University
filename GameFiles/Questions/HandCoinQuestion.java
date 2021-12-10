package GameFiles.Questions;

import java.awt.*;

import GameFiles.Questions.InteractiveItems.*;

import Exceptions.ProjectException;

/**
 * Mini-jeu a deux proposition de reponse
 * <p>Deux mains representent les deux possibilites, l'utilisateur doit cliquer sur la main correspondant à la bonne reponse
 * @author Godfree Akakpo
 * @version 1.4.0
 */
public class HandCoinQuestion extends ADoubleAnswerQuestion {

    /**
     * Contructeur HandCoinQuestion
     * <p>Reprend le constructeur de ADoubleAnswerQuestion, et initialise les mains
     * @author Godfree Akakpo
     * @param _question Intitule question
     * @param _timer Temps necessaire pour repondre
     * @param _answerA Proposition A
     * @param _answerB Proposition B
     * @param _type Reponse correcte
     */
    public HandCoinQuestion(String _question, float _timer, String _answerA, String _answerB, AnswerType _type) {
        super(_question, _timer, _answerA, _answerB, _type);
        //TODO Auto-generated constructor stub
        switch (_type){
        case ANSWER_A:
            rightHand = new Hand("Images/rightHand.png", new Rectangle(500, 150, 128, 128), new Rectangle(550, 166, 48, 48), true);
            leftHand = new Hand("Images/leftHand.png", new Rectangle(650, 150, 128, 128), new Rectangle(700, 166, 48, 48), false);
            break;
        case ANSWER_B:
            rightHand = new Hand("Images/rightHand.png", new Rectangle(500, 150, 128, 128), new Rectangle(550, 166, 48, 48), false);
            leftHand = new Hand("Images/leftHand.png", new Rectangle(650, 150, 128, 128), new Rectangle(700, 166, 48, 48), true);
            break;
        case BOTH:
            rightHand = new Hand("Images/rightHand.png", new Rectangle(500, 150, 128, 128), new Rectangle(550, 166, 48, 48), true);
            leftHand = new Hand("Images/leftHand.png", new Rectangle(650, 150, 128, 128), new Rectangle(700, 166, 48, 48), true);
            break;
        default:
            break;
        }
    }
    
    /**
     * {@inheritDoc}
     * Mise a jour du mini-jeu
     * Mise a jour des differents etats de succes et d'echec
     * @author Godfree Akakpo
     */
    @Override
    public void Update() {
        // TODO Auto-generated method stub
        super.Update();

        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        leftHand.Update(e, bIsWon || bIsLost);
        rightHand.Update(e, bIsWon || bIsLost);

        bIsWon = leftHand.HasWon() || rightHand.HasWon();
        bIsLost = leftHand.HasLost() || rightHand.HasLost();
    }
    
    /**
     * {@inheritDoc}
     * Affichage des deux mains
     * @author Godfree Akakpo
     */
    @Override 
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.LIGHT_GRAY);

        leftHand.Draw();
        rightHand.Draw();
    }
    
    private Hand leftHand;
    private Hand rightHand;
}
