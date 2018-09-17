package Controller;

import Model.Patient;
import Model.Utils.DBConnection;

import java.sql.SQLException;

public class MainPageController {
    private DBConnection mpConnection;

    public String setNameLabel(Patient patient) {
        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        try {
            return String.valueOf(mpConnection.statement.executeQuery("SELECT first_name FROM Patient WHERE first_name = " + patient.getFirstName() + ";"));
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        mpConnection.closeConnection();
        return "";
    }
}
