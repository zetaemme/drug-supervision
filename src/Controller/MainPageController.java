package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MainPageController {
    private DBConnection mpConnection;

    // Initialize the patients list
    public ObservableList<Patient> initPatientsList() {
        final ObservableList<Patient> patients = FXCollections.observableArrayList();

        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT * FROM Patient");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        try {
            while(mpConnection.rs.next()) {
                patients.add(new Patient(mpConnection.rs.getString("idPatient"),
                                            mpConnection.rs.getDate("birthday"),
                                            mpConnection.rs.getString("province"),
                                            mpConnection.rs.getString("profession"),
                                            new RiskFactor("", mpConnection.rs.getInt("risk_factor"))));
            }
        } catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        } catch(NullStringException nse) {
            System.out.println("NULL STRING: Error: " + nse.getLocalizedMessage());
            mpConnection.closeConnection();
        } catch(IllegalRiskValueException irve) {
            System.out.println("Error: " + irve.getLocalizedMessage());
            mpConnection.closeConnection();
        }

        mpConnection.closeConnection();
        return patients;
    }
}
