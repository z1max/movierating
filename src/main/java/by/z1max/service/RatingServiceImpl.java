package by.z1max.service;

import by.z1max.dao.RatingDao;
import by.z1max.dao.UserDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;

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
            return ratingDao.findById(userId, movieId).orElseThrow(() -> new ServiceException("exception.rating.notFound"));
        } catch (DaoException e) {
            throw new ServiceException("exception.rating.getById", e);
        }
    }

    @Override
    public Rating create(Rating rating) throws ServiceException {
        if (rating.getRating() < 1 && rating.getRating() > 10){
            throw new ServiceException("exception.rating.bound");
        }
        try {
            Rating result = ratingDao.create(rating);
            userDao.addPoints(rating.getUserId(), 1);
            return result;
        } catch (DaoException e) {
            throw new ServiceException("exception.rating.create", e);
        }
    }
}
