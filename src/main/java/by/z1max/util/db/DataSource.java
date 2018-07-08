package by.z1max.util.db;

import by.z1max.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataSource {
    private static  volatile DataSource instance;
    private ConnectionPool pool;

    private DataSource(ConnectionPool pool){
        this.pool = pool;
        try {
            pool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getInstance(ConnectionPool pool){
        if (instance == null){
            synchronized (DataSource.class){
                if (instance == null){
                    instance = new DataSource(pool);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws ConnectionPoolException {
        return pool.take();
    }

    public boolean releaseConnection(Connection connection, Statement statement, ResultSet resultSet){
        boolean result = false;
        pool.close(statement, resultSet);
        if (connection != null) {
            result = pool.release(connection);
        }
        return result;
    }

    public void dispose(){
        pool.dispose();
        pool = null;
    }
}
