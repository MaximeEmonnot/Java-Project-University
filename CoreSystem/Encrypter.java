package CoreSystem;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
    public static String GetEncryptedPasswordFrom(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        String cryptedPassword = "";
        MessageDigest digest = MessageDigest.getInstance("SHA3-512");
        digest.reset();
        digest.update(password.getBytes("utf8"));
        cryptedPassword = String.format("%040x", new BigInteger(1, digest.digest()));
        return cryptedPassword;
    }
}
