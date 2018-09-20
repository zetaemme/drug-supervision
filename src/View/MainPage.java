package View;

import Controller.MainPageController;
import Model.Patient;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainPage {
    private ObservableList<Patient> patientsList;

    public MainPage(Stage primaryStage) {
        final MainPageController mpController = new MainPageController();

        VBox root = new VBox();

        // Root size settings
        root.setMinSize(1000, 600);
        root.setMaxSize(1000, 600);

        MenuBar menuBar = new MenuBar();

        // MenuBar size settings
        menuBar.setMaxSize(1000, 10);

        Menu m1 = new Menu("File");
        Menu m2 = new Menu("Help");

        MenuItem miNew = new MenuItem("New");
        MenuItem miDelete = new MenuItem("Delete");
        MenuItem miAbout = new MenuItem("About");

        // If clicked opens a new about window
        miAbout.setOnAction(e -> {
            About about = new About(new Stage());
        });

        // Adds the MenuItem to the respective menu
        m1.getItems().addAll(miNew, miDelete);
        m2.getItems().add(miAbout);

        menuBar.getMenus().addAll(m1, m2);

        SplitPane spRoot = new SplitPane();

        // Sets SplitPane as resizable
        spRoot.isResizable();

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

        TableColumn<Patient, String> idColumn = new TableColumn<>("Patient ID");

        // Initialize the table columns values
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());

        // Add teh columns to the table
        patientTable.getColumns().setAll(idColumn);

        // Set table min/max size
        patientTable.setMinSize(300, 600);
        patientTable.setMaxSize(300, 600);

        idColumn.setMinWidth(300);
        idColumn.setMaxWidth(300);

        patientTable.setItems(patientsList);

        // Set title font and style
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titleLabel.setId("titleLabel");

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
        spRoot.getItems().addAll(sp1, sp2);

        root.getChildren().addAll(menuBar, spRoot);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision");
        primaryStage.setScene(scene);
        // TODO Per ora non è ridimensionabile, sarebbe meglio fixare
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
