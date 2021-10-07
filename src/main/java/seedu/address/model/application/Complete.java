package seedu.address.model.application;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Application's completion status in InternSHIP.
 * ADD THE GUARANTEE LATER
 */
public class Complete {
    public final String value;

    /**
     * Constructs a {@code Complete}.
     *
     * @param status A valid completion status.
     */
    public Complete(String status) {
        requireNonNull(status);
        value = status;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Complete
                && value.equals(((Complete) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
