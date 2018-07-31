package by.epam.service;

import by.epam.dao.MovieDao;
import by.epam.dao.RatingDao;
import by.epam.dao.ReviewDao;
import by.epam.dto.EagerMovie;
import by.epam.dto.LazyMovie;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.Movie;
import by.epam.model.Review;
import by.epam.util.MovieUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static by.epam.util.ValidationUtil.checkLength;
import static by.epam.util.ValidationUtil.checkNotFound;

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
            result.sort(Comparator.comparing(LazyMovie::getRating).reversed());
            return result;
        } catch (DaoException e) {
            throw new ServiceException("exception.movie.getAll", e);
        }
    }

    @Override
    public EagerMovie getEager(int id) throws ServiceException {
        try {
            Movie movie = movieDao.findById(id).orElseThrow(() -> new ServiceException("exception.movie.notFound"));
            float rating = ratingDao.getAverageRating(id);
            List<Review> reviews = reviewDao.findByMovieId(id);
            return MovieUtil.getFrom(movie, rating, reviews);
        } catch (DaoException e) {
            throw new ServiceException("exception.movie.getById", e);
        }
    }

    @Override
    public Movie get(int id) throws ServiceException {
        try {
            return movieDao.findById(id).orElseThrow(() -> new ServiceException("exception.movie.getById"));
        } catch (DaoException e) {
            throw new ServiceException("exception.movie.getById");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            checkNotFound(movieDao.delete(id));
        } catch (DaoException e) {
            throw new ServiceException("exception.movie.delete");
        }
    }

    @Override
    public Movie save(Movie movie) throws ServiceException {
        Objects.requireNonNull(movie);
        if (!checkLength(movie.getTitle(), MAX_TITLE_LENGTH)){
            throw new ServiceException("exception.movie.title");
        }
        if (!checkLength(movie.getDirector(), MAX_DIRECTOR_LENGTH)){
            throw new ServiceException("exception.movie.director");
        }
        if (!checkLength(movie.getDescription(), MAX_DESCRIPTION_LENGTH)){
            throw new ServiceException("exception.movie.description");
        }
        try {
            return movieDao.save(movie);
        } catch (DaoException e) {
            throw new ServiceException("exception.movie.save", e);
        }
    }
}
