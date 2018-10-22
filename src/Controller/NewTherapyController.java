package Controller;

import Model.Utils.DBConnection;

import java.sql.Date;
import java.sql.SQLException;

public class NewTherapyController {
    private DBConnection ntConnection;

    // Adds a new therapy with the corresponding data
    public String addNewTherapy(String drugName, int dose, Date startingDate, Date endingDate, int dailyFrequency) {
        ntConnection = new DBConnection();
        ntConnection.openConnection();

        String idTherapy = drugName.substring(0, 3).toUpperCase() + dose + startingDate.toString().replace("-", "")
                + endingDate.toString().replace("-", "") + dailyFrequency;

        try {
            ntConnection.statement = ntConnection.connection.createStatement();
            ntConnection.statement.executeUpdate("INSERT INTO Therapy(idTherapy,drugName_drug, dose, startingDate, endingDate, dailyFrequency) " +
                                                    "VALUES ('" + idTherapy + "','" + drugName + "', '" + dose + "', '" + startingDate + "', '"
                                                    + endingDate + "', '" + dailyFrequency + "')");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            ntConnection.closeConnection();
        } finally {
            ntConnection.closeConnection();
        }

        return idTherapy;
    }
}
