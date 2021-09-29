package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditApplicationDescriptor descriptorWithSameValues = new EditCommand.EditApplicationDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditApplicationDescriptor editedAmy = new EditApplicationDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different position -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMY).withPosition(VALID_POSITION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different deadline -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMY).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

    }
}
