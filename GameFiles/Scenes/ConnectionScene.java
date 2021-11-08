package GameFiles.Scenes;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Exceptions.ProjectException;
import GameFiles.User.*;
import GraphicsEngine.Sprite;
import MenuSystem.*;
import MenuSystem.Button;

public class ConnectionScene extends AScene {

    private enum ConnectionStep{
        INTRO,
        LOGIN,
        REGISTER
    }

    public ConnectionScene() throws ClassNotFoundException, SQLException{
        super();
        connectionType.add("Eleve");
        connectionType.add("Professeur");
        
        choiceRegister.SetChoices(connectionType);

        connectionType.add("Admin");
        choiceConnection.SetChoices(connectionType);

        //logo = new Sprite("Images/quizzGameLogo.png");

        connectButton = new Button(new Rectangle(200, 400, 400, 50), "Log in", () -> { currentStage = ConnectionStep.LOGIN;});
        createAccountButton = new Button(new Rectangle(200, 475, 400, 50), "Register", () -> { currentStage = ConnectionStep.REGISTER;});
        backButton = new Button(new Rectangle(550, 500, 200, 50), "Back", () -> { currentStage = ConnectionStep.INTRO;});
        loginButton = new Button(new Rectangle(150, 500, 200, 50), "Connect", () -> { 
            if (emailConnection.GetText().length() != 0
            &&  passwordConnection.GetText().length() != 0
            &&  choiceConnection.GetText().length() != 0){
                switch(choiceConnection.GetText()){
                    case "Eleve":
                    try {
                        ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".etudiant WHERE email = '" + emailConnection.GetText() + "';");
                        if (rs.next() && rs.getString("password").equals(CoreSystem.Encrypter.GetEncryptedPasswordFrom(passwordConnection.GetText()))){
                                bChangeScene = true;
                                user = new Student(rs.getString("prenom"), rs.getString("nom"), rs.getString("email"), rs.getString("telephone"), rs.getString("adresse"));
                                nextSceneIndex = 2;
                            }
                            else{
                                loginMessage.SetMessage("Wrong informations", Color.RED, 5.0f);
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                        case "Professeur":
                        try {
                            ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".professeur WHERE email = '" + emailConnection.GetText() + "';");
                            if (rs.next() && rs.getString("password").equals(CoreSystem.Encrypter.GetEncryptedPasswordFrom(passwordConnection.GetText()))){
                                bChangeScene = true;
                                user = new Teacher(rs.getString("prenom"), rs.getString("nom"), rs.getString("email"), rs.getString("telephone"), rs.getString("adresse"));
                                nextSceneIndex = 4;
                            }
                            else{
                                loginMessage.SetMessage("Wrong informations", Color.RED, 5.0f);
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                        case "Admin":
                        try {
                            ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM " + dbm.GetDatabaseName() + ".admin WHERE id_admin = '" + emailConnection.GetText() + "';");
                            if (rs.next() && rs.getString("password").equals(CoreSystem.Encrypter.GetEncryptedPasswordFrom(passwordConnection.GetText()))){
                                bChangeScene = true;
                                nextSceneIndex = 5;
                            }
                            else{
                                loginMessage.SetMessage("Wrong informations", Color.RED, 5.0f);
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        break;
                    default :
                        break;
                }
            }
            else{
                loginMessage.SetMessage("Please fill all blank spaces", Color.RED, 5.0f);
            }
        });
        registerButton = new Button(new Rectangle(150, 500, 200, 50), "Register", () -> {
            if (firstNameRegister.GetText().length() != 0
            &&  lastNameRegister.GetText().length() != 0
            &&  emailRegister.GetText().length() != 0
            &&  phoneRegister.GetText().length() != 0
            &&  addressRegister.GetText().length() != 0
            &&  passwordRegister.GetText().length() != 0){
                if (passwordConfirmationRegister.GetText().equals(passwordRegister.GetText())){
                    switch(choiceRegister.GetText()){
                        case "Eleve":
                        try {
                            dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".etudiant (nom, prenom, email, telephone, password, adresse) VALUES ('" + lastNameRegister.GetText() +"', '" + firstNameRegister.GetText() +  "', '" + emailRegister.GetText() + "', '" + phoneRegister.GetText() + "', '" + CoreSystem.Encrypter.GetEncryptedPasswordFrom(passwordRegister.GetText()) + "', '" + addressRegister.GetText() + "');");
                            System.out.println("Student added !");
                            currentStage = ConnectionStep.INTRO;
                        } 
                        catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                        case "Professeur":
                        try {
                            dbm.SendSQLRequest("INSERT INTO " + dbm.GetDatabaseName() + ".professeur (nom, prenom, email, telephone, password, adresse) VALUES ('" + lastNameRegister.GetText() +"', '" + firstNameRegister.GetText() +  "', '" + emailRegister.GetText() + "', '" + phoneRegister.GetText() + "', '" + CoreSystem.Encrypter.GetEncryptedPasswordFrom(passwordRegister.GetText()) + "', '" + addressRegister.GetText() + "');");
                            System.out.println("Teacher added !");
                            currentStage = ConnectionStep.INTRO;
                        } 
                        catch (SQLException e) {
                            // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (NoSuchAlgorithmException e) {
                            // TODO Auto-generated catch block
                             e.printStackTrace();
                            }
                        break;
                        default:
                            registerMessage.SetMessage("Please select a user type", Color.RED, 5.0f);
                        break;
                    }
                }
                else{
                    registerMessage.SetMessage("Passwords are not the same", Color.RED, 5.0f);
                }
            }
            else {
                registerMessage.SetMessage("Please fill all blank spaces", Color.RED, 5.0f);
            }
        });

        passwordConnection.SetPasswordMode(true);
        passwordRegister.SetPasswordMode(true);
        passwordConfirmationRegister.SetPasswordMode(true);
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();
        
        switch(currentStage){
            case INTRO:
                if (connectButton.OnClick(e)){
                    connectButton.ComputeFunction();
                }
                if (createAccountButton.OnClick(e)){
                    createAccountButton.ComputeFunction();
                }
                ClearMenus();
                break;
            case LOGIN:
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                if (loginButton.OnClick(e)){
                    loginButton.ComputeFunction();
                }
                if(!choiceConnection.IsExpanding()){
                    emailConnection.Update();
                    passwordConnection.Update();
                }
                choiceConnection.Update(e);
                loginMessage.Update();
                break;
            case REGISTER:
                if (backButton.OnClick(e)){
                    backButton.ComputeFunction();
                }
                if (registerButton.OnClick(e)){
                    registerButton.ComputeFunction();
                }
                if (!choiceRegister.IsExpanding()){
                    firstNameRegister.Update();
                    lastNameRegister.Update();
                    emailRegister.Update();
                    phoneRegister.Update();
                    addressRegister.Update();
                    passwordRegister.Update();
                    passwordConfirmationRegister.Update();
                }
                choiceRegister.Update(e);
                registerMessage.Update();
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
            case INTRO:
                //GraphicsEngine.GraphicsSystem.GetInstance().DrawSprite(logo, new Rectangle(169, 50, 463, 250));
                if(connectButton.IsClicked()){
                    connectButton.Draw(Color.GREEN);
                }
                else{
                    connectButton.Draw(Color.GRAY);
                }
                
                if(createAccountButton.IsClicked()){
                    createAccountButton.Draw(Color.GREEN);
                }
                else{
                    createAccountButton.Draw(Color.GRAY);
                }
                break;
            case LOGIN:
                emailConnection.Draw();
                passwordConnection.Draw();
                choiceConnection.Draw(5);
                if(backButton.IsClicked()){
                    backButton.Draw(Color.GREEN);
                }
                else{
                    backButton.Draw(Color.GRAY);
                }
                if (loginButton.IsClicked()){
                    loginButton.Draw(Color.GREEN);
                }
                else{
                    loginButton.Draw(Color.GRAY);
                }
                loginMessage.Draw();
                break;
            case REGISTER:
                firstNameRegister.Draw();
                lastNameRegister.Draw();
                emailRegister.Draw();
                phoneRegister.Draw();
                addressRegister.Draw();
                passwordRegister.Draw();
                passwordConfirmationRegister.Draw();
                choiceRegister.Draw(5);
                if(backButton.IsClicked()){
                    backButton.Draw(Color.GREEN);
                }
                else{
                    backButton.Draw(Color.GRAY);
                }
                if (registerButton.IsClicked()){
                    registerButton.Draw(Color.GREEN);
                }
                else{
                    registerButton.Draw(Color.GRAY);
                }
                registerMessage.Draw();
                break;
            default:
                break;
        }
    }
   
    private void ClearMenus(){
        //Clearing login menu
        emailConnection.Clear();
        passwordConnection.Clear();
        choiceConnection.Reset();

        //Clearing register menu
        firstNameRegister.Clear();
        lastNameRegister.Clear();
        emailRegister.Clear();
        phoneRegister.Clear();
        addressRegister.Clear();
        passwordRegister.Clear();
        passwordConfirmationRegister.Clear();
        choiceRegister.Reset();
    }

    //Intro menu
    //private Sprite logo;
    private Button connectButton;
    private Button createAccountButton;
 
    //Login menu
    private TypingBox emailConnection = new TypingBox(new Rectangle(100, 200, 600, 50), "Enter your email...");
    private TypingBox passwordConnection = new TypingBox(new Rectangle(100, 300, 600, 50), "Enter your password...");
    private ChoiceBox choiceConnection = new ChoiceBox(new Rectangle(100, 400, 600, 50), "Select user type...");
    private UserMessage loginMessage = new UserMessage(new Point(100, 475));
    private Button loginButton;

    //Register menui
    private TypingBox firstNameRegister = new TypingBox(new Rectangle(100, 30, 275, 50), "Enter your first name...");
    private TypingBox lastNameRegister = new TypingBox(new Rectangle(425, 30, 275, 50), "Enter your last name...");
    private TypingBox emailRegister = new TypingBox(new Rectangle(100, 95, 275, 50), "Enter your email...");
    private TypingBox phoneRegister = new TypingBox(new Rectangle(425, 95, 275, 50), "Enter your phone number...");
    private TypingBox addressRegister = new TypingBox(new Rectangle(100, 165, 600, 50), "Enter your address...");
    private TypingBox passwordRegister = new TypingBox(new Rectangle(100, 245, 600, 50), "Enter your password...");
    private TypingBox passwordConfirmationRegister = new TypingBox(new Rectangle(100, 325, 600, 50), "Confirmation password...");
    private ChoiceBox choiceRegister = new ChoiceBox(new Rectangle(100, 405, 600, 50), "Select user type...");
    private UserMessage registerMessage = new UserMessage(new Point(100, 475));
    private Button registerButton;

    private Button backButton;

    private Set<String> connectionType = new HashSet<String>();
    private ConnectionStep currentStage = ConnectionStep.INTRO;
}
