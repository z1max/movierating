package by.epam.dao;

import by.epam.exception.ConnectionPoolException;
import by.epam.exception.DaoException;
import by.epam.model.Genre;
import by.epam.util.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao {
    private static final Logger LOG = Logger.getLogger(GenreDaoImpl.class);
    
    private static final String FIND_ALL = "SELECT id, name FROM genre";
    private static final String FIND_BY_MOVIE_ID = "SELECT genre.id, genre.name FROM movie " +
            "JOIN movie_has_genre ON movie.id = movie_has_genre.movie_id\n" +
            "JOIN genre ON movie_has_genre.genre_id = genre.id\n" +
            "WHERE movie_id = ?";
    private static final String CREATE = "INSERT INTO genre(name) VALUE ?";
    private static final String UPDATE = "UPDATE genre SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM genre WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT id, name FROM genre WHERE name = ?";

    private ConnectionPool pool;

    public GenreDaoImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            return mapList(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding all genres", e);
            throw new DaoException("Error finding all genres", e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public List<Genre> findByMovieId(int movieId) throws DaoException {
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
            LOG.error("Error finding genres by movie id = " + movieId, e);
            throw new DaoException("Error finding genres by movie id = " + movieId, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public Optional<Genre> findByName(String name) throws DaoException {
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
            LOG.error("Error finding genre by name = " + name, e);
            throw new DaoException("Error finding genre by name = " + name, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    @Override
    public Genre save(Genre genre) throws DaoException {
        if (genre.isNew()){
            return create(genre);
        } else {
            return update(genre);
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
            LOG.error("Error deleting genre by id = " + id, e);
            throw new DaoException("Error deleting genre by id = " + id, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private Genre update(Genre genre) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, genre.getName());
            statement.setInt(2, genre.getId());

            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new SQLException("Error updating user");
            }

            return genre;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error updating genre: " + genre, e);
            throw new DaoException("Error updating genre: " + genre, e);
        } finally {
            pool.close(statement);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private Genre create(Genre genre) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, genre.getName());

            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new SQLException("Error creating user");
            }

            resultSet = statement.getGeneratedKeys();
            resultSet.first();
            int id = resultSet.getInt(1);

            genre.setId(id);
            return genre;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error creating genre: " + genre, e);
            throw new DaoException("Error creating genre: " + genre, e);
        } finally {
            pool.close(statement, resultSet);
            boolean released = pool.release(connection);
            LOG.debug("Connection released: " + released);
        }
    }

    private Optional<Genre> map(ResultSet resultSet) throws SQLException {
        Optional<Genre> genre = Optional.empty();
        if (!resultSet.isBeforeFirst()){
            return genre;
        }
        resultSet.next();
        genre = Optional.of(mapFields(resultSet));
        return genre;
    }

    private Genre mapFields(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }

    private List<Genre> mapList(ResultSet resultSet) throws SQLException {
        List<Genre> result = new ArrayList<>();
        Genre genre;
        while (resultSet.next()) {
            genre = mapFields(resultSet);
            result.add(genre);
        }
        return result;
    }
}
