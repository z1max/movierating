package by.epam.util;

import by.epam.dao.*;
import by.epam.service.*;
import by.epam.util.db.ConnectionPool;

public class AppContext {
    private static volatile AppContext instance;
    private ConnectionPool pool = ConnectionPool.getInstance();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();

    private MovieDao movieDao;
    private RatingDao ratingDao;
    private ReviewDao reviewDao;
    private UserDao userDao;

    private MovieService movieService;
    private RatingService ratingService;
    private ReviewService reviewService;
    private UserService userService;

    private AppContext() {
        movieDao = new MovieDaoImpl(pool);
        ratingDao = new RatingDaoImpl(pool);
        reviewDao = new ReviewDaoImpl(pool);
        userDao = new UserDaoImpl(pool);

        movieService = new MovieServiceImpl(movieDao, ratingDao, reviewDao);
        ratingService = new RatingServiceImpl(ratingDao, userDao);
        reviewService = new ReviewServiceImpl(reviewDao, userDao);
        userService = new UserServiceImpl(userDao, passwordEncoder);
    }

    public static AppContext getInstance(){
        if (instance == null){
            synchronized (AppContext.class){
                if (instance == null){
                    instance = new AppContext();
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

    public void destroy(){
        pool.dispose();
    }
}
