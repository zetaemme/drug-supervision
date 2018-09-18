package Controller;

import Model.Patient;
import Model.Utils.DBConnection;
import View.MainPage;
import javafx.scene.control.TableColumn;

import java.sql.SQLException;

public class MainPageController {
    private DBConnection mpConnection;

    TableColumn<Patient, String> firstNameColumn = new TableColumn<>("First Name");
    TableColumn<Patient, String> lastNameColumn = new TableColumn<>("last Name");

    // Initialize the table columns values
    public void initTableView() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
    }
}
