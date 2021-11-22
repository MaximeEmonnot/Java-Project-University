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

/**
 * Scene de forum, commune aux etudiants et aux enseignants
 * Permet aux etudiant de poser des questions aux enseignants
 * Permet egalement de proposer des reponses aux autres questions d'etudiant
 * Les enseignants peuvent soit valider une reponse soit donner leur reponse, ce qui cloture la question
 * @author Godfree Akakpo
 * @author Maxime Emonnot
 */
public class ForumScene extends AScene {

    /**
     * Differentes etapes de la scene ForumScene
     * @author Maxime Emonnot
     */
    private enum SceneStage{
        SELECTION,
        STUDENT_ASK,
        STUDENT_QUESTION_LIST,
        STUDENT_PROPOSE,
        STUDENT_CHECK_ANSWER,
        STUDENT_MANAGE_QUESTION,
        TEACHER_QUESTION_LIST,
        TEACHER_ANSWER_QUESTION
    }

    /**
     * Constructeur ForumScene
     * Initialisation des menus pour les Etudiants et les Enseignants, separes en deux parties
     * @author Maxime Emonnot
     * @see ForumScene#InitStudent()
     * @see ForumScene#InitTeacher()
     */
    public ForumScene() {
        ///PARTIE ETUDIANT
        InitStudent();
        ///PARTIE PROF
        InitTeacher();

        ///PARTIE COMMUNE
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

    /**
     * {@inheritDoc}
     * Mise a jour des differents menus, en deux parties Etudiant et Enseignant, en fonction de l'etape de la scene
     * @author Maxime Emonnot
     * @see ForumScene#UpdateStudent(CoreSystem.Mouse.EventType)
     * @see ForumScene#UpdateTeacher(CoreSystem.Mouse.EventType)
     */
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

    /**
     * {@inheritDoc}
     * Affichage des differents menus, en deux parties Etudiant et Enseignant, en fonction de l'etape de la scene
     * @author Maxime Emonnot
     * @see ForumScene#DrawStudent()
     * @see ForumScene#DrawTeacher()
     */
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

    /**
     * Initialistion du menu Etudiant.
     * Separation pour un souci de clarte au niveau du code
     * @author Maxime Emonnot
     */
    private void InitStudent(){
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
                        studentQuestionMessage.SetMessage("Question already asked !", Color.RED, 2.0f);
                    }
                    else{
                        //Récupération de l'id du l'utilisateur
                        ResultSet uid = dbm.GetResultFromSQLRequest("SELECT id_etudiant FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + user.GetMail() + "';");
                        if (uid.next()){
                            //Insertion de la nouvelle question
                            int id = uid.getInt("id_etudiant");
                            dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".forumQuestion(question, id_student, answer) VALUES('" + question.GetText() + "'," + id + ", '');");
                            studentQuestionMessage.SetMessage("Question added !", Color.GREEN, 2.0f);
                            System.out.println("Question added !");
                        }
                    }
                }
                else {
                    studentQuestionMessage.SetMessage("Please fill question field", Color.RED, 2.0f);
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
                    studentPropositionMessage.SetMessage("Proposition added !", Color.GREEN, 2.0f);
                }
                else{
                    System.out.println("Proposition is empty !");
                    studentPropositionMessage.SetMessage("Please fill proposition field", Color.RED, 2.0f);
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
    /**
     * Initialisation du menu Enseignant.
     * Separation pour un souci de clarte au niveau du code
     * @author Godfree Akakpo
     */
    private void InitTeacher(){
    	
    	tlastQuestionPage = new Button(new Rectangle(100, 450, 25, 25), "<", () -> { tiCurQuestionPage--;});
        tnextQuestionPage = new Button(new Rectangle(500, 450, 25, 25), ">", () -> { tiCurQuestionPage++;});
        
        tLastPropPage = new Button(new Rectangle(100, 500, 25, 25), "<", () -> { tiCurPropositionPage--;}); //A toi de metrte la position que tu veux
        tNextPropPage = new Button(new Rectangle(600, 500, 25, 25), ">", () -> { tiCurPropositionPage++;}); //Meme chose
    	
    	teacherQuestionListButton = new Button(new Rectangle(100, 250, 600, 50), "View question list", () -> {
            currentStage = SceneStage.TEACHER_QUESTION_LIST;
        });
        
    	tValidateAnswer = new Button(new Rectangle(550, 100, 100, 50), "Validate", () -> {
    		if (tAnswer.GetText().length() != 0) {
    			try {
					dbm.SendSQLRequest("UPDATE " + dbm.GetDatabaseName() + ".forumQuestion SET answer = '" + tAnswer.GetText() + "' WHERE id = " + tiChosenQuestion + ";");
					bHasAnswered = true;
					teacherAnswerMessage.SetMessage("Reponse validee pour la question : " + tQuestionText, Color.GREEN, 2.0f);
    			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else {
    			teacherAnswerMessage.SetMessage("Veuillez remplir la zone de texte!", Color.RED, 2.0f);
    		}
    	});
    	
    	tCancelAnswerButton = new Button(new Rectangle(700, 500, 75, 50), "Cancel", () -> {
    		currentStage = SceneStage.TEACHER_QUESTION_LIST;
    		tAnswer.Clear();
    		bHasAnswered = false;
    	});
    	
        tbackButton = new Button(new Rectangle(700, 500, 75, 50), "Back", () -> {
            currentStage = SceneStage.SELECTION;
        });
    }

    /**
     * Mise a jour du menu de la partie Etudiant
     * Separation pour un souci de clarte au niveau du code
     * @author Maxime Emonnot
     * @param e Entree souris enregistree dans la methode Update() generale
     * @throws SQLException Erreurs lors d'envoi de requetes SQL
     */
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
            case STUDENT_CHECK_ANSWER:
                if (exitPropositionButton.OnClick(e)){
                    exitPropositionButton.ComputeFunction();
                }
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
        /**
     * Mise a jour du menu de la partie Enseignant
     * Separation pour un souci de clarte au niveau du code
     * @author Godfree Akakpo
     * @param e Entree souris enregistree dans la methode Update() generale
     * @throws SQLException Erreurs lors d'envoi de requetes SQL
     */
    private void UpdateTeacher(CoreSystem.Mouse.EventType e) throws SQLException {
    	//Ici on va mettre une methode pour mettre a jour la liste des questions pour la partie Teacher
    	ResetTeacherQuestionList();
    	
        switch(currentStage){
            case SELECTION:
            	//j'aicommenc� par ici
            	
            	if (teacherQuestionListButton.OnClick(e)){
            		 teacherQuestionListButton.ComputeFunction(); 
            		}
          
                if (exitForumButton.OnClick(e)){
                    exitForumButton.ComputeFunction();
                }
                if (askQuestionButton.OnClick(e)){
                    askQuestionButton.ComputeFunction();
                }
                
                
            break;
            //mise a jour de liste de question au niveau de teacher
            case TEACHER_QUESTION_LIST:
                if (tquestionArray.containsKey(tiCurQuestionPage)){
                    Iterator<Map.Entry<TextBox, Button>> itrQuestion = tquestionArray.get(tiCurQuestionPage).entrySet().iterator();
                    while (itrQuestion.hasNext()){
                        Button btn = itrQuestion.next().getValue();
                        if (btn.OnClick(e)){
                            btn.ComputeFunction();
                        }
                    }
                }

                if (tquestionArray.containsKey(tiCurQuestionPage - 1)){
                    if (tlastQuestionPage.OnClick(e)){
                        tlastQuestionPage.ComputeFunction();
                    }
                }
                if (tquestionArray.containsKey(tiCurQuestionPage + 1)){
                    if (tnextQuestionPage.OnClick(e)){
                        tnextQuestionPage.ComputeFunction();
                    }
                }

                if (tbackButton.OnClick(e)){
                    tbackButton.ComputeFunction();
                }
                tquestionSearch.Update();
                break;
            case TEACHER_ANSWER_QUESTION:
            	
            	if (!bHasAnswered) {
            		tAnswer.Update();
            		if (tValidateAnswer.OnClick(e)) {
            			tValidateAnswer.ComputeFunction();
            		}
            		
            		if (tPropositionArray.containsKey(tiCurPropositionPage)){
                    	Iterator<Map.Entry<TextBox, Button>> itrQuestion = tPropositionArray.get(tiCurPropositionPage).entrySet().iterator();
                    	while (itrQuestion.hasNext()){
                        	Button btn = itrQuestion.next().getValue();
                        	if (btn.OnClick(e)){
                            	btn.ComputeFunction();
                        	}
                    	}
                	}

                	if (tPropositionArray.containsKey(tiCurPropositionPage - 1)){
                    	if (tLastPropPage.OnClick(e)){
                        	tLastPropPage.ComputeFunction();
                    	}
                	}
                	if (tPropositionArray.containsKey(tiCurPropositionPage + 1)){
                    	if (tNextPropPage.OnClick(e)){
                        	tNextPropPage.ComputeFunction();
                    	}
                    }
            	}
            	teacherAnswerMessage.Update();
            	
                if (tCancelAnswerButton.OnClick(e)) {
            		tCancelAnswerButton.ComputeFunction();
            	}
            break;
            default:
            break;
        }
    }
        
    /**
     * Affichage du menu de la partie Etudiant
     * Separation pour un souci de clarte au niveau du code
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    private void DrawStudent() throws ProjectException {
        switch(currentStage){
        case SELECTION:
            if (askQuestionButton.IsClicked()){
                askQuestionButton.Draw(Color.DARK_GRAY);
            }
            else{
                askQuestionButton.Draw(Color.LIGHT_GRAY);
            }
            if (questionListButton.IsClicked()){
                questionListButton.Draw(Color.DARK_GRAY);
            }
            else{
                questionListButton.Draw(Color.LIGHT_GRAY);
            }
            if (editQuestionsButton.IsClicked()){
                editQuestionsButton.Draw(Color.DARK_GRAY);
            }
            else{
                editQuestionsButton.Draw(Color.LIGHT_GRAY);
            }
            if (exitForumButton.IsClicked()){
                exitForumButton.Draw(Color.DARK_GRAY);
            }
            else{
                exitForumButton.Draw(Color.LIGHT_GRAY);
            }
            break;
        case STUDENT_ASK:
            question.Draw();
            if (addQuestionButton.IsClicked()){
                addQuestionButton.Draw(Color.DARK_GRAY);
            }
            else{
                addQuestionButton.Draw(Color.LIGHT_GRAY);
            }
            if (backButton.IsClicked()){
                backButton.Draw(Color.DARK_GRAY);
            }
            else{
                backButton.Draw(Color.LIGHT_GRAY);
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
                        currentPair.getValue().Draw(Color.DARK_GRAY);
                    }
                    else{
                        currentPair.getValue().Draw(Color.LIGHT_GRAY);
                    }
                }
            }
            
            if (questionArray.containsKey(iCurQuestionPage - 1)){
                if (lastQuestionPage.IsClicked()){
                    lastQuestionPage.Draw(Color.DARK_GRAY);
                }
                else {
                    lastQuestionPage.Draw(Color.LIGHT_GRAY);
                }
            }
            if (questionArray.containsKey(iCurQuestionPage + 1)){
                if (nextQuestionPage.IsClicked()){
                    nextQuestionPage.Draw(Color.DARK_GRAY);
                }
                else{
                    nextQuestionPage.Draw(Color.LIGHT_GRAY);
                }
            }

            questionSearch.Draw();

            if (backButton.IsClicked()){
                backButton.Draw(Color.DARK_GRAY);
            }
            else{
                backButton.Draw(Color.LIGHT_GRAY);
            }
            break;
        case STUDENT_PROPOSE:
            chosenQuestion.Draw(chosenQuestionText, Color.BLACK, Color.GRAY, Color.WHITE);
            proposition.Draw();
            if (addPropositionButton.IsClicked()){
                addPropositionButton.Draw(Color.DARK_GRAY);
            }
            else{
                addPropositionButton.Draw(Color.LIGHT_GRAY);
            }
            if (exitPropositionButton.IsClicked()){
                exitPropositionButton.Draw(Color.DARK_GRAY);
            }
            else{
                exitPropositionButton.Draw(Color.LIGHT_GRAY);
            }
            studentPropositionMessage.Draw();
            break;
        case STUDENT_CHECK_ANSWER:
            chosenQuestion.Draw(chosenQuestionText, Color.BLACK, Color.GRAY, Color.WHITE);
            chosenQuestionAnswer.Draw(chosenQuestionAnswerText, Color.BLACK, Color.GRAY, Color.WHITE);
            if (exitPropositionButton.IsClicked()){
                exitPropositionButton.Draw(Color.DARK_GRAY);
            }
            else{
                exitPropositionButton.Draw(Color.LIGHT_GRAY);
            }
            break;
        case STUDENT_MANAGE_QUESTION: 
            if (questionDeleteArray.containsKey(iCurDeletePage)){
                Iterator<Map.Entry<TextBox, Button>> itrQuestionDelete = questionDeleteArray.get(iCurDeletePage).entrySet().iterator();
                while (itrQuestionDelete.hasNext()){
                    Map.Entry<TextBox, Button> currentPair = itrQuestionDelete.next();
                    currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                    if (currentPair.getValue().IsClicked()){
                        currentPair.getValue().Draw(Color.DARK_GRAY);
                    }
                    else{
                        currentPair.getValue().Draw(Color.LIGHT_GRAY);
                    }
                }
            }
            if (questionDeleteArray.containsKey(iCurDeletePage - 1 )){
                if (lastDeletePage.IsClicked()){
                    lastDeletePage.Draw(Color.DARK_GRAY);
                }
                else{
                    lastDeletePage.Draw(Color.LIGHT_GRAY);
                }
            }
            if (questionDeleteArray.containsKey(iCurDeletePage + 1)){
                if (nextDeletePage.IsClicked()){
                    nextDeletePage.Draw(Color.DARK_GRAY);
                }
                else{
                    nextDeletePage.Draw(Color.LIGHT_GRAY);
                }
            }

            if (backButton.IsClicked()){
                backButton.Draw(Color.DARK_GRAY);
            }
            else{
                backButton.Draw(Color.LIGHT_GRAY);
            }
            studentDeletionMessage.Draw();
        break;
        default:
        break;
        }
    }
        
    /**
     * Affichage du menu de la partie Enseignant
     * Separation pour un souci de clarte au niveau du code
     * @author Godfree Akakpo
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    private void DrawTeacher() throws ProjectException {
        switch(currentStage){
            case SELECTION:
            	tiCurQuestionPage = 0;
            	
            	//pour le bouton de la liste des questions
            	if (teacherQuestionListButton.IsClicked())
           		    teacherQuestionListButton.Draw(Color.DARK_GRAY);
            	else {
           		    teacherQuestionListButton.Draw(Color.LIGHT_GRAY);
           		 }
            	//pour le boutton exit
                if (exitForumButton.IsClicked()){
                    exitForumButton.Draw(Color.DARK_GRAY);
                }
                else{
                    exitForumButton.Draw(Color.LIGHT_GRAY);
                }
            break;
            // pour afficher la liste des questions
            case TEACHER_QUESTION_LIST:
            	tiCurPropositionPage = 0;
            	
                if (tquestionArray.containsKey(tiCurQuestionPage)){
                    Iterator<Map.Entry<TextBox, Button>> itrQuestion = tquestionArray.get(tiCurQuestionPage).entrySet().iterator();
                    while(itrQuestion.hasNext()){
                        Map.Entry<TextBox, Button> currentPair = itrQuestion.next();
                        currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                        if (currentPair.getValue().IsClicked()){
                            currentPair.getValue().Draw(Color.DARK_GRAY);
                        }
                        else{
                            currentPair.getValue().Draw(Color.LIGHT_GRAY);
                        }
                    }
                }
                
                if (tquestionArray.containsKey(tiCurQuestionPage - 1)){
                    if (tlastQuestionPage.IsClicked()){
                        tlastQuestionPage.Draw(Color.DARK_GRAY);
                    }
                    else {
                        tlastQuestionPage.Draw(Color.LIGHT_GRAY);
                    }
                }
                if (tquestionArray.containsKey(tiCurQuestionPage + 1)){
                    if (tnextQuestionPage.IsClicked()){
                        tnextQuestionPage.Draw(Color.DARK_GRAY);
                    }
                    else{
                        tnextQuestionPage.Draw(Color.LIGHT_GRAY);
                    }
                }

                tquestionSearch.Draw();

                if (tbackButton.IsClicked()){
                    tbackButton.Draw(Color.DARK_GRAY);
                }
                else{
                    tbackButton.Draw(Color.LIGHT_GRAY);
                }
                break;
                
            case TEACHER_ANSWER_QUESTION:
            	//Affichage question
            	tQuestion.Draw(tQuestionText, Color.BLACK, Color.GRAY, Color.WHITE);
            	
            	if (!bHasAnswered) {
            		tAnswer.Draw();
            		if (tValidateAnswer.IsClicked()) {
            			tValidateAnswer.Draw(Color.DARK_GRAY);
            		}
            		else {
            			tValidateAnswer.Draw(Color.LIGHT_GRAY);
            		}
            		
            		if (tPropositionArray.containsKey(tiCurPropositionPage)) {
            		Iterator<Map.Entry<TextBox, Button>> itrQuestion = tPropositionArray.get(tiCurPropositionPage).entrySet().iterator();
                    while(itrQuestion.hasNext()){
                        Map.Entry<TextBox, Button> currentPair = itrQuestion.next();
                        currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                        if (currentPair.getValue().IsClicked()){
                            currentPair.getValue().Draw(Color.DARK_GRAY);
                        }
                        else{
                            currentPair.getValue().Draw(Color.LIGHT_GRAY);
                        }
                    }
            	}
            
            	 if (tPropositionArray.containsKey(tiCurPropositionPage - 1)){
                     if (tLastPropPage.IsClicked()){
                    	 tLastPropPage.Draw(Color.DARK_GRAY);
                     }
                     else {
                    	 tLastPropPage.Draw(Color.LIGHT_GRAY);
                     }
                 }
                 if (tPropositionArray.containsKey(tiCurPropositionPage + 1)){
                     if (tNextPropPage.IsClicked()){
                    	 tNextPropPage.Draw(Color.DARK_GRAY);
                     }
                     else{
                    	 tNextPropPage.Draw(Color.LIGHT_GRAY);
                     }
                 }
            	}
            	
            	teacherAnswerMessage.Draw();
                 
                 if (tCancelAnswerButton.IsClicked()) {
                	 tCancelAnswerButton.Draw(Color.DARK_GRAY);
                 }
                 else {
                	 tCancelAnswerButton.Draw(Color.LIGHT_GRAY);
                 }
                 
            	break;
            default:
            break;
        }
    }
        
    /**
     * Reinitialisation de la liste des question Etudiant
     * Appels lors de l'etape STUDENT_QUESTION_LIST
     * @author Maxime Emonnot
     * @throws SQLException Erreurs lors de l'envoi de requetes SQL
     */
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
        ArrayList<Map.Entry<Integer, Map.Entry<String, Map.Entry<String, Integer>>>> priorityQuestion = new ArrayList<Map.Entry<Integer, Map.Entry<String, Map.Entry<String, Integer>>>>();

        while (questionSet.next()){
            int id = questionSet.getInt("id");
            String questionStr = questionSet.getString("question");
            String answerStr = questionSet.getString("answer");
            
            int nKeyW = 0;
            for (String key : keyWords){
                if (questionStr.toLowerCase().contains(key)){
                    nKeyW++;
                }
            }
            priorityQuestion.add(Map.entry(nKeyW, Map.entry(questionStr, Map.entry(answerStr, id))));
        }

        priorityQuestion.sort((e0, e1) -> e1.getKey() - e0.getKey());
        if (priorityQuestion.size() != 0){
            int maxPriority = priorityQuestion.get(0).getKey();

            for (int i = 0; i < priorityQuestion.size(); i++){
                if (maxPriority == priorityQuestion.get(i).getKey()){
                    if (!questionArray.containsKey(i/5)){
                        questionArray.put(i/5, new HashMap<TextBox, Button>());
                    }
                    int id = priorityQuestion.get(i).getValue().getValue().getValue();
                    String questionStr = priorityQuestion.get(i).getValue().getKey();
                    String answerStr = priorityQuestion.get(i).getValue().getValue().getKey();
                    if (answerStr.length() == 0){
                        questionArray.get(i/5).put(new TextBox(new Rectangle(50, 50 + (i % 5) * 75, 400, 50), questionStr), new Button(new Rectangle(500, 50 + (i % 5) * 75, 100, 50), "Add proposition", () -> { 
                            proposition.Clear();
                            currentStage = SceneStage.STUDENT_PROPOSE;
                            iChosenQuestion = id;
                            chosenQuestionText = questionStr;
                        }));
                    }
                    else{
                        questionArray.get(i/5).put(new TextBox(new Rectangle(50, 50 + (i % 5) * 75, 400, 50), questionStr), new Button(new Rectangle(500, 50 + (i % 5) * 75, 100, 50), "See answer", () -> { 
                            currentStage = SceneStage.STUDENT_CHECK_ANSWER;
                            iChosenQuestion = id;
                            chosenQuestionText = questionStr;
                            chosenQuestionAnswerText = answerStr;
                        }));
                    }
                }
            }
        }
    }
       
    /**
     * Reinitialisation de la liste des questions a supprimer
     * Appels lors de l'etape STUDENT_MANAGE_QUESTION
     * @author Maxime Emonnot
     * @throws SQLException Erreurs lors de l'envoi de requetes SQL
     */
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
                    studentDeletionMessage.SetMessage("Question deleted !", Color.GREEN, 2.0f);
                    ResetStudentQuestionDeleteList();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }));
            i++;
        }
    }
    
    /**
     * Mise jour de la liste de question posee par les etudiants dans le cadre de teacher
     * @author Godfree Akakpo
     * @throws SQLException Erreurs lors des envois de requetes de sql
     */
    private void ResetTeacherQuestionList() throws SQLException {
    	 tquestionArray.clear();
         //Définition des mots clés
         String searchQ = tquestionSearch.GetText();
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
        	 if(questionSet.getString("answer").length() == 0) {
        		 int id = questionSet.getInt("id");
             	String questionStr = questionSet.getString("question");
             
             	int nKeyW = 0;
             	for (String key : keyWords){
                 	if (questionStr.toLowerCase().contains(key)){
                     	nKeyW++;
                 	}
             	}
             	priorityQuestion.add(Map.entry(nKeyW, Map.entry(questionStr, id)));
        	 }
         }

         priorityQuestion.sort((e0, e1) -> e1.getKey() - e0.getKey());
         if (priorityQuestion.size() != 0){
             int maxPriority = priorityQuestion.get(0).getKey();

             for (int i = 0; i < priorityQuestion.size(); i++){
                 if (maxPriority == priorityQuestion.get(i).getKey()){
                     if (!tquestionArray.containsKey(i/5)){
                         tquestionArray.put(i/5, new HashMap<TextBox, Button>());
                     }
                     int id = priorityQuestion.get(i).getValue().getValue();
                     tquestionArray.get(i/5).put(new TextBox(new Rectangle(50, 50 + (i % 5) * 75, 400, 50), priorityQuestion.get(i).getValue().getKey()), new Button(new Rectangle(500, 50 + (i % 5) * 75, 100, 50), "Answer", () -> { 
                    	 //Fonction inscrite dans le bouton Answer
                    	 try {
                    		 //Passage � la phase de sc�ne ANSWER_QUESTION
                    		 currentStage = SceneStage.TEACHER_ANSWER_QUESTION;
                    		 //On garde en m�moire l'id de la question pour la r�utiliser plus tard
                    		 tiChosenQuestion = id;
                    		 //On met � jour la liste des propositions pour la question s�lectionn�e
							 ResetTeacherQuestionPropositionList();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                     }));
                 }
             }
         }
    }
    
    
    /**
     * Mise a jour de la liste des propositions donner par les etudiants dans le cadre de teacher pour une question
     * @author Godfree Akakpo
     * @throws SQLException Erreurs lors des envois de requetes SQL
     */
    private void ResetTeacherQuestionPropositionList() throws SQLException {
    	tPropositionArray.clear();
    	//On r�cup�re la liste des questions et des propositions selon l'id que l'on a enregistr� via le bouton answer
    	ResultSet questionSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".forumQuestion WHERE " + dbm.GetDatabaseName() + ".forumQuestion.id = " + tiChosenQuestion + ";");
    	if (questionSet.next()) {
    		tQuestionText = questionSet.getString("question");
    		ResultSet propositionSet = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".forumProposition WHERE " + dbm.GetDatabaseName() + ".forumProposition.id_question = "  + tiChosenQuestion + ";");
    		int i = 0;
    		while(propositionSet.next()) {
    		
    			/*
    		 	* On ajoute les diff�rentes pages de proposition. Si une page existe d�j�, on ajoute juste la proposition dans la page
    		 	* Ensuite, pour l'ajout de propositions, on d�finit une TextBox et un Bouton.
    		 	* La position du couple TextBox/Bouton d�pend du nombre d'�l�ments parcourus dans le tableau (valeur i)
    		 	*/
    			if (!tPropositionArray.containsKey(i / 4)) {
    				tPropositionArray.put(i / 4, new HashMap<TextBox, Button>());
    			}
    			String prop = propositionSet.getString("proposition");
    			tPropositionArray.get(i/4).put(new TextBox(new Rectangle(150, 175 + (i % 4) * 75, 400, 50), prop), new Button(new Rectangle(550, 175 + (i % 4) * 75, 100, 50), "Select", () -> {
    				//Fonction dans les boutons Select (� faire plus tard)
    				try {
						dbm.SendSQLRequest("UPDATE " + dbm.GetDatabaseName() + ".forumQuestion SET answer = '" + prop.replace("'", "\\'") + "' WHERE id = " + tiChosenQuestion + ";");
						bHasAnswered = true;
						teacherAnswerMessage.SetMessage("Reponse validee pour : " + tQuestionText, Color.GREEN, 2.0f);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}));
    			
    			i++;
    		}
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
    
    //Menu proposition Etudiant
    private TextBox chosenQuestion = new TextBox(new Rectangle(100, 25, 600, 50));
    private String chosenQuestionText = "";
    private TypingBox proposition = new TypingBox(new Rectangle(100, 150, 600, 50), "Enter your proposition...");
    private UserMessage studentPropositionMessage = new UserMessage(new Point(100, 225));
    private Button addPropositionButton;
    private Button exitPropositionButton;
    
    //Menu check answer Etudiant
    private TextBox chosenQuestionAnswer = new TextBox(new Rectangle(150, 100, 500, 50));
    private String chosenQuestionAnswerText = "";

    //Menu gestion questions Etudiant
    private Map<Integer, HashMap<TextBox, Button>> questionDeleteArray = new HashMap<Integer, HashMap<TextBox, Button>>();
    private int iCurDeletePage = 0;
    private Button lastDeletePage;
    private Button nextDeletePage;

    private UserMessage studentDeletionMessage = new UserMessage(new Point(100, 500));

    //Back button
    private Button backButton;
    //*** FIN ELEMENTS STUDENT ***//

    //Elements communs aux deux parties
    private Button exitForumButton;
    private final List<String> questionWords = new ArrayList<String>();
    
    //*** ELEMENTS TEACHER ***//
    
    //Menu Selection Teacher
    private Button teacherQuestionListButton;
  
    //Menu liste questions Teacher
    private Map<Integer, HashMap<TextBox, Button>> tquestionArray = new HashMap<Integer, HashMap<TextBox, Button>>();
    private int tiCurQuestionPage = 0;
    private Button tlastQuestionPage;
    private Button tnextQuestionPage;
    private TypingBox tquestionSearch = new TypingBox(new Rectangle(100, 500, 600, 50), "Search question...");
    private int tiChosenQuestion = 0;
    
    //Menu liste propositions questions Teacher
    private Map<Integer, HashMap<TextBox, Button>> tPropositionArray = new HashMap<Integer, HashMap<TextBox, Button>>();
    private int tiCurPropositionPage = 0;
    private Button tLastPropPage;
    private Button tNextPropPage;
    private TypingBox tAnswer = new TypingBox(new Rectangle (150, 100, 400, 50), "Enter your answer...");
    private Button tValidateAnswer;
    private TextBox tQuestion = new TextBox(new Rectangle(100, 25, 600, 50)); 
    private String tQuestionText = "";
    private Button tCancelAnswerButton;
    private boolean bHasAnswered = false;
    private UserMessage teacherAnswerMessage = new UserMessage(new Point(100, 500));
    
    //Back button Teacher
    private Button tbackButton;
    
    //*** FIN ELEMENTS TEACHER ***//
    
}   
