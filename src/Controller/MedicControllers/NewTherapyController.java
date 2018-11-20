package Controller.MedicControllers;

import Model.Drug;
import Model.Utils.DaoImpl.DrugDaoImpl;
import Model.Utils.DaoImpl.TherapyDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NewTherapyController {
    public TherapyDaoImpl therapyDao;
    public DrugDaoImpl drugDao;

    // Adds a new therapy with the corresponding data
    public String addNewTherapy(String drugName, int dose, String startingDate, String endingDate, int dailyFrequency) {
        therapyDao = new TherapyDaoImpl();

        String idTherapy = drugName.substring(0, 3).toUpperCase() + dose + startingDate.replace("-", "")
                + endingDate.replace("-", "") + dailyFrequency;

        therapyDao.createTherapy(idTherapy, drugName, dose, startingDate, endingDate, dailyFrequency);

        return idTherapy;
    }

    public ObservableList<String> initDrugList() {
        drugDao = new DrugDaoImpl();

        ObservableList<String> drugNames = FXCollections.observableArrayList();

        for(Drug d : drugDao.getAllDrugs()){
            drugNames.add(d.getDrugName());
        }

        return drugNames;
    }
}
