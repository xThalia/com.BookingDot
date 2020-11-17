package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsefulFunctions {
    public static String stringToMD5String(String string) {
        try {
            byte[] bytesOfMessage = string.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            return thedigest.toString();
        }
        catch (UnsupportedEncodingException e) { e.printStackTrace(); }
        catch (NoSuchAlgorithmException a) { a.printStackTrace(); }

        return null;
    }
}
