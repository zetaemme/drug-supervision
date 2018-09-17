package View;

import Controller.LoginController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login {
    public Login(Stage primaryStage) {
        LoginController controller = new LoginController();

        VBox root = new VBox();

        root.setMinHeight(400);
        root.setMinWidth(400);

        // Set the spacing between objects
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));

        final TextField username = new TextField();
        final PasswordField password = new PasswordField();
        final Button loginButton = new Button("Login");
        final Label loginLabel = new Label("");
        final Label titleLabel = new Label("Drug Supervision");

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
        titleLabel.setFont(new Font("Fira Code", 30));
        titleLabel.setId("titleLabel");

        // Set the (x, y) position for the objects
        titleLabel.setTranslateX(70);
        titleLabel.setTranslateY(25);

        username.setTranslateX(75);
        username.setTranslateY(100);

        password.setTranslateX(75);
        password.setTranslateY(125);

        loginButton.setTranslateX(330.0);
        loginButton.setTranslateY(275.5);

        loginLabel.setTranslateX(165);
        loginLabel.setTranslateY(160);

        // Add the objects to the panel
        root.getChildren().addAll(titleLabel, username, password, loginButton, loginLabel);

        primaryStage.setTitle("Drug Supervision - Login");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        loginButton.setOnAction(e -> {
            if(controller.login(username.getText(), password.getText())) {
                loginLabel.setText("Login Successful!");
            } else {
                loginLabel.setText("Login Failed!");
            }
        });
    }
}