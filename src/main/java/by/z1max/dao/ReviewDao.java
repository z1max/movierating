package by.z1max.dao;

import by.z1max.exception.DaoException;
import by.z1max.model.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> findByMovieId(int id) throws DaoException;
    Review create(Review review) throws DaoException;
    boolean delete(int id) throws DaoException;
}