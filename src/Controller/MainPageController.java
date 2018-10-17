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
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT idPatient, birthday, province, profession, R.description, R.riskLevel " +
                                                                     "FROM Patient JOIN Patient_has_RiskFactor P " +
                                                                     "on Patient.idPatient = P.Patient_idPatient " +
                                                                     "JOIN RiskFactor R on P.RiskFactor_idFactor = R.idFactor " +
                                                                     "JOIN Report on Patient.idPatient = Report.Patient_idPatient " +
                                                                     "WHERE idPatient = Report.Patient_idPatient");

            while(mpConnection.rs.next()) {
                patients.add(new Patient(mpConnection.rs.getString("idPatient"),
                                         mpConnection.rs.getString("birthday"),
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

    public void deletePatient(String idPatient){
        mpConnection = new DBConnection();
        mpConnection.openConnection();

        String idTherapy = "";


        try{
            mpConnection.statement = mpConnection.connection.createStatement();

            mpConnection.rs = mpConnection.statement.executeQuery("SELECT Therapy_idTherapy FROM Report WHERE Patient_idPatient = '" + idPatient + "'");
            idTherapy = mpConnection.rs.getString("Therapy_idTherapy");

            mpConnection.statement.executeUpdate("DELETE FROM Patient WHERE idPatient = '" + idPatient + "'; " +
                                                      "DELETE FROM Report WHERE Patient_idPatient = '" + idPatient + "'; " +
                                                      "DELETE FROM Therapy WHERE idTherapy = '" + idTherapy + "'; " +
                                                      "DELETE FROM Report_has_Reaction WHERE Report_Patient_idPatient = '" + idPatient + "'");

        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        } finally {
            mpConnection.closeConnection();
        }
    }

    public String getPatientReport(String idPatient){
        String result = "";

        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT reportDate, reactionDate FROM Report WHERE Patient_idPatient = '" + idPatient + "'");

            result = "Report Date: " + mpConnection.rs.getString("reportDate") + "\n\nReactionDate: " + mpConnection.rs.getString("reactionDate");

        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        } finally {
            mpConnection.closeConnection();
        }

        return result;
    }
}
