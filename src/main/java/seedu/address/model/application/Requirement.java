package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Application's submission requirements in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidRequirement(String)}
 */
public class Requirement {
    public static final String MESSAGE_CONSTRAINTS = "Submission requirements is an optional field and "
            + "you can add your requirements using '/r' in the add command.";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";


    public final String value;

    /**
     * Constructs a {@code Requirement}.
     *
     * @param requirements A valid set of requirements.
     */
    public Requirement(String requirements) {
        requireNonNull(requirements);
        checkArgument(isValidRequirement(requirements), MESSAGE_CONSTRAINTS);
        value = requirements;
    }

    /**
     * Returns true if a given string is a valid requirement.
     * A valid requirement should be in String format.
     */
    public static boolean isValidRequirement(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + value + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Requirement // instanceof handles nulls
                && value.equals(((Requirement) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
