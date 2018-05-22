package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

public class LoginOverviewController {

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

    /**
     * Metodo costruttore nullo
     */
    public LoginOverviewController() {
    }

    /**
     * Metodo per inizializzare l'interfaccia ddi login
     */
    @FXML
    private void initialize() {
        username.setOnAction(handleUsername -> handleUsername.);
    }
}
