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
        String message = "Message : " + e.getMessage() + "\n\n" + 
        "[Method name] : " + e.getStackTrace()[0].getMethodName() + 
        "\n[File] : " + e.getStackTrace()[0].getFileName() + 
        "\n[Line] : " + e.getStackTrace()[0].getLineNumber();
        JOptionPane.showMessageDialog(new JFrame(title), message, title, JOptionPane.ERROR_MESSAGE);
    }
}