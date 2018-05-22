package Controller;

import Model.*;
import javafx.event.ActionEvent;
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

    @FXML
    public Button loginButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void handleLogin(ActionEvent actionEvent) throws SQLException {
        if(conn.login(username.getText(), password.getText())){
            System.out.println("Login succesful!");
        }
    }
}
