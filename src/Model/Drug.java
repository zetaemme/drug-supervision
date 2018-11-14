package Model;

import Model.Exceptions.NullStringException;
import javafx.beans.property.SimpleStringProperty;

public class Drug {
    private final SimpleStringProperty drugName;
    private boolean removal;
    private boolean inspection;
    private boolean closeMonitor;

    public Drug(String drugName, boolean removal, boolean inspection, boolean closeMonitor) throws NullStringException {
        if(drugName.length() == 0) {
            throw new NullStringException();
        }

        this.drugName = new SimpleStringProperty(drugName);
        this.removal = removal;
        this.inspection = inspection;
        this.closeMonitor = closeMonitor;
    }

    public String getDrugName() {
        return drugName.get();
    }

    public void setDrugName(String drugName) {
        this.drugName.set(drugName);
    }

    public SimpleStringProperty getDrugNameProperty() {
        return drugName;
    }

    public boolean isRemoval() {
        return removal;
    }

    public void setRemoval(boolean removal) {
        this.removal = removal;
    }

    public boolean isInspection() {
        return inspection;
    }

    public void setInspection(boolean inspection) {
        this.inspection = inspection;
    }

    public boolean isCloseMonitor() {
        return closeMonitor;
    }

    public void setCloseMonitor(boolean closeMonitor) {
        this.closeMonitor = closeMonitor;
    }
}
