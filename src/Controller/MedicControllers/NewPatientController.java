package Controller.MedicControllers;

import Model.RiskFactor;
import Model.Utils.DaoImpl.PatientDaoImpl;
import Model.Utils.DaoImpl.RiskFactorDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class NewPatientController {

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
