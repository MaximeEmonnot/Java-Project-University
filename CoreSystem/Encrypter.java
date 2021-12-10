package CoreSystem;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Gestion du cryptage de mot de passe
 * Utilisation du cryptage SHA3-512
 * @author Maxime Emonnot
 * @version 1.2.0
 */
public class Encrypter {
    /**
     * Recuperation du mot de passe crypte
     * @author Maxime Emonnot
     * @param password Mot de passe a crypter
     * @return Mot de passe apres cryptage
     * @throws UnsupportedEncodingException Si le mode d'encodage n'est pas supporte 
     * @throws NoSuchAlgorithmException Si l'algorithme d'encode n'existe pas
     */
    public static String GetEncryptedPasswordFrom(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        String cryptedPassword = "";
        MessageDigest digest = MessageDigest.getInstance("SHA3-512");
        digest.reset();
        digest.update(password.getBytes("utf8"));
        cryptedPassword = String.format("%040x", new BigInteger(1, digest.digest()));
        return cryptedPassword;
    }
}
