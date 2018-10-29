package Controller;

import Model.User;
import Model.Utils.DaoImpl.UserDaoImpl;

import java.sql.SQLException;

public class LoginController {
    public UserDaoImpl userDao;

    public String loginInstance = null;

    // Checks if the username/password tuple exists in the DB
    public boolean login(String username, String password) throws SQLException {
        userDao = new UserDaoImpl();

        User user = userDao.getUser(username);

        if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
            loginInstance = username;
            return true;
        }

        return false;
    }

    // Checks if the login instance isn't null
    public boolean isLogged() {
        if(loginInstance != null) {
            return true;
        } else {
            return false;
        }
    }

    // Set's the login instance as null
    public void logout() {
        loginInstance = null;
    }
}
