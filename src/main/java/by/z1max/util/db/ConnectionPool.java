package by.z1max.util.db;

import by.z1max.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);
    private final static int DEFAULT_POOL_SIZE = 5;

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayQueue;

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;


    public ConnectionPool(){
        DBResourceManager resourceManager = DBResourceManager.getInstance();
        this.driver = resourceManager.getValue(DBParameter.DRIVER.getParameter());
        this.url = resourceManager.getValue(DBParameter.URL.getParameter());
        this.user = resourceManager.getValue(DBParameter.USER.getParameter());
        this.password = resourceManager.getValue(DBParameter.PASSWORD.getParameter());
        try {
            this.poolSize = Integer.valueOf(resourceManager.getValue(DBParameter.POOLSIZE.getParameter()));
        } catch(NumberFormatException e){
            poolSize = DEFAULT_POOL_SIZE;
        }
    }

    public void init() throws ConnectionPoolException {
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

    public Connection take() throws ConnectionPoolException {
        LOG.debug("Taking connection from pool.");
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayQueue.add(connection);
        } catch (InterruptedException e) {
            LOG.error("Error connection to the data source.", e);
            throw new ConnectionPoolException("Error connection to the data source.", e);
        }
        return connection;
    }

    public boolean release(Connection connection){
        LOG.debug("Releasing connection.");
        if (connection != null){
            givenAwayQueue.remove(connection);
            connectionQueue.add(connection);
            return true;
        }
        return false;
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
