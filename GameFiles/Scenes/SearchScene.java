package GameFiles.Scenes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.sql.ResultSet;

import MenuSystem.*;
import MenuSystem.Button;

import java.awt.*;
import java.sql.SQLException;

import DataBaseSystem.DataBaseManager;
import Exceptions.ProjectException;
import GameFiles.Questions.*;

public class SearchScene extends AScene {
    public SearchScene() throws ClassNotFoundException, SQLException, ProjectException{
        super();
        nextSceneIndex = 1;
        dbm = new DataBaseManager("ok", "graxime");
        System.out.println("Connected");
        ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions");
        while(rs.next()){
            ddcArray.add(new Button(new Rectangle(0, 0, 0, 0), String.valueOf(rs.getLong("id_prof"))  + " - " + rs.getString("domaine") + "  - " + rs.getString("difficulte") + " - " + rs.getString("categorie"), 12, () -> {
                bChangeScene = true;
                try {
                    questions.clear();
                    String name = String.valueOf(rs.getLong("id_prof"))  + " - " + rs.getString("domaine") + "  - " + rs.getString("difficulte") + " - " + rs.getString("categorie");
                    String[] args = name.split(" - ");
                    ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions WHERE (id_prof = " + Integer.parseInt(args[0]) + " AND domaine = \"" + args[1] + "\" AND difficulte = \"" + args[2] + "\" AND categorie = \"" + args[3] + "\")");
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
       refreshButton = new Button(new Rectangle(660, 500, 100, 40), "Refresh", 22, () -> {
           ddcArray.clear();
           try {
                ResultSet rSet = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions");
                while(rSet.next()){
                    ddcArray.add(new Button(new Rectangle(0, 0, 0, 0), String.valueOf(rSet.getLong("id_prof"))  + " - " + rSet.getString("domaine") + "  - " + rSet.getString("difficulte") + " - " + rSet.getString("categorie"), 12, () -> {
                        bChangeScene = true;
                        try {
                            questions.clear();
                            String name = String.valueOf(rs.getLong("id_prof"))  + " - " + rs.getString("domaine") + "  - " + rs.getString("difficulte") + " - " + rs.getString("categorie");
                            String[] args = name.split(" - ");
                            ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM ok.questions WHERE (id_prof = " + Integer.parseInt(args[0]) + " AND domaine = \"" + args[1] + "\" AND difficulte = \"" + args[2] + "\" AND categorie = \"" + args[3] + "\")");
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
        searchDomain.Update();
        searchCategory.Update();
        searchDifficulty.Update();
        searchId.Update();
        if (refreshButton.OnClick()){
            refreshButton.ComputeFunction();
        }
    }
    
    @Override
    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("Enter ID", new Point(30, 480), Color.BLACK, 5);
        searchId.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("Enter Domain", new Point(190, 480), Color.BLACK, 5);
        searchDomain.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("Enter Category", new Point(350, 480), Color.BLACK, 5);
        searchCategory.Draw();
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText("Enter Difficulty", new Point(510, 480), Color.BLACK, 5);
        searchDifficulty.Draw();
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
                btn.Draw(Color.LIGHT_GRAY, new Rectangle(50, 10 + 75 * j, 100, 50));
                j++;
            }
            else {
                btn.Draw(Color.BLACK, new Rectangle(0, 0, 0, 0));
            }
        }
    }
    
    private DataBaseManager dbm;

    private TypingBox searchId = new TypingBox(new Rectangle(15, 500, 150, 50), 22); 
    private TypingBox searchDomain = new TypingBox(new Rectangle(175, 500, 150, 50), 22); 
    private TypingBox searchCategory = new TypingBox(new Rectangle(335, 500, 150, 50), 22); 
    private TypingBox searchDifficulty = new TypingBox(new Rectangle(495, 500, 150, 50), 22); 

    private Button refreshButton;
    private Set<Button> ddcArray = new HashSet<Button>();
}
