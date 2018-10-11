package Controller;

import Model.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class NewReportController {
    private DBConnection nrConnection;

    public ObservableList<String> initIdList(){
        nrConnection = new DBConnection();
        nrConnection.openConnection();

        final ObservableList<String> patientIds = FXCollections.observableArrayList();

        try{
            nrConnection.statement = nrConnection.connection.createStatement();
            nrConnection.rs = nrConnection.statement.executeQuery("SELECT idPatient FROM Patient");

            while(nrConnection.rs.next()) {
                patientIds.add(nrConnection.rs.getString("idPatient"));
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            nrConnection.closeConnection();
        } finally {
            nrConnection.closeConnection();
        }

        return patientIds;
    }
}
