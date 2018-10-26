package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.Utils.DBConnection;
import Model.Utils.DaoImpl.PatientDaoImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageController {
    private DBConnection mpConnection;

    public PatientDaoImpl patientDao;

    // Returns an ObservableArrayList that contains all the patients in the DB
    public ObservableList<Patient> initPatientsList() {
        patientDao = new PatientDaoImpl();

        ObservableList<Patient> patientsOAL = FXCollections.observableArrayList();

        List<Patient> patients = new ArrayList<>();

        try {
            // TODO Lancia NullPointer
            patients = patientDao.getAllPatients();
        } catch (SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Value Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        }

        patientsOAL.addAll(patients);

        return patientsOAL;
    }

    public void deletePatient(String idPatient){
        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try{
            mpConnection.statement = mpConnection.connection.createStatement();

            mpConnection.rs = mpConnection.statement.executeQuery("SELECT Therapy_idTherapy FROM Report WHERE Patient_idPatient = '" + idPatient + "'");

            while(mpConnection.rs.next()) {
                mpConnection.statement.executeUpdate("DELETE FROM Therapy WHERE idTherapy = '" + mpConnection.rs.getString("Therapy_idTherapy") + "'");
                mpConnection.rs.next();
            }

            /*mpConnection.statement.executeUpdate("DELETE FROM Patient WHERE idPatient = '" + idPatient + "'; " +
                                                      "DELETE FROM Report WHERE Patient_idPatient = '" + idPatient + "'; " +
                                                      "DELETE FROM Report_has_Reaction WHERE Report_Patient_idPatient = '" + idPatient + "'");*/
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

    public int getReportNumber() {
        mpConnection = new DBConnection();
        mpConnection.openConnection();

        int counter = 0;

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT idReport FROM Report");

            while(mpConnection.rs.next()) {
                counter++;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        } finally {
            mpConnection.closeConnection();
        }

        return counter;
    }
}
