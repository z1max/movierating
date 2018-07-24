package by.z1max.util.mapper;

import by.z1max.model.Role;
import by.z1max.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserMapper {

    public static Optional<User> map(ResultSet resultSet) throws SQLException {
        Optional<User> user = Optional.empty();
        if (!resultSet.isBeforeFirst()){
            return user;
        }
        resultSet.first();
        user = Optional.of(mapFields(resultSet));
        return user;
    }

    public static List<User> mapUserList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        User user;
        while (resultSet.next()){
            user = mapFields(resultSet);
            users.add(user);
        }
        return users;
    }

    private static User mapFields(ResultSet resultSet) throws SQLException, NullPointerException {
        User user;
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        LocalDate registered = resultSet.getDate("registered").toLocalDate();
        int points = resultSet.getInt("points");
        boolean enabled = resultSet.getBoolean("enabled");
        String roles = resultSet.getString("roles");
        user = new User(id, username, email, password, registered, points, enabled);
        user.setRoles(mapRoles(roles));
        return user;
    }

    private static Set<Role> mapRoles(String roles) {
        Set<Role> result = new HashSet<>();
        String[] rolesArr = roles.split(",");
        for (String role : rolesArr) {
            Role current = Role.values()[Integer.parseInt(role)];
            result.add(current);
        }
        return result;
    }
}
