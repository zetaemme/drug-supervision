package Model;

import java.sql.*;
import java.sql.PreparedStatement;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class DBConnection {
    static Connection connection = null;

    private static void openConnection () {
        try {
            Class.forName("org.sqlite.JBDC");
            connection = DriverManager.getConnection("jdbc:DB:DrugDB.db");
            System.out.println("Connected!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void insert(String buffer, String tableName) throws Exception {
        openConnection();

        PreparedStatement insertQuery;

        switch(tableName){
            case "Patient":
                insertQuery = connection.prepareStatement("INSERT INTO " + tableName + "(idPatient, birthday, province, professionn) " +
                        "VALUES (" + buffer + ")");
                insertQuery.executeUpdate();
                break;
            case "RiskFactor":
                insertQuery = connection.prepareStatement("INSERT INTO " + tableName + "(idFactor, description, riskLevel)" +
                        "VALUES (" + buffer + ")");
                insertQuery.executeUpdate();
                break;
            case "Reaction":
                insertQuery = connection.prepareStatement("INSERT INTO " + tableName + "(Reaction_name, risk, description)" +
                        "VALUES (" + buffer + ")");
                insertQuery.executeUpdate();
                break;
            case "Report":
                insertQuery = connection.prepareStatement("INSERT INTO " + tableName + "(idReport, reaction_date, report_date)" +
                        "VALUES (" + buffer + ")");
                insertQuery.executeUpdate();
                break;
            case "Therapy":
                insertQuery = connection.prepareStatement("INSERT INTO " + tableName + "(idTherapy, drug, dose, startingDate, endingDate, dailyFrequency" +
                        "VALUES (" + buffer + ")");
                insertQuery.executeUpdate();
                break;
        }

        closeConnection();
    }
}
