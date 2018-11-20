package Model.Utils.DAOs;

import Model.Drug;
import Model.Exceptions.NullStringException;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface DrugDao {
    List<Drug> getAllDrugs() throws SQLException, NullStringException;
    //ObservableList<String> initDrugList() throws SQLException, NullStringException;
    void updateRemoval(String drugName);
    void updateInspection(String drugName);
    void updateCloseMonitor(String drugName);
}
