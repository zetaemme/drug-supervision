package View.About;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class About {
    /**
     * Stage primario della finestra
     */
    private Stage primaryStage;

    /**
     * Metodo costruttore
     *
     * @param primaryStage
     */
    public About(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Drug Supervision - About");

        showOverview();
    }

    /**
     * Metodo che visualizza l'overview
     */
    public void showOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(About.class.getResource("Overview.fxml"));
            AnchorPane overview = loader.load();

            primaryStage.setScene(new Scene(overview));
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
