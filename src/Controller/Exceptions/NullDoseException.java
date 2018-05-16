package Controller.Exceptions;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
public class NullDoseException extends Exception {
    public NullDoseException() {
        super("Null dose.");
    }
}
