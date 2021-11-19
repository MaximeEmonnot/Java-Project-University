package GameFiles.User;

/**
 * Definition d'un des types d'utilisateurs : Etudiant
 * @author Maxime Emonnot
 */
public class Student extends AUser{

    /**
     * Constructeur Student.
     * Reprend le constructeur de AUser.
     * @author Maxime Emonnot
     * @param _firstName Prenom de l'etudiant
     * @param _lastName Nom de famille de l'etudiant
     * @param _mail Email de l'etudiant
     * @param _phoneNumber Numero de telephone de l'etudiant
     * @param _address Adresse de l'etudiant
     */
    public Student(String _firstName, String _lastName, String _mail, String _phoneNumber, String _address) {
        super(_firstName, _lastName, _mail, _phoneNumber, _address);
        //TODO Auto-generated constructor stub
    }
}
