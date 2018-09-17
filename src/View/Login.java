package View;

import Controller.LoginController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        final Label label = new Label("");

        username.setMinSize(220, 30);
        username.setMaxSize(230, 30);

        password.setMinSize(220, 30);
        password.setMaxSize(230, 30);

        loginButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        root.getChildren().addAll(username, password, loginButton, label);

        primaryStage.setTitle("Drug Supervision - Login");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        loginButton.setOnAction(e -> {
            if(controller.login(username.getText(), password.getText())) {
                label.setText("Login Successful!");
            } else {
                label.setText("Login Failed!");
            }
        });
    }
}