package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditApplicationDescriptor descriptorWithSameValues = new EditCommand
                .EditApplicationDescriptor(DESC_AMAZON);
        assertEquals(DESC_AMAZON, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_AMAZON, DESC_AMAZON);

        // null -> returns false
        assertNotEquals(null, DESC_AMAZON);

        // different types -> returns false
        assertNotEquals(5, DESC_AMAZON);

        // different values -> returns false
        assertNotEquals(DESC_AMAZON, DESC_BYTEDANCE);

        // different name -> returns false
        EditCommand.EditApplicationDescriptor editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withCompany(VALID_NAME_BYTEDANCE).build();
        assertNotEquals(DESC_AMAZON, editedAmazon);

        // different position -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertNotEquals(DESC_AMAZON, editedAmazon);

        // different deadline -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertNotEquals(DESC_AMAZON, editedAmazon);

        // different priority -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON).withPriority(VALID_PRIORITY_BYTEDANCE).build();
        assertNotEquals(DESC_AMAZON, editedAmazon);

        // different requirements -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withRequirements(VALID_REQUIREMENTS_BYTEDANCE).build();
        assertNotEquals(DESC_AMAZON, editedAmazon);

        // different interview date and time -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE).build();
        assertNotEquals(DESC_AMAZON, editedAmazon);
    }
}
