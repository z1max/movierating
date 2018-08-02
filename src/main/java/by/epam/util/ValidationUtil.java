package by.epam.util;

import by.epam.exception.ServiceException;

public class ValidationUtil {
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=\\S+$)[A-Za-z\\d#$%^&*?!]{5,32}$";

    public static <T> T checkNotFound(T object) throws ServiceException {
        if (object == null){
            throw new ServiceException("Entity not found!");
        }
        return object;
    }

    public static void checkNotFound(boolean found) throws ServiceException {
        if (!found){
            throw new ServiceException("Entity not found");
        }
    }

    public static boolean checkLength(String string, int bound){
        return string.length() <= bound;
    }

    public static boolean validateUsername(String username){
        return username.matches(USERNAME_PATTERN);
    }

    public static boolean validateEmail(String email){
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean validatePassword(String password){
        return password.matches(PASSWORD_PATTERN);
    }
}
