package by.z1max.service;

import by.z1max.dto.EagerMovie;
import by.z1max.dto.LazyMovie;
import by.z1max.exception.ServiceException;
import by.z1max.model.Movie;

import java.util.List;

public interface MovieService {
    public List<LazyMovie> getAll() throws ServiceException;
    public EagerMovie get(int id) throws ServiceException;
    public void delete(Movie movie) throws ServiceException;
    public void save(Movie movie) throws ServiceException;
}
