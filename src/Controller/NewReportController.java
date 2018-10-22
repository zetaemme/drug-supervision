package Controller;

import Model.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.sql.Date;
import java.sql.SQLException;

public class NewReportController {
    private DBConnection nrConnection;

    public ObservableList<String> initIdList() {
        nrConnection = new DBConnection();
        nrConnection.openConnection();

        final ObservableList<String> patientIds = FXCollections.observableArrayList();

        try {
            nrConnection.statement = nrConnection.connection.createStatement();
            nrConnection.rs = nrConnection.statement.executeQuery("SELECT idPatient FROM Patient");

            while (nrConnection.rs.next()) {
                patientIds.add(nrConnection.rs.getString("idPatient"));
            }
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            nrConnection.closeConnection();
        } finally {
            nrConnection.closeConnection();
        }

        return patientIds;
    }

    public void addNewReport(Date reactionDate, Date reportDate, Label therapyLabel, Label reactionLabel, ChoiceBox idPatient){
        nrConnection = new DBConnection();
        nrConnection.openConnection();

        try{
            nrConnection.statement = nrConnection.connection.createStatement();
            nrConnection.statement.executeUpdate("INSERT INTO Report (reactionDate, reportDate, Therapy_idTherapy, Patient_idPatient) " +
                                                    "VALUES ('" + reactionDate + "', '" + reportDate + "', (SELECT idTherapy FROM Therapy WHERE idTherapy = " +
                                                    "'" + therapyLabel.getText() + "'), (SELECT idPatient FROM Patient WHERE idPatient = " +
                                                    "'" + idPatient.getSelectionModel().getSelectedItem().toString() + "'));" +
                                                    "INSERT INTO Report_has_Reaction (Report_idReport, Report_Therapy_idTherapy, Reaction_Reaction_name, Report_Patient_idPatient) " +
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
