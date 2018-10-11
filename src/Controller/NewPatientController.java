package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Date;

public class NewPatientController {
    private DBConnection npConnection;

    public void addNewPatient(Date birthday, String province, String profession, String medic, int riskFactor, String description) {
        npConnection = new DBConnection();
        npConnection.openConnection();


        String idPatient = province + birthday.toString().replace("-", "")
                           + profession.substring(0 , 3).toUpperCase();

        try {
            npConnection.statement = npConnection.connection.createStatement();
            npConnection.statement.executeUpdate(
                    "INSERT INTO Patient (idPatient, birthday, province, profession, Medic_MedicUsername) " +
                        "VALUES ('" + idPatient  + "', '" + birthday + "', '" + province + "', '" + profession + "', '" + medic + "');" +
                        "INSERT INTO  Patient_has_RiskFactor (Patient_idPatient, RiskFactor_idFactor) VALUES " +
                        "((SELECT idPatient FROM Patient WHERE birthday = '" + birthday + "' AND province = '" +
                        province + "' AND profession = '" + profession + "'), (SELECT idFactor FROM RiskFactor " +
                        "WHERE riskLevel = '" + riskFactor + "' AND description = '" + description + "'));"
            );

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
