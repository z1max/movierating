package by.epam.service;

import by.epam.exception.ServiceException;
import by.epam.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll() throws ServiceException;
    Genre getByName(String name) throws ServiceException;
    Genre save(Genre genre) throws ServiceException;
    void delete(int id) throws ServiceException;
}
