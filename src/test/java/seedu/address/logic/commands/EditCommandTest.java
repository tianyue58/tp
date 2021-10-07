package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //    @Test
    //    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Application editedApplication = new ApplicationBuilder().build();
    //        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
    //        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
    //
    //        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplication);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application lastApplication = model.getFilteredApplicationList().get(indexLastPerson.getZeroBased());

        ApplicationBuilder personInList = new ApplicationBuilder(lastApplication);
        Application editedApplication = personInList.withName(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_BYTEDANCE).withTags(VALID_TAG_AMAZON).build();

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withName(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_BYTEDANCE).withTags(VALID_TAG_AMAZON).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, new EditApplicationDescriptor());
        Application editedApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_APPLICATION);

        Application applicationInFilteredList = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        Application editedApplication = new ApplicationBuilder(applicationInFilteredList)
                .withName(VALID_NAME_BYTEDANCE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder().withName(VALID_NAME_BYTEDANCE).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Application firstApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(firstApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit application in filtered list into a duplicate in address book
        Application applicationInList = model.getAddressBook().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder(applicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withName(VALID_NAME_BYTEDANCE).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicationList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicationDescriptorBuilder().withName(VALID_NAME_BYTEDANCE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_AMAZON);

        // same values -> returns true
        EditApplicationDescriptor copyDescriptor = new EditApplicationDescriptor(DESC_AMAZON);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_AMAZON)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_BYTEDANCE)));
    }

}
