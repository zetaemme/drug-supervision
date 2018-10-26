package Model.Utils.DAOs;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    List<Patient> getAllPatients() throws SQLException, IllegalRiskValueException, NullStringException;
    Patient getPatient(String idPatient) throws SQLException;
    void deletePatient(String idPatient);
    void createPatient(String idPatient, Date birthday, String province, String profession, String medic, int riskFactor, String description);
}
