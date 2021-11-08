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
        lastStudentPage = new Button(new Rectangle(100, 450, 25, 25), "<", () -> {iCurPageStudentList--;});
        nextStudentPage = new Button(new Rectangle(500, 450, 25, 25), ">", () -> {iCurPageStudentList++;});
        
        teacherListButton = new Button(new Rectangle(100, 250, 600, 50), "Edit teacher list", () -> {
          try{
            ResetTeacherList();
            currentStage = SceneStage.TEACHER_LIST;
          }
          catch(SQLException e){
            e.printStackTrace();
          }
        });
        lastTeacherPage = new Button(new Rectangle(100, 450, 25, 25), "<", () -> {iCurPageTeacherList--;});
        nextTeacherPage = new Button(new Rectangle(500, 450, 25, 25), ">", () -> {iCurPageTeacherList++;});
      
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
            if(studentList.containsKey(iCurPageStudentList)){
              Iterator<Map.Entry<TextBox, Button>> itrStudent = studentList.get(iCurPageStudentList).entrySet().iterator();
              while (itrStudent.hasNext()){
                Button btn = itrStudent.next().getValue();
                if (btn.OnClick(e)){
                  btn.ComputeFunction();
                  break;
                }
              }
            }
            if (studentList.containsKey(iCurPageStudentList - 1)){
              if (lastStudentPage.OnClick(e)){
                lastStudentPage.ComputeFunction();
              }
            }
            if (studentList.containsKey(iCurPageStudentList + 1)){
              if (nextStudentPage.OnClick(e)){
                nextStudentPage.ComputeFunction();
              }
            }

            if (backButton.OnClick(e)){
              backButton.ComputeFunction();
            }
            deletionMessage.Update();
            break;
          case TEACHER_LIST:
            if (teacherList.containsKey(iCurPageTeacherList)){
              Iterator<Map.Entry<TextBox, Button>> itrTeacher = teacherList.get(iCurPageTeacherList).entrySet().iterator();
              while (itrTeacher.hasNext()){
                Button btn = itrTeacher.next().getValue();
                if (btn.OnClick(e)){
                  btn.ComputeFunction();
                  break;
                }
              }
            }
            if (teacherList.containsKey(iCurPageTeacherList - 1)){
              if (lastTeacherPage.OnClick(e)){
                lastTeacherPage.ComputeFunction();
              }
            }
            if (teacherList.containsKey(iCurPageTeacherList + 1)){
              if (nextTeacherPage.OnClick(e)){
                nextTeacherPage.ComputeFunction();
              }
            }
            if (backButton.OnClick(e)){
              backButton.ComputeFunction();
            }
            deletionMessage.Update();
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
                    studentListButton.Draw(Color.DARK_GRAY);
                }
                else{
                    studentListButton.Draw(Color.LIGHT_GRAY);
                }
                if (teacherListButton.IsClicked()){
                    teacherListButton.Draw(Color.DARK_GRAY);
                }
                else{
                    teacherListButton.Draw(Color.LIGHT_GRAY);
                }
                break;
            case STUDENT_LIST:
                if (studentList.containsKey(iCurPageStudentList)){
                  Iterator<Map.Entry<TextBox, Button>> itrStudent = studentList.get(iCurPageStudentList).entrySet().iterator();
                  while(itrStudent.hasNext()){
                      Map.Entry<TextBox, Button> currentPair = itrStudent.next();
                      currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                      if (currentPair.getValue().IsClicked()){
                        currentPair.getValue().Draw(Color.DARK_GRAY);
                      }
                      else{
                        currentPair.getValue().Draw(Color.LIGHT_GRAY);
                      }
                  }
                }
                if (studentList.containsKey(iCurPageStudentList - 1)){
                  if (lastStudentPage.IsClicked()){
                    lastStudentPage.Draw(Color.DARK_GRAY);
                  }
                  else{
                    lastStudentPage.Draw(Color.LIGHT_GRAY);
                  }
                }
                if (studentList.containsKey(iCurPageStudentList + 1)){
                  if (nextStudentPage.IsClicked()){
                    nextStudentPage.Draw(Color.DARK_GRAY);
                  }
                  else{
                    nextStudentPage.Draw(Color.LIGHT_GRAY);
                  }
                }
                if (backButton.IsClicked()){
                    backButton.Draw(Color.DARK_GRAY);
                }
                else{
                    backButton.Draw(Color.LIGHT_GRAY);
                }
                deletionMessage.Draw();
                break;
              case TEACHER_LIST:
                if (teacherList.containsKey(iCurPageTeacherList)){
                  Iterator<Map.Entry<TextBox, Button>> itrTeacher = teacherList.get(iCurPageTeacherList).entrySet().iterator();
                  while(itrTeacher.hasNext()){
                    Map.Entry<TextBox, Button> currentPair = itrTeacher.next();
                    currentPair.getKey().Draw(Color.BLACK, Color.GRAY, Color.WHITE);
                    if (currentPair.getValue().IsClicked()){
                      currentPair.getValue().Draw(Color.DARK_GRAY);
                    }
                    else{
                      currentPair.getValue().Draw(Color.LIGHT_GRAY);
                    }
                  }
                }

                if (teacherList.containsKey(iCurPageTeacherList - 1)){
                  if (lastTeacherPage.IsClicked()){
                    lastTeacherPage.Draw(Color.DARK_GRAY);
                  }
                  else{
                    lastTeacherPage.Draw(Color.LIGHT_GRAY);
                  }
                }
                if (teacherList.containsKey(iCurPageTeacherList + 1)){
                  if (nextTeacherPage.IsClicked()){
                    nextTeacherPage.Draw(Color.DARK_GRAY);
                  }
                  else{
                    nextTeacherPage.Draw(Color.LIGHT_GRAY);
                  }
                }

                if (backButton.IsClicked()){
                  backButton.Draw(Color.DARK_GRAY);
                }
                else{
                  backButton.Draw(Color.LIGHT_GRAY);
                }
                deletionMessage.Draw();
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
            String name = studentSet.getString("nom") + " " + studentSet.getString("prenom");
            if (!studentList.containsKey(i/5)){
              studentList.put(i/5, new HashMap<TextBox, Button>());
            }
            studentList.get(i/5).put(new TextBox(new Rectangle(100, 50 + (i % 5) * 75, 400, 50), studentSet.getString("nom") + " " + studentSet.getString("prenom")), new Button(new Rectangle(500, 50 + (i % 5) * 75, 200, 50), "Delete", () -> {
              try {
                dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".etudiant WHERE id_etudiant = " + id + ";");
                deletionMessage.SetMessage("Student " + name + " deleted !", Color.GREEN, 5.0f);
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
            String name = teacherSet.getString("nom") + " " + teacherSet.getString("prenom");
            if (!teacherList.containsKey(i/5)){
              teacherList.put(i/5, new HashMap<TextBox, Button>());
            }
            teacherList.get(i/5).put(new TextBox(new Rectangle(100, 50 + (i%5) * 75, 400, 50), teacherSet.getString("nom") + " " + teacherSet.getString("prenom")), new Button(new Rectangle(500, 50 + (i % 5) * 75, 200, 50), "Delete", () -> {
              try {
                dbm.SendSQLRequest("DELETE FROM " + dbm.GetDatabaseName() + ".professeur WHERE id_professeur = " + id + ";");
                deletionMessage.SetMessage("Teacher " + name + " deleted !", Color.GREEN, 5.0f);
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
     private Map<Integer, HashMap<TextBox, Button>> studentList = new HashMap<Integer, HashMap<TextBox, Button>>();
     private int iCurPageStudentList = 0;
     private Button lastStudentPage;
     private Button nextStudentPage;
    
     //Teacher List menu
     private Map<Integer, HashMap<TextBox, Button>> teacherList = new HashMap<Integer, HashMap<TextBox, Button>>();
     private int iCurPageTeacherList = 0;
     private Button lastTeacherPage;
     private Button nextTeacherPage;
    
     private UserMessage deletionMessage = new UserMessage(new Point(100, 500));
     private Button backButton;
     private SceneStage currentStage = SceneStage.SELECTION;
}
