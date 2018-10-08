package View;

import Controller.NewPatientController;

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

public class NewPatient {
    public NewPatient(Stage primaryStage) {
        GridPane root = new GridPane();
        GridPane riskFactorGrid = new GridPane();

        NewPatientController npController = new NewPatientController();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Add new patient");
        final Label insertLabel = new Label("New patient data:");
        final DatePicker birthdayField = new DatePicker();
        final TextField provinceField = new TextField();
        final TextField professionField = new TextField();
        final ChoiceBox riskFactorBox = new ChoiceBox(npController.initRiskFactorList());
        final Button addButton = new Button("Add");
        final Button riskButton = new Button("New");

        riskFactorBox.setPrefWidth(1000);
        riskFactorBox.getSelectionModel().selectFirst();

        // Sets tileLabel font
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setId("titleLabel");

        // Sets prompt texts
        birthdayField.setPromptText("Birthday");
        provinceField.setPromptText("Province");
        professionField.setPromptText("Profession");

        // Sets the RiskFactor GridPane columns
        ColumnConstraints riskColumn1 = new ColumnConstraints();
        riskColumn1.setPercentWidth(70);
        riskColumn1.setHalignment(HPos.CENTER);

        ColumnConstraints riskColumn2 = new ColumnConstraints();
        riskColumn2.setPercentWidth(30);
        riskColumn2.setHalignment(HPos.CENTER);

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

        RowConstraints bDayRow = new RowConstraints();
        bDayRow.setPercentHeight(100.0 / 7);
        bDayRow.setValignment(VPos.CENTER);

        RowConstraints provinceFieldRow = new RowConstraints();
        provinceFieldRow.setPercentHeight(100.0 / 7);
        provinceFieldRow.setValignment(VPos.CENTER);

        RowConstraints professionFieldRow = new RowConstraints();
        professionFieldRow.setPercentHeight(100.0 / 7);
        professionFieldRow.setValignment(VPos.CENTER);

        RowConstraints riskFactorFieldRow = new RowConstraints();
        riskFactorFieldRow.setPercentHeight(100.0 / 7);
        riskFactorFieldRow.setValignment(VPos.CENTER);

        RowConstraints addRow = new RowConstraints();
        addRow.setPercentHeight(100.0 / 7);
        addRow.setValignment(VPos.CENTER);

        // Sets the RiskFactor GridPane row
        RowConstraints riskFactorRow = new RowConstraints();
        riskFactorRow.setPercentHeight(100);
        riskFactorRow.setValignment(VPos.CENTER);

        // Sets BirthdayField width
        birthdayField.setPrefWidth(1000);

        root.add(titleLabel, 0, 0);
        root.add(insertLabel, 0, 1);
        root.add(birthdayField, 0, 2);
        root.add(provinceField, 0, 3);
        root.add(professionField, 0, 4);
        root.add(riskFactorGrid, 0, 5);
        root.add(addButton, 0, 6);

        riskFactorGrid.add(riskFactorBox,0, 0);
        riskFactorGrid.add(riskButton,1, 0);

        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().addAll(titleRow, insertLabelRow, bDayRow, provinceFieldRow, professionFieldRow, riskFactorFieldRow, addRow);

        riskFactorGrid.getColumnConstraints().addAll(riskColumn1, riskColumn2);
        riskFactorGrid.getRowConstraints().add(riskFactorRow);


        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Patient");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

        addButton.setOnAction(e -> {

            primaryStage.close();
        });

        riskButton.setOnAction(e -> {
            NewRiskFactor newRiskFactor = new NewRiskFactor(new Stage());
        });
    }
}
