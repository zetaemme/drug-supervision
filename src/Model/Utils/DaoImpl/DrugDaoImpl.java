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
    public List<Drug> getAllDrugs() throws SQLException, NullStringException {
        drugConnection = new DBConnection();
        drugConnection.openConnection();

        List<Drug> drugList = new ArrayList<>();

        drugConnection.statement = drugConnection.connection.createStatement();
        drugConnection.rs = drugConnection.statement.executeQuery(
                "SELECT  * FROM Drug"
        );

        while(drugConnection.rs.next()) {
            drugList.add(new Drug(
                    drugConnection.rs.getString("drugName"),
                    drugConnection.rs.getBoolean("removalSuggestion"),
                    drugConnection.rs.getBoolean("inspectionSuggestion"),
                    drugConnection.rs.getBoolean("closeMonitorSuggestion")
            ));
        }

        return drugList;
    }
}
