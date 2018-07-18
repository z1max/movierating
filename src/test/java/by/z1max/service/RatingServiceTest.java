package by.z1max.service;

import by.z1max.dao.RatingDao;
import by.z1max.dao.RatingDaoImpl;
import by.z1max.dao.UserDao;
import by.z1max.dao.UserDaoImpl;
import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;
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

import static by.z1max.RatingTestData.NEW;
import static by.z1max.RatingTestData.RATING;
import static org.assertj.core.api.Assertions.assertThat;

public class RatingServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static ConnectionPool connectionPool;
    private static DataSource dataSource;
    private static RatingDao ratingDao;
    private static UserDao userDao;
    private static RatingService service;

    @BeforeClass
    public static void before(){
        connectionPool = new ConnectionPool();
        dataSource = DataSource.getInstance(connectionPool);
        ratingDao = new RatingDaoImpl(dataSource);
        userDao = new UserDaoImpl(dataSource);
        service = new RatingServiceImpl(ratingDao, userDao);
    }

    @AfterClass
    public static void after(){
        connectionPool.dispose();
        dataSource = null;
        ratingDao = null;
        service = null;
    }

    @Test
    public void getById() throws ServiceException {
        Rating actual = service.getById(1, 2);
        assertThat(actual).isEqualToComparingFieldByField(RATING);
    }

    @Test
    public void getByNotExistsId() throws ServiceException {
        thrown.expect(ServiceException.class);
        thrown.expectMessage("Entity not found");
        service.getById(100, 100);
    }

    @Test
    public void create() throws ServiceException, DaoException {
        Rating newRating = new Rating(1, 14, (byte)8);
        Rating created = service.create(newRating);
        assertThat(created).isEqualToComparingFieldByField(NEW);
        restoreAfterCreate();
    }

    private void restoreAfterCreate(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection(true);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM rating WHERE user_id = 1 AND movie_id = 14");
        } catch (ConnectionPoolException | SQLException ignored) {

        } finally {
            dataSource.releaseConnection(connection, statement);
        }
    }
}