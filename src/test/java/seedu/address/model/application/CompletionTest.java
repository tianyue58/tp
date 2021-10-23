package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompletionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Completion(null));
    }

    @Test
    public void constructor_invalidCompletion_throwsIllegalArgumentException() {
        String invalidCompletion = "done";
        assertThrows(IllegalArgumentException.class, () -> new Completion(invalidCompletion));
    }

    @Test
    public void isValidCompletion() {
        // null completion
        assertThrows(NullPointerException.class, () -> Completion.isValidCompletion(null));

        // invalid completion
        assertFalse(Completion.isValidCompletion("")); // empty string
        assertFalse(Completion.isValidCompletion("       ")); // spaces only
        assertFalse(Completion.isValidCompletion("done")); // not a valid completion value
        assertFalse(Completion.isValidCompletion("coMpLEting")); // not a valid completion value
        assertFalse(Completion.isValidCompletion("completed")); // wrong capitalisation

        // valid completion
        assertTrue(Completion.isValidCompletion("Completed"));
        assertTrue(Completion.isValidCompletion("Uncompleted"));
    }
}
