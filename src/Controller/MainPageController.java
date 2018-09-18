package Controller;

import Model.Patient;
import Model.Utils.DBConnection;
import javafx.scene.control.TableColumn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageController {
    private DBConnection mpConnection;
    
    public void initTableView(TableColumn tc1, TableColumn tc2) {
        mpConnection = new DBConnection();
        mpConnection.openConnection();

        // TODO Finire metodo

        mpConnection.closeConnection();
    }
}
