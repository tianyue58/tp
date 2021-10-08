package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Application's name in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Company {

    public static final String MESSAGE_CONSTRAINTS =
            "Company name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullCompanyName;

    /**
     * Constructs a {@code Company}.
     *
     * @param company A valid name.
     */
    public Company(String company) {
        requireNonNull(company);
        checkArgument(isValidCompanyName(company), MESSAGE_CONSTRAINTS);
        fullCompanyName = company;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCompanyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullCompanyName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                && fullCompanyName.equals(((Company) other).fullCompanyName)); // state check
    }

    @Override
    public int hashCode() {
        return fullCompanyName.hashCode();
    }

}
