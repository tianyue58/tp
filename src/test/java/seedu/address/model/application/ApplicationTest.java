package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.*;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class ApplicationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Application application = new ApplicationBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> application.getTags().remove(0));
    }

    @Test
    public void isSameApplication() {
        // same object -> returns true
        assertTrue(AMAZON.isSameApplication(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.isSameApplication(null));

        // same name, all other attributes different -> returns true
        Application editedAMAZON = new ApplicationBuilder(AMAZON).withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE)
                .withTags(VALID_TAG_AMAZON).build();
        assertTrue(AMAZON.isSameApplication(editedAMAZON));

        // different name, all other attributes same -> returns false
        editedAMAZON = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAMAZON));

        // name differs in case, all other attributes same -> returns false
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(VALID_NAME_BYTEDANCE.toLowerCase()).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BYTEDANCE + " ";
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(nameWithTrailingSpaces).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application aliceCopy = new ApplicationBuilder(AMAZON).build();
        assertEquals(AMAZON, aliceCopy);

        // same object -> returns true
        assertEquals(AMAZON, AMAZON);

        // null -> returns false
        assertNotEquals(null, AMAZON);

        // different type -> returns false
        assertNotEquals(5, AMAZON);

        // different application -> returns false
        assertNotEquals(AMAZON, BYTEDANCE);

        // different name -> returns false
        Application editedAMAZON = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAMAZON);

        // different phone -> returns false
        editedAMAZON = new ApplicationBuilder(AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAMAZON);

        // different email -> returns false
        editedAMAZON = new ApplicationBuilder(AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAMAZON);

        // different tags -> returns false
        editedAMAZON = new ApplicationBuilder(AMAZON).withTags(VALID_TAG_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAMAZON);
    }
}
