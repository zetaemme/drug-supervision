package Controller.PharmacologistControllers;

import Model.Drug;
import Model.Report;
import Model.Utils.DaoImpl.DrugDaoImpl;
import Model.Utils.DaoImpl.ReportDaoImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PhMainPageController {

    public ReportDaoImpl reportDao;
    public DrugDaoImpl drugDao;

    public ObservableList<Report> initReportList(String drugName) {
        reportDao = new ReportDaoImpl();

        final ObservableList<Report> reports = FXCollections.observableArrayList();

        List<Report> reportList = reportDao.getAllReports(drugName);

        reports.addAll(reportList);

        return reports;
    }

    public ObservableList<String> initDrugList(){
        drugDao = new DrugDaoImpl();

        final ObservableList<String> drugNames = FXCollections.observableArrayList();

        List<Drug> drugList = drugDao.getAllDrugs();

        for(Drug d : drugList){
            drugNames.add(d.getDrugName());
        }

        return drugNames;
    }
}
