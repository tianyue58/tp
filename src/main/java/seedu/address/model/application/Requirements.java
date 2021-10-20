package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Requirements {
    public static final String MESSAGE_CONSTRAINTS = "Requirements is an optional field and " +
            "you can add your submission requirements as a String using '/r' in the add command.";

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
    public Requirements(String requirements) {
        requireNonNull(requirements);
        checkArgument(isValidRequirements(requirements), MESSAGE_CONSTRAINTS);
        value = requirements;
    }

    /**
     * Returns true if a given string is a valid requirement.
     * A valid requirement should be in String format.
     */
    public static boolean isValidRequirements(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Requirements // instanceof handles nulls
                && value.equals(((Requirements) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
