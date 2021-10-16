package GameFiles.Scenes;

import java.util.ArrayList;

import java.sql.ResultSet;

import MenuSystem.*;
import MenuSystem.Button;

import java.awt.*;
import java.sql.SQLException;

import DataBaseSystem.DataBaseManager;
import Exceptions.ProjectException;

public class SearchScene extends AScene {
    public SearchScene() throws ClassNotFoundException, SQLException, ProjectException{
        super();
       // dbm = new DataBaseManager("ok", "graxime");
        System.out.println("Connected");
        //ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM ok.poi");
       // while(rs.next()){
        //    test.add(rs.getString("name"));
       // }
       refreshButton = new Button(new Rectangle(660, 500, 100, 40), "Refresh", 22, () -> {});
    }
    
    @Override
    public void Update() throws SQLException {
        searchName.Update();
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
        searchName.Draw();
        searchCategory.Draw();
        searchDifficulty.Draw();
        searchId.Draw();
        if (refreshButton.IsClicked()){
            refreshButton.Draw(Color.DARK_GRAY);
        }
        else{
            refreshButton.Draw(Color.LIGHT_GRAY);
        }
        for (int i = 0,  j = 0; i < test.size(); i++){
            if (test.get(i).startsWith(searchName.GetText())){
                GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(50, 10 + 75 * j, 100, 50), Color.WHITE, 5);
                GraphicsEngine.GraphicsSystem.GetInstance().DrawText(test.get(i).toString(), new Point(50, 25 + 75 * j), Color.BLACK, 6);
                j++;
            }
        }
    }
    
    //private DataBaseManager dbm;

    private TypingBox searchName = new TypingBox(new Rectangle(15, 500, 150, 50), 22);
    private TypingBox searchCategory = new TypingBox(new Rectangle(175, 500, 150, 50), 22);
    private TypingBox searchDifficulty = new TypingBox(new Rectangle(335, 500, 150, 50), 22);
    private TypingBox searchId = new TypingBox(new Rectangle(495, 500, 150, 50), 22);

    private Button refreshButton;
    private ArrayList<String> test = new ArrayList<String>();
}
