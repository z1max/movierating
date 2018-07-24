package by.z1max.util.mapper;

import by.z1max.model.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RatingMapper {

    public static Optional<Rating> map(ResultSet resultSet) throws SQLException {
        Optional<Rating> result = Optional.empty();
        if (!resultSet.isBeforeFirst()){
            return result;
        }
        resultSet.first();
        int userId = resultSet.getInt("user_id");
        int movieId = resultSet.getInt("movie_id");
        byte rating = resultSet.getByte("rating");
        result = Optional.of(new Rating(userId, movieId, rating));
        return result;
    }
}
