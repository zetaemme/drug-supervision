package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// Doesn't need a Controller because it's a static page
public class About {
    public About(Stage primaryStage) {
        VBox root = new VBox();

        // Sets root min/max size
        root.setMinSize(400, 300);
        root.setMaxSize(400, 300);

        root.setPadding(new Insets(10));

        final Label titleLabel = new Label("About");
        final Label copyrightLabel = new Label("© Andrea Soglieri and Mattia Zorzan | A.A. 2018/2019");
        final Label dsLabel = new Label("Drug Supervision");
        final Label eisLabel = new Label("Elaborato Ingegneria del Software");
        final Label aaLabel = new Label("A.A. 2018/2019");

        // Sets labels font
        titleLabel.setId("titleLabel");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        copyrightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        copyrightLabel.setOpacity(20);

        dsLabel.setFont(Font.font(20));

        eisLabel.setFont(Font.font(14));

        aaLabel.setFont(Font.font(14));

        // Sets the (x, y) position of the labels
        titleLabel.setTranslateX(150);
        titleLabel.setTranslateY(15);

        dsLabel.setTranslateX(110);
        dsLabel.setTranslateY(80);

        eisLabel.setTranslateX(80);
        eisLabel.setTranslateY(95);

        aaLabel.setTranslateX(140);
        aaLabel.setTranslateY(100);

        copyrightLabel.setTranslateX(70);
        copyrightLabel.setTranslateY(180);

        root.getChildren().addAll(titleLabel, dsLabel, eisLabel, aaLabel, copyrightLabel);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - About");
        primaryStage.setScene(scene);
        // Better be not resizable for this particular window
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
