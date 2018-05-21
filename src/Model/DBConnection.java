package Model;

import java.sql.*;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class DBConnection {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;

    /**
     * Metodo openConnection
     */
    public void openConnection() {
        try {
            Class.forName("org.sqlite.JBDC");
            connection = DriverManager.getConnection("jdbc:DB:DrugDB.db");
            System.out.println("Successfully Connected!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metodo closeConnection
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }


    /**
     * Metodo getType
     *
     * @param username
     * @return Se l'utente è medico o farmacologo
     * @throws SQLException
     */
    private String getType(String username) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT type FROM Users WHERE username = '" + username + "';");

        closeConnection();

        return String.valueOf(rs);
    }

    /**
     * Metodo getUsername
     *
     * @param username
     * @return Lo username dell'utente
     * @throws SQLException
     */
    private String getUsername(String username) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT username FROM Users WHERE username = '" + username + "';");

        closeConnection();

        return String.valueOf(rs);
    }


    /**
     * Metodo insert
     *
     * @param buffer
     * @param tableName
     * @throws SQLException
     */
    private void insert(String buffer, String tableName) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        statement.executeQuery("INSERT INTO " + tableName + " VALUES " + buffer + ";");

        closeConnection();
    }

    /**
     * Metodo login
     *
     * @param username
     * @param password
     * @return true: Nome utente/password  corretti. false: Nome utente/password  errati.
     * @throws SQLException
     */
    public boolean login(String username, String password) throws SQLException {
        openConnection();

        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' password = '" + password + "' logged = false;");

        closeConnection();

        int count = 0;
        while(rs.next()){
            count += 1;
        }
        logged(username);
        return count != 0;
    }

    /**
     * Metodo logged
     *
     * @param username
     * @throws SQLException
     */
    private void logged(String username) throws SQLException {
        openConnection();
        statement = connection.createStatement();
        statement.executeQuery("UPDATE Users SET logged = true WHERE username = '" + username + "';");
        closeConnection();
    }

    /**
     * Metodo logout
     *
     * @param username
     * @throws SQLException
     */
    private void logout(String username) throws SQLException {
        openConnection();
        statement = connection.createStatement();
        statement.executeQuery("UPDATE Users SET logged = false WHERE username = '" + username + "';");
        closeConnection();
    }


}