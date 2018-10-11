package View;

import Controller.NewReportController;

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
    private NewReportController nrController = new NewReportController();

    public NewReport(Stage primaryStage) {
        GridPane root = new GridPane();
        GridPane ThReGrid = new GridPane();

        ObservableList<String> patientIds = nrController.initIdList();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new report");
        final Label insertLabel = new Label("New report data:");
        final ChoiceBox patientBox = new ChoiceBox(patientIds);
        final Button newTherapyButton = new Button("Add Therapy");
        final Button newReactionButton = new Button("Add Reaction");
        final Label therapyLabel = new Label();
        final Label reactionLabel = new Label();
        final DatePicker reactionDate = new DatePicker();
        final DatePicker reportDate = new DatePicker();
        final Button addButton = new Button("Add");

        // Size preferences for the buttons
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
        titleRow.setPercentHeight(100.0 / 7);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints insertLabelRow = new RowConstraints();
        insertLabelRow.setPercentHeight(100.0 / 7);
        insertLabelRow.setValignment(VPos.BOTTOM);

        RowConstraints reactionFieldRow = new RowConstraints();
        reactionFieldRow.setPercentHeight(100.0 / 7);
        reactionFieldRow.setValignment(VPos.CENTER);

        RowConstraints reactionDateRow = new RowConstraints();
        reactionDateRow.setPercentHeight(100.0 / 7);
        reactionDateRow.setValignment(VPos.CENTER);

        RowConstraints reportDateRow = new RowConstraints();
        reportDateRow.setPercentHeight(100.0 / 7);
        reportDateRow.setValignment(VPos.CENTER);

        RowConstraints addRow = new RowConstraints();
        addRow.setPercentHeight(100.0 / 7);
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

        // Adds nodes to the root GridPane
        root.add(titleLabel, 0, 0);
        root.add(insertLabel, 0, 1);
        root.add(patientBox, 0, 2);
        root.add(ThReGrid, 0, 3);
        root.add(reactionDate, 0, 4);
        root.add(reportDate, 0, 5);
        root.add(addButton, 0, 6);

        // Adds nodes to the buttons GridPane
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
        addButton.setOnAction(e -> {
            if(patientBox.getValue() == null || reactionDate.getValue() == null || reportDate.getValue() == null) {
                Alert newPatientAlert = new Alert(Alert.AlertType.WARNING);

                newPatientAlert.setTitle("No Data");
                newPatientAlert.setHeaderText("No data into fields!");
                newPatientAlert.setContentText("Please, insert some values into the fields.");

                newPatientAlert.showAndWait();
            } else {
                // Converts from LocalDate to java.sql.Date


                patientBox.setItems(nrController.initIdList());
                patientBox.getSelectionModel().selectLast();

                primaryStage.close();
            }
        });
    }
}

