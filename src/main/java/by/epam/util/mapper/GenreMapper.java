package by.epam.util.mapper;

import by.epam.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreMapper {

    public static Optional<Genre> map(ResultSet resultSet) throws SQLException {
        Optional<Genre> genre = Optional.empty();
        if (!resultSet.isBeforeFirst()){
            return genre;
        }
        resultSet.next();
        genre = Optional.of(mapFields(resultSet));
        return genre;
    }

    public static List<Genre> mapList(ResultSet resultSet) throws SQLException {
        List<Genre> result = new ArrayList<>();
        Genre genre;
        while (resultSet.next()) {
            genre = mapFields(resultSet);
            result.add(genre);
        }
        return result;
    }

    private static Genre mapFields(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }
}
