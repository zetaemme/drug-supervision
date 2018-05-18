package View.AddPatient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPatient {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public AddPatient(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Drug Supervision - Add Patient");

        initRootLayout();
        showOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddPatient.class.getResource("rootLayout.fxml"));
            rootLayout = loader.load();

            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddPatient.class.getResource("Overview.fxml"));
            AnchorPane overview = loader.load();

            rootLayout.setCenter(overview);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
