package View.MedicViews;

import Controller.MedicControllers.MainPageController;
import Controller.MedicControllers.NewReportController;

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

import java.time.LocalDate;


public class NewReport {
    private NewReportController nrController = new NewReportController();

    public NewReport(Stage primaryStage, TableView patientTable, MainPageController mpController) {
        GridPane root = new GridPane();
        GridPane therapyGrid = new GridPane();
        GridPane reactionGrid = new GridPane();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new report");
        final Label insertLabel = new Label("New report data:");
        final ChoiceBox patientBox = new ChoiceBox(nrController.initIdList());
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
        titleRow.setPercentHeight(100.0 / 8);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints insertLabelRow = new RowConstraints();
        insertLabelRow.setPercentHeight(100.0 / 8);
        insertLabelRow.setValignment(VPos.CENTER);

        RowConstraints insertPatientRow = new RowConstraints();
        insertPatientRow.setPercentHeight(100.0 / 8);
        insertPatientRow.setValignment(VPos.CENTER);

        RowConstraints therapyGridRow = new RowConstraints();
        therapyGridRow.setPercentHeight(100.0 / 8);
        therapyGridRow.setValignment(VPos.CENTER);

        RowConstraints reactionGridRow = new RowConstraints();
        reactionGridRow.setPercentHeight(100.0 / 8);
        reactionGridRow.setValignment(VPos.CENTER);

        RowConstraints reactionDateRow = new RowConstraints();
        reactionDateRow.setPercentHeight(100.0 / 8);
        reactionDateRow.setValignment(VPos.CENTER);

        RowConstraints reportDateRow = new RowConstraints();
        reportDateRow.setPercentHeight(100.0 / 8);
        reportDateRow.setValignment(VPos.CENTER);

        RowConstraints addRow = new RowConstraints();
        addRow.setPercentHeight(100.0 / 8);
        addRow.setValignment(VPos.CENTER);

        //set row constraints for therapy and reaction GridPane
        RowConstraints gridRow = new RowConstraints();
        gridRow.setPercentHeight(100);
        gridRow.setValignment(VPos.CENTER);


        // Sets Dates fields width
        reactionDate.setPrefWidth(1000);
        reportDate.setPrefWidth(1000);

        // Adds nodes to the root GridPane
        root.add(titleLabel, 0, 0);
        root.add(insertLabel, 0, 1);
        root.add(patientBox, 0, 2);
        root.add(therapyGrid, 0, 3);
        root.add(reactionGrid, 0, 4);
        root.add(reactionDate, 0, 5);
        root.add(reportDate, 0, 6);
        root.add(addButton, 0, 7);

        // Adds nodes to the buttons GridPane
        therapyGrid.add(newTherapyButton,0, 0);
        therapyGrid.add(therapyLabel, 1, 0);
        reactionGrid.add(newReactionButton,0, 0);
        reactionGrid.add(reactionLabel, 1, 0);

        // Adds columns and rows to the root GridPane
        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().addAll(titleRow, insertLabelRow, insertPatientRow, therapyGridRow,reactionGridRow,
                                        reactionDateRow, reportDateRow, addRow);

        // Adds columns and rows to the therapy and reaction GridPane
        therapyGrid.getColumnConstraints().addAll(reportColumn1, reportColumn2);
        therapyGrid.getRowConstraints().add(gridRow);
        reactionGrid.getColumnConstraints().addAll(reportColumn1, reportColumn2);
        reactionGrid.getRowConstraints().add(gridRow);

        // Sets defaults values to dates
        reactionDate.setValue(LocalDate.now());
        reportDate.setValue(LocalDate.now());

        // Sets addButton as default button
        addButton.setDefaultButton(true);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Report");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        newTherapyButton.setOnAction(e -> {
            NewTherapy newTherapy = new NewTherapy(new Stage(), therapyLabel);
        });

        newReactionButton.setOnAction(e -> {
            NewReaction newReaction = new NewReaction(new Stage(), reactionLabel);
        });

        // If clicked adds the a new Report for the selected patient to the DB
        addButton.setOnAction(e -> {
            if(patientBox.getValue() == null || reactionDate.getValue() == null || reportDate.getValue() == null) {
                Alert newPatientAlert = new Alert(Alert.AlertType.WARNING);

                newPatientAlert.setTitle("No Data");
                newPatientAlert.setHeaderText("No data into fields!");
                newPatientAlert.setContentText("Please, insert some values into the fields.");

                newPatientAlert.showAndWait();
            } else {
                nrController.addNewReport(
                        patientBox.getSelectionModel().getSelectedItem().toString(), reactionLabel.getText(),
                        reportDate.getValue().toString(), reactionDate.getValue().toString(), therapyLabel.getText()
                );
                patientTable.setItems(mpController.initPatientsList());
                patientTable.getSelectionModel().selectLast();

                if(mpController.getReportNumber() >= 50) {
                    Alert reportOverflowAlert = new Alert(Alert.AlertType.WARNING);

                    reportOverflowAlert.setTitle("Report Overflow");
                    reportOverflowAlert.setHeaderText("50 reports limit reached!");
                    reportOverflowAlert.setContentText("A warning has been sent to the pharmacologist");

                    reportOverflowAlert.showAndWait();
                }

                primaryStage.close();
            }
        });
    }
}

