package Controller;

import Model.Report;
import Model.Utils.DaoImpl.ReportDaoImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PhMainPageController {

    public ReportDaoImpl reportDao;

    public ObservableList<Report> initReportList() {
        reportDao = new ReportDaoImpl();

        final ObservableList<Report> reports = FXCollections.observableArrayList();

        List<Report> reportList = reportDao.getAllReports();

        reports.addAll(reportList);

        return reports;
    }
}
