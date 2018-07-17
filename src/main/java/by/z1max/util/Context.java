package by.z1max.util;

import by.z1max.dao.*;
import by.z1max.service.*;
import by.z1max.util.db.ConnectionPool;
import by.z1max.util.db.DataSource;

public class Context {
    private static volatile Context instance;
    private DataSource dataSource = DataSource.getInstance(new ConnectionPool());
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    private MovieDao movieDao;
    private RatingDao ratingDao;
    private ReviewDao reviewDao;
    private UserDao userDao;

    private MovieService movieService;
    private RatingService ratingService;
    private ReviewService reviewService;
    private UserService userService;

    private Context() {
        movieDao = new MovieDaoImpl(dataSource);
        ratingDao = new RatingDaoImpl(dataSource);
        reviewDao = new ReviewDaoImpl(dataSource);
        userDao = new UserDaoImpl(dataSource);

        movieService = new MovieServiceImpl(movieDao, ratingDao, reviewDao);
        ratingService = new RatingServiceImpl(ratingDao, userDao);
        reviewService = new ReviewServiceImpl(reviewDao, userDao);
        userService = new UserServiceImpl(userDao, passwordEncoder);
    }

    public static Context getInstance(){
        if (instance == null){
            synchronized (DataSource.class){
                if (instance == null){
                    instance = new Context();
                }
            }
        }
        return instance;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public RatingService getRatingService() {
        return ratingService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public UserService getUserService() {
        return userService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void destroy(){
        dataSource.dispose();
    }
}
