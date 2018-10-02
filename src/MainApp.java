import View.Login;
import View.Login_test;
import View.MainPage;
import View.NewPatient;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        /*Login login = new Login(primaryStage);*/
        Login login = new Login(primaryStage);
    }
}