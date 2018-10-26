package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.Utils.DBConnection;
import Model.Utils.DaoImpl.PatientDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class NewReportController {
    private DBConnection nrConnection;

    public PatientDaoImpl patientDao;

    // Initializes the list with all the IDs
    public ObservableList<String> initIdList() {
        nrConnection = new DBConnection();
        nrConnection.openConnection();

        patientDao = new PatientDaoImpl();

        final ObservableList<String> patientIds = FXCollections.observableArrayList();

        try {
            List<Patient> patients = patientDao.getAllPatients();

            for(Patient p : patients) {
                patientIds.add(p.getId());
            }
        } catch (SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Factor Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        }

        return patientIds;
    }

    // Adds a new report with the corresponding data
    public void addNewReport(Date reactionDate, Date reportDate, Label therapyLabel, Label reactionLabel, ChoiceBox idPatient){
        nrConnection = new DBConnection();
        nrConnection.openConnection();

        try{
            nrConnection.statement = nrConnection.connection.createStatement();
            nrConnection.statement.executeUpdate("INSERT INTO Report (reactionDate, reportDate, Therapy_idTherapy, Patient_idPatient) " +
                                                    "VALUES ('" + reactionDate + "', '" + reportDate + "', (SELECT idTherapy FROM Therapy WHERE idTherapy = " +
                                                    "'" + therapyLabel.getText() + "'), (SELECT idPatient FROM Patient WHERE idPatient = " +
                                                    "'" + idPatient.getSelectionModel().getSelectedItem().toString() + "'));" +
                                                    "INSERT INTO Report_has_Reaction (Report_idReport, Therapy_idTherapy, Reaction_Reaction_name, Patient_idPatient) " +
                                                    "VALUES ((SELECT idReport FROM Report WHERE reactionDate = '" +reactionDate + "' AND " +
                                                    " reportDate = '" + reportDate + "' AND Therapy_idTherapy = '" + therapyLabel.getText() +
                                                    "' AND Patient_idPatient = '" + idPatient.getSelectionModel().getSelectedItem().toString() + "'), " +
                                                    "(SELECT idTherapy FROM Therapy WHERE idTherapy = '" + therapyLabel.getText() + "'), " +
                                                    "(SELECT Reaction_name FROM Reaction WHERE Reaction_name = '"+ reactionLabel.getText() +"')," +
                                                    "(SELECT idPatient FROM Patient WHERE idPatient = '" + idPatient.getSelectionModel().getSelectedItem().toString() + "')) ");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            nrConnection.closeConnection();
        } finally {
            nrConnection.closeConnection();
        }
    }
}
