package Model;

import Model.Exceptions.IllegalRiskValueException;
import Model.Exceptions.NullStringException;

public class Reaction {
    private int risk;
    private String description;

    public Reaction(int risk, String description) throws IllegalRiskValueException, NullStringException {
        if(risk < 1 || risk > 5) {
            throw new IllegalRiskValueException();
        } else if(description.length() == 0) {
            throw new NullStringException();
        }

        this.risk = risk;
        this.description = description;
    }

    public String getReactionName() { return risk + description.substring(0, 5).toUpperCase().replace(" ", "");}

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
