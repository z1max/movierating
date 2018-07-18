package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Rating;
import by.z1max.util.db.DataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingDaoImpl implements RatingDao {
    private final static Logger LOG = Logger.getLogger(RatingDaoImpl.class);

    private static final String FIND_BY_USER_ID = "SELECT * FROM rating WHERE user_id= ? AND movie_id = ?";
    private static final String GET_AVERAGE_RATING = "SELECT ROUND(AVG(rating), 1) FROM rating WHERE movie_id = ?";
    private static final String CREATE = "INSERT INTO rating(user_id, movie_id, rating) VALUES (?,?,?)";

    private DataSource dataSource;

    public RatingDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Rating findById(int userId, int movieId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection(true);
            statement = connection.prepareStatement(FIND_BY_USER_ID);
            statement.setInt(1, userId);
            statement.setInt(2, movieId);
            resultSet = statement.executeQuery();
            return map(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding rating by userId = " + userId + " and movieId = " + movieId, e);
            throw new DaoException("Error finding rating by userId = " + userId + " and movieId = " + movieId, e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public float getAverageRating(int movieId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection(true);
            statement = connection.prepareStatement(GET_AVERAGE_RATING);
            statement.setInt(1, movieId);
            resultSet = statement.executeQuery();
            resultSet.first();
            return resultSet.getFloat(1);
        } catch (SQLException | ConnectionPoolException e) {
            LOG.error("Error getting average rating for movie with id = " + movieId, e);
            throw new DaoException("Error getting average rating for movie with id = " + movieId, e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public Rating create(Rating rating) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection(true);
            statement = connection.prepareStatement(CREATE);
            setFields(rating, statement);
            statement.executeUpdate();
            return rating;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error creating review", e);
        } finally {
            dataSource.releaseConnection(connection, statement);
        }
    }

    private void setFields(Rating rating, PreparedStatement statement) throws SQLException {
        statement.setInt(1, rating.getUserId());
        statement.setInt(2, rating.getMovieId());
        statement.setByte(3, rating.getRating());
    }

    private Rating map(ResultSet resultSet) throws SQLException {
        if (!resultSet.isBeforeFirst()){
            return null;
        }
        resultSet.first();
        int userId = resultSet.getInt("user_id");
        int movieId = resultSet.getInt("movie_id");
        byte rating = resultSet.getByte("rating");
        return new Rating(userId, movieId, rating);
    }
}
