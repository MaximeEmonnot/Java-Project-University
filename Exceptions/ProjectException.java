package Exceptions;

import javax.swing.JOptionPane;
import javax.swing.*;

/**
 * Exception propre au Projet
 * Utilisee pour differencier les erreurs
 * @author Maxime Emonnot
 */
public class ProjectException extends Exception{
    /**
     * Constructeur par defaut
     * @author Maxime Emonnot
     */
    public ProjectException(){
        super();
    }
    /**
     * Constructeur avec message
     * @param message Message d'erreur
     * @author Maxime Emonnot
     */
    public ProjectException(String message){
        super(message);
    }
    /**
     * Constructeur avec message et throwable
     * @param message Message d'erreur
     * @param t Objet pouvant etre throw
     * @author Maxime Emonnot
     */
    public ProjectException(String message, Throwable t){
        super(message, t);
    }

    /**
     * Affichage d'une exception attrapee.
     * @param e Exception attrapee dans le bloc try-catch du main
     * @param title Titre de la fenetre d'exception
     * @author Maxime Emonnot
     */
    public static void showMessageBox(Exception e, String title){
        
        String message = "Message : " + e.getMessage() + "\n\n";
        for (int i = 0; i < e.getStackTrace().length; i++){
            message +=
            "[Method name] : " + e.getStackTrace()[i].getMethodName() +
            "\n[File] : " + e.getStackTrace()[i].getFileName() + 
            "\n[Line] : " + e.getStackTrace()[i].getLineNumber() + "\n\n";
        }
        JOptionPane.showMessageDialog(new JFrame(title), message, title, JOptionPane.ERROR_MESSAGE);
    }
}