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
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/home/andrea/Documenti/Sorgenti/Programmazione II/drug-supervision/src/DB/DrugDB.db");

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
        rs = statement.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + password + "' AND logged = 0;");

        closeConnection();

        int count = 0;
        do{
            count++;
        }while(rs.next());

        if(count > 0){
            logged(username);
            System.out.println(username + " ha effettuato l'accesso");
            return true;
        }
        System.out.println("Username non presente nel database");
        return false;
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
     * Metodo logged
     *
     * @param username
     * @throws SQLException
     */
    protected void logged(String username) throws SQLException {
        openConnection();
        statement = connection.createStatement();
        statement.executeUpdate("UPDATE Users SET logged = 1 WHERE username = '" + username + "';");
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
        statement.executeUpdate("UPDATE Users SET logged = 0 WHERE username = '" + username + "';");
        closeConnection();
    }


}