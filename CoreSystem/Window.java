package CoreSystem;

import javax.swing.*;

import java.awt.event.KeyEvent;

/**
 * Gestion de la fenetre de Java Swing. S'occupe de la creation et de la boucle d'execution. 
 * Utilise le patron de conception Singleton pour etre unique dans toute l'application.
 * @author Maxime Emonnot
 */
public class Window {
    /**
     * Constructeur privé de Window dans le cadre du Singleton. Initalisation de l'objet JFrame.
     * @author Maxime Emonnot
     * @throws Exceptions.ProjectException Si une erreur a lieu lors de la creation du JFRame (n'arrive jamais)
     */
    private Window() throws Exceptions.ProjectException{
        frame = new JFrame("Quiz Game");
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

    /**
     * Dans le cadre du patron Singleton.
     * @author Maxime Emonnot
     * @throws Exceptions.ProjectException Si une erreur a lieu lors de la creation du JFrame (n'arrive jamais)
     * @return L'instance de Window
     *  */    
    public synchronized static Window GetInstance() throws Exceptions.ProjectException{
        if (INSTANCE == null)
            INSTANCE = new Window();
        return INSTANCE;
    }

    /**
     * Retourne l'object JFrame de cette classe. Est recupere par GraphicsSystem pour l'initialisation de l'interface graphique
     * @author Maxime Emonnot
     * @return frame
     */
    public JFrame getFrame(){
        return frame;
    }

    private static Window INSTANCE = null;

    private JFrame frame;
    
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
}
