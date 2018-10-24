package Controller;

import Model.Utils.DBConnection;

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
    public boolean getLoginType(String username) throws SQLException{
        loginConnection = new DBConnection();
        loginConnection.openConnection();

        loginConnection.statement = loginConnection.connection.createStatement();
        loginConnection.rs = loginConnection.statement.executeQuery("SELECT type FROM Users WHERE username = '" + username +"'");

        return loginConnection.rs.getBoolean("type");
    }

    public int getReportNumber() {
        loginConnection = new DBConnection();
        loginConnection.openConnection();

        int counter = 0;

        try {
            loginConnection.statement = loginConnection.connection.createStatement();
            loginConnection.rs = loginConnection.statement.executeQuery("SELECT idReport FROM Report");

            while(loginConnection.rs.next()) {
                counter++;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            loginConnection.closeConnection();
        } finally {
            loginConnection.closeConnection();
        }

        return counter;
    }
}
