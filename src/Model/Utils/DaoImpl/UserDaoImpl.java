package Model.Utils.DaoImpl;

import Model.User;
import Model.Utils.DAOs.UserDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    DBConnection userConnection;

    @Override
    public User getUser(String username) throws SQLException {
        userConnection = new DBConnection();
        userConnection.openConnection();

        userConnection.statement = userConnection.connection.createStatement();
        userConnection.rs = userConnection.statement.executeQuery("SELECT username, password, type FROM Users WHERE username = '" + username + "'");

        User user = new User(userConnection.rs.getString("username"), userConnection.rs.getString("password"),
                                userConnection.rs.getBoolean("type"));

        userConnection.closeConnection();

        return user;
    }
}
