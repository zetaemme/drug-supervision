package View.Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    private Stage primaryStage;
    private AnchorPane rootLayout;

    public Login(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Drug Supervision - Login");

        showOverview();
    }

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

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
