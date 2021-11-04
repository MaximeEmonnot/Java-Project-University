package GameFiles.User;

import java.lang.ref.PhantomReference;

public abstract class AUser {
    public AUser(String _firstName, String _lastName, String _mail, String _phoneNumber, String _adress){
        firstName = _firstName;
        lastName = _lastName;
        mail = _mail;
        phoneNumber = _phoneNumber;
        adress = _adress;
    }

    public String GetFirstName(){
        return firstName;
    }
    public String GetLastName(){
        return lastName;
    }
    public String GetMail(){
        return mail;
    }
    public String GetPhoneNumber(){
        return phoneNumber;
    }
    public String GetAdress(){
        return adress;
    }

    private String firstName;
    private String lastName;
    private String mail;
    private String phoneNumber;
    private String adress;
}
