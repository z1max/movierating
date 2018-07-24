package by.z1max.util.db;

import by.z1max.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);
    private final static int DEFAULT_POOL_SIZE = 5;

    private static volatile ConnectionPool instance;

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayQueue;

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;


    private ConnectionPool(){
        DBResourceManager resourceManager = DBResourceManager.getInstance();
        this.driver = resourceManager.getValue(DBParameter.DRIVER.getParameter());
        this.url = resourceManager.getValue(DBParameter.URL.getParameter());
        this.user = resourceManager.getValue(DBParameter.USER.getParameter());
        this.password = resourceManager.getValue(DBParameter.PASSWORD.getParameter());
        try {
            this.poolSize = Integer.valueOf(resourceManager.getValue(DBParameter.POOLSIZE.getParameter()));
        } catch(NumberFormatException e){
            LOG.info("Connection pool size set in default value - " + DEFAULT_POOL_SIZE);
            poolSize = DEFAULT_POOL_SIZE;
        }
    }

    private void init() throws ConnectionPoolException {
        LOG.debug("Initializing connection pool.");
        try {
            Class.forName(driver);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenAwayQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++){
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(connection);
            }
        } catch (ClassNotFoundException e) {
            LOG.error("Can't find database driver class.", e);
            throw new ConnectionPoolException("Can't find database driver class.", e);
        } catch (SQLException e) {
            LOG.error("SQLExceptoin in connection pool.", e);
            throw new ConnectionPoolException("SQLExceptoin in connection pool.", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null){
            synchronized (ConnectionPool.class){
                if (instance == null){
                    instance = new ConnectionPool();
                    try {
                        instance.init();
                    } catch (ConnectionPoolException e) {
                        LOG.error("Can't get instance of ConnectionPool", e);
                    }
                    return instance;
                }
            }
        }
        return instance;
    }

    public Connection take(boolean autoCommit) throws ConnectionPoolException {
        LOG.debug("Taking connection from pool.");
        Connection connection;
        try {
            connection = connectionQueue.take();
            if (!autoCommit){
                connection.setAutoCommit(false);
            }
            givenAwayQueue.add(connection);
        } catch (InterruptedException e) {
            LOG.error("Error connection to the data source.", e);
            throw new ConnectionPoolException("Error connection to the data source.", e);
        } catch (SQLException e) {
            LOG.error("Error getting transactional connection", e);
            throw new ConnectionPoolException("Error getting transactional connection", e);
        }
        return connection;
    }

    public boolean release(Connection connection){
        LOG.debug("Releasing connection.");
        if (connection != null){
            try {
                if (!connection.getAutoCommit()){
                    connection.commit();
                    connection.setAutoCommit(true);
                }
                givenAwayQueue.remove(connection);
                connectionQueue.add(connection);
                return true;
            } catch (SQLException e) {
                LOG.error("Connection commit failure", e);
            }
        }
        return false;
    }
    
    public void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.error("Connection rollback failure", e);
        }
    }

    public void close(Statement statement){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error("Error closing statement", e);
            }
        }  
    }
    
    public void close(Statement statement, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOG.error("Error closing ResultSet", e);
            }
        }
        close(statement);
    }

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while((connection = queue.poll()) != null){
            if (!connection.getAutoCommit()){
                connection.commit();
            }
            connection.close();
        }
    }

    public void dispose(){
        LOG.debug("Disposing pool.");
        try {
            closeConnectionQueue(givenAwayQueue);
            closeConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            LOG.error("Error closing connection.", e);
        }
    }

    public int getAvailableConnectionsCount(){
        return connectionQueue.size();
    }
}
