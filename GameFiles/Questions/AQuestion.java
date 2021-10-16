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
    }

    private String question;
}
