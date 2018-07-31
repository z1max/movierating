package by.epam.util.db;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("db/db");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
