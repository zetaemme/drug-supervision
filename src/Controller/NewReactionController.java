package Controller;

import Model.Utils.DBConnection;

import java.sql.SQLException;

public class NewReactionController {
    private DBConnection nreConnection;

    public void addNewReaction(int risk, String description) {
        nreConnection = new DBConnection();
        nreConnection.openConnection();

        try {
            nreConnection.statement = nreConnection.connection.createStatement();
            nreConnection.statement.executeUpdate("INSERT INTO Reaction(risk, desctription) VALUES ('" + risk +"', '"
                                                    + description + "')");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            nreConnection.closeConnection();
        } finally {
            nreConnection.closeConnection();
        }
    }
}
