package GameFiles.Scenes;

import java.util.ArrayList;

import java.sql.ResultSet;

import MenuSystem.*;
import java.awt.*;
import java.sql.SQLException;

import DataBaseSystem.DataBaseManager;
import Exceptions.ProjectException;

public class SearchScene extends AScene {
    public SearchScene() throws ClassNotFoundException, SQLException{
        super();
        dbm = new DataBaseManager("ok", "graxime");
        System.out.println("Connected");
        ResultSet rs = dbm.GetResultFromSQLRequest("SELECT * FROM ok.poi WHERE (LOWER(name) LIKE '%" + searchName.GetText() + "')");
        while(rs.next()){
            test.add(rs.getString("name"));
        }
    }

    @Override
    public void Update() throws SQLException {
        searchName.Update();
    }

    @Override
    public void Draw() throws ProjectException{
        searchName.Draw();
        for (int i = 0,  j = 0; i < test.size(); i++){
            if (test.get(i).startsWith(searchName.GetText())){
                GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(50, 10 + 75 * j, 100, 50), Color.WHITE, 5);
                GraphicsEngine.GraphicsSystem.GetInstance().DrawText(test.get(i).toString(), new Point(50, 25 + 75 * j), 12, Color.BLACK, 6);
                j++;
            }
        }
    }

    TypingBox searchName = new TypingBox(new Rectangle(400, 450, 100, 50), 22);
    DataBaseManager dbm;
    ArrayList<String> test = new ArrayList<String>();
}
