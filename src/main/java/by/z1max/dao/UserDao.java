package by.z1max.dao;

import by.z1max.exception.DaoException;
import by.z1max.model.User;

import java.util.List;

public interface UserDao {
    User findById(int id) throws DaoException;
    User findByEmail(String email) throws DaoException;
    List<User> findAll() throws DaoException;
    User save(User user) throws DaoException;
    void delete(User user) throws DaoException;
}
