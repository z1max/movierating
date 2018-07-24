package by.z1max.util.mapper;

import by.z1max.model.Country;
import by.z1max.model.Genre;
import by.z1max.model.Movie;

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
        String genres = resultSet.getString("genres");
        movie = new Movie(id, title, director, releaseDate, budget, description, runtime);
        movie.setGenres(mapGenres(genres));
        return movie;
    }

    public static Set<Genre> mapGenres(String genres) {
        Set<Genre> result = new HashSet<>();
        String[] genresArr = genres.split(",");
        for (String genre : genresArr) {
            Genre current = Genre.values()[Integer.parseInt(genre)];
            result.add(current);
        }
        return result;
    }

    public static Set<Country> mapCountries(ResultSet resultSet) throws SQLException {
        Set<Country> result = new HashSet<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            result.add(Country.valueOf(name));
        }
        return result;
    }

    public static List<Movie> mapMovieList(ResultSet resultSet) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()){
            movies.add(mapFields(resultSet));
        }
        return movies;
    }
}
