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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class NewTherapy {

    public NewTherapy(Stage primaryStage) {
        GridPane root = new GridPane();

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("Insert new Therapy");
        final TextField drugTextField = new TextField();
        final TextField doseTextField = new TextField();
        final DatePicker startingDate = new DatePicker();
        final DatePicker endingDate = new DatePicker();
        final TextField dailyFrequencyTextField = new TextField();
        final Button addButton = new Button("Add");

        drugTextField.setPromptText("Drug name");
        doseTextField.setPromptText("Drug dose");
        startingDate.setPromptText("Therapy starting date");
        endingDate.setPromptText("Therapy ending date");
        dailyFrequencyTextField.setPromptText("Daily frequency");

        startingDate.setPrefWidth(400);
        endingDate.setPrefWidth(400);

        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setId("titleLabel");

        //set colums constraints for root grid pane
        ColumnConstraints mainColumn = new ColumnConstraints();
        mainColumn.setPercentWidth(100);
        mainColumn.setHalignment(HPos.CENTER);

        //set row constraints for root grid pane
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

        root.add(titleLabel, 0 ,0);
        root.add(drugTextField, 0,1);
        root.add(doseTextField, 0 ,2);
        root.add(startingDate, 0,3);
        root.add(endingDate, 0,4 );
        root.add(dailyFrequencyTextField, 0,5);
        root.add(addButton, 0, 6);

        root.getColumnConstraints().add(mainColumn);
        root.getRowConstraints().addAll(titleRow, drugRow, doseRow, startingDateRow, endingDateRow, dailyFrequencyRow);

        Scene scene = new Scene(root, 400, 350);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Add Therapy");
        primaryStage.setScene(scene);
        // Better be not resizable
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
