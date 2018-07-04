package by.z1max.service;

import by.z1max.dao.MovieDao;
import by.z1max.dao.RatingDao;
import by.z1max.dao.ReviewDao;
import by.z1max.dto.EagerMovie;
import by.z1max.dto.LazyMovie;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Movie;
import by.z1max.model.Review;
import by.z1max.util.MovieUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.z1max.util.ValidationUtil.checkLength;
import static by.z1max.util.ValidationUtil.checkNotFound;

public class MovieServiceImpl implements MovieService {
    private static final int MAX_TITLE_LENGTH = 60;
    private static final int MAX_DIRECTOR_LENGTH = 45;
    private static final int MAX_DESCRIPTION_LENGTH = 1200;

    private MovieDao movieDao;
    private RatingDao ratingDao;
    private ReviewDao reviewDao;

    public MovieServiceImpl(MovieDao movieDao, RatingDao ratingDao, ReviewDao reviewDao) {
        this.movieDao = movieDao;
        this.ratingDao = ratingDao;
        this.reviewDao = reviewDao;
    }
    @Override
    public List<LazyMovie> getAll() throws ServiceException {
        List<LazyMovie> result = new ArrayList<>();
        try {
            List<Movie> movies = movieDao.findAll();
            for (Movie movie : movies){
                float rating = ratingDao.getAverageRating(movie.getId());
                LazyMovie lazyMovie = MovieUtil.getFrom(movie, rating);
                result.add(lazyMovie);
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error getting all movies", e);
        }
    }

    @Override
    public EagerMovie get(int id) throws ServiceException {
        try {
            Movie movie = movieDao.findById(id);
            float rating = ratingDao.getAverageRating(id);
            List<Review> reviews = reviewDao.findByMovieId(id);
            return MovieUtil.getFrom(movie, rating, reviews);
        } catch (DaoException e) {
            throw new ServiceException("Error getting movie by id", e);
        }
    }

    @Override
    public void delete(Movie movie) throws ServiceException {
        try {
            checkNotFound(movieDao.delete(movie.getId()));
        } catch (DaoException e) {
            throw new ServiceException("Error deleting movie");
        }
    }

    @Override
    public void save(Movie movie) throws ServiceException {
        Objects.requireNonNull(movie);
        if (!checkLength(movie.getTitle(), MAX_TITLE_LENGTH)){
            throw new ServiceException("Title length must be less than " + MAX_TITLE_LENGTH);
        }
        if (!checkLength(movie.getDirector(), MAX_DIRECTOR_LENGTH)){
            throw new ServiceException("Director length must be less than " + MAX_DIRECTOR_LENGTH);
        }
        if (!checkLength(movie.getDescription(), MAX_DESCRIPTION_LENGTH)){
            throw new ServiceException("Description length must be less than " + MAX_DESCRIPTION_LENGTH);
        }
        try {
            movieDao.save(movie);
        } catch (DaoException e) {
            throw new ServiceException("Error saving movie", e);
        }
    }
}
