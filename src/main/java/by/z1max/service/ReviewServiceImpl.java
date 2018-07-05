package by.z1max.service;

import by.z1max.dao.ReviewDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Review;

import java.util.List;

import static by.z1max.util.ValidationUtil.checkNotFound;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
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
        try {
            return reviewDao.create(review);
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