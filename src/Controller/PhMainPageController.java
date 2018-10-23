package Controller;

import Model.Report;
import Model.Utils.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PhMainPageController {
    private DBConnection phMpConnection;

    public ObservableList<Report> initReportList() {
        final ObservableList<Report> reports = FXCollections.observableArrayList();

        phMpConnection = new DBConnection();
        phMpConnection.openConnection();

        try {
            phMpConnection.statement = phMpConnection.connection.createStatement();
            // TODO Creare query
            /*phMpConnection.rs = phMpConnection.statement.executeQuery("SELECT idPatient, birthday, province, profession, R.description, R.riskLevel " +
                                                                            "FROM Patient JOIN Patient_has_RiskFactor P " +
                                                                            "on Patient.idPatient = P.Patient_idPatient " +
                                                                            "JOIN RiskFactor R on P.RiskFactor_idFactor = R.idFactor " +
                                                                            "JOIN Report on Patient.idPatient = Report.Patient_idPatient " +
                                                                            "WHERE idPatient = Report.Patient_idPatient");*/

            while(phMpConnection.rs.next()) {
                // TODO Creare reports
                reports.add(new Report());
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            phMpConnection.closeConnection();
        } finally {
            phMpConnection.closeConnection();
        }

        return reports;
    }
}
