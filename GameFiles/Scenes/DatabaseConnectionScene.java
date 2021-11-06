package GameFiles.Scenes;

import java.awt.*;
import java.sql.SQLException;

import DataBaseSystem.DataBaseManager;
import Exceptions.ProjectException;
import MenuSystem.Button;
import MenuSystem.Lambda;
import MenuSystem.TypingBox;

public class DatabaseConnectionScene extends AScene {

    public DatabaseConnectionScene(Lambda func) throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
        nextSceneIndex = 1;
        connectionButton = new Button(new Rectangle(100, 450, 500, 50), "Connect", () -> {
            if (databaseName.GetText().length() != 0 && databasePassword.GetText().length() != 0){
                try {
                    dbm = new DataBaseManager(databaseName.GetText(), databasePassword.GetText());
                    System.out.println("Connected");
                    bChangeScene = true;
                    func.func();
                } catch (ClassNotFoundException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Please complete all the fields!");
            }
        } );

        databasePassword.SetPasswordMode(true);
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        databaseName.Update();
        databasePassword.Update();
        if (connectionButton.OnClick(e)){
            connectionButton.ComputeFunction();
        }
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

        databaseName.Draw();
        databasePassword.Draw();
        if (connectionButton.IsClicked()){
            connectionButton.Draw(Color.GREEN);
        }
        else{
            connectionButton.Draw(Color.GRAY);
        }
    }
    

    private TypingBox databaseName = new TypingBox(new Rectangle(100, 250, 600, 50), "Enter database name...");
    private TypingBox databasePassword = new TypingBox(new Rectangle(100, 350, 600, 50), "Enter database password...");
    private Button connectionButton;
}
