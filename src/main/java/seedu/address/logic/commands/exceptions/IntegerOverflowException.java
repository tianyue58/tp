package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during integer overflow {@link Command}.
 */
public class IntegerOverflowException extends ArithmeticException {
    public IntegerOverflowException(String message) {
        super(message);
    }
}
