package GameFiles.Scenes;

import java.sql.SQLException;
import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.GraphicsSystem;

public class ForumScene extends AScene {

    public ForumScene() throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

    }
    
}
