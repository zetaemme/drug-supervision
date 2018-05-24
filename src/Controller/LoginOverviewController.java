package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.SQLException;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class LoginOverviewController {
    DBConnection conn = new DBConnection();

    /**
    * Bottone
    */
    @FXML
    public Button loginButton;
    /**
     * Campo di testo
     */
    @FXML
    private TextField username;
    /**
     * Campo di testo tipo password
     */
    @FXML
    private PasswordField password;

    public void handleLogin(ActionEvent actionEvent) throws SQLException {
        if(conn.login(username.getText(), password.getText())){
            System.out.println("Login succesful!");
        }
    }
}
