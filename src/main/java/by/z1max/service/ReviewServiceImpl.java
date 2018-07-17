package by.z1max.service;

import by.z1max.dao.ReviewDao;
import by.z1max.dao.UserDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Review;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static by.z1max.util.ValidationUtil.checkLength;
import static by.z1max.util.ValidationUtil.checkNotFound;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;
    private UserDao userDao;

    public ReviewServiceImpl(ReviewDao reviewDao, UserDao userDao) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
    }

    @Override
    public List<Review> getByMovieId(int movieId) throws ServiceException {
        try {
            return reviewDao.findByMovieId(movieId);
        } catch (DaoException e) {
            throw new ServiceException("Error getting reviews by id", e);
        }
    }

    @Override
    public Review create(Review review) throws ServiceException {
        Objects.requireNonNull(review);
        review.setDate(LocalDate.now());
        if (!checkLength(review.getComment(), 1200)){
            throw new ServiceException("Comment must be less than 1200 characters");
        }
        try {
            Review result = reviewDao.create(review);
            userDao.addPoints(review.getUserId(), 2);
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error creating review", e);
        }
    }

    @Override
    public void delete(Review review) throws ServiceException {
        try {
            checkNotFound(reviewDao.delete(review.getId()));
        } catch (DaoException e) {
            throw new ServiceException("Error deleting review", e);
        }
    }
}