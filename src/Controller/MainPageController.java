package Controller;

import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.RiskFactor;
import Model.Utils.DBConnection;
import Model.Utils.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainPageController {
    private DBConnection mpConnection;

    // Initialize the patients list
    public ObservableList<Patient> initPatientsList() {
        final ObservableList<Patient> patients = FXCollections.observableArrayList();

        mpConnection = new DBConnection();
        mpConnection.openConnection();

        try {
            mpConnection.statement = mpConnection.connection.createStatement();
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        try {
            mpConnection.rs = mpConnection.statement.executeQuery("SELECT * FROM Patient");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        int count = 0;

        try {
            while(mpConnection.rs.next()) {
                patients.add(newPatientfromRS(mpConnection.rs, count));
                count++;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            mpConnection.closeConnection();
        }

        mpConnection.closeConnection();
        return patients;
    }

    // TODO Fixare il return null
    private Patient newPatientfromRS(ResultSet rs, int index) throws SQLException {
        // TODO Pertutti i campi sottostanti bisogna fare uno slice dell'array del ResultSet
        String first_name = rs.getArray("first_name").toString();
        String last_name = rs.getArray("last_name").toString();
        // TODO Ritorna sql.Date, bisogna trasformarlo in Utils.Date
        Date birthday;
        String province = rs.getArray("province").toString();
        String profession = rs.getArray("profession").toString();
        // TODO Trasformare in RiskFactor
        RiskFactor risk_factor;



        try {
            return new Patient(first_name, last_name, birthday, province, profession, risk_factor);
        } catch (NullStringException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
    }
}
