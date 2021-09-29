package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PENDING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ApplicationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Application application = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> application.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameApplication(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameApplication(null));

        // same name, all other attributes different -> returns true
        Application editedAlice = new PersonBuilder(ALICE).withPosition(VALID_POSITION_BYTEDANCE).withDeadline(VALID_DEADLINE_BYTEDANCE)
                .withTags(VALID_TAG_PENDING).build();
        assertTrue(ALICE.isSameApplication(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BYTEDANCE).build();
        assertFalse(ALICE.isSameApplication(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Application editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BYTEDANCE.toLowerCase()).build();
        assertFalse(BOB.isSameApplication(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BYTEDANCE + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameApplication(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different application -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Application editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BYTEDANCE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertFalse(ALICE.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_PENDING).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
