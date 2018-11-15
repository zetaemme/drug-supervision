package Model.Utils.DaoImpl;

import Model.Drug;
import Model.Exceptions.NullStringException;
import Model.Utils.DAOs.DrugDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDaoImpl implements DrugDao {
    DBConnection drugConnection;

    @Override
    public List<Drug> getAllDrugs() {
        drugConnection = new DBConnection();
        drugConnection.openConnection();

        List<Drug> drugList = new ArrayList<>();

        try {
            drugConnection.statement = drugConnection.connection.createStatement();
            drugConnection.rs = drugConnection.statement.executeQuery(
                    "SELECT  * FROM Drug"
            );

            while (drugConnection.rs.next()) {
                drugList.add(new Drug(
                        drugConnection.rs.getString("drugName"),
                        drugConnection.rs.getBoolean("removalSuggestion"),
                        drugConnection.rs.getBoolean("inspectionSuggestion"),
                        drugConnection.rs.getBoolean("closeMonitorSuggestion")
                ));
            }

            drugConnection.closeConnection();

        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        }
        finally {
            drugConnection.closeConnection();
        }

        return drugList;
    }

    @Override
    public void updateRemoval(String drugName) {
        drugConnection = new DBConnection();
        drugConnection.openConnection();

        try {
            drugConnection.statement = drugConnection.connection.createStatement();
            drugConnection.statement.executeQuery(
                    "UPDATE Drug SET removalSuggestion = true WHERE drugName = '" + drugName + "'"
            );
        }catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } finally {
            drugConnection.closeConnection();
        }
    }

    @Override
    public void updateInspection(String drugName) {
        drugConnection = new DBConnection();
        drugConnection.openConnection();

        try {
            drugConnection.statement = drugConnection.connection.createStatement();
            drugConnection.statement.executeQuery(
                    "UPDATE Drug SET inspectionSuggestion = true WHERE drugName = '" + drugName + "'"
            );
        }catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } finally {
            drugConnection.closeConnection();
        }
    }

    @Override
    public void updateCloseMonitor(String drugName) {
        drugConnection = new DBConnection();
        drugConnection.openConnection();

        try {
            drugConnection.statement = drugConnection.connection.createStatement();
            drugConnection.statement.executeQuery(
                    "UPDATE Drug SET closeMonitorSuggestion = true WHERE drugName = '" + drugName + "'"
            );
        }catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } finally {
            drugConnection.closeConnection();
        }
    }
}
