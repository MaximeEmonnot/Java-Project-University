package GameFiles.User;

public class Student extends AUser{

    public Student(String _firstName, String _lastName, String _mail, String _phoneNumber, String _adress) {
        super(_firstName, _lastName, _mail, _phoneNumber, _adress);
        //TODO Auto-generated constructor stub
    }
  
    public void SetDescription(String newDesc){
        description = newDesc;
    }
    public String GetDescription(){
        return description;
    }

    public String description = "";
}
