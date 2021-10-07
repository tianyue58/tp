package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMAZON;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.ALICE;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicationBuilder;

public class ApplicationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Application application = new ApplicationBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> application.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplication(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplication(null));

        // same name, all other attributes different -> returns true
        Application editedAlice = new ApplicationBuilder(ALICE).withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE)
                .withTags(VALID_TAG_AMAZON).build();
        assertTrue(ALICE.isSameApplication(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ApplicationBuilder(ALICE).withName(VALID_NAME_BYTEDANCE).build();
        assertFalse(ALICE.isSameApplication(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withName(VALID_NAME_BYTEDANCE.toLowerCase()).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BYTEDANCE + " ";
        editedBytedance = new ApplicationBuilder(BYTEDANCE).withName(nameWithTrailingSpaces).build();
        assertFalse(BYTEDANCE.isSameApplication(editedBytedance));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application aliceCopy = new ApplicationBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different application -> returns false
        assertFalse(ALICE.equals(BYTEDANCE));

        // different name -> returns false
        Application editedAlice = new ApplicationBuilder(ALICE).withName(VALID_NAME_BYTEDANCE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ApplicationBuilder(ALICE).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ApplicationBuilder(ALICE).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertFalse(ALICE.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new ApplicationBuilder(ALICE).withTags(VALID_TAG_AMAZON).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
