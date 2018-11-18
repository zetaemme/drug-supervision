package Controller.MedicControllers;

import Model.Utils.DaoImpl.TherapyDaoImpl;

public class NewTherapyController {
    public TherapyDaoImpl therapyDao;

    // Adds a new therapy with the corresponding data
    public String addNewTherapy(String drugName, int dose, String startingDate, String endingDate, int dailyFrequency) {
        therapyDao = new TherapyDaoImpl();

        String idTherapy = drugName.substring(0, 3).toUpperCase() + dose + startingDate.replace("-", "")
                + endingDate.replace("-", "") + dailyFrequency;

        therapyDao.createTherapy(idTherapy, drugName, dose, startingDate, endingDate, dailyFrequency);

        return idTherapy;
    }
}
