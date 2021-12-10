package GameFiles.User;

/**
 * Definition d'un des types d'utilisateurs : Professeur
 * @author Maxime Emonnot
 * @version 1.2.0
 */
public class Teacher extends AUser{

    /**
     * Constructeur Teacher
     * <p>Reprend le constructeur de AUser
     * @author Maxime Emonnot
     * @param _firstName Prenom de l'enseignant
     * @param _lastName Nom de famillede l'enseignant
     * @param _mail Email de l'enseignant
     * @param _phoneNumber Numero de telephone de l'enseignant
     * @param _address Adresse de l'enseignant
     */
    public Teacher(String _firstName, String _lastName, String _mail, String _phoneNumber, String _address) {
        super(_firstName, _lastName, _mail, _phoneNumber, _address);
        //TODO Auto-generated constructor stub
    }
    
}
