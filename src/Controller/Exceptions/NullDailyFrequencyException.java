package Controller.Exceptions;

/**
 * @author Andrea Soglieri e Mattia Zorzan
 */
public class NullDailyFrequencyException extends Exception {
    public NullDailyFrequencyException() {
        super("Null daily frequency.");
    }
}
