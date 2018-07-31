package by.epam.service;

import by.epam.exception.ServiceException;
import by.epam.model.Rating;

public interface RatingService {
    Rating getById(int userId, int movieId) throws ServiceException;
    Rating create(Rating rating) throws ServiceException;
}
