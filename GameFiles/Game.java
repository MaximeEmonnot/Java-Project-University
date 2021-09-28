package GameFiles;

import java.awt.*;

public class Game {
    public Game() throws Exceptions.ProjectException{
        //Graphics initialization
        GraphicsEngine.GraphicsSystem.GetInstance();
        kirby = new Character(new Rectangle(150, 150, 64, 64), "Images/kirby25x25.png");
    }

    public void Go() throws Exceptions.ProjectException{
        UpdateFrame();
        RenderFrame();
        GraphicsEngine.GraphicsSystem.GetInstance().Render();
    }

    private void UpdateFrame(){
        //Must be called to update the DeltaTime value
        CoreSystem.Timer.GetInstance().Update();

        kirby.Update();
    }

    private void RenderFrame() throws Exceptions.ProjectException {
        kirby.Draw();
    }

    private Character kirby;
}
