package by.epam.service;

import by.epam.exception.ServiceException;
import by.epam.model.User;

import java.util.List;

public interface UserService {
    User get(int id) throws ServiceException;
    User getByEmail(String email) throws ServiceException;
    User loadUserByEmailAndPassword(String email, String password) throws ServiceException;
    User loadUserByIdAndPassword(int id, String password) throws ServiceException;
    List<User> getAll() throws ServiceException;
    User save(User user) throws ServiceException;
    void grantAdminAuthority(int userId) throws ServiceException;
    void enable(int id) throws ServiceException;
    void delete(User user) throws ServiceException;
}