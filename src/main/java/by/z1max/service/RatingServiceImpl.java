package by.z1max.service;

import by.z1max.dao.RatingDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;

import static by.z1max.util.ValidationUtil.checkNotFound;

public class RatingServiceImpl implements RatingService {

    private RatingDao ratingDao;

    public RatingServiceImpl(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
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
            return ratingDao.create(rating);
        } catch (DaoException e) {
            throw new ServiceException("Error creating rating", e);
        }
    }
}
