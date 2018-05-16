package Controller.Exceptions;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
public class IllegalDateException extends Exception {
    public IllegalDateException() {
        super("Invalid date.");
    }
}
