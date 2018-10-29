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
            rpConnection.rs = rpConnection.statement.executeQuery(
                    "SELECT * FROM Report JOIN Patient P on Report.Patient_idPatient = P.idPatient JOIN " +
                        "Report_has_Reaction RR on Report.idReport = RR.Report_idReport JOIN Reaction R on " +
                        "RR.Reaction_Reaction_name = R.Reaction_name JOIN Therapy T on Report.Therapy_idTherapy = T.idTherapy " +
                        "JOIN Patient_has_RiskFactor PR on P.idPatient = PR.Patient_idPatient JOIN RiskFactor RF on " +
                        "PR.RiskFactor_idFactor = RF.idFactor"
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
                        "((SELECT idReport FROM Report WHERE reactionDate = '" + reactionDate + "', reportDate = '" + reportDate +
                        "', Patient_idPatient = '" + idPatient + "', Therapy_idTherapy = '" + idTherapy + "'), " +
                        "Therapy_idTherapy = '" + idTherapy + "', Patient_idPatient = '" + idPatient + "', " +
                        "Reaction_Reaction_name = '" + Reaction_name + "');"
            );
        } catch (SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } finally {
            rpConnection.closeConnection();
        }
    }

    @Override
    public int getReportNumber() {
        rpConnection = new DBConnection();
        rpConnection.openConnection();

        int counter = 0;

        try {
            rpConnection.statement = rpConnection.connection.createStatement();
            rpConnection.rs = rpConnection.statement.executeQuery(
                    "SELECT idReport FROM Report"
            );

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
