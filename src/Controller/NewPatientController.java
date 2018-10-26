package Controller;

import Model.RiskFactor;
import Model.Utils.DBConnection;
import Model.Utils.DaoImpl.PatientDaoImpl;
import Model.Utils.DaoImpl.RiskFactorDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class NewPatientController {
    private DBConnection npConnection;

    public PatientDaoImpl patientDao;
    public RiskFactorDaoImpl riskFactorDao;

    // Adds a patient with the corresponding data
    public void addNewPatient(Date birthday, String province, String profession, String medic, int riskFactor, String description) {
        patientDao = new PatientDaoImpl();

        String idPatient = province + birthday.toString().replace("-", "")
                           + profession.substring(0 , 3).toUpperCase();

        patientDao.createPatient(idPatient, birthday, province, profession, medic,riskFactor, description);
    }

    // Initializes the list with all the RiskFactors
    public ObservableList<RiskFactor> initRiskFactorList() {
        riskFactorDao = new RiskFactorDaoImpl();

        final ObservableList<RiskFactor> riskFactors = FXCollections.observableArrayList(riskFactorDao.getAllRiskFactors());

        return riskFactors;
    }
}
