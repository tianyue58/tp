package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

/**
 * Represents an Application's deadline in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {


    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in YYYY-MM-DD format.";
    public static final String VALIDATION_REGEX = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public final String value;
    public final LocalDate date;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid application deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(deadline);
        value = deadline;
    }

    /**
     * Returns the date format of the deadline
     *
     * @return The LocalDate object
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            if (test.matches(VALIDATION_REGEX)) {
                return true;
            }
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the month, date,
     * and year pattern of the deadline.
     *
     * @return The formatted String.
     */
    public String toFormattedString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a Comparator object that compares two applications by their deadlines.
     * The application with a deadline that comes first is taken to be greater.
     *
     * @return Comparator object that compares applications by their deadlines.
     */
    public static Comparator<Application> getComparator() {
        return (application, otherApplication) -> {
            String deadline = application.getDeadline().value;
            String otherDeadline = otherApplication.getDeadline().value;

            String[] splitDeadline = deadline.split("-");
            String year = splitDeadline[0];
            String month = splitDeadline[1];
            String day = splitDeadline[2];

            String[] splitOtherDeadline = otherDeadline.split("-");
            String otherYear = splitOtherDeadline[0];
            String otherMonth = splitOtherDeadline[1];
            String otherDay = splitOtherDeadline[2];

            if (year.compareTo(otherYear) != 0) {
                return year.compareTo(otherYear);
            }
            if (month.compareTo(otherMonth) != 0) {
                return month.compareTo(otherMonth);
            }
            if (day.compareTo(otherDay) != 0) {
                return day.compareTo(otherDay);
            }

            return 0;
        };
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
