package by.z1max.service;

import by.z1max.dao.UserDao;
import by.z1max.exception.DaoException;
import by.z1max.exception.ServiceException;
import by.z1max.model.User;
import by.z1max.util.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static by.z1max.model.Role.ROLE_ADMIN;
import static by.z1max.model.Role.ROLE_USER;
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
            return userDao.findById(id).orElseThrow(() -> new ServiceException("exception.user.notFound"));
        } catch (DaoException e) {
            throw new ServiceException("exception.user.getById", e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        Objects.requireNonNull(email, "email must not be null");
        try {
            return userDao.findByEmail(email).orElseThrow(() -> new ServiceException("exception.user.notFound"));
        } catch (DaoException e) {
            throw new ServiceException("exception.user.getByEmail", e);
        }
    }

    @Override
    public User loadUserByEmailAndPassword(String email, String password) throws ServiceException {
        User user = getByEmail(email);
        if (!user.getPassword().equals(passwordEncoder.encode(password))){
            throw new ServiceException("exception.user.password");
        }
        if (!user.isEnabled()){
            throw new ServiceException("exception.user.banned");
        }
        return user;
    }

    @Override
    public User loadUserByIdAndPassword(int id, String password) throws ServiceException {
        User user = get(id);
        if (!user.getPassword().equals(passwordEncoder.encode(password))){
            throw new ServiceException("exception.user.password");
        }
        return user;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("exception.user.getAll", e);
        }
    }

    @Override
    public User save(User user) throws ServiceException {
        Objects.requireNonNull(user);
        if (!validateUsername(user.getUsername())){
            throw new ServiceException("exception.user.username");
        }
        if (!validateEmail(user.getEmail())){
            throw new ServiceException("exception.user.email");
        }
        if (!validatePassword(user.getPassword())){
            throw new ServiceException("exception.user.password");
        }
        if (user.getRegistered() == null){
            user.setRegistered(LocalDate.now());
        }
        if (user.getStatus() == null){
            user.setPoints(0);
        }
        user.addRole(ROLE_USER);
        user.setEnabled(true);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userDao.save(user);
        } catch (DaoException e) {
            if (e.getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")){
                throw new ServiceException("exception.user.duplicate");
            } else {
                throw new ServiceException("exception.user.save");
            }
        }
    }

    @Override
    public void grandAdminAuthority(int id) throws ServiceException {
        try {
            User user = userDao.findById(id).orElseThrow(() -> new ServiceException("exception.user.notFound"));
            user.addRole(ROLE_ADMIN);
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("exception.user.grandAuthority");
        }
    }

    @Override
    public void enable(int id) throws ServiceException {
        try {
            User user = userDao.findById(id).orElseThrow(() -> new ServiceException("exception.user.notFound"));
            user.setEnabled(!user.isEnabled());
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException("exception.user.enable");
        }
    }

    @Override
    public void delete(User user) throws ServiceException{
        try {
            checkNotFound(userDao.delete(user.getId()));
        } catch (DaoException e) {
            throw new ServiceException("exception.user.delete", e);
        }
    }
}
