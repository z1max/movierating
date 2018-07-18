package by.z1max.dao;

import by.z1max.exception.ConnectionPoolException;
import by.z1max.exception.DaoException;
import by.z1max.model.Review;
import by.z1max.util.db.DataSource;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao{
    private static final Logger LOG = Logger.getLogger(ReviewDaoImpl.class);

    private static final String FIND_BY_MOVIE_ID = "SELECT review.id, user.id AS user_id, username, movie_id, comment, date FROM review JOIN movie_rating.user ON user_id = user.id WHERE movie_id = ?";
    private static final String CREATE = "INSERT INTO review(user_id, movie_id, comment, date) VALUES (?,?,?,?)";
    private static final String DELETE = "DELETE FROM review WHERE id = ?";

    private DataSource dataSource;

    public ReviewDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Review> findByMovieId(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection(true);
            statement = connection.prepareStatement(FIND_BY_MOVIE_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return mapReviewList(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error finding all reviews by movie id = " + id, e);
            throw new DaoException("Error finding all reviews by movie id = " + id, e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public Review create(Review review) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection(false);
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
            dataSource.rollback(connection);
            LOG.error("Error creating review: " + review, e);
            throw new DaoException("Error creating review: " + review, e);
        } finally {
            dataSource.releaseConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection(false);
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows == 1;
        } catch (ConnectionPoolException | SQLException e) {
            LOG.error("Error deleting review with id = " + id, e);
            dataSource.rollback(connection);
            throw new DaoException("Error deleting review with id = " + id, e);
        } finally {
            dataSource.releaseConnection(connection, statement);
        }
    }

    private List<Review> mapReviewList(ResultSet resultSet) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int userId = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            int movieId = resultSet.getInt("movie_id");
            String comment = resultSet.getString("comment");
            LocalDate localDate = resultSet.getDate("date").toLocalDate();
            reviews.add(new Review(id, userId, username, movieId, comment, localDate));
        }
        return reviews;
    }

    private void setFields(Review review, PreparedStatement statement) throws SQLException {
        statement.setInt(1, review.getUserId());
        statement.setInt(2, review.getMovieId());
        statement.setString(3, review.getComment());
        statement.setDate(4, Date.valueOf(review.getDate()));
    }
}