package by.z1max.service;

import by.z1max.exception.ServiceException;
import by.z1max.model.User;

import java.util.List;

public interface UserService {
    User get(int id) throws ServiceException;
    User getByEmail(String email) throws ServiceException;
    User loadUserByEmailAndPassword(String email, String password) throws ServiceException;

    User loadUserByIdAndPassword(int id, String password) throws ServiceException;

    List<User> getAll() throws ServiceException;
    User save(User user) throws ServiceException;
    void delete(User user) throws ServiceException;
}
