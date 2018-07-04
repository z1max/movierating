package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Movie;
import by.z1max.util.db.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieDaoImpl implements MovieDao {

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

    private DataSource dataSource;

    public MovieDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Movie findById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Movie movie = map(resultSet);
            if (movie == null){
                return null;
            }
            statement = connection.prepareStatement(FIND_COUNTRIES_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            movie.setCountries(mapCountries(resultSet));
            return movie;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error finding movie by id", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public Movie findByTitle(String title) {
        return null;
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            List<Movie> movies = mapMovieList(resultSet);
            PreparedStatement preparedStatement = null;
            for (Movie movie : movies){
                preparedStatement = connection.prepareStatement(FIND_COUNTRIES_BY_ID);
                preparedStatement.setInt(1, movie.getId());
                resultSet = preparedStatement.executeQuery();
                movie.setCountries(mapCountries(resultSet));
            }
            preparedStatement.close();
            return movies;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error finding all movies", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows == 1;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error deleting movie", e);
        } finally {
            dataSource.releaseConnection(connection, statement, null);
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

    private Movie map(ResultSet resultSet) throws SQLException {
        Movie movie;
        resultSet.first();
        try{
            movie = mapFields(resultSet);
        } catch (NullPointerException e){
            movie = null;
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

    private Movie create(Movie movie) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
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
                statement.setInt(2, country.ordinal());
                statement.executeUpdate();
            }
            movie.setId(id);
            connection.commit();
            connection.setAutoCommit(true);
            return movie;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                //TODO
            }
            throw new DaoException("Error creating movie", e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }
    
    private Movie update(Movie movie) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
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
                statement.setInt(2, country.ordinal());
                statement.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
            return movie;
        }  catch (SQLException | ConnectionPoolException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                //TODO
            }
            throw new DaoException("Error updating movie", e);
        } 
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
