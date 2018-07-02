package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Role;
import by.z1max.model.User;
import by.z1max.model.UserStatus;
import by.z1max.util.db.ConnectionPool;
import by.z1max.util.db.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private static final String FIND_BY_ID = "SELECT id, username, email, password, registered, status, enabled, group_concat(role SEPARATOR ',') AS roles " +
            "FROM movie_rating.user JOIN user_role ON id = user_id WHERE id = ?";
    private static final String FIND_BY_EMAIL = "SELECT id, username, email, password, registered, status, enabled, group_concat(role SEPARATOR ',') AS roles " +
            "FROM movie_rating.user JOIN user_role ON id = user_id WHERE email = ?";
    private static final String FIND_ALL = "SELECT id, username, email, password, registered, status, enabled, group_concat(role SEPARATOR ',') AS roles" +
            " FROM user JOIN user_role ON id = user_id GROUP BY username;";
    private static final String DELETE = "DELETE FROM movie_rating.user WHERE id = ?";
    private static final String DELETE_ROLES = "DELETE FROM movie_rating.user_role WHERE user_id = ?";
    private static final String CREATE = "INSERT INTO movie_rating.user(username, email, password, registered, status, enabled) VALUES (?,?,?,?,?,?)";
    private static final String CREATE_ROLES = "INSERT INTO movie_rating.user_role(user_id, role) VALUES (?,?)";
    private static final String UPDATE = "UPDATE movie_rating.user SET username=?, email=?, password=?, registered=?, status=?, enabled=? WHERE id =?";

    private DataSource dataSource = DataSource.getInstance(new ConnectionPool());

    @Override
    public User findById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            User user = map(resultSet);
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error finding user by id", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            User user = map(resultSet);
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error finding user by email", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            List<User> users = mapUserList(resultSet);
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error finding all users", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
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
    public void delete(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error deleting user", e);
        } finally {
            dataSource.releaseConnection(connection, statement, null);
        }
    }

    private User map(ResultSet resultSet) throws SQLException {
        User user;
        resultSet.first();
        try {
            user = mapFields(resultSet);
        } catch (NullPointerException e){
            user = null;
        }
        return user;
    }

    private List<User> mapUserList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        User user;
        while (resultSet.next()){
            user = mapFields(resultSet);
            users.add(user);
        }
        return users;
     }

    private User mapFields(ResultSet resultSet) throws SQLException, NullPointerException {
        User user;
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        LocalDate registered = resultSet.getDate("registered").toLocalDate();
        UserStatus status = UserStatus.valueOf(resultSet.getString("status"));
        boolean enabled = resultSet.getBoolean("enabled");
        String roles = resultSet.getString("roles");
        user = new User(id, username, email, password, registered, status, enabled);
        user.setRoles(mapRoles(roles));
        return user;
    }

    private Set<Role> mapRoles(String roles) {
        Set<Role> result = new HashSet<>();
        String[] rolesArr = roles.split(",");
        for (String role : rolesArr) {
            Role current = Role.values()[Integer.parseInt(role)];
            result.add(current);
        }
        return result;
    }

    private User create(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            setFields(user, statement);

            int rows = statement.executeUpdate();

            if (rows == 0){
                throw new SQLException("Error creating user.");
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
            connection.commit();
            connection.setAutoCommit(true);
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                //TODO log
            }
            throw new DaoException("Error creating user", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    private User update(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
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

            connection.commit();
            connection.setAutoCommit(true);
            return user;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                //TODO log
            }
            throw new DaoException("Error updating user", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    private void setFields(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setDate(4, Date.valueOf(user.getRegistered()));
        statement.setString(5, user.getStatus().toString());
        statement.setInt(6, user.isEnabled() ? 1 : 0);
    }
}
