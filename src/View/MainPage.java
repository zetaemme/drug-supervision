package View;

import Controller.MainPageController;
import Model.Patient;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

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

        BorderPane infoRoot = new BorderPane();
        GridPane patientInfo = new GridPane();

        // Padding settings
        infoRoot.setPadding(new Insets(10));

        // Title label and settings
        final Label title = new Label("Patient Info");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setId("titleLabel");

        infoRoot.setTop(title);

        // Static labels column
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.CENTER);
        column1.setPercentWidth(50);

        // Dynamic labels column
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.CENTER);
        column2.setPercentWidth(50);

        RowConstraints idRow = new RowConstraints();
        idRow.setValignment(VPos.CENTER);
        idRow.setPercentHeight(100.0 / 6);

        RowConstraints bDayRow = new RowConstraints();
        bDayRow.setValignment(VPos.CENTER);
        bDayRow.setPercentHeight(100.0 / 6);

        RowConstraints provinceRow = new RowConstraints();
        provinceRow.setValignment(VPos.CENTER);
        provinceRow.setPercentHeight(100.0 / 6);

        RowConstraints professionRow = new RowConstraints();
        professionRow.setValignment(VPos.CENTER);
        professionRow.setPercentHeight(100.0 / 6);

        RowConstraints riskFactorRow = new RowConstraints();
        riskFactorRow.setValignment(VPos.CENTER);
        riskFactorRow.setPercentHeight(100.0 / 6);

        RowConstraints medicRow = new RowConstraints();
        medicRow.setValignment(VPos.CENTER);
        medicRow.setPercentHeight(100.0 / 6);

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

        // TODO Non è bellissimo ma per ora può andare
        // Adds the labels on the relative position
        patientInfo.add(idLabel, 0, 0);
        patientInfo.add(patientID, 1, 0);

        patientInfo.add(birthdayLabel, 0, 1);
        patientInfo.add(patientBDay, 1, 1);

        patientInfo.add(provinceLabel, 0, 2);
        patientInfo.add(patientProvince, 1, 2);

        patientInfo.add(professionLabel, 0, 3);
        patientInfo.add(patientProfession, 1, 3);

        patientInfo.add(riskFactorLabel, 0 , 4);
        patientInfo.add(patientRiskFactor, 1 , 4);

        patientInfo.add(medicLabel, 0, 5);
        patientInfo.add(patientMedic, 1, 5);

        // Adds all to the GridPane
        patientInfo.getColumnConstraints().addAll(column1, column2);
        patientInfo.getRowConstraints().addAll(idRow, bDayRow, provinceRow, professionRow, riskFactorRow, medicRow);

        infoRoot.setCenter(patientInfo);

        infoRoot.setAlignment(title, Pos.CENTER);

        TableView<Patient> patientTable = new TableView(patientsList);

        // Initialize the patientList
        patientsList = mpController.initPatientsList();

        // We want the table to have static width
        patientTable.setMinWidth(300);
        patientTable.setMaxWidth(300);

        // The PatientID column
        TableColumn<Patient, String> idColumn = new TableColumn<>("Patient ID");

        // Initialize the table columns values
        idColumn.setCellValueFactory(param -> param.getValue().getIdProperty());

        // Add the columns to the table
        patientTable.getColumns().setAll(idColumn);

        // Set table min/max size
        patientTable.setMinWidth(300);
        patientTable.setMaxWidth(300);

        idColumn.setMinWidth(300);
        idColumn.setMaxWidth(300);

        // Add panes to SplitPane
        spRoot.getItems().addAll(patientTable, infoRoot);

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

        // Stage options
        primaryStage.setTitle("Drug Supervision");
        primaryStage.setScene(scene);
        primaryStage.show();

        // TODO BUGGED Fixare perchè gli resta in buffer il chiudere la finestra
        /*
        primaryStage.setOnCloseRequest(e -> {
            Alert mpLogoutAlert = new Alert(Alert.AlertType.CONFIRMATION);

            mpLogoutAlert.setTitle("Logout");
            mpLogoutAlert.setHeaderText("Logout");
            mpLogoutAlert.setContentText("Closing this window will log you out.\nYou want to continue?");

            Optional<ButtonType> result = mpLogoutAlert.showAndWait();

            if(result.get() == ButtonType.OK) {
                mpController.logout(username);
            } else {
                mpLogoutAlert.close();
            }
        });
        */

        // If clicked opens a new window that allows to add a new patient
        miNew.setOnAction(e -> {
            NewPatient newPatient = new NewPatient(new Stage());
        });

        // TODO Implementare miDelete

        miLogout.setOnAction(e -> {
            // Confirmation alert that ask if you are sure to logout
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);

            logoutAlert.setTitle("Logout");
            logoutAlert.setHeaderText("Logout");
            logoutAlert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> result = logoutAlert.showAndWait();

            if(result.get() == ButtonType.OK) {
                mpController.logout(username);
                primaryStage.close();
                Login login = new Login(new Stage());
            } else {
                logoutAlert.close();
            }
        });

        // If clicked opens a new about window
        miAbout.setOnAction(e -> {
            About about = new About(new Stage());
        });
    }
}
