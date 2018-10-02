package Controller;

import Model.Utils.DBConnection;

import java.sql.SQLException;

public class LoginController {
    private DBConnection loginConnection;

    public boolean login(String username, String password) {
        loginConnection = new DBConnection();
        loginConnection.openConnection();

        int count = 0;


        try {
            loginConnection.statement = loginConnection.connection.createStatement();
            loginConnection.rs = loginConnection.statement.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + password + "' AND logged = 0;");

            while (loginConnection.rs.next()) {
                count++;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            loginConnection.closeConnection();
            //return false;
        } finally {
            loginConnection.closeConnection();
        }

        if (count > 0) {
            logged(username, loginConnection);
            System.out.println("Welcome: " + username);
            return true;
        }

        return false;
    }

    // Controls if the user associated with the the @param username is already logged in
    private void logged(String username, DBConnection loginConnection) {
        loginConnection.openConnection();

        try {
            loginConnection.statement = loginConnection.connection.createStatement();
            loginConnection.statement.executeUpdate("UPDATE Users SET logged = 1 WHERE username = '" + username + "';");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            loginConnection.closeConnection();
        } finally {
            loginConnection.closeConnection();
        }
    }
}
