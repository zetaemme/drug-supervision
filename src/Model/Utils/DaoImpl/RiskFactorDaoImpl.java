package Model.Utils.DaoImpl;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.RiskFactor;
import Model.Utils.DAOs.RiskFactorDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RiskFactorDaoImpl implements RiskFactorDao {
    private DBConnection rfConnection;

    @Override
    public List<RiskFactor> getAllRiskFactors() {
        rfConnection = new DBConnection();
        rfConnection.openConnection();

        List<RiskFactor> riskFactors = new ArrayList<>();

        try {
            rfConnection.statement = rfConnection.connection.createStatement();
            rfConnection.rs = rfConnection.statement.executeQuery("SELECT * FROM RiskFactor");

            while(rfConnection.rs.next()) {
                riskFactors.add(new RiskFactor(
                        rfConnection.rs.getString("description"),
                        rfConnection.rs.getInt("riskLevel"))
                );
            }
        } catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Factor Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        } finally {
            rfConnection.closeConnection();
        }

        return riskFactors;
    }

    @Override
    public void createRiskFactor(String description, int riskLevel) {
        rfConnection = new DBConnection();
        rfConnection.openConnection();

        try {
            rfConnection.statement = rfConnection.connection.createStatement();
            rfConnection.statement.executeUpdate(
                    "INSERT INTO RiskFactor (description, riskLevel) VALUES ('" + description + "','" + riskLevel + "')"
            );
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            rfConnection.closeConnection();
        }
    }
}
