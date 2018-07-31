package by.epam.dao;

import by.epam.exception.DaoException;
import by.epam.model.Rating;

import java.util.Optional;

public interface RatingDao {
    Optional<Rating> findById(int userId, int movieId) throws DaoException;
    float getAverageRating(int movieId) throws DaoException;
    Rating create(Rating rating) throws DaoException;
}
