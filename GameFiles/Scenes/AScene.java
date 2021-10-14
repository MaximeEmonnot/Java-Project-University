package GameFiles.Scenes;

import java.sql.SQLException;

import Exceptions.ProjectException;

public abstract class AScene {
    public AScene(){
    }

    public abstract void Update() throws SQLException;
    public abstract void Draw() throws ProjectException;
}
