package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.*;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AcceptCommand}.
 */
public class AcceptCommandTest {

    private Model model = new ModelManager(getTypicalInternship(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        AcceptCommand acceptCommand = new AcceptCommand(outOfBoundIndex);

        assertCommandFailure(acceptCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of Internship list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternship().getApplicationList().size());

        AcceptCommand acceptCommand = new AcceptCommand(outOfBoundIndex);

        assertCommandFailure(acceptCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexApplicationList_success() {
        Application applicationToAccept = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());

        Company company = applicationToAccept.getCompany();
        Position position = applicationToAccept.getPosition();
        Deadline deadline = applicationToAccept.getDeadline();
        Set<Tag> tagList = applicationToAccept.getTags();
        Status status = new Status("Accepted");
        Completion completion = new Completion("Completed");

        Application addedApplication = new Application(company, position, deadline, completion, status, tagList);

        String expectedMessage = String.format(AcceptCommand.MESSAGE_SUCCESS, addedApplication);
        ModelManager expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
        expectedModel.setApplication(applicationToAccept, addedApplication);

        AcceptCommand acceptCommand = new AcceptCommand(INDEX_FIRST_APPLICATION);

        assertCommandSuccess(acceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        AcceptCommand acceptFirstCommand = new AcceptCommand(INDEX_FIRST_APPLICATION);
        AcceptCommand acceptSecondCommand = new AcceptCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(acceptFirstCommand.equals(acceptFirstCommand));

        // same values -> returns true
        AcceptCommand acceptFirstCommandCopy = new AcceptCommand(INDEX_FIRST_APPLICATION);
        assertTrue(acceptFirstCommand.equals(acceptFirstCommandCopy));

        // different types -> returns false
        assertFalse(acceptFirstCommand.equals(1));

        // null -> returns false
        assertFalse(acceptFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(acceptFirstCommand.equals(acceptSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplication(Model model) {
        model.updateFilteredApplicationList(p -> false);

        assertTrue(model.getFilteredApplicationList().isEmpty());
    }
}
