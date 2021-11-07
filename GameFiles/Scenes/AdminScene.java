package GameFiles.Scenes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;

import Exceptions.ProjectException;
import MenuSystem.*;
import MenuSystem.Button;

public class AdminScene extends AScene{

    private enum SceneStage{
        SELECTION,
        STUDENT_LIST,
        TEACHER_LIST
     }  

    public AdminScene() throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
        backButton = new Button(new Rectangle(500, 500, 100, 50), "Back", () -> {
            currentStage = SceneStage.SELECTION;
        });
        studentListButton = new Button(new Rectangle(100, 150, 600, 50), "Edit student list", () -> {
          try{
            ResetStudentList();
            currentStage = SceneStage.STUDENT_LIST;
          }
          catch(SQLException e){
            e.printStackTrace();
          }
        });
        teacherListButton = new Button(new Rectangle(100, 250, 600, 50), "Edit teacher list", () -> {
          try{
            ResetTeacherList();
            currentStage = SceneStage.TEACHER_LIST;
          }
          catch(SQLException e){
            e.printStackTrace();
          }
        });
    }

    @Override
    public void Update() throws SQLException {
        // TODO Auto-generated method stub
        CoreSystem.Mouse.EventType e = CoreSystem.Mouse.GetInstance().Read();

        switch(currentStage){
          case SELECTION:
            if (studentListButton.OnClick(e)){
              studentListButton.ComputeFunction();  
            }
            if (teacherListButton.OnClick(e)){
              teacherListButton.ComputeFunction();  
            }
            break;
          case STUDENT_LIST:
            Iterator<Map.Entry<TextBox, Button>> itrStudent = studentList.entrySet().iterator();
            while (itrStudent.hasNext()){
              Button btn = itrStudent.next().getValue();
              if (btn.OnClick(e)){
                btn.ComputeFunction();
                break;
              }
            }
            if (backButton.OnClick(e)){
              backButton.ComputeFunction();
            }
            break;
          case TEACHER_LIST:
            Iterator<Map.Entry<TextBox, Button>> itrTeacher = teacherList.entrySet().iterator();
            while (itrTeacher.hasNext()){
              Button btn = itrTeacher.next().getValue();
              if (btn.OnClick(e)){
                btn.ComputeFunction();
                break;
              }
            }
            if (backButton.OnClick(e)){
              backButton.ComputeFunction();
            }
            break;
          default:
            break;
        }
    }

    @Override
    public void Draw() throws ProjectException {
        // TODO Auto-generated method stub
        GraphicsEngine.GraphicsSystem.GetInstance().SetBackgroundColor(Color.WHITE);

        switch(currentStage){
            case SELECTION:
                if (studentListButton.IsClicked()){
                    studentListButton.Draw(Color.GREEN);
                }
                else{
                    studentListButton.Draw(Color.GRAY);
                }
                if (teacherListButton.IsClicked()){
                    teacherListButton.Draw(Color.GREEN);
                }
                else{
                    teacherListButton.Draw(Color.GRAY);
                }
                break;
            case STUDENT_LIST:
                Iterator<Map.Entry<TextBox, Button>> itrStudent = studentList.entrySet().iterator();
                while(itrStudent.hasNext()){
                    Map.Entry<TextBox, Button> currentPair = itrStudent.next();
                    currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                    if (currentPair.getValue().IsClicked()){
                      currentPair.getValue().Draw(Color.GREEN);
                    }
                    else{
                      currentPair.getValue().Draw(Color.GRAY);
                    }
                }
                if (backButton.IsClicked()){
                    backButton.Draw(Color.GREEN);
                }
                else{
                    backButton.Draw(Color.GRAY);
                }
                break;
            case TEACHER_LIST:
                Iterator<Map.Entry<TextBox, Button>> itrTeacher = teacherList.entrySet().iterator();
                while(itrTeacher.hasNext()){
                  Map.Entry<TextBox, Button> currentPair = itrTeacher.next();
                  currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                  if (currentPair.getValue().IsClicked()){
                    currentPair.getValue().Draw(Color.GREEN);
                  }
                  else{
                    currentPair.getValue().Draw(Color.GRAY);
                  }
                }
                if (backButton.IsClicked()){
                    backButton.Draw(Color.GREEN);
                }
                else{
                    backButton.Draw(Color.GRAY);
                }
                break;
            default:
                break;
        }
    }
    
    private void ResetStudentList() throws SQLException{
        studentList.clear();
        ResultSet studentSet = dbm.GetResultFromSQLRequest("SELECT id_etudiant, nom, prenom FROM " + dbm.GetDatabaseName() + ".etudiant");
        int i = 0;
        while(studentSet.next()){
            int id = studentSet.getInt("id_etudiant");
            studentList.put(new TextBox(new Rectangle(100, 50 + i * 75, 400, 50), studentSet.getString("nom") + " " + studentSet.getString("prenom")), new Button(new Rectangle(500,     50 + i * 75, 200, 50), "Delete", () -> {
              try {
                dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".etudiant WHERE id_etudiant = " + id + ";");
                System.out.println("Student deleted !");
                ResetStudentList();
              }
              catch(SQLException e){
                e.printStackTrace();
              }
            }));
            i++;
        }
      }

      private void ResetTeacherList() throws SQLException{
        teacherList.clear();
        ResultSet teacherSet = dbm.GetResultFromSQLRequest("SELECT id_professeur, nom, prenom FROM " + dbm.GetDatabaseName() + ".professeur");
        int i = 0;
        while(teacherSet.next()){
            int id = teacherSet.getInt("id_professeur");
            teacherList.put(new TextBox(new Rectangle(100, 50 + i * 75, 400, 50), teacherSet.getString("nom") + " " + teacherSet.getString("prenom")), new Button(new Rectangle(500,     50 + i * 75, 200, 50), "Delete", () -> {
              try {
                dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".professeur WHERE id_professeur = " + id + ";");
                System.out.println("Teacher deleted !");
                ResetTeacherList();
              }
              catch(SQLException e){
                e.printStackTrace();
            }
        }));
        i++;
        }
      }

    //Selection menu
     private Button studentListButton;
     private Button teacherListButton;
    
     //Student List menu
     private Map<TextBox, Button> studentList = new HashMap<TextBox, Button>();
    
     //Teacher List menu
     private Map<TextBox, Button> teacherList = new HashMap<TextBox, Button>();
    
     private Button backButton;
     private SceneStage currentStage = SceneStage.SELECTION;
}
