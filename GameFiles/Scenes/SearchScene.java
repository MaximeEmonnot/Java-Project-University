package GameFiles.Scenes;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.sql.ResultSet;

import MenuSystem.*;
import MenuSystem.Button;

import java.awt.*;
import java.sql.SQLException;

import Exceptions.ProjectException;
import GameFiles.Questions.*;

public class SearchScene extends AScene {
    public SearchScene() throws ClassNotFoundException, SQLException, ProjectException{
        super();
        nextSceneIndex = 2;
        ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions");
        while(rs.next()){
            String name = String.valueOf(rs.getLong("id_prof"))  + " - " + rs.getString("domaine") + "  - " + rs.getString("categorie") + " - " + rs.getString("niveau");
            domains.add(rs.getString("domaine"));
            difficulty.add(rs.getString("niveau"));
            if (!categories.containsKey(rs.getString("domaine"))){
                categories.put(rs.getString("domaine"), new HashSet<String>());
            }
            categories.get(rs.getString("domaine")).add(rs.getString("categorie"));
            ddcArray.add(new Button(new Rectangle(0, 0, 0, 0), name, () -> {
                bChangeScene = true;
                try {
                    questions.clear();
                    String[] args = name.split(" - ");
                    ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions WHERE (id_prof = " + Integer.parseInt(args[0]) + " AND domaine = \"" + args[1] + "\" AND categorie = \"" + args[2] + "\" AND niveau = \"" + args[3] + "\")");
                    while(questionSet.next()){
                        if (questionSet.getString("reponseD").length() != 0){
                            AQuadrupleAnswerQuestion.AnswerType type = AQuadrupleAnswerQuestion.AnswerType.NONE;
                            switch(Integer.parseInt(questionSet.getString("code_reponses"), 2)){
                                case 1:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_D;
                                    break;
                                case 2: 
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_C;
                                    break;
                                case 3:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_CD;
                                    break;
                                case 4:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_B;
                                    break;
                                case 5:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_BD;
                                    break;
                                case 6:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_BC;
                                    break;
                                case 7:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_BCD;
                                    break;
                                case 8:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_A;
                                    break;
                                case 9:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_AD;
                                    break;
                                case 10:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_AC;
                                    break;
                                case 11:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ACD;
                                    break;
                                case 12:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_AB;
                                    break;
                                case 13:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ABD;
                                    break;
                                case 14:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ABC;
                                    break;
                                case 15:
                                    type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ABCD;
                                    break;
                                default:
                                    break;
                            }

                            questions.add(new ConcreteQuadrupleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), questionSet.getString("reponseD"), type));
                        }
                        else if (questionSet.getString("reponseC").length() != 0) {
                            ATripleAnswerQuestion.AnswerType type = ATripleAnswerQuestion.AnswerType.NONE;
                            switch(Integer.parseInt(questionSet.getString("code_reponses"), 2)){
                            case 1:
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_C;
                                break;
                            case 2:
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_B;
                                break;
                            case 3:
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_BC;
                                break;
                            case 4:
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_A;
                                break;
                            case 5:
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_AC;
                                break;
                            case 6 :
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_AB;
                                break;
                            case 7:
                                type = ATripleAnswerQuestion.AnswerType.ANSWER_ABC;
                                break;
                            default :
                                break;
                            }

                            questions.add(new ConcreteTripleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), type));
                        }
                        else{
                            ADoubleAnswerQuestion.AnswerType type = ADoubleAnswerQuestion.AnswerType.NONE;
                            switch(Integer.parseInt(questionSet.getString("code_reponses"), 2)){
                            case 1:
                                type = ADoubleAnswerQuestion.AnswerType.ANSWER_B;
                                break;
                            case 2:
                                type = ADoubleAnswerQuestion.AnswerType.ANSWER_A;
                                break;
                            case 3:
                                type = ADoubleAnswerQuestion.AnswerType.BOTH;
                                break;
                            default:
                                break;
                            }
                           
                            questions.add(new ConcreteDoubleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), type));
                        }
                    }
                    Collections.shuffle(questions);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }));
        }
       refreshButton = new Button(new Rectangle(660, 500, 100, 40), "Refresh", () -> {
           ddcArray.clear();
           domains.clear();
           categories.clear();
           difficulty.clear();
           try {
                ResultSet rSet = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions");
                while(rSet.next()){
                    String name = String.valueOf(rSet.getLong("id_prof"))  + " - " + rSet.getString("domaine") + "  - " + rSet.getString("categorie") + " - " + rSet.getString("niveau");
                    domains.add(rSet.getString("domaine"));
                    difficulty.add(rSet.getString("niveau"));
                    if (!categories.containsKey(rSet.getString("domaine"))){
                        categories.put(rSet.getString("domaine"), new HashSet<String>());
                    }
                    categories.get(rSet.getString("domaine")).add(rSet.getString("categorie"));
                    ddcArray.add(new Button(new Rectangle(0, 0, 0, 0), name, () -> {
                        bChangeScene = true;
                        try {
                            questions.clear();
                            String[] args = name.split(" - ");
                            ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions WHERE (id_prof = " + Integer.parseInt(args[0]) + " AND domaine = \"" + args[1] + "\" AND categorie = \"" + args[2] + "\" AND niveau = \"" + args[3] + "\")");
                            while(questionSet.next()){
                                if (questionSet.getString("reponseD").length() != 0){
                                    AQuadrupleAnswerQuestion.AnswerType type = AQuadrupleAnswerQuestion.AnswerType.NONE;
                                    switch(Integer.parseInt(questionSet.getString("code_reponses"), 2)){
                                        case 1:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_D;
                                            break;
                                        case 2: 
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_C;
                                            break;
                                        case 3:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_CD;
                                            break;
                                        case 4:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_B;
                                            break;
                                        case 5:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_BD;
                                            break;
                                        case 6:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_BC;
                                            break;
                                        case 7:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_BCD;
                                            break;
                                        case 8:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_A;
                                            break;
                                        case 9:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_AD;
                                            break;
                                        case 10:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_AC;
                                            break;
                                        case 11:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ACD;
                                            break;
                                        case 12:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_AB;
                                            break;
                                        case 13:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ABD;
                                            break;
                                        case 14:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ABC;
                                            break;
                                        case 15:
                                            type = AQuadrupleAnswerQuestion.AnswerType.ANSWER_ABCD;
                                            break;
                                        default:
                                            break;
                                    }
        
                                    questions.add(new ConcreteQuadrupleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), questionSet.getString("reponseD"), type));
                                }
                                else if (questionSet.getString("reponseC").length() != 0) {
                                    ATripleAnswerQuestion.AnswerType type = ATripleAnswerQuestion.AnswerType.NONE;
                                    switch(Integer.parseInt(questionSet.getString("code_reponses"), 2)){
                                    case 1:
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_C;
                                        break;
                                    case 2:
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_B;
                                        break;
                                    case 3:
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_BC;
                                        break;
                                    case 4:
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_A;
                                        break;
                                    case 5:
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_AC;
                                        break;
                                    case 6 :
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_AB;
                                        break;
                                    case 7:
                                        type = ATripleAnswerQuestion.AnswerType.ANSWER_ABC;
                                        break;
                                    default :
                                        break;
                                    }
        
                                    questions.add(new ConcreteTripleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), type));
                                }
                                else{
                                    ADoubleAnswerQuestion.AnswerType type = ADoubleAnswerQuestion.AnswerType.NONE;
                                    switch(Integer.parseInt(questionSet.getString("code_reponses"), 2)){
                                    case 1:
                                        type = ADoubleAnswerQuestion.AnswerType.ANSWER_B;
                                        break;
                                    case 2:
                                        type = ADoubleAnswerQuestion.AnswerType.ANSWER_A;
                                        break;
                                    case 3:
                                        type = ADoubleAnswerQuestion.AnswerType.BOTH;
                                        break;
                                    default:
                                        break;
                                    }
                                   
                                    questions.add(new ConcreteDoubleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), type));
                                }
                            }
                            Collections.shuffle(questions);
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       });
    }
    
    @Override
    public void Update() throws SQLException {
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        searchDomain.SetChoices(domains);
        if (categories.containsKey(searchDomain.GetText())){
            searchCategory.SetChoices(categories.get(searchDomain.GetText()));
        }
        searchDifficulty.SetChoices(difficulty);

        lives = 3;
        iCurQuestion = 0;

        if (!searchDomain.IsExpanding() && !searchCategory.IsExpanding() && !searchDifficulty.IsExpanding()){
            if (refreshButton.OnClick(e)){
                refreshButton.ComputeFunction();
            }
            
            ddcArray.forEach((btn) -> {
                if (btn.OnClick(e)){
                    btn.ComputeFunction();
                }
            });
        }
        searchDomain.Update(e);
        searchCategory.Update(e);
        searchDifficulty.Update(e);
        searchId.Update();
    }
    
    @Override
    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

        GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(0, 70), new Point(800, 70), Color. BLACK);
        searchId.Draw();
        searchDomain.Draw(5);
        searchCategory.Draw(5);
        searchDifficulty.Draw(5);
        if (refreshButton.IsClicked()){
            refreshButton.Draw(Color.DARK_GRAY);
        }
        else{
            refreshButton.Draw(Color.LIGHT_GRAY);
        }

        Iterator<Button> itr = ddcArray.iterator();

        int j = 0;
        while(itr.hasNext()){
            Button btn = itr.next();
            if (btn.GetText().startsWith(searchId.GetText()) && btn.GetText().contains(searchDomain.GetText()) && btn.GetText().contains(searchCategory.GetText()) && btn.GetText().contains(searchDifficulty.GetText())){
                btn.Draw(Color.LIGHT_GRAY, new Rectangle(25 + 380 * (int)(j / 8), 85 + 50 * (j % 8), 350, 45));
                j++;
            }
            else {
                btn.Draw(Color.BLACK, new Rectangle(-100, -100, 0, 0));
            }
        }
    }
    
    private TypingBox searchId = new TypingBox(new Rectangle(15, 500, 150, 50), "Enter ID..."); 
    private ChoiceBox searchDomain = new ChoiceBox(new Rectangle(175, 500, 150, 50), "Select domain..."); 
    private ChoiceBox searchCategory = new ChoiceBox(new Rectangle(335, 500, 150, 50), "Select category..."); 
    private ChoiceBox searchDifficulty = new ChoiceBox(new Rectangle(495, 500, 150, 50), "Select level..."); 


    private Set<String> domains = new HashSet<String>();
    private Map<String, HashSet<String>> categories = new HashMap<String, HashSet<String>>();
    private Set<String> difficulty = new HashSet<String>();
    private Button refreshButton;
    private Set<Button> ddcArray = new HashSet<Button>();
}
