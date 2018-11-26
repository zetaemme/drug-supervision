package View.MedicViews;

import Controller.MedicControllers.NewTherapyController;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NewTherapy {
    private NewTherapyController ntController = new NewTherapyController();

    public NewTherapy(Stage primaryStage , Label therapyLabel) {
        GridPane root = new GridPane();

        // Options for the GridPane
        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Insert new Therapy");
        final ChoiceBox<String> drugChoiceBox = new ChoiceBox(ntController.initDrugList());
        final Button addDrugButton = new Button("New");
        final TextField doseField = new TextField();
        final DatePicker startingDate = new DatePicker();
        final DatePicker endingDate = new DatePicker();
        final TextField dailyFrequencyField = new TextField();
        final Button addButton = new Button("Add");

        drugChoiceBox.setPrefWidth(500);
        drugChoiceBox.getSelectionModel().selectFirst();

        // Prompt texts for the TextFields
        doseField.setPromptText("Drug dose (mg/ml)");
        startingDate.setPromptText("Therapy starting date");
        endingDate.setPromptText("Therapy ending date");
        dailyFrequencyField.setPromptText("Daily frequency");

        startingDate.setPrefWidth(400);
        endingDate.setPrefWidth(400);

        // Sets titleLabel Id and Font
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        titleLabel.setId("titleLabel");

        GridPane drugGridPane = new GridPane();

        // drugGridPane columns
        ColumnConstraints choiceBoxColumn = new ColumnConstraints();
        choiceBoxColumn.setPercentWidth(70);
        choiceBoxColumn.setHalignment(HPos.CENTER);

        ColumnConstraints addDrugColumn = new ColumnConstraints();
        addDrugColumn.setPercentWidth(30);
        addDrugColumn.setHalignment(HPos.CENTER);

        // drugGridPane row
        RowConstraints drugGridPaneRow = new RowConstraints();
        drugGridPaneRow.setPercentHeight(100);
        drugGridPaneRow.setValignment(VPos.CENTER);

        // Adds all nodes to drugGridPane
        drugGridPane.add(drugChoiceBox, 0, 0);
        drugGridPane.add(addDrugButton, 1, 0);

        drugGridPane.getColumnConstraints().addAll(choiceBoxColumn, addDrugColumn);
        drugGridPane.getRowConstraints().add(drugGridPaneRow);

        // GridPane's column
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        mainColumn.setHalignment(HPos.CENTER);

        // GridPane's rows
        RowConstraints titleRow = new RowConstraints();
        titleRow.setPercentHeight(100.0 / 7);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints drugRow = new RowConstraints();
        drugRow.setPercentHeight(100.0 / 7);
        drugRow.setValignment(VPos.CENTER);

        RowConstraints doseRow = new RowConstraints();
        doseRow.setPercentHeight(100.0 / 7);
        doseRow.setValignment(VPos.CENTER);

        RowConstraints startingDateRow = new RowConstraints();
        startingDateRow.setPercentHeight(100.0 / 7);
        startingDateRow.setValignment(VPos.CENTER);

        RowConstraints endingDateRow = new RowConstraints();
        endingDateRow.setPercentHeight(100.0 / 7);
        endingDateRow.setValignment(VPos.CENTER);

        RowConstraints dailyFrequencyRow = new RowConstraints();
        dailyFrequencyRow.setPercentHeight(100.0 / 7);
        dailyFrequencyRow.setValignment(VPos.CENTER);

        RowConstraints addRow = new RowConstraints();
        addRow.setPercentHeight(100.0 / 7);
        addRow.setValignment(VPos.CENTER);

        // Add all nodes to the GridPane
        root.add(titleLabel, 0 ,0);
        root.add(drugGridPane, 0,1);
        root.add(doseField, 0 ,2);
        root.add(startingDate, 0,3);
        root.add(endingDate, 0,4 );
        root.add(dailyFrequencyField, 0,5);
        root.add(addButton, 0, 6);

        // Sets addButton as default button
        addButton.setDefaultButton(true);

        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().addAll(titleRow, drugRow, doseRow, startingDateRow, endingDateRow, dailyFrequencyRow);

        // Sets default starting date value
        startingDate.setValue(LocalDate.now());

        // Creates scene and add a stylesheet
        Scene scene = new Scene(root, 400, 350);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Therapy");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        // If clicked adds the therapy into the DB
        addButton.setOnAction(e -> {
            if(drugChoiceBox.getSelectionModel().isEmpty() || doseField.getText().equals("") || startingDate.getValue() == null
                || endingDate.getValue() == null || dailyFrequencyField.getText().equals("")) {
                Alert ntAlert = new Alert(Alert.AlertType.WARNING);

                ntAlert.setTitle("No Data");
                ntAlert.setHeaderText("No therapy data!");
                ntAlert.setContentText("Please, fill all fields to add a new therapy.");

                ntAlert.showAndWait();
            } else {
                therapyLabel.setText(ntController.addNewTherapy(
                        drugChoiceBox.getSelectionModel().getSelectedItem(), Integer.valueOf(doseField.getText()), startingDate.getValue().toString(),
                        endingDate.getValue().toString(), Integer.valueOf(dailyFrequencyField.getText()))
            );

                primaryStage.close();
            }
        });

        addDrugButton.setOnAction(e -> {
            NewDrug newDrug = new NewDrug(new Stage(), drugChoiceBox, ntController);
        });
    }
}

