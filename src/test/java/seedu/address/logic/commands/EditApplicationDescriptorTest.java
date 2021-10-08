package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditApplicationDescriptor descriptorWithSameValues = new EditCommand
                .EditApplicationDescriptor(DESC_AMAZON);
        assertTrue(DESC_AMAZON.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMAZON.equals(DESC_AMAZON));

        // null -> returns false
        assertFalse(DESC_AMAZON.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMAZON.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMAZON.equals(DESC_BYTEDANCE));

        // different name -> returns false
        EditCommand.EditApplicationDescriptor editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withCompany(VALID_NAME_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

        // different position -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

        // different deadline -> returns false
        editedAmy = new EditApplicationDescriptorBuilder(DESC_AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmy));

    }
}
