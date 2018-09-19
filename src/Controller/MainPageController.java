package Controller;

import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import Model.Utils.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MainPageController {
    private DBConnection mpConnection;

    // Initialize the patients list
   /* public ObservableList<Patient> initPatientsList() {
        final ObservableList<Patient> patients = FXCollections.observableArrayList();

        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        try {
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT * FROM Patient");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        int count = 0;

        try {
            while(mpConnection.rs.next()) {
                patients.add(new Patient(mpConnection.rs.getString("first_name"),
                                            mpConnection.rs.getString("last_name"),
                                            ,
                                            mpConnection.rs.getString("province"),
                                            mpConnection.rs.getString("profession"),
                                            ));
                count++;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        mpConnection.closeConnection();
        return patients;
    } */
}
