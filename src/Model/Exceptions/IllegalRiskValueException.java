package Model.Exceptions;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
public class IllegalRiskValueException extends Exception {
    public IllegalRiskValueException() {
        super("Invalid risk value.");
    }
}
