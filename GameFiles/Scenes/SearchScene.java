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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Exceptions.ProjectException;
import GameFiles.Questions.*;

/**
 * Scene principale de l'etudiant
 * <p>Permet de rechercher un domaine de quiz a realiser
 * <p>Permet egalement d'acceder a ses statistiques, a son profil, et de modifier son mot de passe
 * <p>Possibilite de lien vers la scene de Forum
 * @author Maxime Emonnot
 * @version 1.1.0
 */
public class SearchScene extends AScene{
    /**
     * Differentes etapes de la scene
     * @author Maxime Emonnot
     */
    private enum SceneStage{
        SEARCHING,
        PROFILE,
        STATISTICS,
        CHANGE_PASSWORD
    }

    /**
     * Constructeur SearchScene
     * <p>Initialisation des differents boutons
     * @author Maxime Emonnot
     * @throws SQLException Erreurs lors des envois de requetes SQL (voir ResetQuestionList)
     * @see SearchScene#ResetQuestionList()
     */
    public SearchScene() throws SQLException {
        ResetQuestionList();
        refreshButton = new Button(new Rectangle(1150, 600, 100, 40), "Refresh", () -> {
           try {
                ResetQuestionList();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       });
    
       profileButton = new Button(new Rectangle(50, 10, 350, 50), "View profile", () -> {
           currentStage = SceneStage.PROFILE;
       });
       statisticsButton = new Button(new Rectangle(425, 10, 350, 50), "View statistics", () -> {
        statsArray.clear();
        try {
            ResultSet rSet = dbm.GetResultFromSQLRequest("SELECT domaine, categorie, niveau, score FROM " + dbm.GetDatabaseName() + ".etudiant, " + dbm.GetDatabaseName() + ".statistique, " + dbm.GetDatabaseName() + ".sujets WHERE email = '" + user.GetMail() + "' AND id_etudiant = id_statistique AND " + dbm.GetDatabaseName() + ".sujets.id = " + dbm.GetDatabaseName() + ".statistique.id_subject;");
            int i = 0;
            while(rSet.next()){
                if (!statsArray.containsKey(i/6)){
                    statsArray.put(i/6, new HashSet<TextBox>());
                }
                statsArray.get(i/6).add(new TextBox(new Rectangle(340, 50 + 75 * (i%6), 600, 50), rSet.getString("domaine") + " - " + rSet.getString("categorie") + " - " + rSet.getString("niveau") + " - Score : " + rSet.getFloat("score") + "%"));
                i++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
           currentStage = SceneStage.STATISTICS;
       });

       lastStatPage = new Button(new Rectangle(450, 550, 25, 25), "<", () -> { iCurPage--;});
       nextStatPage = new Button(new Rectangle(800, 550, 25, 25), ">", () -> { iCurPage++;});

       forumButton = new Button(new Rectangle(800, 10, 350, 50), "Go to forum", () ->{
            bChangeScene = true;
            nextSceneIndex = 6;
       });
       backButton = new Button(new Rectangle(1100, 600, 100, 50), "Back", () -> {
           currentStage = SceneStage.SEARCHING;
       });
       changePasswordButton = new Button(new Rectangle(70, 600, 200, 50), "Change password", () -> {
            currentStage = SceneStage.CHANGE_PASSWORD;
       });
       cancelButton = new Button(new Rectangle(740, 500, 200, 50), "Cancel", () -> {
            currentStage = SceneStage.PROFILE;
       });
       confirmPasswordButton = new Button(new Rectangle(340, 500, 200, 50), "Confirm password", () -> {
           if (oldPassword.GetText().length() != 0
            && newPassword.GetText().length() != 0
            && confirmNewPassword.GetText().length() != 0){
                if(newPassword.GetText().equals(confirmNewPassword.GetText())){
                    try {
                        ResultSet rSet = dbm.GetResultFromSQLRequest("SELECT password FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + user.GetMail() + "';");
                        if(rSet.next() && rSet.getString("password").equals(CoreSystem.Encrypter.GetEncryptedPasswordFrom(oldPassword.GetText()))){
                            dbm.SendSQLRequest("UPDATE " + dbm.GetDatabaseName() + ".etudiant SET password = '" + CoreSystem.Encrypter.GetEncryptedPasswordFrom(newPassword.GetText()) + "' WHERE email = '" + user.GetMail() + "';");
                            System.out.println("New password set !");
                            currentStage = SceneStage.PROFILE;
                        }
                        else{
                           passwordMessage.SetMessage("Wrong old password", Color.RED, 2.0f);
                        }
                    } catch (UnsupportedEncodingException | NoSuchAlgorithmException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else{
                   passwordMessage.SetMessage("Passwords are not the same", Color.RED, 2.0f);
                }
            }
            else{
                passwordMessage.SetMessage("Please fill all blank spaces", Color.RED, 2.0f);
            }
       });
       
       oldPassword.SetPasswordMode(true);
       newPassword.SetPasswordMode(true);
       confirmNewPassword.SetPasswordMode(true);
    }
    
    /**
     * {@inheritDoc}
     * <p>Mise a jour des menus en fonction de l'entree utilisateur, via notamment des champs de texte et des boites de choix.
     * <p>Mise a jour en fonction de l'etape de la scene.
     * @author Maxime Emonnot
     */
    @Override
    public void Update() throws SQLException {
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        switch(currentStage){
            case SEARCHING:
                searchDomain.SetChoices(domains);
                if (categories.containsKey(searchDomain.GetText())){
                    searchCategory.SetChoices(categories.get(searchDomain.GetText()));
                    if (difficulty.get(searchDomain.GetText()).containsKey(searchCategory.GetText())){
                        searchDifficulty.SetChoices(difficulty.get(searchDomain.GetText()).get(searchCategory.GetText()));
                    }
                }

                lives = 3;
                iCurQuestion = 0;

                if (!searchDomain.IsExpanding() && !searchCategory.IsExpanding() && !searchDifficulty.IsExpanding()){
                    if (refreshButton.OnClick(e)){
                        refreshButton.ComputeFunction();
                    }
                    if (profileButton.OnClick(e)){
                        profileButton.ComputeFunction(); 
                    }
                    if (statisticsButton.OnClick(e)){
                        statisticsButton.ComputeFunction();
                    }
                    if (forumButton.OnClick(e)){
                        forumButton.ComputeFunction();
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
                break;
            case PROFILE:
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                if (changePasswordButton.OnClick(e)){
                    changePasswordButton.ComputeFunction();
                }
                break;
            case STATISTICS: 
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                if (statsArray.containsKey(iCurPage - 1)){
                    if (lastStatPage.OnClick(e)){
                        lastStatPage.ComputeFunction();
                    }
                }
                if (statsArray.containsKey(iCurPage + 1)){
                    if (nextStatPage.OnClick(e)){
                        nextStatPage.ComputeFunction();
                    }
                }
                break;
            case CHANGE_PASSWORD:
                oldPassword.Update();
                newPassword.Update();
                confirmNewPassword.Update();
                if (confirmPasswordButton.OnClick(e)){
                    confirmPasswordButton.ComputeFunction();
                }
                if (cancelButton.OnClick(e)){
                    cancelButton.ComputeFunction();
                }
                passwordMessage.Update();
                break;
            default:
                break;
        }
    }
    
    /**
     * {@inheritDoc}
     * <p>Affichage des differents menus, en fonction de l'etape de la scene
     * @author Maxime Emonnot
     */
    @Override
    public void Draw() throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

        switch(currentStage){
            case SEARCHING: 
                GraphicsEngine.GraphicsSystem.GetInstance().DrawLine(new Point(0, 70), new Point(1280, 70), Color. BLACK);
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
                if (profileButton.IsClicked()){
                    profileButton.Draw(Color.DARK_GRAY);
                }
                else{
                    profileButton.Draw(Color.LIGHT_GRAY);
                }
                if (statisticsButton.IsClicked()){
                    statisticsButton.Draw(Color.DARK_GRAY);
                }
                else{
                    statisticsButton.Draw(Color.LIGHT_GRAY);
                }
                if (forumButton.IsClicked()){
                    forumButton.Draw(Color.DARK_GRAY);
                }
                else{
                    forumButton.Draw(Color.LIGHT_GRAY);
                }
            
                Iterator<Button> itr = ddcArray.iterator();
            
                int j = 0;
                while(itr.hasNext()){
                    Button btn = itr.next();
                    if (btn.GetText().startsWith(searchId.GetText()) && btn.GetText().contains(searchDomain.GetText()) && btn.GetText().contains(searchCategory.GetText()) && btn.GetText().contains(searchDifficulty.GetText())){
                        btn.Draw(Color.LIGHT_GRAY, new Rectangle(25 + 415 * (int)(j / 10), 85 + 50 * (j % 10), 375, 45));
                        j++;
                    }
                    else {
                        btn.Draw(Color.BLACK, new Rectangle(-100, -100, 0, 0));
                    }
                }
            break;
            case PROFILE: 
                firstNameBox.Draw("First Name : " + user.GetFirstName(), Color.BLACK, Color.GRAY, Color.WHITE);
                lastNameBox.Draw("Last Name : " + user.GetLastName(), Color.BLACK, Color.GRAY, Color.WHITE);
                emailBox.Draw("Email : " + user.GetMail(), Color.BLACK, Color.GRAY, Color.WHITE);
                phoneBox.Draw("Phone : " + user.GetPhoneNumber(), Color.BLACK, Color.GRAY, Color.WHITE);
                addressBox.Draw("Address : " + user.GetAddress(), Color.BLACK, Color.GRAY, Color.WHITE);
                if (backButton.IsClicked()){
                    backButton.Draw(Color.DARK_GRAY);
                }
                else {
                    backButton.Draw(Color.LIGHT_GRAY);
                }
                if (changePasswordButton.IsClicked()){
                    changePasswordButton.Draw(Color.DARK_GRAY);
                }
                else{
                    changePasswordButton.Draw(Color.LIGHT_GRAY);
                }
                break;
            case STATISTICS:
                if (backButton.IsClicked()){
                    backButton.Draw(Color.DARK_GRAY);
                }
                else {
                    backButton.Draw(Color.LIGHT_GRAY);
                }

                if (statsArray.containsKey(iCurPage)){
                    Iterator<TextBox> itrTB = statsArray.get(iCurPage).iterator();
                    while (itrTB.hasNext()){
                        TextBox tb = itrTB.next();
                        tb.Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                    }
                }
                if (statsArray.containsKey(iCurPage - 1)){
                    if (lastStatPage.IsClicked()){
                        lastStatPage.Draw(Color.DARK_GRAY);
                    }
                    else{
                        lastStatPage.Draw(Color.LIGHT_GRAY);
                    }
                }
                if (statsArray.containsKey(iCurPage + 1)){
                    if (nextStatPage.IsClicked()){
                        nextStatPage.Draw(Color.DARK_GRAY);
                    }
                    else{
                        nextStatPage.Draw(Color.LIGHT_GRAY);
                    }
                }
                break;          
            case CHANGE_PASSWORD:
                oldPassword.Draw();
                newPassword.Draw();
                confirmNewPassword.Draw();
                if (confirmPasswordButton.IsClicked()){
                    confirmPasswordButton.Draw(Color.DARK_GRAY);
                }
                else{
                    confirmPasswordButton.Draw(Color.LIGHT_GRAY);
                }
                if (cancelButton.IsClicked()){
                    cancelButton.Draw(Color.DARK_GRAY);
                }
                else {
                    cancelButton.Draw(Color.LIGHT_GRAY);
                }
                passwordMessage.Draw();
                break;
            default:
            break;
        } 
    }
     
    /**
     * Mise a jour de la liste de question, avec initialisation en fonction du domaine de la question.
     * @author Maxime Emonnot
     * @throws SQLException Erreurs lors de l'envoi de requetes SQL
     */
    private void ResetQuestionList() throws SQLException{
           ddcArray.clear();
           domains.clear();
           categories.clear();
           difficulty.clear();
        ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".questions, " + dbm.GetDatabaseName() + ".sujets WHERE " + dbm.GetDatabaseName() + ".questions.id_subject =  " + dbm.GetDatabaseName() + ".sujets.id");
        while(rs.next()){
            String name = String.valueOf(rs.getLong("id_prof"))  + " - " + rs.getString("domaine") + " - " + rs.getString("categorie") + " - " + rs.getString("niveau");
            String[] args = name.split(" - ");
            domains.add(args[1]);
            if (!categories.containsKey(args[1])){
                categories.put(args[1], new HashSet<String>());
            }
            categories.get(args[1]).add(args[2]);
            if (!difficulty.containsKey(args[1])){
                difficulty.put(args[1], new HashMap<String, HashSet<String>>());
                difficulty.get(args[1]).put(args[2], new HashSet<String>());
            }
            else if (!difficulty.get(args[1]).containsKey(args[2])){
                difficulty.get(args[1]).put(args[2], new HashSet<String>());
            }
            difficulty.get(args[1]).get(args[2]).add(args[3]);
            ddcArray.add(new Button(new Rectangle(0, 0, 0, 0), name, () -> {
                bChangeScene = true;
                nextSceneIndex = 3;
                currentQuizz = name;
                try {
                    questions.clear();
                    ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".questions, " + dbm.GetDatabaseName() + ".sujets WHERE (" + dbm.GetDatabaseName() + ".questions.id_subject = " + dbm.GetDatabaseName() + ".sujets.id AND id_prof = " + Integer.parseInt(args[0]) + " AND domaine = \"" + args[1].replace("'", "\\'") + "\" AND categorie = \"" + args[2].replace("'", "\\'") + "\" AND niveau = \"" + args[3] + "\")");
                    while(questionSet.next()){
                        float questionTimer;
                        switch(questionSet.getString("difficulte")){
                        case "Facile":
                            questionTimer = 10.0f;
                            break;
                        case "Moyen":
                            questionTimer = 15.0f;
                            break;
                        case "Difficile":
                            questionTimer = 20.0f;
                            break;
                        default:
                            questionTimer = 0.0f;
                            break;
                        }

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
                            
                            double rng = Math.random() * 3;
                            if (rng < 1){
                            	questions.add(new CatsLightQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), questionSet.getString("reponseD"), type));
                            }
                            else if (rng < 2){
                            	questions.add(new CardQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), questionSet.getString("reponseD"), type));
                            }else{
                            	questions.add(new CardBoardQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), questionSet.getString("reponseD"), type));
                            }

                            //questions.add(new ConcreteQuadrupleQuestion(questionSet.getString("question"), questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), questionSet.getString("reponseD"), type));
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
                            
                            //Génération aléatoire de mini-jeux (structure à reprendre pour les minis jeux à 2 et 4 réponses)
                            
                            double rng = Math.random() * 3;
                            if (rng < 1){
                                questions.add(new EggQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), type));
                            }
                            else if(rng < 2){
                                questions.add(new AppleQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), type));
                            }
                            else {
                                questions.add(new BoxQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), questionSet.getString("reponseC"), type));
                            }
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
                            double rng = Math.random() * 3;
                            if (rng < 1){
                                questions.add(new HandCoinQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), type));
                            }
                            else if (rng < 2){
                                questions.add(new BubbleGiftQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), type));
                            }
                            else{
                                questions.add(new FingerNoseQuestion(questionSet.getString("question"), questionTimer, questionSet.getString("reponseA"), questionSet.getString("reponseB"), type));

                            }
                           
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
    }

    private SceneStage currentStage = SceneStage.SEARCHING;

    //Searching buttons
    private Button profileButton;
    private Button statisticsButton;
    private Button forumButton;

    private Button backButton;
    private Button changePasswordButton;
    private Button confirmPasswordButton;
    private Button cancelButton;

    //Change password menu
    private TypingBox oldPassword = new TypingBox(new Rectangle(340, 150, 600, 50), "Enter old password...");
    private TypingBox newPassword = new TypingBox(new Rectangle(340, 250, 600, 50), "Enter new password...");
    private TypingBox confirmNewPassword = new TypingBox(new Rectangle(340, 350, 600, 50), "Confirm new password...");
    private UserMessage passwordMessage = new UserMessage(new Point(340, 425));

    //Profile menu
    private TextBox firstNameBox = new TextBox(new Rectangle(150, 100, 450, 50));
    private TextBox lastNameBox = new TextBox(new Rectangle(650, 100, 450, 50));
    private TextBox emailBox = new TextBox(new Rectangle(150, 200, 450, 50));
    private TextBox phoneBox = new TextBox(new Rectangle(650, 200, 450, 50));
    private TextBox addressBox = new TextBox(new Rectangle(150, 300, 950, 50));

    //Statistics menu
    private Map<Integer, HashSet<TextBox>> statsArray = new HashMap<Integer, HashSet<TextBox>>(); 
    private int iCurPage = 0;
    private Button nextStatPage;
    private Button lastStatPage;

    //Searching menu
    private TypingBox searchId = new TypingBox(new Rectangle(50, 600, 250, 50), "Enter ID..."); 
    private ChoiceBox searchDomain = new ChoiceBox(new Rectangle(325, 600, 250, 50), "Select domain..."); 
    private ChoiceBox searchCategory = new ChoiceBox(new Rectangle(600, 600, 250, 50), "Select category..."); 
    private ChoiceBox searchDifficulty = new ChoiceBox(new Rectangle(875, 600, 250, 50), "Select level..."); 

    private Set<String> domains = new HashSet<String>();
    private Map<String, HashSet<String>> categories = new HashMap<String, HashSet<String>>();
    private Map<String, HashMap<String, HashSet<String>>> difficulty = new HashMap<String, HashMap<String, HashSet<String>>>();
    private Button refreshButton;
    private Set<Button> ddcArray = new HashSet<Button>();
}
