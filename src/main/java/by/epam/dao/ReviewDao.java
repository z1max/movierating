package by.epam.dao;

import by.epam.exception.DaoException;
import by.epam.model.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> findByMovieId(int id) throws DaoException;
    Review create(Review review) throws DaoException;
    boolean delete(int id) throws DaoException;
}