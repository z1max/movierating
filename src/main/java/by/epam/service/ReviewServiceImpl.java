package by.epam.service;

import by.epam.dao.ReviewDao;
import by.epam.dao.UserDao;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.Review;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static by.epam.util.ValidationUtil.checkLength;
import static by.epam.util.ValidationUtil.checkNotFound;

public class ReviewServiceImpl implements ReviewService {
    private static final Logger LOG = Logger.getLogger(ReviewServiceImpl.class);

    private ReviewDao reviewDao;
    private UserDao userDao;

    public ReviewServiceImpl(ReviewDao reviewDao, UserDao userDao) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
    }

    @Override
    public List<Review> getByMovieId(int movieId) throws ServiceException {
        LOG.info("Getting reviews for movie with id = " + movieId);
        try {
            return reviewDao.findByMovieId(movieId);
        } catch (DaoException e) {
            LOG.error("Error getting reviews for movie with id = " + movieId, e);
            throw new ServiceException("exception.review.getByMovieId", e);
        }
    }

    @Override
    public Review create(Review review) throws ServiceException {
        Objects.requireNonNull(review);
        LOG.info("Creating " + review);
        review.setDate(LocalDate.now());
        if (!checkLength(review.getComment(), 1200)){
            throw new ServiceException("exception.review.commentLength");
        }
        try {
            Review result = reviewDao.create(review);
            userDao.addPoints(review.getUserId(), 2);
            return result;
        } catch (DaoException e) {
            LOG.error("Error creating review: " + review, e);
            throw new ServiceException("exception.review.create", e);
        }
    }

    @Override
    public void delete(Review review) throws ServiceException {
        LOG.info("Deleting " + review);
        try {
            checkNotFound(reviewDao.delete(review.getId()));
        } catch (DaoException e) {
            LOG.error("Error deleting review: " + review, e);
            throw new ServiceException("exception.review.delete", e);
        }
    }
}