package View;

import Controller.MainPageController;
import Model.Patient;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainPage {
    // All patients list
    private ObservableList<Patient> patientsList;

    public MainPage(Stage primaryStage, String username) {
        // Connection with the controller
        final MainPageController mpController = new MainPageController();

        VBox root = new VBox();

        // Root size settings
        root.setMinSize(1000, 600);
        root.setMaxSize(1000, 600);

        MenuBar menuBar = new MenuBar();

        // MenuBar size settings
        menuBar.setMaxHeight(10);

        // The two menus
        Menu m1 = new Menu("File");
        Menu m2 = new Menu("Help");

        // All the items in the menu
        MenuItem miNew = new MenuItem("New");
        MenuItem miDelete = new MenuItem("Delete");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem miLogout = new MenuItem("Logout");
        MenuItem miAbout = new MenuItem("About");

        // Adds the MenuItem to the respective menu
        m1.getItems().addAll(miNew, miDelete, separator, miLogout);
        m2.getItems().add(miAbout);

        menuBar.getMenus().addAll(m1, m2);

        // The main view of the window
        SplitPane spRoot = new SplitPane();

        // Sets SplitPane as resizable
        spRoot.isResizable();

        BorderPane infoBP = new BorderPane();
        VBox labelsGroup = new VBox();
        final VBox patientInfoPane = new VBox();

        // Set resizable
        patientInfoPane.isResizable();

        patientInfoPane.setPadding(new Insets(10));

        // TODO Completare impaginazione, sembra un po' vuota
        final Label titleLabel = new Label("Patient Info");
        final Label idLabel = new Label("Patient ID:");
        final Label birthdayLabel = new Label("Birthday:");
        final Label provinceLabel = new Label("Province:");
        final Label professionLabel = new Label("Profession:");
        final Label riskFactorLabel = new Label("Risk Factor:");
        final Label medicLabel = new Label("Medic:");

        // This labels will show the patient personal data
        final Label patientID = new Label("");
        final Label patientBDay = new Label("");
        final Label patientProvince = new Label("");
        final Label patientProfession = new Label("");
        final Label patientRiskFactor = new Label("");
        final Label patientMedic = new Label("");

        labelsGroup.getChildren().addAll(idLabel, patientID, birthdayLabel, patientBDay, provinceLabel, patientProvince,
                                            professionLabel, patientProfession, riskFactorLabel, patientRiskFactor,
                                            medicLabel, patientMedic);

        // Function that initializes the patient list inside the TableView
        patientsList = mpController.initPatientsList();

        TableView patientTable = new TableView();

        // We want the table to have static width
        patientTable.setMinWidth(300);
        patientTable.setMaxWidth(300);

        // The PatientID column
        TableColumn<Patient, String> idColumn = new TableColumn<>("Patient ID");

        // Initialize the table columns values
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());

        // Add the columns to the table
        patientTable.getColumns().setAll(idColumn);

        // Set table min/max size
        patientTable.setMinWidth(300);
        patientTable.setMaxWidth(300);

        idColumn.setMinWidth(300);
        idColumn.setMaxWidth(300);

        // TODO Indagare, sembra non funzionare
        patientTable.setItems(patientsList);

        // Set title font and style
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        titleLabel.setId("titleLabel");

        // Set objects (x, y) position
        titleLabel.setTranslateY(5);

        idLabel.setTranslateX(150);
        idLabel.setTranslateY(120);

        patientID.setTranslateX(220);
        patientID.setTranslateY(103);

        birthdayLabel.setTranslateX(150);
        birthdayLabel.setTranslateY(120);

        patientBDay.setTranslateX(212);
        patientBDay.setTranslateY(103);

        provinceLabel.setTranslateX(150);
        provinceLabel.setTranslateY(120);

        patientProvince.setTranslateX(213);
        patientProvince.setTranslateY(103);

        professionLabel.setTranslateX(150);
        professionLabel.setTranslateY(120);

        patientProfession.setTranslateX(225);
        patientProfession.setTranslateY(103);

        riskFactorLabel.setTranslateX(150);
        riskFactorLabel.setTranslateY(120);

        patientRiskFactor.setTranslateX(229);
        patientRiskFactor.setTranslateY(103);

        medicLabel.setTranslateX(150);
        medicLabel.setTranslateY(120);

        patientMedic.setTranslateX(197);
        patientMedic.setTranslateY(103);

        // Add objects to panes
        infoBP.setTop(titleLabel);
        infoBP.setCenter(labelsGroup);

        infoBP.setAlignment(titleLabel, Pos.TOP_CENTER);

        patientInfoPane.getChildren().add(infoBP);

        // Add panes to SplitPane
        spRoot.getItems().addAll(patientTable, patientInfoPane);

        // Add all to root
        root.getChildren().addAll(menuBar, spRoot);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("CSS/style.css");

        // Resize policy options
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        menuBar.prefWidthProperty().bind(root.widthProperty());

        spRoot.prefWidthProperty().bind(root.widthProperty());
        spRoot.prefHeightProperty().bind(root.heightProperty());

        patientTable.prefHeightProperty().bind(spRoot.heightProperty());

        patientInfoPane.prefWidthProperty().bind(spRoot.widthProperty());
        patientInfoPane.prefHeightProperty().bind(spRoot.heightProperty());

        infoBP.prefWidthProperty().bind(patientInfoPane.widthProperty());
        infoBP.prefHeightProperty().bind(patientInfoPane.heightProperty());

        // Stage options
        primaryStage.setTitle("Drug Supervision");
        primaryStage.setScene(scene);
        primaryStage.show();

        // If clicked opens a new window that allows to add a new patient
        miNew.setOnAction(e -> {
            NewPatient newPatient = new NewPatient(new Stage());
        });

        // TODO Implementare miDelete

        miLogout.setOnAction(e -> {
            mpController.logout(username);
            primaryStage.close();
            Login login = new Login(new Stage());
        });

        // If clicked opens a new about window
        miAbout.setOnAction(e -> {
            About about = new About(new Stage());
        });
    }
}
