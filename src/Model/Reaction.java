package Model;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;

public class Reaction {
    private final int risk;
    private final String description;

    public Reaction(int risk, String description) throws IllegalRiskValueException, NullStringException {
        if(risk < 1 || risk > 5) {
            throw new IllegalRiskValueException();
        } else if(description.length() == 0) {
            throw new NullStringException();
        }

        this.risk = risk;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return risk ^ description.hashCode();
    }
}
