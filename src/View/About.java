package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

        final ImageView gitAS = new ImageView("IMG/github.png");
        final ImageView gitMZ = new ImageView("IMG/github.png");

        Hyperlink asGit = new Hyperlink("Andrea Soglieri");
        Hyperlink mzGit = new Hyperlink("Mattia Zorzan");

        Pane asPane = new Pane(gitAS);
        Pane mzPane = new Pane(gitMZ);

        // Opens Andrea's GitHub profile
        asGit.setOnAction(e -> {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Sogrea"));
                } catch(IOException ioe) {
                    System.out.println("I/O Error: " + ioe.getMessage());
                } catch(URISyntaxException use) {
                    System.out.println("URI Error: " + use.getMessage());
                }
            }
        });

        // Opens Mattia's GitHub profile
        mzGit.setOnAction(e -> {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/zetaemme"));
                } catch(IOException ioe) {
                    System.out.println("I/O Error: " + ioe.getMessage());
                } catch(URISyntaxException use) {
                    System.out.println("URI Error: " + use.getMessage());
                }
            }
        });

        // Sets Hyperlink border
        asGit.setBorder(Border.EMPTY);
        mzGit.setBorder(Border.EMPTY);

        // GitHub logos resize
        gitAS.setFitHeight(40);
        gitAS.setFitWidth(40);

        gitAS.setPreserveRatio(true);

        gitMZ.setFitHeight(40);
        gitMZ.setFitWidth(40);

        gitMZ.setPreserveRatio(true);

        // Sets labels font
        titleLabel.setId("titleLabel");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        copyrightLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        copyrightLabel.setOpacity(20);

        dsLabel.setFont(Font.font(20));

        eisLabel.setFont(Font.font(14));

        aaLabel.setFont(Font.font(14));

        // Sets the (x, y) position of the labels/images
        titleLabel.setTranslateX(150);
        titleLabel.setTranslateY(5);

        dsLabel.setTranslateX(110);
        dsLabel.setTranslateY(40);

        eisLabel.setTranslateX(80);
        eisLabel.setTranslateY(55);

        aaLabel.setTranslateX(140);
        aaLabel.setTranslateY(60);

        asPane.setTranslateX(110);
        asPane.setTranslateY(89);

        mzPane.setTranslateX(230);
        mzPane.setTranslateY(50);

        asGit.setTranslateX(80);
        asGit.setTranslateY(50);

        mzGit.setTranslateX(205);
        mzGit.setTranslateY(27);

        copyrightLabel.setTranslateX(70);
        copyrightLabel.setTranslateY(58);

        root.getChildren().addAll(titleLabel, dsLabel, eisLabel, aaLabel, asPane, mzPane, asGit, mzGit, copyrightLabel);

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
