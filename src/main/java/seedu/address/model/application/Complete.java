package seedu.address.model.application;

import static java.util.Objects.requireNonNull;

public class Complete {
    public final String value;

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
