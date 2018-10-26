package Model.Utils.DaoImpl;

import Model.Report;
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
                    "SELECT * FROM Report"
            );

            // TODO Finire implementazione e aggiornare model
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            rpConnection.closeConnection();
        }
    }
}
