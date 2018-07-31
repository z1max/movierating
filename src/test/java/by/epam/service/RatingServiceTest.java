package by.epam.service;

import by.epam.dao.RatingDao;
import by.epam.dao.RatingDaoImpl;
import by.epam.dao.UserDao;
import by.epam.dao.UserDaoImpl;
import by.epam.exception.ConnectionPoolException;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.Rating;
import by.epam.util.db.ConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static by.epam.RatingTestData.NEW;
import static by.epam.RatingTestData.RATING;
import static org.assertj.core.api.Assertions.assertThat;

public class RatingServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static ConnectionPool connectionPool;
    private static RatingDao ratingDao;
    private static UserDao userDao;
    private static RatingService service;

    @BeforeClass
    public static void before(){
        connectionPool = ConnectionPool.getInstance();
        ratingDao = new RatingDaoImpl(connectionPool);
        userDao = new UserDaoImpl(connectionPool);
        service = new RatingServiceImpl(ratingDao, userDao);
    }

    @AfterClass
    public static void after(){
        connectionPool.dispose();
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
            connection = connectionPool.take(true);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM rating WHERE user_id = 1 AND movie_id = 14");
        } catch (ConnectionPoolException | SQLException ignored) {

        } finally {
            connectionPool.close(statement);
            connectionPool.release(connection);
        }
    }
}