package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "higher";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("prioritised")); // not a valid priority value
        assertFalse(Priority.isValidPriority("HiGher")); // not a valid priority value

        // valid priority
        assertTrue(Priority.isValidPriority("High")); // only first letter upper case
        assertTrue(Priority.isValidPriority("low")); // all lower case
        assertTrue(Priority.isValidPriority("MEDIUM")); // all upper case
        assertTrue(Priority.isValidPriority("HiGh")); // mix of lower and upper case
    }
}
