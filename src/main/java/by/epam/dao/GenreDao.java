package by.epam.dao;

import by.epam.exception.DaoException;
import by.epam.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    List<Genre> findAll() throws DaoException;
    List<Genre> findByMovieId(int movieId) throws DaoException;
    Optional<Genre> findByName(String name) throws DaoException;
    Genre save(Genre genre) throws DaoException;
    boolean delete(int id) throws DaoException;
}
