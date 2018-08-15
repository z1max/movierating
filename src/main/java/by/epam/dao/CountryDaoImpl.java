package by.epam.dao;

import by.epam.exception.ConnectionPoolException;
import by.epam.exception.DaoException;
import by.epam.model.Country;
import by.epam.util.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static by.epam.util.mapper.CountryMapper.map;
import static by.epam.util.mapper.CountryMapper.mapList;

public class CountryDaoImpl implements CountryDao {
    private static final Logger LOG = Logger.getLogger(CountryDaoImpl.class);

    private static final String FIND_ALL = "SELECT id, name FROM country";
    private static final String FIND_BY_MOVIE_ID = "SELECT country.id, country.name FROM movie " +
            "JOIN movie_has_country ON movie.id = movie_has_country.movie_id " +
            "JOIN country ON movie_has_country.country_id = country.id " +
            "WHERE movie_id = ?";
    private static final String CREATE = "INSERT INTO country(name) VALUE (?)";
    private static final String UPDATE = "UPDATE country SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM country WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT id, name FROM country WHERE name = ?";

    private ConnectionPool pool;

    public CountryDaoImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<Country> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            return mapList(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding all countries", e);
            throw new DaoException("Error finding all countries", e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public List<Country> findByMovieId(int movieId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(FIND_BY_MOVIE_ID);
            statement.setInt(1, movieId);
            resultSet = statement.executeQuery();
            return mapList(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding countries by movie id = " + movieId, e);
            throw new DaoException("Error finding countries by movie id = " + movieId, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public Optional<Country> findByName(String name) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            return map(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding country by name = " + name, e);
            throw new DaoException("Error finding country by name = " + name, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public Country save(Country country) throws DaoException {
        if (country.isNew()){
            return create(country);
        } else {
            return update(country);
        }
    }

    private Country update(Country country) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, country.getName());
            statement.setInt(2, country.getId());

            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new SQLException("Error updating country");
            }

            return country;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error updating country: " + country, e);
            throw new DaoException("Error updating country: " + country, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private Country create(Country country) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, country.getName());

            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new SQLException("Error creating country");
            }

            resultSet = statement.getGeneratedKeys();
            resultSet.first();
            int id = resultSet.getInt(1);

            country.setId(id);
            return country;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error creating country: " + country, e);
            throw new DaoException("Error creating country: " + country, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error deleting country by id = " + id, e);
            throw new DaoException("Error deleting country by id = " + id, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }
}
