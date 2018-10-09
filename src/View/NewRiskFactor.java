package View;

import Controller.NewRiskFactorController;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NewRiskFactor {
    private NewRiskFactorController riskFactorController = new NewRiskFactorController();

    public NewRiskFactor(Stage primaryStage) {
        GridPane root = new GridPane();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new Risk Factor");
        final Label insertLabel = new Label("New data:");
        final TextField riskLevelField = new TextField();
        final TextField descriptionField = new TextField();
        final Button addButton = new Button("Add");

        // Sets tileLabel font
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setId("titleLabel");

        // Sets prompt texts
        riskLevelField.setPromptText("Risk level");
        descriptionField.setPromptText("Risk description");

        // Sets the Pane column
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);
        column.setHalignment(HPos.CENTER);

        // Sets the Pane rows
        RowConstraints titleRow = new RowConstraints();
        titleRow.setPercentHeight(100.0 / 5);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints insertLabelRow = new RowConstraints();
        insertLabelRow.setPercentHeight(100.0 / 5);
        insertLabelRow.setValignment(VPos.BOTTOM);

        RowConstraints riskLevelRow = new RowConstraints();
        riskLevelRow.setPercentHeight(100.0 / 5);
        riskLevelRow.setValignment(VPos.CENTER);

        RowConstraints descriptionRow = new RowConstraints();
        descriptionRow.setPercentHeight(100.0 / 5);
        descriptionRow.setValignment(VPos.CENTER);

        RowConstraints buttonRow = new RowConstraints();
        buttonRow.setPercentHeight(100.0 / 5);
        buttonRow.setValignment(VPos.CENTER);


        root.add(titleLabel, 0, 0);
        root.add(insertLabel, 0, 1);
        root.add(riskLevelField, 0, 2);
        root.add(descriptionField, 0 , 3);
        root.add(addButton, 0, 4);

        // Adds column and rows to the root GridPane
        root.getColumnConstraints().add(column);
        root.getRowConstraints().addAll(titleRow, insertLabelRow, riskLevelRow, descriptionRow, buttonRow);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Risk Factor");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        // If clicked adds a new RiskFactor to the DB
        addButton.setOnAction(e -> {
            riskFactorController.addRiskFactor(Integer.valueOf(riskLevelField.getText()), descriptionField.getText());
            primaryStage.close();
        });
    }
}
