package Controller;

import Model.Patient;
import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageController {
    private DBConnection mpConnection;

    // TODO Eseguire test, penso sia corretto ma bisogna prima verificare
    public List<String> initPatientList() {
        List<String> patientList = new ArrayList<>();

        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        try {
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT last_name, first_name FROM Patient;");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        try {
            while(mpConnection.rs.next()) {
                patientList.add(mpConnection.rs.getNString("last_name") + " " + mpConnection.rs.getNString("first_name"));
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        mpConnection.closeConnection();
        return patientList;
    }

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
