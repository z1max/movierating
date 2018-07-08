package by.z1max.service;

import by.z1max.dao.*;
import by.z1max.exception.ServiceException;
import by.z1max.util.PasswordEncoder;
import by.z1max.util.db.ConnectionPool;
import by.z1max.util.db.DataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class MovieServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static ConnectionPool connectionPool;
    private static DataSource dataSource;
    private static MovieDao movieDao;
    private static RatingDao ratingDao;
    private static ReviewDao reviewDao;
    private static MovieService service;

    @BeforeClass
    public static void before(){
        connectionPool = new ConnectionPool();
        dataSource = DataSource.getInstance(connectionPool);
        movieDao = new MovieDaoImpl(dataSource);
        ratingDao = new RatingDaoImpl(dataSource);
        reviewDao = new ReviewDaoImpl(dataSource);
        service = new MovieServiceImpl(movieDao, ratingDao, reviewDao);
    }

    @AfterClass
    public static void after(){
        connectionPool.dispose();
        dataSource = null;
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