package Model.Utils.DaoImpl;

import Model.*;
import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullDailyFrequencyException;
import Model.Exceptions.NullDoseException;
import Model.Exceptions.NullStringException;
import Model.Utils.DAOs.ReportDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {
    private DBConnection rpConnection;

    @Override
    public List<Report> getAllReports() {
        rpConnection = new DBConnection();
        rpConnection.openConnection();

        List<Report> reports = new ArrayList<>();

        try {
            rpConnection.statement = rpConnection.connection.createStatement();
            // TODO L'errore è nella query, provata in SQLBrowser, torna 0 righe
            rpConnection.rs = rpConnection.statement.executeQuery(
                    "SELECT idPatient, birthday, province, province, profession, description, riskLevel, risk, " +
                        "reactionDescription, reportDate, reactionDate, drugName_drug, dose, dailyFrequency, " +
                        "startingDate, endingDate " +
                        "FROM Report " +
                        "JOIN Patient on Report.Patient_idPatient = Patient.idPatient " +
                        "JOIN Report_has_Reaction on Report.idReport = Report_has_Reaction.Report_idReport " +
                        "JOIN Reaction on Report_has_Reaction.Reaction_Reaction_name = Reaction.Reaction_name " +
                        "JOIN Therapy on Report.Therapy_idTherapy = Therapy.idTherapy " +
                        "JOIN Patient_has_RiskFactor on Patient.idPatient = Patient_has_RiskFactor.Patient_idPatient " +
                        "JOIN RiskFactor on Patient_has_RiskFactor.RiskFactor_idFactor = RiskFactor.idFactor"
            );

            while(rpConnection.rs.next()) {
                reports.add(new Report(
                        new Patient(
                                rpConnection.rs.getString("idPatient"),
                                rpConnection.rs.getString("birthday"),
                                rpConnection.rs.getString("province"),
                                rpConnection.rs.getString("profession"),
                                new RiskFactor(
                                        rpConnection.rs.getString("description"),
                                        rpConnection.rs.getInt("riskLevel")
                                )
                        ),
                        new Reaction(
                                rpConnection.rs.getInt("risk"),
                                rpConnection.rs.getString("reactionDescription")
                        ),
                        rpConnection.rs.getString("reportDate"),
                        rpConnection.rs.getString("reactionDate"),
                        new Therapy(
                                rpConnection.rs.getString("drugName_drug"),
                                rpConnection.rs.getDouble("dose"),
                                rpConnection.rs.getInt("dailyFrequency"),
                                rpConnection.rs.getString("startingDate"),
                                rpConnection.rs.getString("endingDate")
                        )
                    )
                );
            }
        } catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Factor Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        } catch (NullDoseException nde) {
            System.out.println("Dose Error: " + nde.getMessage());
        } catch (NullDailyFrequencyException ndfe) {
            System.out.println("Daily Frequency Error: " + ndfe.getMessage());
        } finally {
            rpConnection.closeConnection();
        }

        return reports;
    }

    public String getReport(String idPatient) {
        rpConnection = new DBConnection();
        rpConnection.openConnection();

        String result = "";

        try {
            rpConnection.statement = rpConnection.connection.createStatement();
            rpConnection.rs = rpConnection.statement.executeQuery(
                    "SELECT reportDate, reactionDate FROM Report WHERE Patient_idPatient = '" + idPatient + "'"
            );

            result = "Report Date: " + rpConnection.rs.getString("reportDate") + "\n\nReaction Date: " + rpConnection.rs.getString("reactionDate");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            rpConnection.closeConnection();
        }

        return result;
    }

    @Override
    public void createReport(String idPatient, String Reaction_name, String reportDate, String reactionDate, String idTherapy) {
        rpConnection = new DBConnection();
        rpConnection.openConnection();

        try {
            rpConnection.statement = rpConnection.connection.createStatement();
            rpConnection.statement.executeUpdate(
                    "INSERT INTO Report (reactionDate, reportDate, Patient_idPatient, Therapy_idTherapy) VALUES " +
                        "('" + reactionDate + "', '" + reportDate + "', '" + idPatient + "', '" + idTherapy +"');" +

                        "INSERT INTO Report_has_Reaction(Report_idReport, Therapy_idTherapy, Patient_idPatient, Reaction_Reaction_name) VALUES " +
                        "((SELECT idReport FROM Report WHERE reactionDate = '" + reactionDate + "' AND reportDate = '" + reportDate + "'AND " +
                        "Patient_idPatient = '" + idPatient + "' AND Therapy_idTherapy = '" + idTherapy + "'), " +
                        "'" + idTherapy + "', '" + idPatient + "', '" + Reaction_name + "');"
            );
        } catch (SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } finally {
            rpConnection.closeConnection();
        }
    }

    @Override
    public int getReportNumber(){
        return getReportNumber(null);
    }


    @Override
    public int getReportNumber(String drugName) {
        rpConnection = new DBConnection();
        rpConnection.openConnection();

        int counter = 0;

        try {
            rpConnection.statement = rpConnection.connection.createStatement();
            if(drugName == null){
                rpConnection.rs = rpConnection.statement.executeQuery(
                        "SELECT idReport FROM Report"
                );
            }
            else{
                rpConnection.rs = rpConnection.statement.executeQuery(
                        "SELECT idReport FROM Report " +
                           "JOIN Therapy on Report.Therapy_idTherapy = Therapy.idTherapy " +
                           "WHERE Therapy.drugName_drug = '" + drugName + "'"
                );
            }


            while(rpConnection.rs.next()) {
                counter++;
            }
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            rpConnection.closeConnection();
        }

        return counter;
    }
}
