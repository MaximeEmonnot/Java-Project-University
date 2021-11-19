package GameFiles.Scenes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataBaseSystem.DataBaseManager;
import Exceptions.ProjectException;
import GameFiles.Questions.AQuestion;
import GameFiles.User.AUser;

/**
 * Classe abstraite
 * Definit l'ensemble des methodes de base concernant les differentes scenes 
 * @author Maxime Emonnot
 */
public abstract class AScene {
    /**
     * Constructeur AScene.
     * Pas d'initialisation particuliere
     * @author Maxime Emonnot
     */
    public AScene(){}

    /**
     * Calculs et mises a jour de la scene
     * @author Maxime Emonnot
     * @throws SQLException Erreurs lors des envois de requetes SQL
     */
    public abstract void Update() throws SQLException;
    /**
     * Affichage de la scene
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public abstract void Draw() throws ProjectException;

    /**
     * Recuperation de l'index de la prochaine scene. Appele dans Game.Go() lorsque bChangeScene vaut true
     * @author Maxime Emonnot
     * @return L'index de la prochaine scene
     */
    public int GetNextSceneIndex(){
        return nextSceneIndex;
    }

    /**
     * Changement de scene et reinitialisation de l'etat de changement. Appels dans Game.Go()
     * @author Maxime Emonnot
     * @return L'etat de changement de la scene
     */
    public boolean ChangeScene(){
        boolean out = bChangeScene;
        bChangeScene = false;
        return out;
    }
    
    protected static DataBaseManager dbm;
    protected static AUser user;
    protected static String currentQuizz;

    protected static List<AQuestion> questions = new ArrayList<AQuestion>();
    protected static boolean bChangeScene = false;
    protected static int lives = 3;
    protected static int iCurQuestion = 0;
    protected int nextSceneIndex;
}
