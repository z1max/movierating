package by.epam.dao;

import by.epam.exception.DaoException;
import by.epam.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    List<Country> findAll() throws DaoException;
    List<Country> findByMovieId(int movieId) throws DaoException;
    Optional<Country> findByName(String name) throws DaoException;
    Country save(Country country) throws DaoException;
    boolean delete(int id) throws DaoException;
}
