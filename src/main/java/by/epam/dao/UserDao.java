package by.epam.dao;

import by.epam.exception.DaoException;
import by.epam.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(int id) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    List<User> findAll() throws DaoException;
    User save(User user) throws DaoException;
    boolean addPoints(int userId, int points) throws DaoException;
    boolean delete(int id) throws DaoException;
}
