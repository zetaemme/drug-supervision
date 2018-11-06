package View.PharmacologistViews;

import Controller.LoginController;
import Controller.PharmacologistControllers.PhMainPageController;
import Model.Report;

import View.About;
import View.Login;
import javafx.collections.FXCollections;
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

public class PhMainPage {
    public PhMainPage(Stage primaryStage, LoginController loginController) {
        // Checks if the loginInstance isn't null
        assert loginController.isLogged() : "You shouldn't be there!";

        final PhMainPageController phMpController = new PhMainPageController();

        VBox root = new VBox();

        // Sets max and min size for the view
        root.setMinSize(1000, 600);
        root.setMaxSize(1000, 600);

        MenuBar menuBar = new MenuBar();

        // Sets the max height of the menuBar
        menuBar.setMaxHeight(10);

        Menu m1 = new Menu("File");
        Menu m2 = new Menu("Help");

        // Creates the menuItems
        MenuItem miAbout = new MenuItem("About");
        MenuItem miLogout = new MenuItem("Logout");

        // Adds the items to the menus
        m1.getItems().add(miLogout);
        m2.getItems().add(miAbout);

        // Adds the menus to the menuBars
        menuBar.getMenus().addAll(m1, m2);

        SplitPane spPh = new SplitPane();

        // The list that contains all the reports in the DB
        ObservableList<Report> reportList = FXCollections.observableArrayList(phMpController.initReportList());

        // Creates the reportTable TableView
        TableView<Report> reportTable = new TableView<>(reportList);
        reportTable.setId("patientTable");

        // Sets max and min width fot the TableView
        reportTable.setMinWidth(300);
        reportTable.setMaxWidth(300);

        TableColumn<Report, String> reportColumn = new TableColumn<>("Report");

        reportColumn.setCellValueFactory(cellData -> cellData.getValue().getId());

        reportTable.getSelectionModel().selectFirst();

        reportTable.getColumns().add(reportColumn);

        // Report info will be shown inside this BorderPane
        BorderPane reportInfo = new BorderPane();

        reportInfo.setPadding(new Insets(10, 0, 25, 0));

        final Label titleLabel = new Label("Report Info");

        final Label reactionDateLabel = new Label("Reaction Date:");
        final Label reportDateLabel = new Label("Report Date:");
        final Label therapyLabel = new Label("Therapy ID:");
        final Label drugLabel = new Label("Drug:");
        final Label doseLabel = new Label("Dose:");
        final Label dailyFrequencyLabel = new Label("Daily Frequency:");

        final Label reactionDateData = new Label();
        final Label reportDateData = new Label();
        final Label therapyData = new Label();
        final Label drugData = new Label();
        final Label doseData = new Label();
        final Label dailyfrequencyData = new Label();

        final Button removeButton = new Button("Remove");
        final Button inspectButton = new Button("Inspect");
        final Button closeButton = new Button("Close Monitor");

        // titleLabel options
        titleLabel.setId("titleLabel");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        reportInfo.setTop(titleLabel);

        // Buttons GridPane
        GridPane buttonGrid = new GridPane();

        RowConstraints buttonRow = new RowConstraints();
        buttonRow.setValignment(VPos.CENTER);
        buttonRow.setPercentHeight(100);

        ColumnConstraints removeColumn = new ColumnConstraints();
        removeColumn.setHalignment(HPos.CENTER);
        removeColumn.setPercentWidth(100.0 / 3);

        ColumnConstraints inspectColumn = new ColumnConstraints();
        inspectColumn.setHalignment(HPos.CENTER);
        inspectColumn.setPercentWidth(100.0 / 3);

        ColumnConstraints closeColumn = new ColumnConstraints();
        closeColumn.setHalignment(HPos.CENTER);
        closeColumn.setPercentWidth(100.0 / 3);

        buttonGrid.getRowConstraints().add(buttonRow);
        buttonGrid.getColumnConstraints().addAll(removeColumn, inspectColumn, closeColumn);

        buttonGrid.add(removeButton, 0, 0);
        buttonGrid.add(inspectButton, 1, 0);
        buttonGrid.add(closeButton, 2, 0);

        reportInfo.setBottom(buttonGrid);

        // Center GridPane
        GridPane reportGridPane = new GridPane();

        // reportGridPane columns
        ColumnConstraints reportLabelsColumn = new ColumnConstraints();
        reportLabelsColumn.setHalignment(HPos.CENTER);
        reportLabelsColumn.setPercentWidth(50);

        ColumnConstraints reportDataColumn = new ColumnConstraints();
        reportDataColumn.setHalignment(HPos.CENTER);
        reportDataColumn.setPercentWidth(50);

        // reportGridPane rows
        RowConstraints reactionRow = new RowConstraints();
        reactionRow.setValignment(VPos.CENTER);
        reactionRow.setPercentHeight(100.0 / 6);

        RowConstraints reportRow = new RowConstraints();
        reportRow.setValignment(VPos.CENTER);
        reportRow.setPercentHeight(100.0 / 6);

        RowConstraints therapyRow = new RowConstraints();
        therapyRow.setValignment(VPos.CENTER);
        therapyRow.setPercentHeight(100.0 / 6);

        RowConstraints drugRow = new RowConstraints();
        drugRow.setValignment(VPos.CENTER);
        drugRow.setPercentHeight(100.0 / 6);

        RowConstraints doseRow = new RowConstraints();
        doseRow.setValignment(VPos.CENTER);
        doseRow.setPercentHeight(100.0 / 6);

        RowConstraints dailyFrequencyRow = new RowConstraints();
        dailyFrequencyRow.setValignment(VPos.CENTER);
        dailyFrequencyRow.setPercentHeight(100.0 / 6);

        // Adds rows/columns to the reportGridPane
        reportGridPane.getColumnConstraints().addAll(reportLabelsColumn, reportDataColumn);
        reportGridPane.getRowConstraints().addAll(reactionRow, reportRow, therapyRow, drugRow, doseRow, dailyFrequencyRow);

        // Adds all the nodes to the reportGridPane
        reportGridPane.add(reactionDateLabel, 0, 0);
        reportGridPane.add(reactionDateData, 1, 0);
        reportGridPane.add(reportDateLabel, 0, 1);
        reportGridPane.add(reportDateData, 1, 1);
        reportGridPane.add(therapyLabel, 0, 2);
        reportGridPane.add(therapyData, 1, 2);
        reportGridPane.add(drugLabel, 0, 3);
        reportGridPane.add(drugData, 1, 3);
        reportGridPane.add(doseLabel, 0, 4);
        reportGridPane.add(doseData, 1, 4);
        reportGridPane.add(dailyFrequencyLabel, 0, 5);
        reportGridPane.add(dailyfrequencyData, 1, 5);

        reportInfo.setCenter(reportGridPane);

        // Sets titleLabel alignment
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Adds the items to the split pane
        spPh.getItems().addAll(reportTable, reportInfo);

        // Adds the menuBar to the root VBox
        root.getChildren().addAll(menuBar, spPh);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("CSS/style.css");

        // Resize policy options
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        menuBar.prefWidthProperty().bind(root.widthProperty());

        spPh.prefWidthProperty().bind(root.widthProperty());
        spPh.prefHeightProperty().bind(root.heightProperty());

        reportTable.prefHeightProperty().bind(spPh.heightProperty());

        reportColumn.prefWidthProperty().bind(reportTable.widthProperty());

        // primaryStage options
        primaryStage.setTitle("Drug Supervision - Pharmacologist Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();

        if(phMpController.reportDao.getReportNumber() > 1){
            Alert mpLogoutAlert = new Alert(Alert.AlertType.CONFIRMATION);

            mpLogoutAlert.setTitle("Report ");
            mpLogoutAlert.setHeaderText("You will exit the program");
            mpLogoutAlert.setContentText("Closing this window will log you out.\nYou want to continue?");

            Optional<ButtonType> result = mpLogoutAlert.showAndWait();
        }

        // Shows an alert to check if you want to close the window
        primaryStage.setOnCloseRequest(e -> {
            Alert mpLogoutAlert = new Alert(Alert.AlertType.CONFIRMATION);

            mpLogoutAlert.setTitle("Logout");
            mpLogoutAlert.setHeaderText("You will exit the program");
            mpLogoutAlert.setContentText("Closing this window will log you out.\nYou want to continue?");

            Optional<ButtonType> result = mpLogoutAlert.showAndWait();

            if(result.get() == ButtonType.OK) {
                loginController.logout();
            } else {
                // Cancels the close request
                e.consume();
                mpLogoutAlert.close();
            }
        });

        // If clicked log the logged user out
        miLogout.setOnAction(e -> {
            // Confirmation alert that ask if you are sure to logout
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);

            logoutAlert.setTitle("Logout");
            logoutAlert.setHeaderText("Logout");
            logoutAlert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> result = logoutAlert.showAndWait();

            if(result.get() == ButtonType.OK) {
                loginController.logout();
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

        removeButton.setOnAction(e -> {
            // TODO Implementare funzionalità removeDrug
        });

        inspectButton.setOnAction(e -> {
            // TODO Implementare funzionalità inspectDrug
        });

        closeButton.setOnAction(e -> {
            // TODO Implementare funzionalità closeMonitor
        });
    }
}
