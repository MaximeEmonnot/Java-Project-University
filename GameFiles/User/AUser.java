package GameFiles.User;

/**
 * Classe abstraite.
 * Contient les informations des utilisateurs
 * @author Maxime Emonnot
 */
public abstract class AUser {
    /**
     * Constructeur AUser.
     * Enregistre les differentes valeurs importantes lors de la connexion
     * @author Maxime Emonnot
     * @param _firstName Prenom de l'utilisateur
     * @param _lastName Nom de famille de l'utilisateur
     * @param _mail Email de l'utilisateur
     * @param _phoneNumber Numero de telephone de l'utilisateur
     * @param _address Adresse de l'utilisateur
     */
    public AUser(String _firstName, String _lastName, String _mail, String _phoneNumber, String _address){
        firstName = _firstName;
        lastName = _lastName;
        mail = _mail;
        phoneNumber = _phoneNumber;
        address = _address;
    }

    /**
     * Recuperation du prenom
     * @author Maxime Emonnot
     * @return Le prenom de l'utilisateur
     */
    public String GetFirstName(){
        return firstName;
    }
    /**
     * Recuperation du nom de famille
     * @author Maxime Emonnot
     * @return Le nom de famille de l'utilisateur
     */
    public String GetLastName(){
        return lastName;
    }
    /**
     * Recuperation de l'email
     * @return L'email de l'utilisateur
     */
    public String GetMail(){
        return mail;
    }
    /**
     * Recuperation du numero de telephone
     * @return Le numero de telephone de l'utilisateur
     */
    public String GetPhoneNumber(){
        return phoneNumber;
    }
    /**
     * Recuperation de l'adresse
     * @return L'adresse de l'utilisateur
     */
    public String GetAddress(){
        return address;
    }

    private String firstName;
    private String lastName;
    private String mail;
    private String phoneNumber;
    private String address;
}
