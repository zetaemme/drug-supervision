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
}
