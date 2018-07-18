package by.z1max.dao;

import by.z1max.exception.DaoException;
import by.z1max.model.User;

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
