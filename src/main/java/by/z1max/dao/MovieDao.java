package by.z1max.dao;

import by.z1max.exception.DaoException;
import by.z1max.model.Movie;

import java.util.List;

public interface MovieDao {
    Movie findById(int id) throws DaoException;
    Movie findByTitle(String title);
    List<Movie> findAll() throws DaoException;
    boolean delete(int id) throws DaoException;
    Movie save(Movie movie) throws DaoException;
}
