package by.z1max.service;

import by.z1max.dao.RatingDao;
import by.z1max.dao.UserDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;

import static by.z1max.util.ValidationUtil.checkNotFound;

public class RatingServiceImpl implements RatingService {

    private RatingDao ratingDao;
    private UserDao userDao;

    public RatingServiceImpl(RatingDao ratingDao, UserDao userDao) {
        this.ratingDao = ratingDao;
        this.userDao = userDao;
    }

    @Override
    public Rating getById(int userId, int movieId) throws ServiceException {
        try {
            return checkNotFound(ratingDao.findById(userId, movieId));
        } catch (DaoException e) {
            throw new ServiceException("Error getting rating by id", e);
        }
    }

    @Override
    public Rating create(Rating rating) throws ServiceException {
        if (rating.getRating() < 1 && rating.getRating() > 10){
            throw new ServiceException("Rating must be between 1 and 10");
        }
        try {
            Rating result = ratingDao.create(rating);
            userDao.addPoints(rating.getUserId(), 1);
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Error creating rating", e);
        }
    }
}
