package by.z1max;

import by.z1max.model.Role;
import by.z1max.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class UserTestData {

    public static final User ADMIN = new User(1, "admin", "admin@gmail.com",
            "713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca",
            LocalDate.of(2018, 06, 10), 10, true);
    public static final User USER1 = new User(2, "valera", "valera@yandex.ru",
            "4bb6db7e55593590fc92506ef021dbe671d87fb0de1390a23d9672a7d1e7d7f5",
            LocalDate.of(2018, 06, 11), 32, true);
    public static final User USER2 = new User(3, "sobakez", "sobakez@gmail.com",
            "f68b67dd51c8d06677324d21ee9005b9c809c6ddfc12e5b67c9353eed4ab54f4",
            LocalDate.of(2018, 06, 12), 60, true);
    public static final User USER3 = new User(4, "tomimt", "tomimt@hotmail.com",
            "5eef1353ac5c84b464f1eed1de4f322661a15316a9c88f7c688c354284a91d3e",
            LocalDate.of(2018, 06, 12), 27, true);
    public static final User USER4 = new User(5, "Sam", "sam@gmail.com",
            "d4daffdfad7c75260eeaa52d1221e2e494b9a42650728fd110a70ac952d8974f",
            LocalDate.of(2018, 06, 13), 16, true);
    public static final User NEW = new User(6, "newuser", "newuser@gmail.com",
            "27dceaed838cb3521de19aaa22284a3d07d371a07cc000db1acd870ed4740ce5",
            LocalDate.now(), 0, true);
    public static final User UPDATED = new User(2, "updated", "updated@gmail.com",
            "18b643757ed8ffc0afb23784843da053c42945cd048fe5762eccc56c7cdac500",
            LocalDate.of(2018, 06, 11), 32, true);
    static {
        ADMIN.setRoles(EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER));
        USER1.setRoles(EnumSet.of(Role.ROLE_USER));
        USER2.setRoles(EnumSet.of(Role.ROLE_USER));
        USER3.setRoles(EnumSet.of(Role.ROLE_USER));
        USER4.setRoles(EnumSet.of(Role.ROLE_USER));
        NEW.setRoles(EnumSet.of(Role.ROLE_USER));
        UPDATED.setRoles(EnumSet.of(Role.ROLE_USER));
    }

    public static List<User> getAll(){
        return Arrays.asList(ADMIN, USER4, USER2, USER3, USER1);
    }

    public static List<User> getAllForCreate(){
        return Arrays.asList(ADMIN, NEW, USER4, USER2, USER3, USER1);
    }

    public static List<User> getAllForDelete(){
        return Arrays.asList(USER4, USER2, USER3, USER1);
    }
}
