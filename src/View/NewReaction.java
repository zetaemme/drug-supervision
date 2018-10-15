package View;

import Controller.NewReactionController;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NewReaction {
    private NewReactionController nreController = new NewReactionController();

    public NewReaction(Stage primaryStage, Label reactionLabel) {
        GridPane root = new GridPane();

        // Default size for root GridPane
        root.setPrefSize(300, 200);
        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new reaction");
        final Label addLabel = new Label("New data:");
        final TextField riskField = new TextField();
        final TextField descriptionField = new TextField();
        final Button addButton = new Button("Add");

        // titleLabel options
        titleLabel.setId("titleLabel");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // Prompt texts for TextFields
        riskField.setPromptText("Risk value");
        descriptionField.setPromptText("Description");

        // GridPane's column
        ColumnConstraints column = new ColumnConstraints();
        column.setHalignment(HPos.CENTER);
        column.setPercentWidth(100);

        // GridPane's rows
        RowConstraints titleRow = new RowConstraints();
        titleRow.setValignment(VPos.CENTER);
        titleRow.setPercentHeight(100.0 / 5);

        RowConstraints addLabelRow = new RowConstraints();
        addLabelRow.setValignment(VPos.CENTER);
        addLabelRow.setPercentHeight(100.0 / 5);

        RowConstraints riskRow = new RowConstraints();
        riskRow.setValignment(VPos.CENTER);
        riskRow.setPercentHeight(100.0 / 5);

        RowConstraints descriptionRow = new RowConstraints();
        descriptionRow.setValignment(VPos.CENTER);
        descriptionRow.setPercentHeight(100.0 / 5);

        RowConstraints addButtonRow = new RowConstraints();
        addButtonRow.setValignment(VPos.CENTER);
        addButtonRow.setPercentHeight(100.0 / 5);

        // Adds rows and columns to root GridPane
        root.getColumnConstraints().add(column);
        root.getRowConstraints().addAll(titleRow, addLabelRow, riskRow, descriptionRow, addButtonRow);

        // Adds all nodes to GridPane
        root.add(titleLabel, 0, 0);
        root.add(addLabel, 0, 1);
        root.add(riskField, 0, 2);
        root.add(descriptionField, 0, 3);
        root.add(addButton, 0, 4);

        // Sets addButton as default button
        addButton.setDefaultButton(true);

        // Creates scene and sets stylesheet
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - New Reaction");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        addButton.setOnAction(e -> {
            if(riskField.getText().equals("") || descriptionField.getText().equals("")) {
                Alert nreAlert = new Alert(Alert.AlertType.WARNING);

                nreAlert.setTitle("No Data");
                nreAlert.setHeaderText("No reaction data!");
                nreAlert.setContentText("Please, fill all fields to add a new reaction.");

                nreAlert.showAndWait();
            } else {
                reactionLabel.setText(nreController.addNewReaction(Integer.valueOf(riskField.getText()), descriptionField.getText()));
                primaryStage.close();
            }
        });
    }
}
