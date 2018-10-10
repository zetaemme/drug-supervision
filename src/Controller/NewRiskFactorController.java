package Controller;

import Model.Utils.DBConnection;

import java.sql.SQLException;

public class NewRiskFactorController {
    private DBConnection npConnection;

    public void addRiskFactor(int riskLevel, String description) {
        npConnection = new DBConnection();
        npConnection.openConnection();

        try {
            npConnection.statement = npConnection.connection.createStatement();
            npConnection.statement.executeUpdate("INSERT INTO RiskFactor (description, riskLevel) VALUES ('" + description + "','" + riskLevel + "')");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            npConnection.closeConnection();
        } finally {
            npConnection.closeConnection();
        }
    }
}
