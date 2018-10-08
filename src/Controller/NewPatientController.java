package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Date;

public class NewPatientController {
    private DBConnection npConnection;

    public void addNewPatient(Date birthday, String profession, String province, int riskFactor, String medic) {
        npConnection = new DBConnection();
        npConnection.openConnection();

        try {
            npConnection.statement = npConnection.connection.createStatement();
            npConnection.statement.executeUpdate("INSERT INTO Patient (birthday, province, profession, Medic_MedicUsername) VALUES ('"
                                                    + birthday + "','" + province + "','" + profession + "','" + medic + "')");
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
