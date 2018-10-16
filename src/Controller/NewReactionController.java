package Controller;

import Model.Utils.DBConnection;

import java.sql.SQLException;

public class NewReactionController {
    private DBConnection nreConnection;

    // Adds a new reaction with the corresponding data
    public String addNewReaction(int risk, String description) {
        nreConnection = new DBConnection();
        nreConnection.openConnection();

        String idRaction = risk + description.substring(0, 5).toUpperCase().replace(" ", "");

        try {
            nreConnection.statement = nreConnection.connection.createStatement();
            nreConnection.statement.executeUpdate("INSERT INTO Reaction(Reaction_name, risk, desctription) " +
                                                    "VALUES ('" + idRaction +"', '" + risk +"', '" + description + "')");
        } catch(SQLException sqle) {
            System.out.println("Error: " + sqle.getMessage());
            nreConnection.closeConnection();
        } finally {
            nreConnection.closeConnection();
        }

        return idRaction;
    }
}
