package by.epam.service;

import by.epam.dao.*;
import by.epam.dto.EagerMovie;
import by.epam.dto.LazyMovie;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.Movie;
import by.epam.model.Review;
import by.epam.util.MovieUtil;
import org.apache.log4j.Logger;

import java.util.*;

import static by.epam.util.ValidationUtil.checkLength;
import static by.epam.util.ValidationUtil.checkNotFound;

public class MovieServiceImpl implements MovieService {
    private static final Logger LOG = Logger.getLogger(MovieServiceImpl.class);

    private static final int MAX_TITLE_LENGTH = 60;
    private static final int MAX_DIRECTOR_LENGTH = 45;
    private static final int MAX_DESCRIPTION_LENGTH = 1200;

    private MovieDao movieDao;
    private RatingDao ratingDao;
    private ReviewDao reviewDao;
    private GenreDao genreDao;
    private CountryDao countryDao;

    public MovieServiceImpl(MovieDao movieDao, RatingDao ratingDao, ReviewDao reviewDao, GenreDao genreDao, CountryDao countryDao) {
        this.movieDao = movieDao;
        this.ratingDao = ratingDao;
        this.reviewDao = reviewDao;
        this.genreDao = genreDao;
        this.countryDao = countryDao;
    }
    @Override
    public List<LazyMovie> getAll() throws ServiceException {
        LOG.info("Getting all movies");
        List<LazyMovie> result = new ArrayList<>();
        try {
            List<Movie> movies = movieDao.findAll();
            for (Movie movie : movies){
                movie.setCountries(new HashSet<>(countryDao.findByMovieId(movie.getId())));
                movie.setGenres(new HashSet<>(genreDao.findByMovieId(movie.getId())));
                float rating = ratingDao.getAverageRating(movie.getId());
                LazyMovie lazyMovie = MovieUtil.getFrom(movie, rating);
                result.add(lazyMovie);
            }
            result.sort(Comparator.comparing(LazyMovie::getRating).reversed());
            return result;
        } catch (DaoException e) {
            LOG.error("Error getting all movies", e);
            throw new ServiceException("exception.movie.getAll", e);
        }
    }

    @Override
    public EagerMovie getEager(int id) throws ServiceException {
        LOG.info("Getting movie by id = " + id);
        try {
            Movie movie = movieDao.findById(id).orElseThrow(() -> new ServiceException("exception.movie.notFound"));
            movie.setCountries(new HashSet<>(countryDao.findByMovieId(movie.getId())));
            movie.setGenres(new HashSet<>(genreDao.findByMovieId(movie.getId())));
            float rating = ratingDao.getAverageRating(id);
            List<Review> reviews = reviewDao.findByMovieId(id);
            return MovieUtil.getFrom(movie, rating, reviews);
        } catch (DaoException e) {
            LOG.error("Error getting movie with id = " + id, e);
            throw new ServiceException("exception.movie.getById", e);
        }
    }

    @Override
    public Movie get(int id) throws ServiceException {
        LOG.info("Getting movie by id = " + id);
        try {
            Movie movie = movieDao.findById(id)
                    .orElseThrow(() -> new ServiceException("exception.movie.notFound"));
            movie.setCountries(new HashSet<>(countryDao.findByMovieId(movie.getId())));
            movie.setGenres(new HashSet<>(genreDao.findByMovieId(movie.getId())));
            return movie;
        } catch (DaoException e) {
            LOG.error("Error getting movie with id = " + id, e);
            throw new ServiceException("exception.movie.getById");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        LOG.info("Deleting movie by id = " + id);
        try {
            checkNotFound(movieDao.delete(id));
        } catch (DaoException e) {
            LOG.error("Error deleting movie with id = " + id, e);
            throw new ServiceException("exception.movie.delete");
        }
    }

    @Override
    public Movie save(Movie movie) throws ServiceException {
        Objects.requireNonNull(movie);
        LOG.info("Saving " + movie);
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
            LOG.error("Error saving movie: " + movie, e);
            throw new ServiceException("exception.movie.save", e);
        }
    }
}
