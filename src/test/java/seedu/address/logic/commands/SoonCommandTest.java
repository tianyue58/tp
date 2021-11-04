package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.InterviewDateAndTimePredicate;
import seedu.address.model.application.SoonDeadlinePredicate;


/**
 * Contains integration tests (interaction with the Model) and unit tests for SoonCommand.
 */
public class SoonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternship(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
    }

//    @Test
//    public void //success

    @Test
    public void execute_emptyList() {
        model = new ModelManager(new Internship(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
        assertCommandSuccess(new SoonCommand(Index.fromZeroBased(1),
                        new SoonDeadlinePredicate(Index.fromOneBased(1))),
                model, SoonCommand.MESSAGE_EMPTY_LIST, expectedModel);
    }

    @Test
    public void equals() {
        Index daysOne = Index.fromZeroBased(1);
        SoonCommand soonFirstCommand = new SoonCommand(daysOne,
                new SoonDeadlinePredicate(daysOne));
        SoonCommand soonSecondCommand = new SoonCommand(daysOne,
                new InterviewDateAndTimePredicate(daysOne));

        // same object -> returns true
        assertTrue(soonFirstCommand.equals(soonFirstCommand));

        // same values -> returns true
        SoonCommand soonFirstCommandCopy = new SoonCommand(daysOne,
                new SoonDeadlinePredicate(daysOne));
        assertTrue(soonFirstCommand.equals(soonFirstCommandCopy));

        // different types -> returns false
        assertFalse(soonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(soonFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(soonFirstCommand.equals(soonSecondCommand));
    }
}
