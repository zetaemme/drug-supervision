package Controller;

import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.Date;

public class NewPatientController {
    private DBConnection npConnection;

    public void addNewPatient(Date birthday, String profession, String province, int riskFactor) {
        npConnection = new DBConnection();
        npConnection.openConnection();

        try {
            npConnection.statement = npConnection.connection.createStatement();
            npConnection.statement.executeUpdate("");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            npConnection.closeConnection();
        } finally {
            npConnection.closeConnection();
        }
    }
}
