package Model.Utils.DAOs;

import Model.RiskFactor;

import java.util.List;

public interface RiskFactorDao {
    List<RiskFactor> getAllRiskFactors();
    // TODO Implementala
    void createRiskFactor(String description, int riskLevel);
}
