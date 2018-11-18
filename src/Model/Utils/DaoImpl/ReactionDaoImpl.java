package Model.Utils.DaoImpl;

import Model.Utils.DAOs.ReactionDao;
import Model.Utils.DBConnection;

import java.sql.SQLException;

public class ReactionDaoImpl implements ReactionDao {
    private DBConnection raConnection;

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
