package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the interview date and time of an application in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDateAndTime(String)}
 */
public class InterviewDateAndTime {
    public static final String MESSAGE_CONSTRAINTS = "The 'interview date and time' must be in yyyy-MM-dd HHmm format.";

    public final String value;
    public final LocalDateTime dateAndTime;

    /**
     * Constructs a {@code InterviewDateAndTime}.
     *
     * @param interviewDateAndTime A valid set of date and time for multiple interviews.
     */
    public InterviewDateAndTime(String interviewDateAndTime) {
        requireNonNull(interviewDateAndTime);
        checkArgument(isValidInterviewDateAndTime(interviewDateAndTime), MESSAGE_CONSTRAINTS);
        this.dateAndTime = LocalDateTime.parse(interviewDateAndTime, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        value = interviewDateAndTime;
    }

    /**
     * Returns the month, date,
     * and year pattern of the date and time.
     *
     * @return The formatted String.
     */
    public String toFormattedString() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    /**
     * Returns the LocalDateTime object.
     *
     * @return The LocalDateTime object.
     */
    public LocalDateTime getDate() {
        return this.dateAndTime;
    }

    /**
     * Returns true if a given string is a valid Interview date and time.
     * A valid date and time should be in String format.
     */
    public static boolean isValidInterviewDateAndTime(String test) {
        try {
            LocalDateTime.parse(test, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                    .withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
