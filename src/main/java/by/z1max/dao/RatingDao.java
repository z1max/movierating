package by.z1max.dao;

import by.z1max.exception.DaoException;
import by.z1max.model.Rating;

import java.util.Optional;

public interface RatingDao {
    Optional<Rating> findById(int userId, int movieId) throws DaoException;
    float getAverageRating(int movieId) throws DaoException;
    Rating create(Rating rating) throws DaoException;
}
