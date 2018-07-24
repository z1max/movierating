package by.z1max.service;

import by.z1max.dao.ReviewDao;
import by.z1max.dao.ReviewDaoImpl;
import by.z1max.dao.UserDao;
import by.z1max.dao.UserDaoImpl;
import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Review;
import by.z1max.util.db.ConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewServiceTest {

    private static ConnectionPool connectionPool;
    private static ReviewDao reviewDao;
    private static UserDao userDao;
    private static ReviewService service;

    @BeforeClass
    public static void before(){
        connectionPool = ConnectionPool.getInstance();
        reviewDao = new ReviewDaoImpl(connectionPool);
        userDao = new UserDaoImpl(connectionPool);
        service = new ReviewServiceImpl(reviewDao, userDao);
    }

    @AfterClass
    public static void after(){
        connectionPool.dispose();
        reviewDao = null;
        service = null;
    }

    @Test
    public void getByMovieId() throws ServiceException {
        List<Review> reviews =  service.getByMovieId(1);
        assertThat(reviews.size()).isEqualTo(4);
        for (Review review : reviews){
            assertThat(review).isNotNull();
        }
    }

    @Test
    public void create() throws ServiceException {
        Review review = new Review(null, 1, "admin", 1, "Comment", LocalDate.now());
        Review created = service.create(review);
        review.setId(created.getId());
        assertThat(created).isEqualToComparingFieldByField(review);
        restoreAfterCreate();
    }

    @Test
    public void delete() {

    }

    private void restoreAfterCreate(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectionPool.take(true);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM review WHERE id = 21");
        } catch (ConnectionPoolException | SQLException ignored) {

        } finally {
            connectionPool.close(statement);
            connectionPool.release(connection);
        }

    }
}