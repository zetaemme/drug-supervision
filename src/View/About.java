package View;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

// Doesn't need a Controller because it's a static page
public class About {
    public About(Stage primaryStage) {
        GridPane root = new GridPane();

        // Sets root min/max size
        root.setPrefSize(400, 300);

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

        // Opens Andrea's GitHub profile
        asGit.setOnAction(e -> {
            openWebpage("https://github.com/Sogrea");
        });

        // Opens Mattia's GitHub profile
        mzGit.setOnAction(e -> {
            openWebpage("https://github.com/zetaemme");
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

        // GridPane column
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);
        column.setHalignment(HPos.CENTER);

        // GridPane rows
        RowConstraints titleRow = new RowConstraints();
        titleRow.setPercentHeight(100.0 / 6);
        titleRow.setValignment(VPos.CENTER);

        RowConstraints drugSupervisionRow = new RowConstraints();
        drugSupervisionRow.setPercentHeight(100.0 / 6);
        drugSupervisionRow.setValignment(VPos.CENTER);

        RowConstraints elaboratoRow = new RowConstraints();
        elaboratoRow.setPercentHeight(100.0 / 6);
        elaboratoRow.setValignment(VPos.CENTER);

        RowConstraints aaRow = new RowConstraints();
        aaRow.setPercentHeight(100.0 / 6);
        aaRow.setValignment(VPos.CENTER);

        RowConstraints linkRow = new RowConstraints();
        linkRow.setPercentHeight(100.0 / 6);
        linkRow.setValignment(VPos.CENTER);

        RowConstraints copyrightRow = new RowConstraints();
        copyrightRow.setPercentHeight(100.0 / 6);
        copyrightRow.setValignment(VPos.BOTTOM);

        GridPane gitPane = new GridPane();

        // gitPane columns
        ColumnConstraints asColumn = new ColumnConstraints();
        asColumn.setPercentWidth(50);
        asColumn.setHalignment(HPos.CENTER);

        ColumnConstraints mzColumn = new ColumnConstraints();
        mzColumn.setPercentWidth(50);
        mzColumn.setHalignment(HPos.CENTER);

        // gitPane row
        RowConstraints upperRow = new RowConstraints();
        upperRow.setPercentHeight(50);
        upperRow.setValignment(VPos.CENTER);

        RowConstraints lowerRow = new RowConstraints();
        lowerRow.setPercentHeight(50);
        lowerRow.setValignment(VPos.TOP);

        // Sets Y translate to aaLabel
        aaLabel.setTranslateY(-20);

        gitPane.add(gitAS, 0, 0);
        gitPane.add(gitMZ, 1, 0);
        gitPane.add(asGit, 0, 1);
        gitPane.add(mzGit, 1, 1);

        gitPane.getColumnConstraints().addAll(asColumn, mzColumn);
        gitPane.getRowConstraints().addAll(upperRow, lowerRow);

        root.add(titleLabel, 0, 0);
        root.add(dsLabel, 0, 1);
        root.add(eisLabel, 0, 2);
        root.add(aaLabel, 0, 3);
        root.add(gitPane, 0, 4);
        root.add(copyrightLabel, 0, 5);

        root.getColumnConstraints().add(column);
        root.getRowConstraints().addAll(titleRow, drugSupervisionRow, elaboratoRow, aaRow, linkRow, copyrightRow);

        // Sets scene stylesheet
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("CSS/style.css");

        primaryStage.setTitle("Drug Supervision - About");
        primaryStage.setScene(scene);
        // Better be not resizable for this particular window
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static void openWebpage(String url) {
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch(IOException ioe) {
            System.out.println("I/O Error: " + ioe.getMessage());
        }
    }
}
