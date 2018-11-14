package Model.Utils.DAOs;

import Model.Report;

import java.util.List;

public interface ReportDao {
    List<Report> getAllReports(String drugName);
    String getReport(String idPatient);
    void createReport(String idPatient, String Reaction_name, String reportDate, String reactionDate, String idTherapy);
    int getReportNumber(String drugName);
    int getReportNumber();
}
