/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

import Model.DBConnection;
import View.Login;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Login login = new Login(primaryStage);
    }
}
