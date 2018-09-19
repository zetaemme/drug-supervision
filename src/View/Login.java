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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Login {
    public Login(Stage primaryStage) {
        LoginController controller = new LoginController();

        VBox root = new VBox();
        root.isResizable();

        // TODO Fixare il resize
        root.setMinHeight(400);
        root.setMinWidth(400);

        root.setMaxHeight(400);
        root.setMaxWidth(400);

        // Set the spacing between objects
        root.setSpacing(10);
        root.setPadding(new Insets(10));

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
        titleLabel.setStyle("-fx-text-fill: #696969");

        // Copyright label options
        copyrightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        copyrightLabel.setOpacity(10);

        // Set the (x, y) position for the objects
        titleLabel.setTranslateX(75);
        titleLabel.setTranslateY(25);

        username.setTranslateX(75);
        username.setTranslateY(80);

        password.setTranslateX(75);
        password.setTranslateY(105);

        loginButton.setTranslateX(280);
        loginButton.setTranslateY(130);

        loginLabel.setTranslateX(145);
        loginLabel.setTranslateY(100);

        copyrightLabel.setTranslateX(75);
        copyrightLabel.setTranslateY(180);

        // Add the objects to the panel
        root.getChildren().addAll(titleLabel, username, password, loginButton, loginLabel, copyrightLabel);

        primaryStage.setTitle("Drug Supervision - Login");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        // You can press 'Enter' to login
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(e -> {
            /*

            !Test area!

            if(controller.login(username.getText(), password.getText())) {
                loginLabel.setText("Login Successful!");
            } else {
                loginLabel.setText("Login Failed!");
            }
            */

            // Loads the main page if the login is right
            if(username.getText().equals("aaa") && password.getText().equals("aaa")) {
                primaryStage.close();
                MainPage mainPage = new MainPage(new Stage());
            } else {
                loginLabel.setText("Login Failed!");
            }
        });
    }
}