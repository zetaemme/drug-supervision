package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class DBConnection {
    Connection connection = null;
    Statement statement = null;

    public DBConnection() {
        try {
            Class.forName("org.sqlite.JBDC");
            connection = DriverManager.getConnection("jdbc:DB:DrugDB.db");
            System.out.println("Successfully Connected!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
