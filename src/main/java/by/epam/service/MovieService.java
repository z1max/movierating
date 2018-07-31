package by.epam.service;

import by.epam.dto.EagerMovie;
import by.epam.dto.LazyMovie;
import by.epam.exception.ServiceException;
import by.epam.model.Movie;

import java.util.List;

public interface MovieService {
    List<LazyMovie> getAll() throws ServiceException;
    EagerMovie getEager(int id) throws ServiceException;
    Movie get(int id) throws ServiceException;
    void delete(int id) throws ServiceException;
    Movie save(Movie movie) throws ServiceException;
}
