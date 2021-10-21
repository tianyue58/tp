package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Application's priority in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priority can be 'Low' or 'Medium' or 'High'."
            + "It will be 'Medium' by default for a new Application that is added.";

    public final String value;

    /**
     * Constructs a {@code Priority}.
     *
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals("Low") || test.equals("Medium") || test.equals("High");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Priority
                && value.equals(((Priority) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
