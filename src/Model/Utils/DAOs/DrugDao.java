package Model.Utils.DAOs;

import Model.Drug;
import Model.Exceptions.NullStringException;

import java.sql.SQLException;
import java.util.List;

public interface DrugDao {
    List<Drug> getAllDrugs() throws SQLException, NullStringException;
    Drug getDrug(String drugName) throws SQLException, NullStringException;
}
