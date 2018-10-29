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
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error: " + cnfe.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        }
    }
}