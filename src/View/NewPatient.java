package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class NewPatient {
    public NewPatient(Stage primaryStage) {
        VBox root = new VBox();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new patient");

        // Sets tileLabel font
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setId("titleLabel");

        // Set (x, y) position of objects
        titleLabel.setTranslateX(75);


        root.getChildren().add(titleLabel);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Patient");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
