package View.Login;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    /**
     * Stage primario della finestra
     */
    private Stage primaryStage;
    /**
     * Barra dei menù
     */
    private AnchorPane rootLayout;

    /**
     * Metodo costruttore
     *
     * @param primaryStage
     */
    public Login(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Drug Supervision - Login");

        showOverview();
    }

    /**
     * Metodo che visualizza l'overview
     */
    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource("Overview.fxml"));
            AnchorPane overwiew = loader.load();

            primaryStage.setScene(new Scene(overwiew));
            primaryStage.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo getPrimaryStage
     *
     * @return Lo stage primario
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
