package GameFiles.Scenes;

import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.*;

import Exceptions.ProjectException;
import MenuSystem.*;
import MenuSystem.Button;

public class TeacherScene extends AScene {

    public enum SceneStage{
        SELECTION,
        ADD_DOMAIN,
        ADD_QUESTION,
        QUESTION_LIST
    }

    public TeacherScene() throws ClassNotFoundException, SQLException{
        super();
        addQButton = new Button(new Rectangle(25, 500, 100, 50), "Add", () -> {
            try {
                ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".questions, " + dbm.GetDatabaseName() + ".sujets WHERE " + dbm.GetDatabaseName() + ".questions.id_subject = " + dbm.GetDatabaseName() + ".sujets.id AND domaine = '" + domainChoice.GetText() + "' AND categorie = '" + categoryChoice.GetText() + "' AND niveau = '" + levelChoice.GetText() + "' AND question = '" + question.GetText() + "' AND reponseA = '" + answerA.GetText() + "' AND reponseB = '" + answerB.GetText() + "' AND reponseC = '" + answerC.GetText() + "' AND reponseD = '" + answerD.GetText() + "';");
                if (questionSet.next()){
                    System.out.println("Question already added !");             
                }
                else{
                    int codeA = checkAnswerA.IsChecked() ? 1 : 0;
                    int codeB = checkAnswerB.IsChecked() ? 1 : 0;
                    int codeC = checkAnswerC.IsChecked() ? 1 : 0;
                    int codeD = checkAnswerD.IsChecked() ? 1 : 0;
                    String answerCode = Integer.toString(codeA) + Integer.toString(codeB);
                    if (answerC.GetText().length() != 0){
                        answerCode += Integer.toString(codeC);
                    }
                    if (answerD.GetText().length() != 0){
                        answerCode += Integer.toString(codeD);
                    }
                    ResultSet domainSet = dbm.GetResultFromSQLRequest("SELECT id FROM " + dbm.GetDatabaseName() + ".sujets WHERE domaine = '" + domainChoice.GetText() + "' AND categorie = '" + categoryChoice.GetText() + "' AND niveau = '" + levelChoice.GetText() + "';");
                    if (domainSet.next()){
                        int subjectId = domainSet.getInt("id");
                        ResultSet teacherSet = dbm.GetResultFromSQLRequest("SELECT id_professeur FROM " + dbm.GetDatabaseName() + ".professeur WHERE email = '" + user.GetMail() + "';");
                        if (teacherSet.next()){
                            dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".questions (id_prof, id_subject, difficulte, question, code_reponses, reponseA, reponseB, reponseC, reponseD)" +
                                               "VALUES (" + teacherSet.getInt("id_professeur") + ", " + subjectId + ", 'Facile' , '" + question.GetText() + "', '" + answerCode + "', '" + answerA.GetText() + "', '" + answerB.GetText() + "', '" + answerC.GetText() + "', '" + answerD.GetText() + "');");
                            System.out.println("Question added ! ");
                        }
                    }
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    
        addDButton = new Button(new Rectangle(25, 500, 100, 50), "Add", () -> {
            try {
                ResultSet domainSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".sujets WHERE domaine = '" + domain.GetText() + "' AND categorie = '" + category.GetText() + "' AND niveau = '" + levelSelection.GetText() + "';");
                if (domainSet.next()){
                    System.out.println("Domain already added !");
                }
                else{
                    dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".sujets (domaine, categorie, niveau) VALUES ('" + domain.GetText() + "', '" + category.GetText() + "', '" + levelSelection.GetText() + "');");
                    System.out.println("Domain added !");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        addDomainButton = new Button(new Rectangle(100, 150, 600, 50), "Add domain", () -> {
            currentStage = SceneStage.ADD_DOMAIN;
        });
        addQuestionButton = new Button(new Rectangle(100, 250, 600, 50), "Add question", () -> {
            domains.clear();
            categories.clear();
            level.clear();
            
            ResultSet rSet;
            try {
                rSet = dbm.GetResultFromSQLRequest("SELECT domaine, categorie, niveau FROM " + dbm.GetDatabaseName() + ".sujets");
                while(rSet.next()){
                    domains.add(rSet.getString("domaine"));
                    if (!categories.containsKey(rSet.getString("domaine"))){
                        categories.put(rSet.getString("domaine"), new HashSet<String>());
                    }
                    categories.get(rSet.getString("domaine")).add(rSet.getString("categorie"));
                    if (!level.containsKey(rSet.getString("domaine"))){
                        level.put(rSet.getString("domaine"), new HashMap<String, HashSet<String>>());
                        level.get(rSet.getString("domaine")).put(rSet.getString("categorie"), new HashSet<String>());
                    }
                    else if (!level.get(rSet.getString("domaine")).containsKey(rSet.getString("categorie"))){
                        level.get(rSet.getString("domaine")).put(rSet.getString("categorie"), new HashSet<String>());
                    }
                    level.get(rSet.getString("domaine")).get(rSet.getString("categorie")).add(rSet.getString("niveau"));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentStage = SceneStage.ADD_QUESTION;
        });
        editQuestionListButton = new Button(new Rectangle(100, 350, 600, 50), "Edit questions", () -> {
            try {
                ResetQuestionList();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            currentStage = SceneStage.QUESTION_LIST;
        });
        
        forumButton = new Button(new Rectangle(100, 450, 600, 50), "Go to forum", () -> {
            bChangeScene = true;
            nextSceneIndex = 6;
        });
        backButton = new Button(new Rectangle(600, 500, 100, 50), "Back", () -> {
            currentStage = SceneStage.SELECTION;
        });
   
        levelSelection.AddChoice("Primaire");
        levelSelection.AddChoice("College");
        levelSelection.AddChoice("Lycee");
        levelSelection.AddChoice("Superieur");
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        switch(currentStage){
            case SELECTION:
                ClearMenus();
                if (addDomainButton.OnClick(e)){
                    addDomainButton.ComputeFunction();
                }
                if (addQuestionButton.OnClick(e)){
                    addQuestionButton.ComputeFunction();
                }
                if (editQuestionListButton.OnClick(e)){
                    editQuestionListButton.ComputeFunction();
                }
                if (forumButton.OnClick(e)){
                    forumButton.ComputeFunction();
                }
                break;
            case ADD_DOMAIN:
                if(!levelSelection.IsExpanding()){
                    domain.Update();
                    category.Update();
                    if (addDButton.OnClick(e)){
                        addDButton.ComputeFunction();
                    }
                    if (backButton.OnClick(e)){
                        backButton.ComputeFunction();
                    } 
                }
                levelSelection.Update(e);
                break;
            case ADD_QUESTION:
                //Update choices
                domainChoice.SetChoices(domains);
                if(categories.containsKey(domainChoice.GetText())){
                    categoryChoice.SetChoices(categories.get(domainChoice.GetText()));
                    if (level.get(domainChoice.GetText()).containsKey(categoryChoice.GetText())){
                        levelChoice.SetChoices(level.get(domainChoice.GetText()).get(categoryChoice.GetText()));
                    }
                }
            
                if (!domainChoice.IsExpanding() && !categoryChoice.IsExpanding() && !levelChoice.IsExpanding()){
                
                    question.Update();
                    answerA.Update();
                    answerB.Update();
                    answerC.Update();
                    answerD.Update();
                
                    checkAnswerA.Update(e);
                    checkAnswerB.Update(e);
                    checkAnswerC.Update(e);
                    checkAnswerD.Update(e);
                }
            
                domainChoice.Update(e);
                categoryChoice.Update(e);
                levelChoice.Update(e);
            
                if (addQButton.OnClick(e)){
                    addQButton.ComputeFunction();
                }
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                break;
            case QUESTION_LIST:
                Iterator<Map.Entry<TextBox, Button>> itr = questionList.entrySet().iterator();
                while(itr.hasNext()){
                    Button btn = itr.next().getValue();
                    if (btn.OnClick(e)){
                        btn.ComputeFunction();
                        break;
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

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

        switch(currentStage){
            case SELECTION:
                if(addDomainButton.IsClicked()){
                    addDomainButton.Draw(Color.GREEN);
                }
                else{
                    addDomainButton.Draw(Color.GRAY);
                }
                if (addQuestionButton.IsClicked()){
                    addQuestionButton.Draw(Color.GREEN);
                }
                else{
                    addQuestionButton.Draw(Color.GRAY);
                }
                if (editQuestionListButton.IsClicked()){
                    editQuestionListButton.Draw(Color.GREEN);
                }
                else{
                    editQuestionListButton.Draw(Color.GRAY);
                }
                if (forumButton.IsClicked()){
                    forumButton.Draw(Color.GREEN);
                }
                else{
                    forumButton.Draw(Color.GRAY);
                }
                break;
            case ADD_DOMAIN:
                domain.Draw();
                category.Draw();
                levelSelection.Draw(5);
                if (addDButton.IsClicked()){
                    addDButton.Draw(Color.GREEN);
                }
                else{
                    addDButton.Draw(Color.GRAY);
                }
                if (backButton.IsClicked()){
                    backButton.Draw(Color.GREEN);
                }
                else{
                    backButton.Draw(Color.GRAY);
                }
                break;
            case ADD_QUESTION:
                //Drawing typing boxes
                question.Draw();
                answerA.Draw();
                answerB.Draw();
                answerC.Draw();
                answerD.Draw();
                
                //Drawing check boxes
                checkAnswerA.Draw();
                checkAnswerB.Draw();
                checkAnswerC.Draw();
                checkAnswerD.Draw();
                
                //Drawing comboBox
                domainChoice.Draw(5);
                categoryChoice.Draw(5);
                levelChoice.Draw(5);
                
                //Drawing button
                if (addQButton.IsClicked()){
                    addQButton.Draw(Color.GREEN);
                }
                else{
                   addQButton.Draw(Color.GRAY);
                }
                if (backButton.IsClicked()){
                    backButton.Draw(Color.GREEN);
                }
                else{
                    backButton.Draw(Color.GRAY);
                }

                break;
            case QUESTION_LIST:
                Iterator<Map.Entry<TextBox, Button>> itr = questionList.entrySet().iterator();
                while (itr.hasNext()){
                    Map.Entry<TextBox, Button> currentPair = itr.next();
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

    private void ClearMenus(){
        //Domain menu
        domain.Clear();
        category.Clear();
        levelSelection.Reset();

        //Question menu
        question.Clear();
        answerA.Clear();
        answerB.Clear();
        answerC.Clear();
        answerD.Clear();
        checkAnswerA.Clear();
        checkAnswerB.Clear();
        checkAnswerC.Clear();
        checkAnswerD.Clear();
        domainChoice.Reset();
        categoryChoice.Reset();
        levelChoice.Reset();
    }

    private void ResetQuestionList() throws SQLException{
        questionList.clear();
        ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT id, question FROM " + dbm.GetDatabaseName() + ".questions, " + dbm.GetDatabaseName() + ".professeur WHERE " + dbm.GetDatabaseName() + ".questions.id_prof = " + dbm.GetDatabaseName() + ".professeur.id_professeur AND " + dbm.GetDatabaseName() + ".professeur.email = '" + user.GetMail() + "';");
        int i = 0;
        while(questionSet.next() && i < 5){
            int id = questionSet.getInt("id");
            questionList.put(new TextBox(new Rectangle(100, 50 + 75 * i, 400, 50), questionSet.getInt("id") + " - " + questionSet.getString("question")), new Button(new Rectangle(550, 50 + i * 75, 150, 50), "Delete", () -> {
                try {
                    dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".questions WHERE id = " + id + ";");
                    ResetQuestionList();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }));
            i++;
        }
    }

    private Set<String> domains = new HashSet<String>();
    private Map<String, HashSet<String>> categories = new HashMap<String, HashSet<String>>();
    private Map<String, HashMap<String, HashSet<String>>> level = new HashMap<String, HashMap<String, HashSet<String>>>();
    
    private Button backButton;

    //Selection menu
    private Button addDomainButton;
    private Button addQuestionButton;
    private Button editQuestionListButton;
    private Button forumButton;

    //Add domain menu
    private TypingBox domain = new TypingBox(new Rectangle(100, 150, 600, 50), "Enter domain...");
    private TypingBox category = new TypingBox(new Rectangle(100, 250, 600, 50), "Enter category...");
    private ChoiceBox levelSelection = new ChoiceBox(new Rectangle(100, 350, 600, 50), "Select level...");
    private Button addDButton;

    //Add question menu
    private TypingBox question = new TypingBox(new Rectangle(50, 125, 600, 50), "Question");
    private TypingBox answerA = new TypingBox(new Rectangle(50, 200, 500, 50), "Answer A");
    private TypingBox answerB = new TypingBox(new Rectangle(50, 275, 500, 50), "Answer B");
    private TypingBox answerC = new TypingBox(new Rectangle(50, 350, 500, 50), "Answer C");
    private TypingBox answerD = new TypingBox(new Rectangle(50, 425, 500, 50), "Answer D");
    private CheckBox checkAnswerA = new CheckBox(new Rectangle(575, 212, 25, 25));
    private CheckBox checkAnswerB = new CheckBox(new Rectangle(575, 287, 25, 25));
    private CheckBox checkAnswerC = new CheckBox(new Rectangle(575, 362, 25, 25));
    private CheckBox checkAnswerD = new CheckBox(new Rectangle(575, 437, 25, 25));
    private ChoiceBox domainChoice = new ChoiceBox(new Rectangle(25, 25, 175, 50), "Select domain...");
    private ChoiceBox categoryChoice = new ChoiceBox(new Rectangle(300, 25, 175, 50), "Select category...");
    private ChoiceBox levelChoice = new ChoiceBox(new Rectangle(575, 25, 175, 50), "Select level...");
    private Button addQButton;

    //Edit question list menu
    private Map<TextBox, Button> questionList = new HashMap<TextBox, Button>();

    private SceneStage currentStage = SceneStage.SELECTION;
}
