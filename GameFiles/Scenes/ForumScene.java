package GameFiles.Scenes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
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
        //Question words initialization
        questionWords.add("comment");
        questionWords.add("pourquoi");
        questionWords.add("quoi");
        questionWords.add("quel");
        questionWords.add("quels");
        questionWords.add("quelle");
        questionWords.add("quelle");
        questionWords.add("lesquelles");
        questionWords.add("laquelle");
        questionWords.add("lesquels");
        questionWords.add("lequel");
        questionWords.add("quand");

        //Selection menu initialization
        askQuestionButton = new Button(new Rectangle(100, 150, 600, 50), "Ask question", () -> {
            question.Clear();
            currentStage = SceneStage.STUDENT_ASK;
        });
        questionListButton = new Button(new Rectangle(100, 250, 600, 50), "View question list", () -> {
            currentStage = SceneStage.STUDENT_QUESTION_LIST;
        });
        lastQuestionPage = new Button(new Rectangle(100, 450, 25, 25), "<", () -> { iCurQuestionPage--;});
        nextQuestionPage = new Button(new Rectangle(500, 450, 25, 25), ">", () -> { iCurQuestionPage++;});

        editQuestionsButton = new Button(new Rectangle(100, 350, 600, 50), "Manage questions", () -> {
            try {
                ResetStudentQuestionDeleteList();
                currentStage = SceneStage.STUDENT_MANAGE_QUESTION;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        lastDeletePage = new Button(new Rectangle(100, 450, 25, 25), "<", () -> { iCurDeletePage--;});
        nextDeletePage = new Button(new Rectangle(500, 450, 25, 25), ">", () -> { iCurDeletePage++;});

        //Ajout question menu 
        addQuestionButton = new Button(new Rectangle(250, 500, 100, 50), "Add question", () -> {
            try {
                if (question.GetText().length() != 0){
                    //Vérification de l'existence de la question
                    ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT question FROM " + dbm.GetDatabaseName() + ".forumQuestion WHERE question = '" + question.GetText() + "';");
                    if (questionSet.next()){
                        System.out.println("Question already asked !");
                        studentQuestionMessage.SetMessage("Question already asked !", Color.RED, 5.0f);
                    }
                    else{
                        //Récupération de l'id du l'utilisateur
                        ResultSet uid = dbm.GetResultFromSQLRequest("SELECT id_etudiant FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + user.GetMail() + "';");
                        if (uid.next()){
                            //Insertion de la nouvelle question
                            int id = uid.getInt("id_etudiant");
                            dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".forumQuestion(question, id_student, answer) VALUES('" + question.GetText() + "'," + id + ", '');");
                            studentQuestionMessage.SetMessage("Question added !", Color.GREEN, 5.0f);
                            System.out.println("Question added !");
                        }
                    }
                }
                else {
                    studentQuestionMessage.SetMessage("Please fill question field", Color.RED, 5.0f);
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
                    studentPropositionMessage.SetMessage("Proposition added !", Color.GREEN, 5.0f);
                }
                else{
                    System.out.println("Proposition is empty !");
                    studentPropositionMessage.SetMessage("Please fill proposition field", Color.RED, 5.0f);
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

    private void UpdateStudent(CoreSystem.Mouse.EventType e) throws SQLException {
        //Ici, l'Update dépend de l'écran en cours dans la scène. Il n'y a pas grand chose à part les Update de base des menus
        ResetStudentQuestionList();

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
                studentQuestionMessage.Update();
                break;
            case STUDENT_QUESTION_LIST:
                if (questionArray.containsKey(iCurQuestionPage)){
                    Iterator<Map.Entry<TextBox, Button>> itrQuestion = questionArray.get(iCurQuestionPage).entrySet().iterator();
                    while (itrQuestion.hasNext()){
                        Button btn = itrQuestion.next().getValue();
                        if (btn.OnClick(e)){
                            btn.ComputeFunction();
                        }
                    }
                }

                if (questionArray.containsKey(iCurQuestionPage - 1)){
                    if (lastQuestionPage.OnClick(e)){
                        lastQuestionPage.ComputeFunction();
                    }
                }
                if (questionArray.containsKey(iCurQuestionPage + 1)){
                    if (nextQuestionPage.OnClick(e)){
                        nextQuestionPage.ComputeFunction();
                    }
                }

                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                questionSearch.Update();
                break;
            case STUDENT_PROPOSE:
                proposition.Update();
                if (addPropositionButton.OnClick(e)){
                    addPropositionButton.ComputeFunction();
                }
                if (exitPropositionButton.OnClick(e)){
                    exitPropositionButton.ComputeFunction();
                }
                studentPropositionMessage.Update();
                break;
            case STUDENT_MANAGE_QUESTION:
                if (questionDeleteArray.containsKey(iCurDeletePage)){
                    Iterator<Map.Entry<TextBox, Button>> itrQuestionDelete = questionDeleteArray.get(iCurDeletePage).entrySet().iterator();
                    while(itrQuestionDelete.hasNext()){
                        Button btn = itrQuestionDelete.next().getValue();
                        if (btn.OnClick(e)){
                            btn.ComputeFunction();
                            break;
                        }
                    }
                }
                if (questionDeleteArray.containsKey(iCurDeletePage - 1 )){
                    if (lastDeletePage.OnClick(e)){
                        lastDeletePage.ComputeFunction();
                    }
                }
                if (questionDeleteArray.containsKey(iCurDeletePage + 1)){
                    if (nextDeletePage.OnClick(e)){
                        nextDeletePage.ComputeFunction();
                    }
                }

                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                studentDeletionMessage.Update();
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
            studentQuestionMessage.Draw();
            break;
        case STUDENT_QUESTION_LIST:
            if (questionArray.containsKey(iCurQuestionPage)){
                Iterator<Map.Entry<TextBox, Button>> itrQuestion = questionArray.get(iCurQuestionPage).entrySet().iterator();
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
            }
            
            if (questionArray.containsKey(iCurQuestionPage - 1)){
                if (lastQuestionPage.IsClicked()){
                    lastQuestionPage.Draw(Color.GREEN);
                }
                else {
                    lastQuestionPage.Draw(Color.GRAY);
                }
            }
            if (questionArray.containsKey(iCurQuestionPage + 1)){
                if (nextQuestionPage.IsClicked()){
                    nextQuestionPage.Draw(Color.GREEN);
                }
                else{
                    nextQuestionPage.Draw(Color.GRAY);
                }
            }

            questionSearch.Draw();

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
            studentPropositionMessage.Draw();
            break;
        case STUDENT_MANAGE_QUESTION: 
            if (questionDeleteArray.containsKey(iCurDeletePage)){
                Iterator<Map.Entry<TextBox, Button>> itrQuestionDelete = questionDeleteArray.get(iCurDeletePage).entrySet().iterator();
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
            }
            if (questionDeleteArray.containsKey(iCurDeletePage - 1 )){
                if (lastDeletePage.IsClicked()){
                    lastDeletePage.Draw(Color.GREEN);
                }
                else{
                    lastDeletePage.Draw(Color.GRAY);
                }
            }
            if (questionDeleteArray.containsKey(iCurDeletePage + 1)){
                if (nextDeletePage.IsClicked()){
                    nextDeletePage.Draw(Color.GREEN);
                }
                else{
                    nextDeletePage.Draw(Color.GRAY);
                }
            }

            if (backButton.IsClicked()){
                backButton.Draw(Color.GREEN);
            }
            else{
                backButton.Draw(Color.GRAY);
            }
            studentDeletionMessage.Draw();
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
        //Définition des mots clés
        String searchQ = questionSearch.GetText();
        searchQ = searchQ.toLowerCase();
        String[] words = searchQ.split(" ");
        List<String> keyWords = new ArrayList<String>();
        for (int i = 0; i < words.length; i++){
            if (words[i].length() > 4 && !questionWords.contains(words[i])){
                keyWords.add(words[i]);
            }
        }

        ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM " +  dbm.GetDatabaseName() + ".forumQuestion");
        ArrayList<Map.Entry<Integer, Map.Entry<String, Integer>>> priorityQuestion = new ArrayList<Map.Entry<Integer, Map.Entry<String, Integer>>>();

        while (questionSet.next()){
            int id = questionSet.getInt("id");
            String questionStr = questionSet.getString("question").toLowerCase();
            
            int nKeyW = 0;
            for (String key : keyWords){
                if (questionStr.contains(key)){
                    nKeyW++;
                }
            }
            priorityQuestion.add(Map.entry(nKeyW, Map.entry(questionStr, id)));
        }

        priorityQuestion.sort((e0, e1) -> e1.getKey() - e0.getKey());
        int maxPriority = priorityQuestion.get(0).getKey();

        for (int i = 0; i < priorityQuestion.size(); i++){
            if (maxPriority == priorityQuestion.get(i).getKey()){
                if (!questionArray.containsKey(i/5)){
                    questionArray.put(i/5, new HashMap<TextBox, Button>());
                }
                int id = priorityQuestion.get(i).getValue().getValue();
                questionArray.get(i/5).put(new TextBox(new Rectangle(50, 50 + (i % 5) * 75, 400, 50), priorityQuestion.get(i).getValue().getKey()), new Button(new Rectangle(500, 50 + (i % 5) * 75, 100, 50), "Add proposition", () -> { 
                    proposition.Clear();
                    currentStage = SceneStage.STUDENT_PROPOSE;
                    iChosenQuestion = id;
                }));
            }
        }
    }
       
    //Reset de la liste des questions à supprimer
    private void ResetStudentQuestionDeleteList() throws SQLException {
        questionDeleteArray.clear();
        ResultSet questionDeleteSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".forumQuestion, " + dbm.GetDatabaseName() + ".etudiant WHERE forumQuestion.id_student = etudiant.id_etudiant AND etudiant.email = '" + user.GetMail() + "';");
        int i = 0;
        while (questionDeleteSet.next()){
            int currentId = questionDeleteSet.getInt("id");
            if (!questionDeleteArray.containsKey(i / 5)){
                questionDeleteArray.put(i/5, new HashMap<TextBox, Button>());
            }
            //On ajoute tous les couples TextBox Button à partir de la base de données. Le bouton va enclencher la fonction en Lambda (suppression de la question).
            questionDeleteArray.get(i/5).put(new TextBox(new Rectangle(50, 50 + (i % 5) * 75, 400, 50), questionDeleteSet.getString("question")), new Button(new Rectangle(500, 50 + (i % 5) * 75, 100, 50), "Delete", () -> {
                try {
                    //On supprime toutes les propositions à la quesiton, puis on supprime la question. Enfin, on fait un reset la liste des boutons
                    dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".forumProposition WHERE id_question = " + currentId + ";");
                    dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".forumQuestion WHERE id = " + currentId + ";");
                    studentDeletionMessage.SetMessage("Question deleted !", Color.GREEN, 5.0f);
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
    private UserMessage studentQuestionMessage = new UserMessage(new Point(100, 225));
    private Button addQuestionButton;
    
    //Menu liste questions Etudiant
    private Map<Integer, HashMap<TextBox, Button>> questionArray = new HashMap<Integer, HashMap<TextBox, Button>>();
    private int iCurQuestionPage = 0;
    private Button lastQuestionPage;
    private Button nextQuestionPage;
    private TypingBox questionSearch = new TypingBox(new Rectangle(100, 500, 600, 50), "Search question...");
    private int iChosenQuestion = 0;
    private final List<String> questionWords = new ArrayList<String>();
    
    //Menu proposition Etudiant
    private TypingBox proposition = new TypingBox(new Rectangle(100, 150, 600, 50), "Enter your proposition...");
    private UserMessage studentPropositionMessage = new UserMessage(new Point(100, 225));
    private Button addPropositionButton;
    private Button exitPropositionButton;
    
    //Menu gestion questions Etudiant
    private Map<Integer, HashMap<TextBox, Button>> questionDeleteArray = new HashMap<Integer, HashMap<TextBox, Button>>();
    private int iCurDeletePage = 0;
    private Button lastDeletePage;
    private Button nextDeletePage;

    private UserMessage studentDeletionMessage = new UserMessage(new Point(100, 500));

    //Back button
    private Button backButton;
    //*** FIN ELEMENTS STUDENT ***//

    //Bouton commun aux deux parties
    private Button exitForumButton;
}   
