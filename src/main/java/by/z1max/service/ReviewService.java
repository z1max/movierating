package by.z1max.service;

import by.z1max.exception.ServiceException;
import by.z1max.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getByMovieId(int movieId) throws ServiceException;
    Review create(Review review) throws ServiceException;
    void delete(Review review) throws ServiceException;
}