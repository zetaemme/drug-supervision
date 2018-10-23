package View;

import Controller.LoginController;
import Controller.PhMainPageController;
import Model.Report;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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

        ObservableList<Report> reportList = FXCollections.observableArrayList(phMpController.initReportList());

        // Creates the reportTable TableView
        TableView<Report> reportTable = new TableView<>(reportList);
        reportTable.setId("patientTable");

        // Sets max and min width fot the TableView
        reportTable.setMinWidth(300);
        reportTable.setMaxWidth(300);

        TableColumn<String, Report> reportColumn = new TableColumn<>("Report");

        reportColumn.cellValueFactoryProperty(cellData -> cellData.getValue())

        // Adds the menuBar to the root VBox
        root.getChildren().addAll(menuBar);

        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setTitle("Drug Supervision - Pharmacologist Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();

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
    }
}
