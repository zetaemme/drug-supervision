package Model.Utils.DaoImpl;

import Model.Utils.DAOs.TherapyDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;

public class TherapyDaoImpl implements TherapyDao {
    private DBConnection thConnection;

    @Override
    public void createTherapy(String idTherapy, String drugName, int dose, String startingDate, String endingDate, int dailyFrequency) {
        thConnection = new DBConnection();
        thConnection.openConnection();

        try {
            thConnection.statement = thConnection.connection.createStatement();
            thConnection.statement.executeUpdate(
                    "INSERT INTO Therapy(idTherapy, drugName_drug, dose, startingDate, endingDate, dailyFrequency) " +
                        "VALUES ('" + idTherapy + "','" + drugName + "', '" + dose + "', '" + startingDate + "', '"
                        + endingDate + "', '" + dailyFrequency + "');" +
                        "INSERT INTO Drug(drugName, removalSuggestion, inspectionSuggestion, closeMonitorSuggestion)" +
                        "VALUES ('" + drugName + "', null, null, null );"
            );
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            thConnection.closeConnection();
        }
    }

}
