package ua.training.model.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import static ua.training.constants.Constants.*;

public class UserMapper implements ObjectMapper<User>{
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setName(rs.getString(NAME));
        user.setLogin(rs.getString(LOGIN));
        user.setPassword(rs.getString(PASSWORD));
        String role = rs.getString(ROLE);
        user.setRole(Role.valueOf(role.toUpperCase()));
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
