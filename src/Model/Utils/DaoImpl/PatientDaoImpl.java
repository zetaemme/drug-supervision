package Model.Utils.DaoImpl;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.RiskFactor;
import Model.Utils.DAOs.PatientDao;
import Model.Utils.DBConnection;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {
    DBConnection patientConnection;

    @Override
    public List<Patient> getAllPatients() throws SQLException, IllegalRiskValueException, NullStringException {
        patientConnection = new DBConnection();
        patientConnection.openConnection();

        List<Patient> patients = new ArrayList<>();

        patientConnection.statement = patientConnection.connection.createStatement();
        patientConnection.rs = patientConnection.statement.executeQuery("SELECT idPatient, birthday, province, " +
                                                                            "profession, riskLevel, description " +
                                                                            "FROM Patient JOIN  Patient_has_RiskFactor PR " +
                                                                            "on Patient.idPatient = PR.Patient_idPatient " +
                                                                            "JOIN RiskFactor R on PR.RiskFactor_idFactor = R.idFactor");

        while(patientConnection.rs.next()) {
            patients.add(new Patient(
                    patientConnection.rs.getString("idPatient"),
                    patientConnection.rs.getString("birthday"),
                    patientConnection.rs.getString("province"),
                    patientConnection.rs.getString("profession"),
                    new RiskFactor(
                            patientConnection.rs.getString("description"),
                            patientConnection.rs.getInt("riskLevel")
                    )
                    )
            );
        }

        patientConnection.closeConnection();

        return patients;
    }

    @Override
    public Patient getPatient(String idPatient) throws SQLException {
        patientConnection = new DBConnection();
        patientConnection.openConnection();

        patientConnection.statement = patientConnection.connection.createStatement();
        patientConnection.rs = patientConnection.statement.executeQuery("SELECT idPatient, birthday, province, profession, " +
                                                                            "description, riskLevel FROM Patient JOIN  " +
                                                                            "Patient_has_RiskFactor PR on Patient.idPatient =" +
                                                                            "PR.Patient_idPatient JOIN RiskFactor R on" +
                                                                            " PR.RiskFactor_idFactor = R.idFactor WHERE" +
                                                                            " idPatient = '" + idPatient + "'");

        Patient patient = null;

        try {
            patient = new Patient(
                    patientConnection.rs.getString("idPatient"),
                    patientConnection.rs.getString("birthday"),
                    patientConnection.rs.getString("province"),
                    patientConnection.rs.getString("profession"),
                    new RiskFactor(
                            patientConnection.rs.getString("description"),
                            patientConnection.rs.getInt("riskLevel")
                    )
            );
        } catch(IllegalRiskValueException irve) {
            System.out.println("RiskValue Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        } finally {
            patientConnection.closeConnection();
        }

        return patient;
    }
}
