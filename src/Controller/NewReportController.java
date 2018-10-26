package Controller;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Patient;
import Model.Utils.DBConnection;
import Model.Utils.DaoImpl.PatientDaoImpl;

import Model.Utils.DaoImpl.ReportDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class NewReportController {
    private DBConnection nrConnection;

    public PatientDaoImpl patientDao;
    public ReportDaoImpl reportDao;

    // Initializes the list with all the IDs
    public ObservableList<String> initIdList() {
        nrConnection = new DBConnection();
        nrConnection.openConnection();

        patientDao = new PatientDaoImpl();

        final ObservableList<String> patientIds = FXCollections.observableArrayList();

        try {
            List<Patient> patients = patientDao.getAllPatients();

            for(Patient p : patients) {
                patientIds.add(p.getId());
            }
        } catch (SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Factor Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        }

        return patientIds;
    }

    // Adds a new report with the corresponding data
    public void addNewReport(String idPatient, String Reaction_name, String reportDate, String reactionDate, String idTherapy){
        reportDao = new ReportDaoImpl();

        reportDao.createReport(idPatient, Reaction_name, reportDate, reactionDate, idTherapy);
    }
}
