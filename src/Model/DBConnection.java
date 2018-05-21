package Model;

import java.sql.*;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class DBConnection {
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    private void openConnection() {
        try {
            Class.forName("org.sqlite.JBDC");
            connection = DriverManager.getConnection("jdbc:DB:DrugDB.db");
            System.out.println("Successfully Connected!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void closeConnection() throws SQLException {
        connection.close();
    }


    private String getType(String username) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT type FROM Users WHERE username = '" + username + "';");

        closeConnection();

        return String.valueOf(rs);
    }

    private String getUsername(String username) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "';");

        closeConnection();

        return String.valueOf(rs);
    }

    public boolean login(String username, String password) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' password = '" + password + "' logged = false;");

        closeConnection();

        int count = 0;
        while(rs.next()){
            count += 1;
        }
        logged(username);
        return count != 0;
    }


    private void insert(String buffer, String tableName) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        statement.executeQuery("INSERT INTO " + tableName + " VALUES " + buffer + ";");

        closeConnection();
    }

    protected void logged(String username) throws SQLException {
        openConnection();
        statement = connection.createStatement();
        statement.executeQuery("UPDATE Users SET logged = true WHERE username = '" + username + "';");
        closeConnection();
    }

    private void logout(String username) throws SQLException {
        openConnection();
        statement = connection.createStatement();
        statement.executeQuery("UPDATE Users SET logged = false WHERE username = '" + username + "';");
        closeConnection();
    }


}