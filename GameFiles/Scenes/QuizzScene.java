package GameFiles.Scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Exceptions.ProjectException;
import SoundEngine.SoundSystem;

/**
 * Scene de jeu.
 * Deroulement de la phase de quiz, ou l'etudiant doit repondre rapidement a differentes questions, a travers des mini-jeux
 * @author Maxime Emonnot
 */
public class QuizzScene extends AScene {
    
    /**
     * Constructeur QuizzScene
     * Initialisation de l'index de la prochaine scene
     * @author Maxime Emonnot
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public QuizzScene() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        nextSceneIndex = 2;
        
        SoundSystem.GetInstance().AddNewSong("Audio/coin.wav");
        SoundSystem.GetInstance().AddNewSong("Audio/bigFart.wav");
        SoundSystem.GetInstance().AddNewSong("Audio/smallFart.wav");
        SoundSystem.GetInstance().AddNewSong("Audio/wetFart.wav");
    }

    /**
     * {@inheritDoc}
     * Mise a jour et calculs des differents mini-jeux.
     * Traitement des donnees de reussite ou d'echec
     * @author Maxime Emonnot
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws ProjectException
     */
    @Override
    public void Update() throws UnsupportedAudioFileException, IOException, LineUnavailableException, ProjectException {
        // TODO Auto-generated method stub
        if (CoreSystem.Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_ESCAPE)){
            bChangeScene = true;
            rightAnswers = 0;
            timerNextQuestion = 1.0f;
        }

        if (lives <= 0){
            SendStatistics();
            bChangeScene = true;
            rightAnswers = 0;
        }
        
        questions.get(iCurQuestion).Update();
        if (questions.get(iCurQuestion).IsLost() || questions.get(iCurQuestion).IsWon()){
            if (!bIsPlayingSong){
                threadPool.execute(() -> {
                    bIsPlayingSong = true;
                    if (questions.get(iCurQuestion).IsWon()){
                        try {
                            SoundSystem.GetInstance().RestartSong(0);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException
                                | ProjectException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            SoundSystem.GetInstance().RestartSong((int)(Math.random() * 3) + 1);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException
                                | ProjectException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
            }
            timerNextQuestion -= CoreSystem.Timer.GetInstance().DeltaTime();
            if (timerNextQuestion <= 0.0f){
                if (questions.get(iCurQuestion).IsLost()){
                    lives--;
                }
                else{
                    rightAnswers++;
                }
                iCurQuestion++;
                if (iCurQuestion >= questions.size()){
                    SendStatistics();
                    bChangeScene = true;
                    rightAnswers = 0;
                }
                timerNextQuestion = 1.0f;
            }
        }
        else{
            bIsPlayingSong = false;
        }
    }

    /**
     * {@inheritDoc}
     * Affichage des differents mini-jeux
     * @author Maxime Emonnot
     */
    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        questions.get(iCurQuestion).Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("LIVES : " + lives, new Point(50, 575), Color.BLACK, 15);
    }
    
    private void SendStatistics(){
        try{
            ResultSet studentSet = dbm.GetResultFromSQLRequest("SELECT id_etudiant FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + user.GetMail() + "';");
            if (studentSet.next()){
                String[] statsArgs = currentQuizz.split(" - ");
                int studentId = studentSet.getInt("id_etudiant");
                ResultSet subjectSet = dbm.GetResultFromSQLRequest("SELECT id FROM " + dbm.GetDatabaseName() + ".sujets WHERE domaine = '" + statsArgs[1].replace("'", "\\'") + "' AND categorie = '" + statsArgs[2].replace("'", "\\'") + "' AND niveau = '" + statsArgs[3] + "';");
                if (subjectSet.next()) {
                    int subjectId = subjectSet.getInt("id");
                    float currentScore = (float)rightAnswers / (float)questions.size() * 100;
                    ResultSet testStatSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".statistique WHERE id_statistique = " + studentId + " AND id_subject = " + subjectId + ";");
                    if (testStatSet.next()){
                        if (testStatSet.getFloat("score") < currentScore){
                            dbm.SendSQLRequest("UPDATE " + dbm.GetDatabaseName() + ".statistique SET score = " + currentScore + " WHERE id_subject = " + subjectId + ";");
                        }
                    }
                    else{
                        dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".statistique (id_statistique, score, id_subject) VALUES (" + studentId + ", " + currentScore + ", " + subjectId + ");");
                    }
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private ExecutorService threadPool = Executors.newFixedThreadPool(3);
    private float timerNextQuestion = 1.0f;
    private int rightAnswers = 0;
    private boolean bIsPlayingSong = false;
}
