package GameFiles.Scenes;

import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.awt.*;

import Exceptions.ProjectException;
import MenuSystem.*;
import MenuSystem.Button;

public class TeacherScene extends AScene {

    public TeacherScene() throws ClassNotFoundException, SQLException{
        super();
        ResultSet rs = dbm.GetResultFromSQLRequest("SELECT code_question, domaine, categorie, niveau FROM ok.questions");
        while(rs.next()){
            domains.add(rs.getString("domaine"));
            level.add(rs.getString("niveau"));
            if (!categories.containsKey(rs.getString("domaine"))){
                categories.put(rs.getString("domaine"), new HashSet<String>());
            }
            categories.get(rs.getString("domaine")).add(rs.getString("categorie"));
            if (nQuestions <= Integer.parseInt(rs.getString("code_question"))){
                nQuestions = Integer.parseInt(rs.getString("code_question"));
            }
        }
        addButton = new Button(new Rectangle(25, 500, 100, 50), "Add", () -> {
            try {
                if (dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions WHERE id_prof = 1 AND domaine = '" + domainChoice.GetText() + "' AND categorie = '" + categoryChoice.GetText() + "' AND difficulte = '" + levelChoice.GetText() + "' AND question = '" + question.GetText() + "' AND reponseA = '" + answerA.GetText() + "' AND reponseB = '" + answerB.GetText() + "' AND reponseC = '" + answerC.GetText() + "' AND reponseD = '" + answerD.GetText() + "';").next()){
                    System.out.println("Question already added !");             
                }
                else{
                    nQuestions++;
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
                    dbm.SendSDLRequest("INSERT INTO ok.questions (code_question, id_prof, domaine, categorie, niveau, difficulte, question, code_reponses, reponseA, reponseB, reponseC, reponseD)" +
                                       "VALUES ('" + nQuestions + "', '1', '" + domainChoice.GetText() + "', '" + categoryChoice.GetText() + "', '" + levelChoice.GetText() + "', 'Facile' , '" + question.GetText() + "', '" + answerCode + "', '" + answerA.GetText() + "', '" + answerB.GetText() + "', '" + answerC.GetText() + "', '" + answerD.GetText() + "');");
                    System.out.println("Question added ! ");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        //Update choices
        domainChoice.SetChoices(domains);
        if(categories.containsKey(domainChoice.GetText())){
            categoryChoice.SetChoices(categories.get(domainChoice.GetText()));
        }
        levelChoice.SetChoices(level);

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

        if (addButton.OnClick(e)){
            addButton.ComputeFunction();
        }
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

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
        if (addButton.IsClicked()){
            addButton.Draw(Color.GREEN);
        }
        else{
           addButton.Draw(Color.GRAY);
        }
    }

    private Set<String> domains = new HashSet<String>();
    private Map<String, HashSet<String>> categories = new HashMap<String, HashSet<String>>();
    private Set<String> level = new HashSet<String>();

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
    private Button addButton;

    private int nQuestions = 0;
}
