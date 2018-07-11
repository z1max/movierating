package by.z1max.service;

import by.z1max.dto.EagerMovie;
import by.z1max.dto.LazyMovie;
import by.z1max.exception.ServiceException;
import by.z1max.model.Movie;

import java.util.List;

public interface MovieService {
    List<LazyMovie> getAll() throws ServiceException;
    EagerMovie getEager(int id) throws ServiceException;
    Movie get(int id) throws ServiceException;
    void delete(Movie movie) throws ServiceException;
    Movie save(Movie movie) throws ServiceException;
}
