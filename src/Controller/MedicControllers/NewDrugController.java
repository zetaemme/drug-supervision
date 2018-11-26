package Controller.MedicControllers;

import Model.Utils.DaoImpl.DrugDaoImpl;

public class NewDrugController {
    public DrugDaoImpl drugDAO;

    public void addNewDrug(String drugName) {
        drugDAO = new DrugDaoImpl();

        drugDAO.createDrug(drugName);
    }
}
