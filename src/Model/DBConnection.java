package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class DBConnection {
    Connection connection = null;
    Statement statement = null;

    DBConnection() {
        try {
            Class.forName("org.sqlite.JBDC");
            connection = DriverManager.getConnection("jdbc:DB:DrugDB.db");
            System.out.println("Connected!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
