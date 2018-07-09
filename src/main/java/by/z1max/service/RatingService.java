package by.z1max.service;

import by.z1max.exception.ServiceException;
import by.z1max.model.Rating;

public interface RatingService {
    Rating getById(int userId, int movieId) throws ServiceException;
    Rating create(Rating rating) throws ServiceException;
}
