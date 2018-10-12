package View;

import Controller.LoginController;
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

public class Login {
    private LoginController loginController = new LoginController();

    public Login(Stage primaryStage){
        GridPane root = new GridPane();

        root.setPadding(new Insets(30, 30, 10, 30));

        // Rows of the GridPane
        RowConstraints titleRow = new RowConstraints();
        titleRow.setPercentHeight(20.0);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints usernameRow = new RowConstraints();
        usernameRow.setPercentHeight(20.0);
        usernameRow.setValignment(VPos.CENTER);

        RowConstraints passwordRow = new RowConstraints();
        passwordRow.setPercentHeight(20.0);
        passwordRow.setValignment(VPos.CENTER);

        RowConstraints buttonRow = new RowConstraints();
        buttonRow.setPercentHeight(20.0);
        buttonRow.setValignment(VPos.CENTER);

        RowConstraints copyrightRow = new RowConstraints();
        copyrightRow.setPercentHeight(20.0);
        copyrightRow.setValignment(VPos.BOTTOM);

        // Columns of the GridPane
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);
        column.setHalignment(HPos.CENTER);

        // Add all rows/columns to the root
        root.getColumnConstraints().add(column);
        root.getRowConstraints().addAll(titleRow, usernameRow, passwordRow, buttonRow, copyrightRow);

        // Nodes of the view
        final Label title = new Label("Drug Supervision");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setId("titleLabel");

        final TextField username = new TextField();
        final PasswordField password = new PasswordField();
        final Button loginButton = new Button("Login");
        final Label copyrightLabel = new Label("© Andrea Soglieri and Mattia Zorzan | A.A. 2018/2019");

        // Sets the font of the title
        copyrightLabel.setFont(Font.font(12));

        // Adds al nodes to root
        root.add(title, 0, 0);
        root.add(username, 0 , 1);
        root.add(password, 0, 2);
        root.add(loginButton, 0 ,3);
        root.add(copyrightLabel, 0 , 4);

        primaryStage.setTitle("Drug Supervision - Login");
        primaryStage.setScene(new Scene(root, 400,400));
        primaryStage.setResizable(false);
        primaryStage.show();

        // Logs you in if you press 'Enter'
        loginButton.setDefaultButton(true);

        // Log you in if username/password is correct
        loginButton.setOnAction(e -> {
            // Logs you in in case of correct username/password
            if (loginController.login(username.getText(), password.getText())) {
                MainPage mainPage = new MainPage(new Stage(), username.getText(), loginController);
                primaryStage.close();
            } else if(username.getText().equals("") || password.getText().equals("")) {
                // Alert window in case of empty username/password
                Alert emptyUserPassAlert = new Alert(Alert.AlertType.WARNING);

                emptyUserPassAlert.setTitle("Error!");
                emptyUserPassAlert.setHeaderText("Empty username/password");
                emptyUserPassAlert.setContentText("Please, insert both username and password.");

                emptyUserPassAlert.showAndWait();
            } else {
                // Alert window in case of wrong username/password
                Alert loginFailedAlert = new Alert(Alert.AlertType.WARNING);

                loginFailedAlert.setTitle("Login Failed");
                loginFailedAlert.setHeaderText("Login Failed");
                loginFailedAlert.setContentText("Wrong username or password.\nPlease, try again.");

                loginFailedAlert.showAndWait();
            }
        });
    }
}