package Model.Utils.DaoImpl;

import Model.User;
import Model.Utils.DAOs.UserDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    DBConnection userConnection;

    @Override
    public List<User> getAllUsers() {
        userConnection = new DBConnection();
        userConnection.openConnection();

        List<User> usersList = new ArrayList<>();

        try {
            userConnection.statement = userConnection.connection.createStatement();
            userConnection.rs = userConnection.statement.executeQuery("SELECT * FROM Users");

            while(userConnection.rs.next()) {
               usersList.add(new User(userConnection.rs.getString("username"), userConnection.rs.getString("password"),
                                        userConnection.rs.getBoolean("type")));
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            userConnection.closeConnection();
        }

        return usersList;
    }

    @Override
    public User getUser(String username) {
        userConnection = new DBConnection();
        userConnection.openConnection();

        User user = null;

        try {
            userConnection.statement = userConnection.connection.createStatement();
            userConnection.rs = userConnection.statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "'");

            user = new User(userConnection.rs.getString("username"), userConnection.rs.getString("password"),
                                    userConnection.rs.getBoolean("type"));
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            userConnection.closeConnection();
        }

        return user;
    }
}
