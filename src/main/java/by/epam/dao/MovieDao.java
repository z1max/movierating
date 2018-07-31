package by.epam.dao;

import by.epam.exception.DaoException;
import by.epam.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {
    Optional<Movie> findById(int id) throws DaoException;
    List<Movie> findAll() throws DaoException;
    boolean delete(int id) throws DaoException;
    Movie save(Movie movie) throws DaoException;
}
