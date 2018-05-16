package Controller; /**
 * @author Andrea Soglieri e Mattia Zorzan
 */
import Controller.Exceptions.IllegalRiskValueException;
import Controller.Exceptions.NullStringException;

public class Reaction {
    /**
     * Livello di gravità (da 1 a 5)
     */
    private final int risk;
    /**
     * Descrizione della reazione avversa
     */
    private final String description;

    /**
     * Metodo costruttore
     *
     * @param risk
     * @param description
     */
    private Reaction(int risk, String description) throws IllegalRiskValueException, NullStringException {
        if(risk < 1 || risk > 5) {
            throw new IllegalRiskValueException();
        } else if(description.length() == 0) {
            throw new NullStringException();
        }

        this.risk = risk;
        this.description = description;
    }

    /**
     * Metodo hashCode
     *
     * @return L'hash della reazione avversa
     */
    @Override
    public int hashCode() {
        return risk ^ description.hashCode();
    }
}
