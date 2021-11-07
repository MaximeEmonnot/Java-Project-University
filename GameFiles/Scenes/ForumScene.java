package GameFiles.Scenes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;

import Exceptions.ProjectException;
import GameFiles.User.*;
import GraphicsEngine.GraphicsSystem;
import MenuSystem.*;
import MenuSystem.Button;

public class ForumScene extends AScene {

    private enum SceneStage{
        SELECTION,
        STUDENT_ASK,
        STUDENT_QUESTION_LIST,
        STUDENT_PROPOSE,
        STUDENT_MANAGE_QUESTION,
        TEACHER_INFOS
    }

    public ForumScene() throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
        ///PARTIE ETUDIANT
        InitStudent();
        ///PARTIE PROF
        InitTeacher();

        ///PARTIE COMMUNE
        exitForumButton = new Button(new Rectangle(700, 500, 75, 50), "Exit", () -> {
            bChangeScene = true;
            if (user instanceof Student){
                nextSceneIndex = 2;
            }
            else if (user instanceof Teacher){
                nextSceneIndex = 4;
            }
        });
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        //Récupération de la dernière entrée souris
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        //Partie étudiant
        if (user instanceof Student){
            UpdateStudent(e);
        }
        //Partie prof
        else if (user instanceof Teacher){
            UpdateTeacher(e);
        }
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);
        //Partie étudiant
        if (user instanceof Student){
            DrawStudent();
        }
        //Partie prof
        else if (user instanceof Teacher){
            DrawTeacher();
        }
    }

    /*NOTE IMPORTANTE :
     * J'ai séparé les deux parties Student et Teacher pour que ce soit plus simple de se retrouver dans le code
     * La partie Student peut donc servir d'exemple pour la création de la partie Teacher 
     */

    private void InitStudent(){
        //Selection menu initialization
        askQuestionButton = new Button(new Rectangle(100, 150, 600, 50), "Ask question", () -> {
            question.Clear();
            currentStage = SceneStage.STUDENT_ASK;
        });
        questionListButton = new Button(new Rectangle(100, 250, 600, 50), "View question list", () -> {
            try {
                ResetStudentQuestionList();
                currentStage = SceneStage.STUDENT_QUESTION_LIST;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        editQuestionsButton = new Button(new Rectangle(100, 350, 600, 50), "Manage questions", () -> {
            try {
                ResetStudentQuestionDeleteList();
                currentStage = SceneStage.STUDENT_MANAGE_QUESTION;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        //Ajout question menu 
        addQuestionButton = new Button(new Rectangle(250, 500, 100, 50), "Add question", () -> {
            try {
                if (question.GetText().length() != 0){
                    //Vérification de l'existence de la question
                    ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT question FROM " + dbm.GetDatabaseName() + ".forumQuestion WHERE question = '" + question.GetText() + "';");
                    if (questionSet.next()){
                        System.out.println("Question already asked !");
                    }
                    else{
                        //Récupération de l'id du l'utilisateur
                        ResultSet uid = dbm.GetResultFromSQLRequest("SELECT id_etudiant FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + user.GetMail() + "';");
                        if (uid.next()){
                            //Insertion de la nouvelle question
                            int id = uid.getInt("id_etudiant");
                            dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".forumQuestion(question, id_student, answer) VALUES('" + question.GetText() + "'," + id + ", '');");
                            System.out.println("Question added !");
                        }
                    }
                }
                else {
                    System.out.println("Question is empty !");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        //Ajout proposition menu
        addPropositionButton = new Button(new Rectangle(250, 500, 100, 50), "Add proposition", () -> {
            try {
                if (proposition.GetText().length() != 0){
                    //Insertion de la proposition de réponse, pas de vérification d'existence car les étudiants n'ont pas accès aux autres propositions
                    dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".forumProposition(id_question, proposition) VALUES(" + iChosenQuestion + ", '" + proposition.GetText() + "');");
                    System.out.println("Proposition added !");
                }
                else{
                    System.out.println("Proposition is empty !");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        exitPropositionButton = new Button(new Rectangle(700, 500, 75, 50), "Back to question list", () -> {
            currentStage = SceneStage.STUDENT_QUESTION_LIST;
        });
        backButton = new Button(new Rectangle(700, 500, 75, 50), "Back", () -> {
            currentStage = SceneStage.SELECTION;
        });
    }
    private void InitTeacher(){
        
    }

    private void UpdateStudent(CoreSystem.Mouse.EventType e) {
        //Ici, l'Update dépend de l'écran en cours dans la scène. Il n'y a pas grand chose à part les Update de base des menus
        switch(currentStage){
            case SELECTION:
            if (askQuestionButton.OnClick(e)){
                askQuestionButton.ComputeFunction();
            }
            if (questionListButton.OnClick(e)){
                questionListButton.ComputeFunction();
            }
            if (editQuestionsButton.OnClick(e)){
                editQuestionsButton.ComputeFunction();
            }
            if (exitForumButton.OnClick(e)){
                exitForumButton.ComputeFunction();
                }
                break;
                case STUDENT_ASK:
                question.Update();
                if (addQuestionButton.OnClick(e)){
                    addQuestionButton.ComputeFunction();
                }
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                break;
            case STUDENT_QUESTION_LIST:
            Iterator<Map.Entry<TextBox, Button>> itrQuestion = questionArray.entrySet().iterator();
                while (itrQuestion.hasNext()){
                    Button btn = itrQuestion.next().getValue();
                    if (btn.OnClick(e)){
                        btn.ComputeFunction();
                    }
                }
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                break;
                case STUDENT_PROPOSE:
                proposition.Update();
                if (addPropositionButton.OnClick(e)){
                    addPropositionButton.ComputeFunction();
                }
                if (exitPropositionButton.OnClick(e)){
                    exitPropositionButton.ComputeFunction();
                }
                break;
                case STUDENT_MANAGE_QUESTION:
                Iterator<Map.Entry<TextBox, Button>> itrQuestionDelete = questionDeleteArray.entrySet().iterator();
                while(itrQuestionDelete.hasNext()){
                    Button btn = itrQuestionDelete.next().getValue();
                    if (btn.OnClick(e)){
                        btn.ComputeFunction();
                    }
                }
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                break;
                default:
                break;
            }
        }
    private void UpdateTeacher(CoreSystem.Mouse.EventType e) {
        switch(currentStage){
            case SELECTION:
                if (exitForumButton.OnClick(e)){
                    exitForumButton.ComputeFunction();
                }
            break;
            case TEACHER_INFOS:
            
            break;
            default:
            break;
        }
    }
        
    private void DrawStudent() throws ProjectException {
        switch(currentStage){
            case SELECTION:
            if (askQuestionButton.IsClicked()){
                askQuestionButton.Draw(Color.GREEN);
            }
            else{
                askQuestionButton.Draw(Color.GRAY);
            }
            if (questionListButton.IsClicked()){
                questionListButton.Draw(Color.GREEN);
            }
            else{
                questionListButton.Draw(Color.GRAY);
            }
            if (editQuestionsButton.IsClicked()){
                editQuestionsButton.Draw(Color.GREEN);
            }
            else{
                editQuestionsButton.Draw(Color.GRAY);
            }
            if (exitForumButton.IsClicked()){
                exitForumButton.Draw(Color.GREEN);
            }
            else{
                exitForumButton.Draw(Color.GRAY);
            }
            break;
            case STUDENT_ASK:
            question.Draw();
            if (addQuestionButton.IsClicked()){
                addQuestionButton.Draw(Color.GREEN);
            }
            else{
                addQuestionButton.Draw(Color.GRAY);
            }
            if (backButton.IsClicked()){
                backButton.Draw(Color.GREEN);
            }
            else{
                backButton.Draw(Color.GRAY);
            }
            break;
        case STUDENT_QUESTION_LIST:
        
        Iterator<Map.Entry<TextBox, Button>> itrQuestion = questionArray.entrySet().iterator();
        while(itrQuestion.hasNext()){
            Map.Entry<TextBox, Button> currentPair = itrQuestion.next();
            currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
            if (currentPair.getValue().IsClicked()){
                currentPair.getValue().Draw(Color.GREEN);
                }
                else{
                    currentPair.getValue().Draw(Color.GRAY);
                }
            }
            
            if (backButton.IsClicked()){
                backButton.Draw(Color.GREEN);
            }
            else{
                backButton.Draw(Color.GRAY);
            }
            break;
            case STUDENT_PROPOSE:
            proposition.Draw();
            if (addPropositionButton.IsClicked()){
                addPropositionButton.Draw(Color.GREEN);
            }
            else{
                addPropositionButton.Draw(Color.GRAY);
            }
            if (exitPropositionButton.IsClicked()){
                exitPropositionButton.Draw(Color.GREEN);
            }
            else{
                exitPropositionButton.Draw(Color.GRAY);
            }
            break;
        case STUDENT_MANAGE_QUESTION:
        
        Iterator<Map.Entry<TextBox, Button>> itrQuestionDelete = questionDeleteArray.entrySet().iterator();
        while (itrQuestionDelete.hasNext()){
            Map.Entry<TextBox, Button> currentPair = itrQuestionDelete.next();
            currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
            if (currentPair.getValue().IsClicked()){
                currentPair.getValue().Draw(Color.GREEN);
                }
                else{
                    currentPair.getValue().Draw(Color.GRAY);
                }
            }
            
            if (backButton.IsClicked()){
                backButton.Draw(Color.GREEN);
            }
            else{
                backButton.Draw(Color.GRAY);
            }
            break;
            default:
            break;
        }
    }
    private void DrawTeacher() throws ProjectException {
        switch(currentStage){
            case SELECTION:
                if (exitForumButton.IsClicked()){
                    exitForumButton.Draw(Color.GREEN);
                }
                else{
                    exitForumButton.Draw(Color.GRAY);
                }
            break;
            case TEACHER_INFOS:
            
            break;
            default:
            break;
        }
    }
        
    //Reset de la list des questions
    private void ResetStudentQuestionList() throws SQLException {
        questionArray.clear();
        ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM " +  dbm.GetDatabaseName() + ".forumQuestion");
        int i = 0;
        while (questionSet.next() && i < 6){
            int id = questionSet.getInt("id");
            //Ici on ajoute un nouveau couple de TextBox Button à partir des infos de la base de données. Chaque bouton permet d'accéder à l'ajout de proposition pour la question sélectionnée
            questionArray.put(new TextBox(new Rectangle(50, 50 + i * 75, 400, 50), questionSet.getString("question")), new Button(new Rectangle(500, 50 + i * 75, 100, 50), "Add proposition", () -> { 
                proposition.Clear();
                currentStage = SceneStage.STUDENT_PROPOSE;
                iChosenQuestion = id;
            }));
            i++;
        }
    }
       
    //Reset de la liste des questions à supprimer
    private void ResetStudentQuestionDeleteList() throws SQLException {
        questionDeleteArray.clear();
        ResultSet questionDeleteSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".forumQuestion, " + dbm.GetDatabaseName() + ".etudiant WHERE forumQuestion.id_student = etudiant.id_etudiant AND etudiant.email = '" + user.GetMail() + "';");
        int i = 0;
        while (questionDeleteSet.next() && i < 6){
            int currentId = questionDeleteSet.getInt("id");
            //On ajoute tous les couples TextBox Button à partir de la base de données. Le bouton va enclencher la fonction en Lambda (suppression de la question).
            questionDeleteArray.put(new TextBox(new Rectangle(50, 50 + i * 75, 400, 50), questionDeleteSet.getString("question")), new Button(new Rectangle(500, 50 + i * 75, 100, 50), "Delete", () -> {
                try {
                    //On supprime toutes les propositions à la quesiton, puis on supprime la question. Enfin, on fait un reset la liste des boutons
                    dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".forumProposition WHERE id_question = " + currentId + ";");
                    dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".forumQuestion WHERE id = " + currentId + ";");
                    System.out.println("Question deleted !");
                    ResetStudentQuestionDeleteList();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }));
            i++;
        }
    }
       
    //*** ELEMENTS STUDENT ***//
    //Etapes de la scène (type d'écran)
    private SceneStage currentStage = SceneStage.SELECTION;

    //Menu Selection Etudiant
    private Button askQuestionButton;
    private Button questionListButton;
    private Button editQuestionsButton;
    
    //Menu poser question Etudiant
    private TypingBox question = new TypingBox(new Rectangle(100, 150, 600, 50), "Enter your question...");
    private Button addQuestionButton;
    
    //Menu liste questions Etudiant
    private Map<TextBox, Button> questionArray = new HashMap<TextBox, Button>();
    private int iChosenQuestion = 0;
    
    //Menu proposition Etudiant
    private TypingBox proposition = new TypingBox(new Rectangle(100, 150, 600, 50), "Enter your proposition...");
    private Button addPropositionButton;
    private Button exitPropositionButton;
    
    //Menu gestion questions Etudiant
    private Map<TextBox, Button> questionDeleteArray = new HashMap<TextBox, Button>();
    
    //Back button
    private Button backButton;
    //*** FIN ELEMENTS STUDENT ***//

    //Bouton commun aux deux parties
    private Button exitForumButton;
}   
