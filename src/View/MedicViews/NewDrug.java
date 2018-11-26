package View.MedicViews;

import Controller.MedicControllers.NewDrugController;
import Controller.MedicControllers.NewTherapyController;
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

public class NewDrug {
    private NewDrugController ndController = new NewDrugController();

    public NewDrug(Stage primaryStage, ChoiceBox drugChoiceBox, NewTherapyController ntController) {
        final Label titleLabel = new Label("Add new drug");
        final TextField drugNameField = new TextField();
        final Button addButton = new Button("Add");

        // titleLabel options
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setId("titleLabel");

        // Sets the prompt text for drugNameFiled
        drugNameField.setPromptText("Drug Name");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));

        // root GridPane's rows and column
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        mainColumn.setHalignment(HPos.CENTER);

        RowConstraints titleRow = new RowConstraints();
        titleRow.setPercentHeight(100.0/3);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints textFieldRow = new RowConstraints();
        textFieldRow.setPercentHeight(100.0/3);
        textFieldRow.setValignment(VPos.CENTER);

        RowConstraints buttonRow = new RowConstraints();
        buttonRow.setPercentHeight(100.0/3);
        buttonRow.setValignment(VPos.CENTER);

        // Adds all to root
        root.add(titleLabel, 0, 0);
        root.add(drugNameField, 0, 1);
        root.add(addButton, 0, 2);

        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().addAll(titleRow, textFieldRow, buttonRow);

        Scene scene = new Scene(root, 400, 220);
        scene.getStylesheets().add("CSS/style.css");

        addButton.setDefaultButton(true);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Drug Supervision - New Drug");
        primaryStage.setResizable(false);
        primaryStage.show();

        addButton.setOnAction(e -> {
            if(drugNameField.getText().equals("")) {
                Alert newDrugAlert = new Alert(Alert.AlertType.WARNING);

                newDrugAlert.setTitle("No Data");
                newDrugAlert.setHeaderText("No data into fields!");
                newDrugAlert.setContentText("Please, insert some values into the fields.");

                newDrugAlert.showAndWait();
            } else {
                ndController.addNewDrug(drugNameField.getText());

                drugChoiceBox.setItems(ntController.initDrugList());
                drugChoiceBox.getSelectionModel().selectLast();

                primaryStage.close();
            }
        });
    }
}
