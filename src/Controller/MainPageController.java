package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.Utils.DaoImpl.PatientDaoImpl;
import Model.Utils.DaoImpl.ReportDaoImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageController {
    public PatientDaoImpl patientDao;
    public ReportDaoImpl reportDao;

    // Returns an ObservableArrayList that contains all the patients in the DB
    public ObservableList<Patient> initPatientsList() {
        patientDao = new PatientDaoImpl();

        final ObservableList<Patient> patientsOAL = FXCollections.observableArrayList();

        List<Patient> patients = new ArrayList<>();

        try {
            patients = patientDao.getAllPatients();
        } catch (SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Value Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        }

        patientsOAL.addAll(patients);

        return patientsOAL;
    }

    public void deletePatient(String idPatient){
        patientDao = new PatientDaoImpl();
        patientDao.deletePatient(idPatient);
    }

    public String getPatientReport(String idPatient){
        reportDao = new ReportDaoImpl();
        return reportDao.getReport(idPatient);
    }

    public int getReportNumber() {
        reportDao = new ReportDaoImpl();
        return reportDao.getReportNumber();
    }
}
