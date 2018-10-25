package Controller;

import Model.*;
import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullDailyFrequencyException;
import Model.Exceptions.NullDoseException;
import Model.Exceptions.NullStringException;
import Model.Utils.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PhMainPageController {
    private DBConnection phMpConnection;

    public ObservableList<Report> initReportList() {
        final ObservableList<Report> reports = FXCollections.observableArrayList();

        phMpConnection = new DBConnection();
        phMpConnection.openConnection();

        try {
            phMpConnection.statement = phMpConnection.connection.createStatement();
            phMpConnection.rs = phMpConnection.statement.executeQuery("SELECT reactionDate, reportDate, P.idPatient," +
                                                                            "P.birthday, P.province, P.profession, R.riskLevel, R.description," +
                                                                            "T.drugName_drug, T.dose, T.dailyFrequency, T.startingDate, T.endingDate," +
                                                                            "R2.risk, R2.reactionDescription " +
                                                                            "FROM Report JOIN Patient P on P.idPatient = Report.Patient_idPatient " +
                                                                            "JOIN Patient_has_RiskFactor PR on P.idPatient = PR.Patient_idPatient " +
                                                                            "JOIN RiskFactor R on R.idFactor = PR.RiskFactor_idFactor " +
                                                                            "JOIN Therapy T on Report.Therapy_idTherapy = T.idTherapy " +
                                                                            "JOIN Report_has_Reaction RR on Report.idReport = RR.Report_idReport " +
                                                                            "JOIN Reaction R2 on RR.Reaction_Reaction_name = R2.Reaction_name");

            while(phMpConnection.rs.next()) {
                reports.add(new Report(new Patient(phMpConnection.rs.getString("idPatient"),
                                                    phMpConnection.rs.getString("birthday"),
                                                    phMpConnection.rs.getString("province"),
                                                    phMpConnection.rs.getString("profession"),
                                                    new RiskFactor(phMpConnection.rs.getString("description"),
                                                                    phMpConnection.rs.getInt("riskLevel"))),
                            new Reaction(phMpConnection.rs.getInt("risk"), phMpConnection.rs.getString("reactionDescription")),
                            phMpConnection.rs.getString("reportDate"),
                            phMpConnection.rs.getString("reactionDate"),
                            new Therapy(phMpConnection.rs.getString("drugName_drug"),
                                        phMpConnection.rs.getDouble("dose"),
                                        phMpConnection.rs.getInt("dailyFrequency"),
                                        phMpConnection.rs.getString("startingDate"),
                                        phMpConnection.rs.getString("endingDate"))));
            }
        } catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
            phMpConnection.closeConnection();
        } catch (IllegalRiskValueException irve) {
            System.out.println("RiskValue Error: " + irve.getMessage());
            phMpConnection.closeConnection();
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
            phMpConnection.closeConnection();
        } catch (NullDoseException nde) {
            System.out.println("Dose Error: " + nde.getMessage());
            phMpConnection.closeConnection();
        } catch (NullDailyFrequencyException ndfe) {
            System.out.println("DailyFrequency Error: " + ndfe.getMessage());
            phMpConnection.closeConnection();
        } finally {
            phMpConnection.closeConnection();
        }

        return reports;
    }
}
