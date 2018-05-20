package View.History;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class History {
    /**
     * Stage primario della finestra
     */
    private Stage primaryStage;
    /**
     * Barra dei menù
     */
    private BorderPane rootLayout;

    /**
     * Metodo costruttore
     *
     * @param primaryStage
     */
    public History(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Drug Supervision - History");

        initRootLayout();
        showOverview();
    }

    /**
     * Metodo che inizializza la finestra
     */
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

    /**
     * Metodo che visualizza l'overview
     */
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

    /**
     * Metodo getPrimaryStage
     *
     * @return Lo stage primario
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
