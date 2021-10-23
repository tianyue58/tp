package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Application's completion status in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompletion(String)}
 */
public class Completion {

    public static final String MESSAGE_CONSTRAINTS = "Completion can only be 'Completed' or 'Uncompleted'.";

    public final String value;

    /**
     * Constructs a {@code Completion}.
     *
     */
    public Completion(String status) {
        requireNonNull(status);
        checkArgument(isValidCompletion(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidCompletion(String test) {
        return test.equals("Completed") || test.equals("Uncompleted");
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
