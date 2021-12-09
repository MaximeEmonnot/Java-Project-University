package GameFiles.Questions;

import java.awt.*;

import GameFiles.Questions.InteractiveItems.*;

import Exceptions.ProjectException;

public class HandCoinQuestion extends ADoubleAnswerQuestion {

    public HandCoinQuestion(String _question, float _timer, String _answerA, String _answerB, AnswerType _type) {
        super(_question, _timer, _answerA, _answerB, _type);
        //TODO Auto-generated constructor stub
        switch (_type){
        case ANSWER_A:
            rightHand = new Hand("Images/rightHand.png", new Rectangle(175, 150, 128, 128), new Rectangle(225, 166, 48, 48), true);
            leftHand = new Hand("Images/leftHand.png", new Rectangle(375, 150, 128, 128), new Rectangle(425, 166, 48, 48), false);
            break;
        case ANSWER_B:
            rightHand = new Hand("Images/rightHand.png", new Rectangle(175, 150, 128, 128), new Rectangle(225, 166, 48, 48), false);
            leftHand = new Hand("Images/leftHand.png", new Rectangle(375, 150, 128, 128), new Rectangle(425, 166, 48, 48), true);
            break;
        case BOTH:
            rightHand = new Hand("Images/rightHand.png", new Rectangle(175, 150, 128, 128), new Rectangle(225, 166, 48, 48), true);
            leftHand = new Hand("Images/leftHand.png", new Rectangle(375, 150, 128, 128), new Rectangle(425, 166, 48, 48), true);
            break;
            default:
            break;
        }
    }
    
    /**
     * {@inheritDoc}
     * Mise a jour du mini-jeu
     * Mise a jour des differentes animations, et des differents etats de succes et d'echec
     * @author Maxime Emonnot
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
     * Affichage du nez et du doigt
     * @author Maxime Emonnot
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
