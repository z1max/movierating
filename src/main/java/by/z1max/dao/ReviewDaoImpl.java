package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Review;
import by.z1max.util.db.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static by.z1max.util.mapper.ReviewMapper.mapReviewList;

public class ReviewDaoImpl implements ReviewDao{
    private static final Logger LOG = Logger.getLogger(ReviewDaoImpl.class);

    private static final String FIND_BY_MOVIE_ID = "SELECT review.id, user.id AS user_id, username, movie_id, comment, date FROM review JOIN movie_rating.user ON user_id = user.id WHERE movie_id = ?";
    private static final String CREATE = "INSERT INTO review(user_id, movie_id, comment, date) VALUES (?,?,?,?)";
    private static final String DELETE = "DELETE FROM review WHERE id = ?";

    private ConnectionPool pool;

    public ReviewDaoImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<Review> findByMovieId(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(true);
            statement = connection.prepareStatement(FIND_BY_MOVIE_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return mapReviewList(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding all reviews by movie id = " + id, e);
            throw new DaoException("Error finding all reviews by movie id = " + id, e);
        } finally {
            pool.close(statement, resultSet);
            pool.release(connection);
        }
    }

    @Override
    public Review create(Review review) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.take(false);
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            setFields(review, statement);

            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Error creating review");
            }
            resultSet = statement.getGeneratedKeys();
            resultSet.first();
            int id = resultSet.getInt(1);
            review.setId(id);
            return review;
        } catch (ConnectionPoolException | SQLException e) {
            pool.rollback(connection);
            LOG.error("Error creating review: " + review, e);
            throw new DaoException("Error creating review: " + review, e);
        } finally {
            pool.close(statement, resultSet);
            pool.release(connection);
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
            int rows = statement.executeUpdate();
            return rows == 1;
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error deleting review with id = " + id, e);
            pool.rollback(connection);
            throw new DaoException("Error deleting review with id = " + id, e);
        } finally {
            pool.close(statement);
            pool.release(connection);
        }
    }

    private void setFields(Review review, PreparedStatement statement) throws SQLException {
        statement.setInt(1, review.getUserId());
        statement.setInt(2, review.getMovieId());
        statement.setString(3, review.getComment());
        statement.setDate(4, Date.valueOf(review.getDate()));
    }
}