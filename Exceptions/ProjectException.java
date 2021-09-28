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
        JOptionPane.showMessageDialog(new JFrame(title), e.getMessage(), title, JOptionPane.ERROR_MESSAGE);
    }
}