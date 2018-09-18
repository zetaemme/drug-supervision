package View;

import Controller.MainPageController;
import Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainPage {
    private ObservableList<Patient> patientsList = FXCollections.observableArrayList();

    public MainPage(Stage primaryStage) {
        final MainPageController mpController = new MainPageController();

        SplitPane root = new SplitPane();

        // Sets root as resizable
        root.isResizable();

        final VBox sp1 = new VBox();
        final VBox sp2 = new VBox();

        // Set both the panes as resizable
        sp1.isResizable();
        sp2.isResizable();

        sp2.setPadding(new Insets(10));

        final Label titleLabel = new Label("Patient Info");
        final Label nameLabel = new Label("Name:");

        final Label patientName = new Label("");

        // Settings for the SplitPane
        sp1.setMinSize(300, 600);
        sp1.setMaxSize(300, 600);

        patientsList = mpController.initPatientsList();

        TableView patientTable = new TableView();

        TableColumn<Patient, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<Patient, String> lastNameColumn = new TableColumn<>("Last Name");

        // Initialize the table columns values
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

        // Add teh columns to the table
        patientTable.getColumns().setAll(firstNameColumn, lastNameColumn);

        // Set table min/max size
        patientTable.setMinSize(300, 600);
        patientTable.setMaxSize(300, 600);

        firstNameColumn.setMinWidth(150);
        firstNameColumn.setMaxWidth(150);

        lastNameColumn.setMinWidth(150);
        lastNameColumn.setMaxWidth(150);

        // TODO Test ancora da eseguire, c'è già in MainPageController
        patientTable.setItems(patientsList);

        // Set title font and style
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
        titleLabel.setStyle("} {-fx-text-fill: #696969} {");

        // Set objects (x, y) position
        titleLabel.setTranslateX(220);
        titleLabel.setTranslateY(5);

        nameLabel.setTranslateX(50);
        nameLabel.setTranslateY(70);

        patientName.setTranslateX(100);
        patientName.setTranslateY(53.3);

        // Add objects to panes
        sp1.getChildren().add(patientTable);
        sp2.getChildren().addAll(titleLabel, nameLabel, patientName);

        // Add panes to SplitPane
        root.getItems().addAll(sp1, sp2);

        primaryStage.setTitle("Drug Supervision");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }
}
