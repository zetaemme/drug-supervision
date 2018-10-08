package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Date;

public class NewPatientController {
    private DBConnection npConnection;

    // TODO Manca il MedicUsername sia a View che a Controller, vorrei un modo per ricavarlo dallo User loggato
    public void addNewPatient(Date birthday, String province, String profession) {
        npConnection = new DBConnection();
        npConnection.openConnection();

        try {
            npConnection.statement = npConnection.connection.createStatement();
            npConnection.statement.executeUpdate("INSERT INTO Patient (birthday, province, profession) " +
                                                    "VALUES ('" + birthday + "','" + province + "','" + profession + "')");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            npConnection.closeConnection();
        } finally {
            npConnection.closeConnection();
        }
    }

    public ObservableList<RiskFactor> initRiskFactorList() {
        final ObservableList<RiskFactor> riskFactors = FXCollections.observableArrayList();

        npConnection = new DBConnection();
        npConnection.openConnection();

        try {
            npConnection.statement = npConnection.connection.createStatement();
            npConnection.rs = npConnection.statement.executeQuery("SELECT riskLevel, description FROM RiskFactor");

            while(npConnection.rs.next()) {
                riskFactors.add(new RiskFactor(npConnection.rs.getString("description"), npConnection.rs.getInt("riskLevel")));
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            npConnection.closeConnection();
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Value Error: " + irve.getMessage());
            npConnection.closeConnection();
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
            npConnection.closeConnection();
        } finally {
            npConnection.closeConnection();
        }

        return riskFactors;
    }
}
