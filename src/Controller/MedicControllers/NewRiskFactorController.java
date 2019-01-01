package Controller.MedicControllers;

import Model.Utils.DaoImpl.RiskFactorDaoImpl;

public class NewRiskFactorController {
    public RiskFactorDaoImpl riskFactorDao;

    // Adds a RiskFactor with the corresponding data
    public void addRiskFactor(String description, int riskLevel) {
        riskFactorDao = new RiskFactorDaoImpl();

        riskFactorDao.createRiskFactor(description, riskLevel);
    }
}
