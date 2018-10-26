package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import Model.Utils.DaoImpl.PatientDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Date;

public class NewPatientController {
    private DBConnection npConnection;

    public PatientDaoImpl patientDao;

    // Adds a patient with the corresponding data
    public void addNewPatient(Date birthday, String province, String profession, String medic, int riskFactor, String description) {
        patientDao = new PatientDaoImpl();

        String idPatient = province + birthday.toString().replace("-", "")
                           + profession.substring(0 , 3).toUpperCase();

        patientDao.createPatient(idPatient, birthday, province, profession, medic,riskFactor, description);
    }

    // Initializes the list with all the RiskFactors
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
