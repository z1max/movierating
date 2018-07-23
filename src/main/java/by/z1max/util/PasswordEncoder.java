package by.z1max.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger LOG = Logger.getLogger(PasswordEncoder.class);
    private MessageDigest digest;

    public PasswordEncoder()  {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Error creating MessageDigest", e);
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
