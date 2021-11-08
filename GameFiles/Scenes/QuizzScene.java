package GameFiles.Scenes;

import java.sql.ResultSet;
import java.sql.SQLException;
import Exceptions.ProjectException;
import java.awt.*;

public class QuizzScene extends AScene {

    public QuizzScene() throws Exception{
        super();
        nextSceneIndex = 2;
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        if (lives <= 0){
            SendStatistics();
            bChangeScene = true;
            rightAnswers = 0;
        }
        
        questions.get(iCurQuestion).Update();
        if (questions.get(iCurQuestion).IsLost() || questions.get(iCurQuestion).IsWon()){
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
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        questions.get(iCurQuestion).Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("LIVES : " + lives, new Point(10, 500), Color.BLACK, 15);
    }
    
    private void SendStatistics(){
        try{
            ResultSet studentSet = dbm.GetResultFromSQLRequest("SELECT id_etudiant FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + user.GetMail() + "';");
            if (studentSet.next()){
                String[] statsArgs = currentQuizz.split(" - ");
                int studentId = studentSet.getInt("id_etudiant");
                ResultSet subjectSet = dbm.GetResultFromSQLRequest("SELECT id FROM " + dbm.GetDatabaseName() + ".sujets WHERE domaine = '" + statsArgs[1] + "' AND categorie = '" + statsArgs[2] + "' AND niveau = '" + statsArgs[3] + "';");
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

    private float timerNextQuestion = 1.0f;
    private int rightAnswers = 0;
}
