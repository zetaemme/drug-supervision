package Model.Exceptions;

public class IllegalRiskValueException extends Exception {
    public IllegalRiskValueException() {
        super("Invalid risk value.");
    }
}
