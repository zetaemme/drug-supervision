package Model.Utils.DaoImpl;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.RiskFactor;
import Model.Utils.DAOs.PatientDao;
import Model.Utils.DBConnection;

import java.sql.Date;
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
        patientConnection.rs = patientConnection.statement.executeQuery(
                "SELECT idPatient, birthday, province, profession, riskLevel, description " +
                    "FROM Patient JOIN  Patient_has_RiskFactor PR on Patient.idPatient = PR.Patient_idPatient " +
                    "JOIN RiskFactor R on PR.RiskFactor_idFactor = R.idFactor"
        );

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
    public List<Patient> getAllPatientsWithReport() throws SQLException, IllegalRiskValueException, NullStringException{
        patientConnection = new DBConnection();
        patientConnection.openConnection();

        List<Patient> patients = new ArrayList<>();

        patientConnection.statement = patientConnection.connection.createStatement();
        patientConnection.rs = patientConnection.statement.executeQuery(
                "SELECT idPatient, birthday, province, profession, riskLevel, description FROM Patient " +
                    "JOIN  Patient_has_RiskFactor PR on Patient.idPatient = PR.Patient_idPatient " +
                    "JOIN RiskFactor R on PR.RiskFactor_idFactor = R.idFactor " +
                    "JOIN Report on Patient.idPatient = Report.Patient_idPatient " +
                    "WHERE idPatient = Report.Patient_idPatient"
        );

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
        patientConnection.rs = patientConnection.statement.executeQuery(
                "SELECT idPatient, birthday, province, profession, description, riskLevel FROM Patient " +
                    "JOIN Patient_has_RiskFactor PR on Patient.idPatient = PR.Patient_idPatient " +
                    "JOIN RiskFactor R on PR.RiskFactor_idFactor = R.idFactor " +
                    "WHERE idPatient = '" + idPatient + "'"
        );

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

    @Override
    public void deletePatient(String idPatient) {
        patientConnection.openConnection();

        try {
            patientConnection.statement = patientConnection.connection.createStatement();
            patientConnection.statement.executeUpdate(
                    "DELETE FROM Patient WHERE idPatient = '" + idPatient + "' AND birthday = '" + getPatient(idPatient).getBirthday() +
                        "' AND province = '" + getPatient(idPatient).getProvince() + "' AND profession = '" +
                        getPatient(idPatient).getProfession() + "';" +

                        "DELETE FROM Report WHERE Patient_idPatient = '" + idPatient + "';" +

                        "DELETE FROM Report_has_Reaction WHERE Patient_idPatient = '" + idPatient + "';"
            );

            List<String> therapies = new ArrayList<>();

            patientConnection.rs = patientConnection.statement.executeQuery(
                    "SELECT Therapy_idTherapy FROM Report " +
                        "WHERE Patient_idPatient = '" + idPatient + "'"
            );

            while(patientConnection.rs.next()) {
                therapies.add(patientConnection.rs.getString("Therapy_idTherapy"));
            }

            for(String t : therapies) {
                patientConnection.statement.executeUpdate("DELETE FROM Report WHERE Therapy_idTherapy = '" + t + "'");
            }
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            patientConnection.closeConnection();
        }
    }

    @Override
    public void createPatient(String idPatient, Date birthday, String province, String profession, String medic, int riskFactor, String description) {
        patientConnection = new DBConnection();
        patientConnection.openConnection();

        try {
            patientConnection.statement = patientConnection.connection.createStatement();
            patientConnection.statement.executeUpdate(
                    "INSERT INTO Patient (idPatient, birthday, province, profession, Medic_MedicUsername) " +
                    "VALUES ('" + idPatient  + "', '" + birthday + "', '" + province + "', '" + profession + "', '" + medic + "');" +

                    "INSERT INTO  Patient_has_RiskFactor (Patient_idPatient, RiskFactor_idFactor) VALUES " +
                    "((SELECT idPatient FROM Patient WHERE birthday = '" + birthday + "' AND province = '" +
                    province + "' AND profession = '" + profession + "'), (SELECT idFactor FROM RiskFactor " +
                    "WHERE riskLevel = '" + riskFactor + "' AND description = '" + description + "'));"
            );
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            patientConnection.closeConnection();
        }
    }
}
