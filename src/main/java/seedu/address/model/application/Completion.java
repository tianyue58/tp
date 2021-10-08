package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Application's completion status in InternSHIP.
 * ADD THE GUARANTEE LATER
 */
public class Completion {

    public static final String MESSAGE_CONSTRAINTS = "Completion can be 'Completed' or 'Uncompleted'."
            + "It will be 'Uncompleted' by default for a new Application that is added.";

    public final String value;

    /**
     * Constructs a {@code Completion}.
     *
     * @param status A valid completion status.
     */

    /*
     * The first character of the completion must not be
     * a whitespace.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public Completion(String status) {
        requireNonNull(status);
        checkArgument(isValidCompletion(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidCompletion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Completion
                && value.equals(((Completion) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
