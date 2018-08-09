package by.epam.util.mapper;

import by.epam.model.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class MovieMapper {

    public static Optional<Movie> map(ResultSet resultSet) throws SQLException {
        Optional<Movie> movie = Optional.empty();
        if (!resultSet.isBeforeFirst()){
            return movie;
        }
        resultSet.first();
        movie = Optional.of(mapFields(resultSet));
        return movie;
    }

    private static Movie mapFields(ResultSet resultSet) throws SQLException, NullPointerException {
        Movie movie;
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String director = resultSet.getString("director");
        LocalDate releaseDate = resultSet.getDate("release_date").toLocalDate();
        int budget = resultSet.getInt("budget");
        String description = resultSet.getString("description");
        short runtime = resultSet.getShort("runtime");
        movie = new Movie(id, title, director, releaseDate, budget, description, runtime);
        return movie;
    }

    public static List<Movie> mapMovieList(ResultSet resultSet) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()){
            movies.add(mapFields(resultSet));
        }
        return movies;
    }
}
