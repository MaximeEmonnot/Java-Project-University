package GameFiles.Questions;

import java.awt.*;
import Exceptions.ProjectException;

/**
 * Classe abstraite
 * Definitions de base d'une question a quatre possibilites de reponse
 * @author Maxime Emonnot
 */
public abstract class AQuadrupleAnswerQuestion extends AQuestion {

        /**
     * Differents types de reponses pour les questions a quatre possibilites
     * @author Maxime Emonnot
     */
    public enum AnswerType{
        ANSWER_A,
        ANSWER_B,
        ANSWER_C,
        ANSWER_D,
        ANSWER_AB,
        ANSWER_AC,
        ANSWER_AD,
        ANSWER_BC,
        ANSWER_BD,
        ANSWER_CD,
        ANSWER_ABC,
        ANSWER_ABD,
        ANSWER_ACD,
        ANSWER_BCD,
        ANSWER_ABCD,
        NONE
    }

    /**
     * Constructeur AQuadrupleAnswerQuestion
     * Reprend le constructeur de AQuestion et initialise les differentes propositions ainsi que la reponse
     * @author Maxime Emonnot
     * @param _question Intitule de la question
     * @param _anwserA Proposition A
     * @param _answerB Proposition B
     * @param _answerC Proposition C
     * @param _answerD Proposition D
     * @param _type Reponse correcte
     */
    public AQuadrupleAnswerQuestion(String _question, float _timer, String _anwserA, String _answerB, String _answerC, String _answerD, AnswerType _type) {
        super(_question, _timer);
        //TODO Auto-generated constructor stub
        answerA = _anwserA;
        answerB = _answerB;
        answerC = _answerC;
        answerD = _answerD;
        type = _type;
    }

    /**
     * {@inheritDoc}
     * Affichage des quatres propositions de reponse
     * @author Maxime Emonnot
     */
    @Override
    public void Draw() throws ProjectException{
        super.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("A) " + answerA, new Point(320 - (answerA.length() * 4), 625), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("B) " + answerB, new Point(960 - (answerB.length() * 4), 625), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("C) " + answerC, new Point(320 - (answerC.length() * 4), 650), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("D) " + answerD, new Point(960 - (answerD.length() * 4), 650), new Font("Arial Bold", Font.PLAIN, 16), Color.BLACK, 16);
    }
    
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final String answerD;
    private final AnswerType type;
}
