package GameFiles.Scenes;

import java.sql.SQLException;
import java.awt.*;

import Exceptions.ProjectException;

public class AdminScene extends AScene{

    public AdminScene() throws ClassNotFoundException, SQLException {
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
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);
    }
    
}
