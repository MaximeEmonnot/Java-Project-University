package GameFiles.Questions;

import java.awt.*;

import Exceptions.ProjectException;

public abstract class AQuestion {
    public AQuestion(String _question){
        question = _question;
    }

    public abstract void Update();
    public void Draw() throws ProjectException
    {
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(new Rectangle(0, 480, 800, 105), Color.WHITE, 15);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(new Rectangle(0, 480, 800, 105), Color.BLACK, 16);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(question, new Point((int)((800 - (question.length() * 11))/2), 500), new Font("Arial Bold", Font.BOLD, 22), Color.BLACK, 16);
     
        if (bIsWon){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText("WON", new Point(700, 550), new Font("Arial Bold", Font.BOLD, 22), Color.GREEN, 20);
        }
        else if (bIsLost){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText("LOST", new Point(700, 550), new Font("Arial Bold", Font.BOLD, 22), Color.RED, 20);
        }

    }

    private String question;
    protected boolean bIsWon;
    protected boolean bIsLost;
}
