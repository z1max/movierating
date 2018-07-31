package by.epam.service;

import by.epam.dao.*;
import by.epam.exception.ServiceException;
import by.epam.util.db.ConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MovieServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static ConnectionPool connectionPool;
    private static MovieDao movieDao;
    private static RatingDao ratingDao;
    private static ReviewDao reviewDao;
    private static MovieService service;

    @BeforeClass
    public static void before(){
        connectionPool = ConnectionPool.getInstance();
        movieDao = new MovieDaoImpl(connectionPool);
        ratingDao = new RatingDaoImpl(connectionPool);
        reviewDao = new ReviewDaoImpl(connectionPool);
        service = new MovieServiceImpl(movieDao, ratingDao, reviewDao);
    }

    @AfterClass
    public static void after(){
        connectionPool.dispose();
        movieDao = null;
        ratingDao = null;
        reviewDao = null;
        service = null;
    }

    @Test
    public void getAll() throws ServiceException {
    }

    @Test
    public void get() throws ServiceException {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}