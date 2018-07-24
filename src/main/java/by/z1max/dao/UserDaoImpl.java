package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Role;
import by.z1max.model.User;
import by.z1max.util.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static by.z1max.util.mapper.UserMapper.map;
import static by.z1max.util.mapper.UserMapper.mapUserList;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private static final String FIND_BY_ID = "SELECT id, username, email, password, registered, points, enabled, group_concat(role SEPARATOR ',') AS roles " +
            "FROM movie_rating.user JOIN user_role ON id = user_id WHERE id = ?";
    private static final String FIND_BY_EMAIL = "SELECT id, username, email, password, registered, points, enabled, group_concat(role SEPARATOR ',') AS roles " +
            "FROM movie_rating.user JOIN user_role ON id = user_id WHERE email = ?";
    private static final String FIND_ALL = "SELECT id, username, email, password, registered, points, enabled, group_concat(role SEPARATOR ',') AS roles" +
            " FROM user JOIN user_role ON id = user_id GROUP BY username;";
    private static final String DELETE = "DELETE FROM movie_rating.user WHERE id = ?";
    private static final String DELETE_ROLES = "DELETE FROM movie_rating.user_role WHERE user_id = ?";
    private static final String CREATE = "INSERT INTO movie_rating.user(username, email, password, registered, points, enabled) VALUES (?,?,?,?,?,?)";
    private static final String CREATE_ROLES = "INSERT INTO movie_rating.user_role(user_id, role) VALUES (?,?)";
    private static final String UPDATE = "UPDATE movie_rating.user SET username=?, email=?, password=?, registered=?, points=?, enabled=? WHERE id =?";
    private static final String ADD_POINTS = "UPDATE user SET points=points+? WHERE id=?";

    private ConnectionPool pool;

    public UserDaoImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Optional<User> findById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return map(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding user by id = " + id, e);
            throw new DaoException("Error finding user by id = " + id, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            return map(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding user by email = " + email, e);
            throw new DaoException("Error finding user by email = " + email, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            return mapUserList(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding all users", e);
            throw new DaoException("Error finding all users", e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public User save(User user) throws DaoException {
        if (user.isNew()){
            return create(user);
        } else {
            return update(user);
        }
    }

    @Override
    public boolean addPoints(int userId, int points) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(ADD_POINTS);
            statement.setInt(1, points);
            statement.setInt(2, userId);
            return statement.executeUpdate() == 1;
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error adding " + points + " points to user with id = " + userId, e);
            throw new DaoException("Error adding " + points + " points to user with id = " + userId, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(false);
            Statement checkForKeys = connection.createStatement();
            checkForKeys.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            statement = connection.prepareStatement(DELETE_ROLES);
            statement.setInt(1, id);
            statement.executeUpdate();
            checkForKeys.executeUpdate("SET FOREIGN_KEY_CHECKS=1");
            checkForKeys.close();
            return rows == 1;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error deleting user with id = " + id);
            throw new DaoException("Error deleting user with id = " + id, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private User create(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            setFields(user, statement);

            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new SQLException("Error creating user");
            }

            resultSet = statement.getGeneratedKeys();
            resultSet.first();
            int id = resultSet.getInt(1);

            for (Role role : user.getRoles()) {
                statement = connection.prepareStatement(CREATE_ROLES);
                statement.setInt(1, id);
                statement.setInt(2, role.ordinal());
                statement.executeUpdate();
            }

            user.setId(id);
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            throw new DaoException("Error creating user: " + user, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private User update(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(UPDATE);

            setFields(user, statement);
            statement.setInt(7, user.getId());
            int rows = statement.executeUpdate();

            if (rows == 0){
                throw new SQLException("Error updating user");
            }
            statement = connection.prepareStatement(DELETE_ROLES);
            statement.setInt(1, user.getId());
            statement.executeUpdate();

            for (Role role : user.getRoles()) {
                statement = connection.prepareStatement(CREATE_ROLES);
                statement.setInt(1, user.getId());
                statement.setInt(2, role.ordinal());
                statement.executeUpdate();
            }
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            throw new DaoException("Error updating user: " + user, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private void setFields(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setDate(4, Date.valueOf(user.getRegistered()));
        statement.setInt(5, user.getPoints());
        statement.setInt(6, user.isEnabled() ? 1 : 0);
    }
}
