package by.epam.service;

import by.epam.exception.ServiceException;
import by.epam.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getByMovieId(int movieId) throws ServiceException;
    Review create(Review review) throws ServiceException;
    void delete(int id) throws ServiceException;
}