package GameFiles.Questions;

import java.awt.*;
import Exceptions.ProjectException;

/**
 * Classe abstraite
 * <p>Definitions de base d'une question a trois possibilites de reponse
 * @author Maxime Emonnot
 * @version 1.1.0
 */
public abstract class ATripleAnswerQuestion extends AQuestion{

    /**
     * Differents types de reponses pour les questions a trois possibilites
     * @author Maxime Emonnot
     */
    public enum AnswerType{
        ANSWER_A,
        ANSWER_B,
        ANSWER_C,
        ANSWER_AB,
        ANSWER_AC,
        ANSWER_BC,
        ANSWER_ABC,
        NONE
    }

    /**
     * Constructeur ATripleAnswerQuestion
     * <p>Reprend le constructeur de AQuestion et initialise les differentes propositions ainsi que la reponse
     * @author Maxime Emonnot
     * @param _question Intitule de la question
     * @param _timer Temps necessaire pour repondre
     * @param _answerA Proposition A
     * @param _answerB Proposition B
     * @param _answerC Proposition C
     * @param _type Reponse correcte
     */
    public ATripleAnswerQuestion(String _question, float _timer, String _answerA, String _answerB, String _answerC, AnswerType _type) {
        super(_question, _timer);
        //TODO Auto-generated constructor stub
        answerA = _answerA;
        answerB = _answerB;
        answerC = _answerC;
        type = _type;
    }

    /**
     * {@inheritDoc}
     * <p>Affichage des trois propositions de reponse
     * @author Maxime Emonnot
     */
    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A) " + answerA, new Point(320 - (answerA.length() * 4), 625), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B) " + answerB, new Point(960 - (answerB.length() * 4), 625), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C) " + answerC, new Point(640 - (answerC.length() * 4), 650), new Font("Arial Bold", Font.BOLD, 16), Color.BLACK, 16);
    }
    
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final AnswerType type;
}
