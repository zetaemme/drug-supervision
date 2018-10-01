package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class NewPatient {
    public NewPatient(Stage primaryStage) {
        VBox root = new VBox();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new patient");
        final Label insertLabel = new Label("New patient data:");
        final TextField idField = new TextField();
        final DatePicker birthdayField = new DatePicker();
        final TextField provinceField = new TextField();
        final TextField professionField = new TextField();
        final TextField riskFactorField = new TextField();
        final Button addButton = new Button("Add");

        // Sets tileLabel font
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setId("titleLabel");

        // Sets prompt texts
        idField.setPromptText("Patient ID");
        birthdayField.setPromptText("Birthday");
        provinceField.setPromptText("Province");
        professionField.setPromptText("Profession");
        riskFactorField.setPromptText("Risk Factor");

        // Sets addButton as default button
        addButton.isDefaultButton();

        // Set (x, y) position of objects
        titleLabel.setTranslateX(75);

        insertLabel.setTranslateX(5);
        insertLabel.setTranslateY(45);

        idField.setTranslateY(50);

        birthdayField.setTranslateY(70);

        provinceField.setTranslateY(90);

        professionField.setTranslateY(110);

        riskFactorField.setTranslateY(130);

        addButton.setTranslateX(290);
        addButton.setTranslateY(155);

        // Size preferences for the objects
        addButton.setPrefSize(70, 10);

        birthdayField.setPrefWidth(380);

        root.getChildren().addAll(titleLabel, insertLabel, idField, birthdayField, provinceField, professionField, riskFactorField, addButton);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Patient");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        addButton.setOnAction(e -> {
            // TODO Implementare INSERT (SQL)
            primaryStage.close();
        });
    }
}
