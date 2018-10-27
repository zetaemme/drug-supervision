package Model.Utils.DaoImpl;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;
import Model.Reaction;
import Model.Utils.DAOs.ReactionDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReactionDaoImpl implements ReactionDao {
    private DBConnection raConnection;

    @Override
    public List<Reaction> getAllReactions() {
        raConnection = new DBConnection();
        raConnection.openConnection();

        List<Reaction> reactions = new ArrayList<>();

        try {
            raConnection.statement = raConnection.connection.createStatement();
            raConnection.rs = raConnection.statement.executeQuery("SELECT * FROM Reaction");

            while(raConnection.rs.next()) {
                reactions.add(new Reaction(
                        raConnection.rs.getInt("risk"),
                        raConnection.rs.getString("description")
                ));
            }
        } catch(SQLException sqle) {
            System.out.println("SQL Error: " + sqle.getMessage());
        } catch (IllegalRiskValueException irve) {
            System.out.println("Risk Error: " + irve.getMessage());
        } catch (NullStringException nse) {
            System.out.println("String Error: " + nse.getMessage());
        } finally {
            raConnection.closeConnection();
        }

        return reactions;
    }

    @Override
    public void createReaction(String Reaction_name, int risk, String description) {
        raConnection = new DBConnection();
        raConnection.openConnection();

        try {
            raConnection.statement = raConnection.connection.createStatement();
            raConnection.statement.executeUpdate(
                    "INSERT INTO Reaction(Reaction_name, risk, reactionDescription) " +
                        "VALUES ('" + Reaction_name +"', '" + risk +"', '" + description + "')"
            );
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
        } finally {
            raConnection.closeConnection();
        }
    }
}
