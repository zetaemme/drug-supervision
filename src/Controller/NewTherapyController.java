package Controller;

import Model.Utils.DBConnection;

import java.sql.SQLException;

public class NewTherapyController {
    private DBConnection ntConnection;

    public void addNewTherapy(String drugName, int dose, String startingDate, String endingDate, int dailyFrequency) {
        ntConnection = new DBConnection();
        ntConnection.openConnection();

        try {
            ntConnection.statement = ntConnection.connection.createStatement();
            ntConnection.statement.executeUpdate("INSERT INTO Therapy(drugName_drug, dose, startingDate, endingDate, dailyFrequency) " +
                                                    "VALUES ('" + drugName + "', '" + dose + "', '" + startingDate + "', '"
                                                    + endingDate + "', '" + dailyFrequency + "')");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            ntConnection.closeConnection();
        } finally {
            ntConnection.closeConnection();
        }
    }
}
