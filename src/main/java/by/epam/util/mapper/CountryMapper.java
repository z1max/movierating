package by.epam.util.mapper;

import by.epam.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryMapper {

    public static Optional<Country> map(ResultSet resultSet) throws SQLException {
        Optional<Country> country = Optional.empty();
        if (!resultSet.isBeforeFirst()) {
            return country;
        }
        resultSet.next();
        country = Optional.of(mapFields(resultSet));
        return country;
    }

    public static List<Country> mapList(ResultSet resultSet) throws SQLException {
        List<Country> result = new ArrayList<>();
        Country country;
        while (resultSet.next()){
            country = mapFields(resultSet);
            result.add(country);
        }
        return result;
    }

    private static Country mapFields(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setName(resultSet.getString("name"));
        return country;
    }
}
