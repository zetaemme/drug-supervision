package Model;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;

public class RiskFactor {
    private final String description;
    private final int risk_level;

    private RiskFactor(String description, int risk_level) throws NullStringException, IllegalRiskValueException {
        if(description.length() == 0) {
            throw new NullStringException();
        } else if(risk_level < 1 || risk_level > 4) {
            throw new IllegalRiskValueException();
        }

        this.description = description;
        this.risk_level = risk_level;
    }

    @Override
    public int hashCode() {
        return description.hashCode() ^ risk_level;
    }

    public String getDescription() {
        return description;
    }

    public int getRisk_level() {
        return risk_level;
    }
}
