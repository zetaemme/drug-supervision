package View;

import Controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login {
    public Login(Stage primaryStage) {
        LoginController controller = new LoginController();

        Group root = new Group();
        BorderPane borderPane = new BorderPane();

        root.isResizable();

        // Set the spacing between objects

        final TextField username = new TextField();
        final PasswordField password = new PasswordField();
        final Button loginButton = new Button("Login");
        final Label loginLabel = new Label("");
        final Label titleLabel = new Label("Drug Supervision");
        final Label copyrightLabel = new Label("© Andrea Soglieri and Mattia Zorzan | A.A. 2018/2019");

        // Set the promptText for the TextField/PasswordField
        username.setPromptText("Username");
        password.setPromptText("Password");

        // Set the sizes for the objects
        username.setMinSize(220, 30);
        username.setMaxSize(230, 30);

        password.setMinSize(220, 30);
        password.setMaxSize(230, 30);

        loginButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        // Title label options
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        titleLabel.setId("titleLabel");

        // Copyright label options
        copyrightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        copyrightLabel.setOpacity(10);

        // Set the (x, y) position for the objects
        titleLabel.setTranslateY(-310);

        username.setTranslateY(-200);

        password.setTranslateY(-135);

        loginButton.setTranslateX(168);
        loginButton.setTranslateY(-80);

        // Riattivare per check login()
        //loginLabel.setTranslateY(100);

        copyrightLabel.setTranslateY(-3);

        // Add the objects to the panel
        root.getChildren().addAll(titleLabel, username, password, loginButton, loginLabel);

        // Sets the positions and the alignments for the BorderPane
        borderPane.setBottom(copyrightLabel);
        borderPane.setCenter(root);

        borderPane.setAlignment(copyrightLabel, Pos.BASELINE_CENTER);
        borderPane.setAlignment(root, Pos.CENTER);

        // Scene and CSS
        Scene scene = new Scene(borderPane, 400, 400);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        // You can press 'Enter' to login
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(e -> {
            /*
            if(controller.login(username.getText(), password.getText())) {
                primaryStage.close();
                MainPage mainPage = new MainPage(new Stage());
            } else {
                loginLabel.setText("Login Failed!");
            } */

            // Loads the main page if the login is right

            if(username.getText().equals("aaa") && password.getText().equals("aaa")) {
                primaryStage.close();
                MainPage mainPage = new MainPage(new Stage(), username.getText());
            } else {
                loginLabel.setText("Login Failed!");
            }
        });
    }
}