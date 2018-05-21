/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

import View.Login.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    /**
     * Metodo main
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodo start
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Login login = new Login(primaryStage);
    }
}