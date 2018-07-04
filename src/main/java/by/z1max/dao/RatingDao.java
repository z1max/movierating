package by.z1max.dao;

import by.z1max.exception.DaoException;
import by.z1max.model.Rating;

public interface RatingDao {
    Rating findById(int userId, int movieId) throws DaoException;
    float getAverageRating(int movieId) throws DaoException;
    Rating create(Rating rating) throws DaoException;
}
