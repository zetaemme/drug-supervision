package View.History;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class History {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public History(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Drug Supervision - History");

        initRootLayout();
        showOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(History.class.getResource("rootLayout.fxml"));
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
            loader.setLocation(History.class.getResource("Overview.fxml"));
            AnchorPane overview = loader.load();

            rootLayout.setCenter(overview);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
