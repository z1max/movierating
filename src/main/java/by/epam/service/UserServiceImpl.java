package by.epam.service;

import by.epam.dao.UserDao;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.User;
import by.epam.util.PasswordEncoder;
import org.apache.log4j.Logger;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static by.epam.model.Role.ROLE_ADMIN;
import static by.epam.model.Role.ROLE_USER;
import static by.epam.util.ValidationUtil.*;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User get(int id) throws ServiceException {
        LOG.info("Getting user by id = " + id);
        try {
            return userDao.findById(id).orElseThrow(() -> new ServiceException("exception.user.notFound"));
        } catch (DaoException e) {
            LOG.error("Error getting user by id = " + id, e);
            throw new ServiceException("exception.user.getById", e);
        }
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        Objects.requireNonNull(email, "email must not be null");
        LOG.info("Getting user by email = " + email);
        try {
            return userDao.findByEmail(email).orElseThrow(() -> new ServiceException("exception.user.notFound"));
        } catch (DaoException e) {
            LOG.error("Error getting user by email = " + email, e);
            throw new ServiceException("exception.user.getByEmail", e);
        }
    }

    @Override
    public User loadUserByEmailAndPassword(String email, String password) throws ServiceException {
        LOG.info("Loading user by email = " + email + " and password");
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
        LOG.info("Loading user by id = " + id + " and password");
        User user = get(id);
        if (!user.getPassword().equals(passwordEncoder.encode(password))){
            throw new ServiceException("exception.user.password");
        }
        return user;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        LOG.info("Getting all users");
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            LOG.error("Error getting all users", e);
            throw new ServiceException("exception.user.getAll", e);
        }
    }

    @Override
    public User save(User user) throws ServiceException {
        Objects.requireNonNull(user);
        LOG.info("Saving " + user);
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
            if (e.getCause().getClass().equals(SQLIntegrityConstraintViolationException.class)){
                throw new ServiceException("exception.user.duplicate");
            } else {
                LOG.error("Error saving user: " + user, e);
                throw new ServiceException("exception.user.save", e);
            }
        }
    }

    @Override
    public void grantAdminAuthority(int id) throws ServiceException {
        LOG.info("Granting admin authority to user with id = " + id);
        try {
            User user = userDao.findById(id).orElseThrow(() -> new ServiceException("exception.user.notFound"));
            user.addRole(ROLE_ADMIN);
            userDao.save(user);
        } catch (DaoException e) {
            LOG.error("Error granting admin authority to user with id = " + id, e);
            throw new ServiceException("exception.user.grandAuthority", e);
        }
    }

    @Override
    public void enable(int id) throws ServiceException {
        LOG.info("Enabling/disabling user with id = " + id);
        try {
            User user = userDao.findById(id).orElseThrow(() -> new ServiceException("exception.user.notFound"));
            user.setEnabled(!user.isEnabled());
            userDao.save(user);
        } catch (DaoException e) {
            LOG.error("Error enabling/disabling user with id = " + id, e);
            throw new ServiceException("exception.user.enable", e);
        }
    }

    @Override
    public void delete(User user) throws ServiceException{
        LOG.info("Deleting " + user);
        try {
            checkNotFound(userDao.delete(user.getId()));
        } catch (DaoException e) {
            LOG.error("Error deleting user: " + user, e);
            throw new ServiceException("exception.user.delete", e);
        }
    }
}
