package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;

/**
 * Classe abstraite.
 * <p>Contient toutes les initialisation de base d'une question, ainsi que les calculs et l'affichage de base
 * @author Maxime Emonnot
 * @version 1.1.0
 */
public abstract class AQuestion {
    /**
     * Constructeur AQuestion
     * <p>Initialisattion a partir d'une question donnee
     * @author Maxime Emonnot
     * @param _question Intitule de la question
     * @param _timer Temps necessaire pour repondre
     */
    public AQuestion(String _question, float _timer){
        question = _question;
        timer = _timer;
    }

    /**
     * Calculs de base d'une question.
     * <p>Gestion du timer pour la question en cours
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
     * <p>Affichage de la question, du timer et du message de victoire/defaite
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException
    {
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(0, 550, 1280, 150), Color.WHITE, 15);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(0, 550, 1280, 150), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(question, new Point(640 - (question.length() * 4), 575), new Font("Arial Bold", Font.BOLD, 16), Color.BLACK, 16);
    
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(String.valueOf(timer), new Point(50, 600), new Font ("Arial Bold", Font.PLAIN, 16), Color.BLACK, 18);

        if (bIsWon){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText("WON", new Point(1100, 575), new Font("Arial Bold", Font.BOLD, 22), Color.GREEN, 20);
        }
        else if (bIsLost){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText("LOST", new Point(1100, 575), new Font("Arial Bold", Font.BOLD, 22), Color.RED, 20);
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

    private float timer;
    private String question;
    protected boolean bIsWon;
    protected boolean bIsLost;
}
