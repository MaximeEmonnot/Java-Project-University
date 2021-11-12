package Exceptions;

import javax.swing.JOptionPane;
import javax.swing.*;

public class ProjectException extends Exception{
    public ProjectException(){
        super();
    }
    public ProjectException(String message){
        super(message);
    }
    public ProjectException(String message, Throwable t){
        super(message, t);
    }

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