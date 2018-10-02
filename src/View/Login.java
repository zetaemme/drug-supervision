package View;

import Controller.LoginController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class Login {
    LoginController controller = new LoginController();

    public Login(Stage primaryStage){
        GridPane root = new GridPane();

        root.setPadding(new Insets(30, 30, 10, 30));

        RowConstraints tc = new RowConstraints();
        tc.setPercentHeight(20.0);
        tc.setValignment(VPos.CENTER);
        root.getRowConstraints().add(tc);

        RowConstraints uc = new RowConstraints();
        uc.setPercentHeight(20.0);
        uc.setValignment(VPos.CENTER);
        root.getRowConstraints().add(uc);

        RowConstraints pc = new RowConstraints();
        pc.setPercentHeight(20.0);
        pc.setValignment(VPos.CENTER);
        root.getRowConstraints().add(pc);

        RowConstraints lc = new RowConstraints();
        lc.setPercentHeight(20.0);
        lc.setValignment(VPos.CENTER);
        root.getRowConstraints().add(lc);

        RowConstraints about = new RowConstraints();
        about.setPercentHeight(20.0);
        about.setValignment(VPos.BOTTOM);
        root.getRowConstraints().add(about);



        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100);
        cc.setHalignment(HPos.CENTER);
        root.getColumnConstraints().add(cc);

        final Label title = new Label("Drug Supervision");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        root.add(title, 0, 0);

        final TextField username = new TextField();
        root.add(username, 0 , 1);

        final PasswordField password = new PasswordField();
        root.add(password, 0, 2);

        final Button loginButton = new Button("Login");
        root.add(loginButton, 0 ,3);

        final Label copyrightLabel = new Label("© Andrea Soglieri and Mattia Zorzan | A.A. 2018/2019");
        copyrightLabel.setFont(Font.font(12));
        root.add(copyrightLabel, 0 , 4);

        primaryStage.setTitle("Drug Supervision - Login");
        primaryStage.setScene(new Scene(root, 400,400));
        primaryStage.setResizable(false);
        primaryStage.show();

        loginButton.setOnAction(e -> {
            if(controller.login(username.getText(), password.getText())){
                MainPage mainPage = new MainPage(new Stage(), username.getText());
                primaryStage.close();
            }
            else{
                Alert loginAlert = new Alert(Alert.AlertType.WARNING);

                loginAlert.setTitle("Warning");
                loginAlert.setHeaderText("Login Failed");
                loginAlert.setContentText("Wrong username or password, please try again");

                loginAlert.showAndWait();
            }

        });
    }
}