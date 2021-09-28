package CoreSystem;

import javax.swing.*;

import java.awt.event.KeyEvent;

public class Window {
    private Window() throws Exceptions.ProjectException{
        frame = new JFrame("Project frame");
        if (frame == null)
            throw new Exceptions.ProjectException("Error during Swing Window creation.");
        frame.setVisible(true);
        frame.setBounds(100, 100, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(Mouse.GetInstance());
        frame.addMouseMotionListener(Mouse.GetInstance());
        frame.addMouseWheelListener(Mouse.GetInstance());
        frame.addKeyListener(Keyboard.GetInstance());
    }

    public synchronized static Window GetInstance() throws Exceptions.ProjectException{
        if (INSTANCE == null)
            INSTANCE = new Window();
        return INSTANCE;
    }

    public JFrame getFrame(){
        return frame;
    }

    public boolean listensToEvents(){ 
        return !Keyboard.GetInstance().KeyIsPressed(KeyEvent.VK_ESCAPE);
    }

    private static Window INSTANCE = null;

    private JFrame frame;
    
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
}
