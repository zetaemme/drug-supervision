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
}
