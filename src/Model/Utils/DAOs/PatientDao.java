package Model.Utils.DAOs;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    List<Patient> getAllPatients() throws SQLException, IllegalRiskValueException, NullStringException;
    Patient getPatient(String idPatient) throws SQLException;
    void deletePatient(String idPatient);
}
