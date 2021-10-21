package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;
import java.util.Locale;

/**
 * Represents an Application's priority in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priority can only be 'Low' or 'Medium' or 'High'.";

    public final String value;

    /**
     * Constructs a {@code Priority}. Regardless of the capitalisation of the input String, it is reformatted to having
     * only the first letter capitalised before being initialised as the value of the Priority object.
     * (e.g. low is reformatted to Low, mEdIuM is reformatted to Medium)
     *
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority.substring(0, 1).toUpperCase() + priority.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid priority. Capitalisation of characters is ignored.
     */
    public static boolean isValidPriority(String test) {
        String lowerCaseTest = test.toLowerCase();
        return lowerCaseTest.equals("low") || lowerCaseTest.equals("medium") || lowerCaseTest.equals("high");
    }

    public static Comparator<Application> getComparator() {
        return new Comparator<Application>() {
            @Override
            public int compare(Application application, Application otherApplication) {
                String priority = application.getPriority().value;
                String otherPriority = otherApplication.getPriority().value;

                String numericPriority = "0";
                switch (priority) {
                case "High":
                    numericPriority = "1";
                    break;
                case "Medium":
                    numericPriority = "2";
                    break;
                case "Low":
                    numericPriority = "3";
                    break;
                default:
                    // Should not reach this line.
                }

                String otherNumericPriority = "0";
                switch (otherPriority) {
                case "High":
                    otherNumericPriority = "1";
                    break;
                case "Medium":
                    otherNumericPriority = "2";
                    break;
                case "Low":
                    otherNumericPriority = "3";
                    break;
                default:
                    // Should not reach this line.
                }

                return numericPriority.compareTo(otherNumericPriority);
            }
        };
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
