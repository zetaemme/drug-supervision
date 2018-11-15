package Model.Utils.DAOs;

import Model.User;

import java.sql.SQLException;

public interface UserDao {
    User getUser(String username) throws SQLException;
}
