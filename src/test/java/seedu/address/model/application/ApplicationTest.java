package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

/**
 * Contains tests for the Application entity.
 */
public class ApplicationTest {

    @Test
    public void isSameApplication() {
        // same object -> returns true
        assertTrue(AMAZON.isSameApplication(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.isSameApplication(null));

        // same name and position, all other attributes different -> returns true
        Application editedAmazon = new ApplicationBuilder(AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE)
                .build();
        assertTrue(AMAZON.isSameApplication(editedAmazon));

        // different name and position, all other attributes same -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAmazon));

        // different name, all other attributes same -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAmazon));

        // different position, all other attributes same -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(AMAZON.isSameApplication(editedAmazon));

        // name differs in case, all other attributes same -> returns false
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(
                VALID_NAME_BYTEDANCE.toLowerCase()).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // position differs in case, all other attributes same -> returns false
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withPosition(
                VALID_POSITION_BYTEDANCE.toLowerCase()).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BYTEDANCE + " ";
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(nameWithTrailingSpaces).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // name has trailing spaces, all other attributes same -> returns false
        String positionWithTrailingSpaces = VALID_POSITION_BYTEDANCE + " ";
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withPosition(positionWithTrailingSpaces).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application amazonCopy = new ApplicationBuilder(AMAZON).build();
        assertEquals(AMAZON, amazonCopy);

        // same object -> returns true
        assertEquals(AMAZON, AMAZON);

        // null -> returns false
        assertNotEquals(null, AMAZON);

        // different type -> returns false
        assertNotEquals(5, AMAZON);

        // different application -> returns false
        assertNotEquals(AMAZON, BYTEDANCE);

        // different name -> returns false
        Application editedAmazon = new ApplicationBuilder(AMAZON).withCompany(VALID_NAME_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAmazon);

        // different position -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAmazon);

        // different deadline -> returns false
        editedAmazon = new ApplicationBuilder(AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertNotEquals(AMAZON, editedAmazon);
    }
}
