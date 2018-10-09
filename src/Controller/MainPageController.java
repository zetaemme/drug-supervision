package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.RiskFactor;
import Model.Utils.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
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
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT idPatient, birthday, province, profession, R.description, R.riskLevel " +
                                                                     "FROM Patient JOIN Patient_has_RiskFactor P " +
                                                                     "on Patient.idPatient = P.Patient_idPatient " +
                                                                     "JOIN RiskFactor R on P.RiskFactor_idFactor = R.idFactor");

            while(mpConnection.rs.next()) {
                patients.add(new Patient(mpConnection.rs.getString("idPatient"),
                                         new Date(mpConnection.rs.getDate("birthday").getTime()),
                                         mpConnection.rs.getString("province"),
                                         mpConnection.rs.getString("profession"),
                                         new RiskFactor(mpConnection.rs.getString("description"), mpConnection.rs.getInt("riskLevel"))));
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Value Error: " + irve.getMessage());
            mpConnection.closeConnection();
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
            mpConnection.closeConnection();
        } finally {
            mpConnection.closeConnection();
        }

        return patients;
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
