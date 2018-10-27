package Model.Utils.DaoImpl;

import Model.Exceptions.NullDailyFrequencyException;
import Model.Exceptions.NullDoseException;
import Model.Exceptions.NullStringException;
import Model.Therapy;
import Model.Utils.DAOs.TherapyDao;
import Model.Utils.DBConnection;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyDaoImpl implements TherapyDao {
    private DBConnection thConnection;

    @Override
    public List<Therapy> getAllTherapies() {
        thConnection = new DBConnection();
        thConnection.openConnection();

        List<Therapy> therapies = new ArrayList<>();

        try {
            thConnection.statement = thConnection.connection.createStatement();
            thConnection.rs = thConnection.statement.executeQuery("SELECT * FROM Therapy");

            while(thConnection.rs.next()) {
                therapies.add(new Therapy(
                            thConnection.rs.getString("drugName_drug"),
                            thConnection.rs.getDouble("dose"),
                            thConnection.rs.getInt("dailyFrequency"),
                            thConnection.rs.getString("startingDate"),
                            thConnection.rs.getString("endingDate")
                        )
                );
            }
        } catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (NullDailyFrequencyException ndfe) {
            System.out.println("Daily Frequency Error: " + ndfe.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        } catch (NullDoseException nde) {
            System.out.println("Dose Error: " + nde.getMessage());
        } finally {
            thConnection.closeConnection();
        }

        return therapies;
    }

    @Override
    public void createTherapy(String idTherapy, String drugName, int dose, String startingDate, String endingDate, int dailyFrequency) {
        thConnection = new DBConnection();
        thConnection.openConnection();

        try {
            thConnection.statement = thConnection.connection.createStatement();
            thConnection.statement.executeUpdate(
                    "INSERT INTO Therapy(idTherapy, drugName_drug, dose, startingDate, endingDate, dailyFrequency) " +
                        "VALUES ('" + idTherapy + "','" + drugName + "', '" + dose + "', '" + startingDate + "', '"
                        + endingDate + "', '" + dailyFrequency + "')"
            );
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            thConnection.closeConnection();
        }
    }
}
