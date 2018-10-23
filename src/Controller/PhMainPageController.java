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
            phMpConnection.rs = phMpConnection.statement.executeQuery("");

            while(phMpConnection.rs.next()) {
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
