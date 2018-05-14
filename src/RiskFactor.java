/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
import Exceptions.IllegalRiskValueException;
import Exceptions.NullStringException;

public class RiskFactor {
    /**
     * Descrizione
     */
    private final String description;
    /**
     * I livelli di rischio sono così divisi:
     *
     * 1 - Codice Bianco (Non urgente)
     * 2 - Codice Verde (Intervento differibile)
     * 3 - Codice Giallo (Pericolo)
     * 4 - Codice Rosso (Massima urgenza)
     */
    private final int risk_level;

    /**
     * Metodo costruttore
     *
     * @param description
     * @param risk_level
     */
    private RiskFactor(String description, int risk_level) throws NullStringException, IllegalRiskValueException {
        if(description.length() == 0) {
            throw new NullStringException();
        } else if(risk_level < 1 || risk_level > 4) {
            throw new IllegalRiskValueException();
        }

        this.description = description;
        this.risk_level = risk_level;
    }

    /**
     * Metodo hashCode
     *
     * @return L'hash del risk_factor
     */
    @Override
    public int hashCode() {
        return description.hashCode() ^ risk_level;
    }

    /**
     * Metodo getDescription
     *
     * @return La descrizione del rischio
     */
    public String getDescription() {
        return description;
    }

    /**
     * Metodo getRisk_level
     *
     * @return Il livello di rischio
     */
    public int getRisk_level() {
        return risk_level;
    }
}
