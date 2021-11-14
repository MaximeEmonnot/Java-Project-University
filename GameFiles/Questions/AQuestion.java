package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;

/**
 * Classe abstraite.
 * Contient toutes les initialisation de base d'une question, ainsi que les calculs et l'affichage de base
 * @author Maxime Emonnot
 */
public abstract class AQuestion {
    /**
     * Constructeur AQuestion
     * Initialisattion a partir d'une question donnee
     * @author Maxime Emonnot
     * @param _question Intitule de la question
     */
    public AQuestion(String _question){
        question = _question;
    }

    /**
     * Calculs de base d'une question.
     * Gestion du timer pour la question en cours
     * @author Maxime Emonnot
     */
    public void Update(){
        if (!bIsWon && !bIsLost){
            timer -= CoreSystem.Timer.GetInstance().DeltaTime();
            if (timer <= 0.0f){
                bIsLost = true;
            }
        }
    }
    /**
     * Affichage de base d'une question
     * Affichage de la question, du timer et du message de victoire/defaite
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException
    {
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(0, 480, 800, 105), Color.WHITE, 15);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(0, 480, 800, 105), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(question, new Point((int)((800 - (question.length() * 8))/2), 500), new Font("Arial Bold", Font.BOLD, 16), Color.BLACK, 16);
     
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(String.valueOf(timer), new Point(700, 525), new Font ("Arial Bold", Font.PLAIN, 16), Color.BLACK, 18);

        if (bIsWon){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText("WON", new Point(700, 550), new Font("Arial Bold", Font.BOLD, 22), Color.GREEN, 20);
        }
        else if (bIsLost){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText("LOST", new Point(700, 550), new Font("Arial Bold", Font.BOLD, 22), Color.RED, 20);
        }

    }

    /**
     * Recuperation de l'etat d'echec
     * @author Maxime Emonnot
     * @return Vrai si la question est echouee, Faux sinon
     */
    public boolean IsLost(){
        return bIsLost;
    }
    /**
     * Recuperation de l'etat de reussite
     * @author Maxime Emonnot
     * @return Vrai si la question est reussie, Faux sinon
     */
    public boolean IsWon(){
        return bIsWon;
    }

    private float timer = 10.0f;
    private String question;
    protected boolean bIsWon;
    protected boolean bIsLost;
}
