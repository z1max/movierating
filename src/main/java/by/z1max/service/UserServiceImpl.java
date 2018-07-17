package by.z1max.service;

import by.z1max.dao.UserDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.util.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static by.z1max.util.ValidationUtil.*;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User get(int id) throws ServiceException {
        try {
            return checkNotFound(userDao.findById(id));
        } catch (DaoException e) {
            throw new ServiceException("Error getting user by id", e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        Objects.requireNonNull(email, "email must not be null");
        try {
            return checkNotFound(userDao.findByEmail(email));
        } catch (DaoException e) {
            throw new ServiceException("Error getting user by id", e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error getting all users", e);
        }
    }

    @Override
    public User save(User user) throws ServiceException {
        Objects.requireNonNull(user);
        if (!validateUsername(user.getUsername())){
            throw new ServiceException("Username is not valid!");
        }
        if (!validateEmail(user.getEmail())){
            throw new ServiceException("Email is not valid!");
        }
        if (!validatePassword(user.getPassword())){
            throw new ServiceException("Password is not valid!");
        }
        if (user.getRegistered() == null){
            user.setRegistered(LocalDate.now());
        }
        if (user.getStatus() == null){
            user.setPoints(0);
        }
        user.setEnabled(true);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("Error saving user");
        }
    }

    @Override
    public void delete(User user) throws ServiceException{
        try {
            checkNotFound(userDao.delete(user.getId()));
        } catch (DaoException e) {
            throw new ServiceException("Error deleting user", e);
        }
    }
}
