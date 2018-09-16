package Model.Utils;

import java.sql.*;

public class DBConnection {
    public Connection connection = null;
    public Statement statement = null;
    public ResultSet rs = null;

    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/DB/DrugDB.db");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }
    }

    private String getType(String username) {
        openConnection();

        try {
            statement = connection.createStatement();
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }

        try {
            rs = statement.executeQuery("SELECT type FROM Users WHERE username = '" + username + "';");
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }

        closeConnection();

        return String.valueOf(rs);
    }

    private String getUsername(String username) {
        openConnection();

        try {
            statement = connection.createStatement();
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }

        try {
            rs = statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "';");
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }

        closeConnection();

        return String.valueOf(rs);
    }

    private void insert(String buffer, String tableName) {
        openConnection();

        try {
            statement = connection.createStatement();
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }

        try {
            statement.executeQuery("INSERT INTO " + tableName + " VALUES " + buffer + ";");
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }

        closeConnection();
    }
}