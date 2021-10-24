package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


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

    @Test
    public void execute_emptyList() {
        model = new ModelManager(new Internship(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
        assertCommandSuccess(new SoonCommand(), model, SoonCommand.MESSAGE_EMPTY_LIST, expectedModel);
    }

}
