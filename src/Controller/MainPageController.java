package Controller;

import Model.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MainPageController {
    private DBConnection mpConnection;

    // Initialize the patients list
    public ObservableList<String> initPatientsList() {
        final ObservableList<String> patients = FXCollections.observableArrayList();

        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT idPatient FROM Patient");

            while(mpConnection.rs.next()) {
                for(int i = 1; i <= mpConnection.rs.getMetaData().getColumnCount(); i++) {
                    patients.add(mpConnection.rs.getString(i));
                }
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        } finally {
            mpConnection.closeConnection();
            return patients;
        }
    }

    public void logout(String username) {
        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
          mpConnection.statement = mpConnection.connection.createStatement();
          mpConnection.statement.executeUpdate("UPDATE Users SET logged = 0 WHERE username = '" + username + "';");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            mpConnection.closeConnection();
        }
    }
}
