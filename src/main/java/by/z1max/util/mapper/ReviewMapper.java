package by.z1max.util.mapper;

import by.z1max.model.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {

    public static List<Review> mapReviewList(ResultSet resultSet) throws SQLException {
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
}
