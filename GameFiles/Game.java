package GameFiles;

import java.util.List;
import java.util.ArrayList;

import GameFiles.Scenes.*;

/**
 * Gestion de la boucle de jeu.
 * Contient tout le deroulement de l'application, calculs et affichage.
 * @author Maxime Emonnot
 */
public class Game {
    /**
     * Constructeur de la boucle de jeu. Initialisation des differents elements composant l'application. 
     * @author Maxime Emonnot
     * @throws Exception Renvoie une erreur de connexion a la base de donnees
     */
    public Game() throws Exception{
        scenes.add(new DatabaseConnectionScene(() -> {
            try {
                scenes.add(new ConnectionScene());
                scenes.add(new SearchScene());
                scenes.add(new QuizzScene());
                scenes.add(new TeacherScene());
                scenes.add(new AdminScene());
                scenes.add(new ForumScene());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }));
    }

    /**
     * Deroulement de la boucle de jeu.
     * Realise d'abord une phasse de calcul, puis une phase de definition des affichages de la frame, avant de rendre le tout.
     * @author Maxime Emonnot
     * @throws Exception Les differentes erreurs contenues dans UpdateFrame
     * @see Game#UpdateFrame()
     * @see Game#RenderFrame()
     */
    public void Go() throws Exception{
        UpdateFrame();
        RenderFrame();
        GraphicsEngine.GraphicsSystem.GetInstance().Render();
    }

    /**
     * Methode privee contenant l'ensemble des calculs de l'application
     * @author Maxime Emonnot
     * @throws Exception Les differentes erreurs pouvant arrier durant le deroulement de la scene.
     * @see Game#Go()
     */
    private void UpdateFrame() throws Exception{
        //Must be called to update the DeltaTime value
        CoreSystem.Timer.GetInstance().Update();

        scenes.get(iCurScene).Update();
        if (scenes.get(iCurScene).ChangeScene()){
            iCurScene = scenes.get(iCurScene).GetNextSceneIndex();
        }
    }

    /**
     * Methode privee contenant l'ensemble des methodes d'affichage
     * @author Maxime Emonnot
     * @throws Exceptions.ProjectException Erreur d'instanciation de GraphisSystem
     * @see Game#Go()
     */
    private void RenderFrame() throws Exceptions.ProjectException {
        scenes.get(iCurScene).Draw();
    }

    private List<AScene> scenes = new ArrayList<AScene>();
    private int iCurScene = 0;
}
