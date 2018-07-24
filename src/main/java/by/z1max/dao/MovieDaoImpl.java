package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Movie;
import by.z1max.util.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class MovieDaoImpl implements MovieDao {
    private static final Logger LOG = Logger.getLogger(MovieDaoImpl.class);

    private static final String FIND_BY_ID = "SELECT id, title, director, release_date, budget, description, runtime, group_concat(genre SEPARATOR ',') AS genres" +
            " FROM movie JOIN genre ON id = genre.movie_id WHERE id = ?";
    private static final String FIND_COUNTRIES_BY_ID = "SELECT name FROM country WHERE movie_id = ?";
    private static final String FIND_ALL = "SELECT id, title, director, release_date, budget, description, runtime, group_concat(genre SEPARATOR ',') AS genres" +
            " FROM movie JOIN genre ON id = genre.movie_id GROUP BY id";
    private static final String DELETE = "DELETE FROM movie WHERE id = ?";
    private static final String CREATE = "INSERT INTO movie(title, director, release_date, budget, description, runtime) VALUES (?,?,?,?,?,?)";
    private static final String CREATE_GENRES = "INSERT INTO genre(movie_id, genre) VALUES (?,?)";
    private static final String CREATE_COUNTRIES = "INSERT INTO country(movie_id, name) VALUES (?,?)";
    private static final String UPDATE = "UPDATE movie SET title = ?, director = ?, release_date = ?, budget = ?, description = ?, runtime = ? WHERE id = ?";
    private static final String DELETE_GENRES = "DELETE FROM genre WHERE movie_id = ?";
    private static final String DELETE_COUNTRIES = "DELETE FROM country WHERE movie_id = ?";

    private ConnectionPool pool;

    public MovieDaoImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Optional<Movie> findById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Optional<Movie> movie = map(resultSet);
            if (!movie.isPresent()){
                return movie;
            }
            statement = connection.prepareStatement(FIND_COUNTRIES_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            movie.get().setCountries(mapCountries(resultSet));
            return movie;
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding movie by id = " + id, e);
            throw new DaoException("Error finding movie by id = " + id, e);
        } finally {
            pool.close(statement, resultSet);
            pool.release(connection);
        }
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.take(true);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            List<Movie> movies = mapMovieList(resultSet);
            for (Movie movie : movies){
                preparedStatement = connection.prepareStatement(FIND_COUNTRIES_BY_ID);
                preparedStatement.setInt(1, movie.getId());
                resultSet = preparedStatement.executeQuery();
                movie.setCountries(mapCountries(resultSet));
            }
            return movies;
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding all movies", e);
            throw new DaoException("Error finding all movies", e);
        } finally {
            pool.close(statement, resultSet);
            pool.close(preparedStatement);
            pool.release(connection);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows == 1;
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error deleting movie with id = " + id, e);
            throw new DaoException("Error deleting movie with id = " + id, e);
        } finally {
            pool.close(statement);
            pool.release(connection);
        }
    }

    @Override
    public Movie save(Movie movie) throws DaoException {
        if (movie.isNew()){
            return create(movie);
        } else {
            return update(movie);
        }
    }

    private Movie create(Movie movie) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            setFields(movie, statement);

            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new SQLException("Error creating movie");
            }

            resultSet = statement.getGeneratedKeys();
            resultSet.first();
            int id = resultSet.getInt(1);

            for (Genre genre : movie.getGenres()) {
                statement = connection.prepareStatement(CREATE_GENRES);
                statement.setInt(1, id);
                statement.setInt(2, genre.ordinal());
                statement.executeUpdate();
            }
            for (Country country : movie.getCountries()) {
                statement = connection.prepareStatement(CREATE_COUNTRIES);
                statement.setInt(1, id);
                statement.setString(2, country.toString());
                statement.executeUpdate();
            }
            movie.setId(id);
            return movie;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error creating movie: " + movie, e);
            throw new DaoException("Error creating movie: " + movie, e);
        } finally {
            pool.close(statement, resultSet);
            pool.release(connection);
        }
    }

    private Movie update(Movie movie) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(UPDATE);
            setFields(movie, statement);
            statement.setInt(7, movie.getId());

            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Error creating movie");
            }

            statement = connection.prepareStatement(DELETE_GENRES);
            statement.setInt(1, movie.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(DELETE_COUNTRIES);
            statement.setInt(1, movie.getId());
            statement.executeUpdate();

            for (Genre genre : movie.getGenres()) {
                statement = connection.prepareStatement(CREATE_GENRES);
                statement.setInt(1, movie.getId());
                statement.setInt(2, genre.ordinal());
                statement.executeUpdate();
            }
            for (Country country : movie.getCountries()) {
                statement = connection.prepareStatement(CREATE_COUNTRIES);
                statement.setInt(1, movie.getId());
                statement.setString(2, country.toString());
                statement.executeUpdate();
            }
            return movie;
        }  catch (SQLException | ConnectionPoolException e) {
            pool.rollback(connection);
            LOG.error("Error updating movie: " + movie, e);
            throw new DaoException("Error updating movie: " + movie, e);
        } finally {
            pool.close(statement);
            pool.release(connection);
        }
    }

    private Optional<Movie> map(ResultSet resultSet) throws SQLException {
        Optional<Movie> movie;
        resultSet.first();
        try{
            movie = Optional.of(mapFields(resultSet));
        } catch (NullPointerException e){
            movie = Optional.empty();
        }
        return movie;
    }

    private List<Movie> mapMovieList(ResultSet resultSet) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()){
            movies.add(mapFields(resultSet));
        }
        return movies;
    }

    private Movie mapFields(ResultSet resultSet) throws SQLException, NullPointerException {
        Movie movie;
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String director = resultSet.getString("director");
        LocalDate releaseDate = resultSet.getDate("release_date").toLocalDate();
        int budget = resultSet.getInt("budget");
        String description = resultSet.getString("description");
        short runtime = resultSet.getShort("runtime");
        String genres = resultSet.getString("genres");
        movie = new Movie(id, title, director, releaseDate, budget, description, runtime);
        movie.setGenres(mapGenres(genres));
        return movie;
    }

    private Set<Genre> mapGenres(String genres) {
        Set<Genre> result = new HashSet<>();
        String[] genresArr = genres.split(",");
        for (String genre : genresArr) {
            Genre current = Genre.values()[Integer.parseInt(genre)];
            result.add(current);
        }
        return result;
    }

    private Set<Country> mapCountries(ResultSet resultSet) throws SQLException {
        Set<Country> result = new HashSet<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            result.add(Country.valueOf(name));
        }
        return result;
    }
    
    private void setFields(Movie movie, PreparedStatement statement) throws SQLException {
        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getDirector());
        statement.setDate(3, Date.valueOf(movie.getReleaseDate()));
        statement.setInt(4, movie.getBudget());
        statement.setString(5, movie.getDescription());
        statement.setShort(6, movie.getRuntime());
    }
}
