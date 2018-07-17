package by.z1max.service;

import by.z1max.UserTestData;
import by.z1max.dao.UserDaoImpl;
import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Role;
import by.z1max.model.User;
import by.z1max.util.PasswordEncoder;
import by.z1max.util.db.ConnectionPool;
import by.z1max.util.db.DataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

import static by.z1max.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static ConnectionPool connectionPool;
    private static DataSource dataSource;
    private static UserService service;

    @BeforeClass
    public static void before(){
        connectionPool = new ConnectionPool();
        dataSource = DataSource.getInstance(connectionPool);
        service = new UserServiceImpl(new UserDaoImpl(dataSource), new PasswordEncoder());
    }

    @AfterClass
    public static void after(){
        connectionPool.dispose();
        dataSource = null;
        service = null;
    }

    @Test
    public void get() throws ServiceException {
        User actual = service.get(ADMIN.getId());
        assertThat(actual).isEqualToComparingFieldByField(ADMIN);
    }

    @Test
    public void getNotExistId() throws ServiceException {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("Entity not found!");
        service.get(100);
    }

    @Test
    public void getByEmail() throws ServiceException {
        User actual = service.getByEmail(ADMIN.getEmail());
        assertThat(actual).isEqualToComparingFieldByField(ADMIN);
    }

    @Test
    public void getByNotExistEmail() throws ServiceException {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("Entity not found!");
        service.getByEmail("notexist@gmail.com");
    }

    @Test
    public void getAll() throws ServiceException {
        List<User> actual =  service.getAll();
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(UserTestData.getAll());
    }

    @Test
    public void create() throws ServiceException {
        User user = new User("newuser", "newuser@gmail.com", "newuserpass");
        user.setRoles(EnumSet.of(Role.ROLE_USER));
        User actual = service.save(user);
        assertThat(actual).isEqualToComparingFieldByField(NEW);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(UserTestData.getAllForCreate());
        restoreAfterCreate();
    }

    @Test
    public void createWithDuplicateUsername() throws ServiceException {
        User user = new User("admin", "superadmin@gmail.com","superadminpass");
        thrown.expect(ServiceException.class);
        thrown.expectMessage("Error saving user");
        service.save(user);
    }

    @Test
    public void createWithDuplicateEmail() throws ServiceException {
        User user = new User("admin1", "admin@gmail.com","superadminpass");
        thrown.expect(ServiceException.class);
        thrown.expectMessage("Error saving user");
        service.save(user);
    }

    @Test
    public void update() throws ServiceException {
        User user = new User(2, "updated", "updated@gmail.com", "updatedpass",
                LocalDate.of(2018, 06, 11), 32, true);
        user.setRoles(EnumSet.of(Role.ROLE_USER));
        User updated = service.save(user);
        assertThat(updated).isEqualToComparingFieldByField(UPDATED);
        User restored = new User(USER1.getId(), USER1.getUsername(), USER1.getEmail(), "valerapass",
                USER1.getRegistered(), USER1.getPoints(), USER1.isEnabled());
        restored.setRoles(EnumSet.of(Role.ROLE_USER));
        service.save(restored);
    }

    @Test
    public void delete() throws ServiceException {
        service.delete(ADMIN);
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(UserTestData.getAllForDelete());
        restoreAfterDelete();
    }

    private void restoreAfterCreate(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE id = 6");
            statement.executeUpdate("ALTER TABLE user AUTO_INCREMENT = 6");
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.releaseConnection(connection, statement, null);
        }
    }

    private void restoreAfterDelete(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user(id, username, email, password, registered, points, enabled)" +
                    " VAlUES (1, 'admin', 'admin@gmail.com', '713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca'," +
                    "'2018-06-10', 10, 1)");
            statement.executeUpdate("INSERT INTO user_role(user_id, role) VALUES (1, 0), (1, 1)");
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.releaseConnection(connection, statement, null);
        }
    }
}