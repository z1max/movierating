package by.z1max.util.db;

import by.z1max.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataSource {
    private static final Logger LOG = Logger.getLogger(DataSource.class);
    
    private static volatile DataSource instance;
    private ConnectionPool pool;

    private DataSource(ConnectionPool pool){
        this.pool = pool;
        try {
            pool.init();
        } catch (ConnectionPoolException e) {
            LOG.error("Error instantiating datasource");
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

    public Connection getConnection(boolean autoCommit) throws ConnectionPoolException {
        return pool.take(autoCommit);
    }

    public boolean releaseConnection(Connection connection, Statement statement, ResultSet resultSet){
        boolean result = false;
        pool.close(statement, resultSet);
        if (connection != null) {
            result = pool.release(connection);
        }
        return result;
    }
    
    public void rollback(Connection connection) {
        pool.rollback(connection);
    }
    
    
    public boolean releaseConnection(Connection connection, Statement statement){
        boolean result = false;
        pool.close(statement);
        if (connection !=null){
            result = pool.release(connection);
        }
        return result;
    }

    public void dispose(){
        pool.dispose();
        pool = null;
    }
}
