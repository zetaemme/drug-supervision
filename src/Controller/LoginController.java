package Controller;

import Model.Utils.DBConnection;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class LoginController {
    private DBConnection loginConnection;

    public String loginInstance = null;

    // Checks if the username/password tuple exists in the DB
    public boolean login(String username, String password) {
        loginConnection = new DBConnection();
        loginConnection.openConnection();

        try {
            loginConnection.statement = loginConnection.connection.createStatement();
            loginConnection.rs = loginConnection.statement.executeQuery("SELECT * FROM Users WHERE username = '"
                                                                            + username + "' AND password = '" + password + "';");

            String result = loginConnection.rs.getString("username");

            if(!loginConnection.rs.wasNull()) {
                loginInstance = username;
                return true;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            loginConnection.closeConnection();
        } finally {
            loginConnection.closeConnection();
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

    // Gets user type(medic/pharmacologist)
    public boolean getLoginType(String username) {
        loginConnection = new DBConnection();
        loginConnection.openConnection();

        try {
            loginConnection.statement = loginConnection.connection.createStatement();
            loginConnection.rs = loginConnection.statement.executeQuery("SELECT type FROM Users WHERE username = '" + username + "'");

            return loginConnection.rs.getBoolean("type");
        } catch (SQLException sqle) {
            Alert sqlErrorAlert = new Alert(Alert.AlertType.WARNING);

            sqlErrorAlert.setTitle("Can't login");
            sqlErrorAlert.setHeaderText("An error is occurred!");
            sqlErrorAlert.setContentText("Can't log you in!");

            sqlErrorAlert.showAndWait();
        } finally {
            loginConnection.closeConnection();
        }

        return true;
    }
}
