package View;

import Controller.MainPageController;
import Controller.NewPatientController;
import Model.Patient;
import Model.RiskFactor;
import View.NewRiskFactor;
import javafx.collections.ObservableList;
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


public class NewReport {

    public NewReport(Stage primaryStage, MainPageController mpController) {
        GridPane root = new GridPane();
        GridPane ThReGrid = new GridPane();

        ObservableList<Patient> patients = mpController.initPatientsList();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new report");
        final Label insertLabel = new Label("New report data:");
        final ChoiceBox patientBox = new ChoiceBox(patients);
        final Button newTherapyButton = new Button("Add Therapy");
        final Button newReactionButton = new Button("Add Reaction");
        final Label therapyLabel = new Label();
        final Label reactionLabel = new Label();
        final DatePicker reactionDate = new DatePicker();
        final DatePicker reportDate = new DatePicker();
        final Button addButton = new Button("Add");


        newReactionButton.setPrefWidth(300);
        newTherapyButton.setPrefWidth(300);

        // RiskFactor ChoiceBox preferences
        patientBox.setPrefWidth(400);
        patientBox.getSelectionModel().selectFirst();

        // Sets tileLabel font
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setId("titleLabel");

        // Sets prompt texts
        reactionDate.setPromptText("Reaction date");
        reportDate.setPromptText("Report date");

        // Sets the  GridPane columns
        ColumnConstraints reportColumn1 = new ColumnConstraints();
        reportColumn1.setPercentWidth(30);
        reportColumn1.setHalignment(HPos.CENTER);

        ColumnConstraints reportColumn2 = new ColumnConstraints();
        reportColumn2.setPercentWidth(70);
        reportColumn2.setHalignment(HPos.CENTER);

        // Sets the Pane column
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        mainColumn.setHalignment(HPos.CENTER);

        // Sets the Pane rows
        RowConstraints titleRow = new RowConstraints();
        titleRow.setPercentHeight(100.0 / 6);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints insertLabelRow = new RowConstraints();
        insertLabelRow.setPercentHeight(100.0 / 6);
        insertLabelRow.setValignment(VPos.BOTTOM);

        RowConstraints reactionFieldRow = new RowConstraints();
        reactionFieldRow.setPercentHeight(100.0 / 6);
        reactionFieldRow.setValignment(VPos.CENTER);

        RowConstraints reactionDateRow = new RowConstraints();
        reactionDateRow.setPercentHeight(100.0 / 6);
        reactionDateRow.setValignment(VPos.CENTER);

        RowConstraints reportDateRow = new RowConstraints();
        reportDateRow.setPercentHeight(100.0 / 6);
        reportDateRow.setValignment(VPos.CENTER);

        RowConstraints addRow = new RowConstraints();
        addRow.setPercentHeight(100.0 / 6);
        addRow.setValignment(VPos.CENTER);

        // Sets the RiskFactor GridPane row
        RowConstraints therapyRow = new RowConstraints();
        therapyRow.setPercentHeight(100);
        therapyRow.setValignment(VPos.CENTER);

        RowConstraints reactionRow = new RowConstraints();
        reactionRow.setPercentHeight(100);
        reactionRow.setValignment(VPos.CENTER);

        // Sets Dates fields width
        reactionDate.setPrefWidth(1000);
        reportDate.setPrefWidth(1000);

        root.add(titleLabel, 0, 0);
        root.add(insertLabel, 0, 1);
        root.add(patientBox, 0, 2);
        root.add(ThReGrid, 0, 3);
        root.add(reactionDate, 0, 4);
        root.add(reportDate, 0, 5);
        root.add(addButton, 0, 6);

        ThReGrid.add(newTherapyButton,0, 0);
        ThReGrid.add(therapyLabel, 1, 0);
        ThReGrid.add(newReactionButton,0, 1);
        ThReGrid.add(reactionLabel, 1, 1);

        // Adds columns and rows to the root GridPane
        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().addAll(titleRow, insertLabelRow, reactionFieldRow, reactionDateRow, reportDateRow, addRow);

        // Adds columns and rows to the RiskFactor GridPane
        ThReGrid.getColumnConstraints().addAll(reportColumn1, reportColumn2);
        ThReGrid.getRowConstraints().addAll(therapyRow, reactionRow);


        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Patient");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        // If clicked adds the new Patient to the DB
        /*addButton.setOnAction(e -> {
            if(birthdayField.getValue() == null || provinceField.getText().equals("") || professionField.getText().equals("")) {
                Alert newPatientAlert = new Alert(Alert.AlertType.WARNING);

                newPatientAlert.setTitle("No Data");
                newPatientAlert.setHeaderText("No data into fields!");
                newPatientAlert.setContentText("Please, insert some values into the fields.");

                newPatientAlert.showAndWait();
            } else {
                // Converts from LocalDate to java.sql.Date
                java.sql.Date queryBDayDate = java.sql.Date.valueOf(birthdayField.getValue());
                npController.addNewPatient(queryBDayDate, provinceField.getText(), professionField.getText(), username,
                        ((RiskFactor) riskFactorBox.getSelectionModel().getSelectedItem()).getRisk_level(),
                        ((RiskFactor) riskFactorBox.getSelectionModel().getSelectedItem()).getDescription());

                patientTable.setItems(mpController.initPatientsList());
                patientTable.getSelectionModel().selectLast();

                primaryStage.close();
            }
        });

        // If clicked opens a NewRiskFactor window
        riskButton.setOnAction(e -> {
            NewRiskFactor newRiskFactor = new NewRiskFactor(new Stage(), riskFactorBox, npController);
        });*/
    }
}

