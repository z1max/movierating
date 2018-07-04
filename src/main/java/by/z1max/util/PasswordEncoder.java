package by.z1max.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private MessageDigest digest;

    public PasswordEncoder()  {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            //TODO
        }
    }

    public String encode(String password){
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();

        StringBuilder hexString = new StringBuilder();
        for (byte nextByte : bytes) {
            String hex = Integer.toHexString(0xff & nextByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
