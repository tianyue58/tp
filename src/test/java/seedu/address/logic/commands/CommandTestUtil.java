package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMAZON = "Amazon";
    public static final String VALID_NAME_BYTEDANCE = "ByteDance";
    public static final String VALID_NAME_GRAB = "Grab";
    public static final String VALID_POSITION_AMAZON = "Software Engineer";
    public static final String VALID_POSITION_BYTEDANCE = "Web Developer";
    public static final String VALID_POSITION_GRAB = "Backend Engineer";
    public static final String VALID_DEADLINE_AMAZON = "2021-12-12";
    public static final String VALID_DEADLINE_BYTEDANCE = "2021-12-29";
    public static final String VALID_DEADLINE_GRAB = "2021-12-30";
    public static final String VALID_COMPLETION_AMAZON = "Uncompleted";
    public static final String VALID_COMPLETION_BYTEDANCE = "Completed";
    public static final String VALID_COMPLETION_GRAB = "Uncompleted";
    public static final String VALID_STATUS_AMAZON = "Pending";
    public static final String VALID_STATUS_BYTEDANCE = "Rejected";
    public static final String VALID_STATUS_GRAB = "Pending";
    public static final String VALID_REQUIREMENTS_AMAZON = "Resume";
    public static final String VALID_REQUIREMENTS_BYTEDANCE = "CV";
    public static final String VALID_REQUIREMENTS_GRAB = "Coding Challenge";
    public static final String VALID_TAG_AMAZON = "Important";
    public static final String VALID_TAG_BYTEDANCE = "Selective";
    public static final String VALID_TAG_GRAB = "Essential";

    public static final String NAME_DESC_AMAZON = " " + PREFIX_COMPANY_NAME + VALID_NAME_AMAZON;
    public static final String NAME_DESC_BYTEDANCE = " " + PREFIX_COMPANY_NAME + VALID_NAME_BYTEDANCE;
    public static final String POSITION_DESC_AMAZON = " " + PREFIX_INTERNSHIP_POSITION + VALID_POSITION_AMAZON;
    public static final String POSITION_DESC_BYTEDANCE = " " + PREFIX_INTERNSHIP_POSITION + VALID_POSITION_BYTEDANCE;
    public static final String DEADLINE_DESC_AMAZON = " " + PREFIX_DEADLINE_OF_APPLICATION + VALID_DEADLINE_AMAZON;
    public static final String DEADLINE_DESC_BYTEDANCE = " " + PREFIX_DEADLINE_OF_APPLICATION
            + VALID_DEADLINE_BYTEDANCE;
    public static final String TAG_DESC_AMAZON = " " + PREFIX_TAG + VALID_TAG_AMAZON;
    public static final String TAG_DESC_BYTEDANCE = " " + PREFIX_TAG + VALID_TAG_BYTEDANCE;
    public static final String STATUS_DESC_AMAZON = " " + VALID_STATUS_AMAZON;
    public static final String STATUS_DESC_BYTEDANCE = " " + VALID_STATUS_BYTEDANCE;
    public static final String REQUIREMENTS_DESC_AMAZON = " " + PREFIX_REQUIREMENTS + VALID_REQUIREMENTS_AMAZON;
    public static final String REQUIREMENTS_DESC_BYTEDANCE = " " + PREFIX_REQUIREMENTS + VALID_REQUIREMENTS_BYTEDANCE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_COMPANY_NAME + "Google&"; // '&' not allowed in names
    public static final String INVALID_POSITION_DESC = " "
            + PREFIX_INTERNSHIP_POSITION; // empty string not allowed for positions
    public static final String INVALID_DEADLINE_DESC = " "
            + PREFIX_DEADLINE_OF_APPLICATION + "03-12-2001"; // wrong date format (DD-MM-YYY) not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "Pending*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicationDescriptor DESC_AMAZON;
    public static final EditCommand.EditApplicationDescriptor DESC_BYTEDANCE;

    static {
        DESC_AMAZON = new EditApplicationDescriptorBuilder().withCompany(VALID_NAME_AMAZON)
                .withPosition(VALID_POSITION_AMAZON).withDeadline(VALID_DEADLINE_AMAZON)
                .withRequirements(VALID_REQUIREMENTS_AMAZON).build();
        DESC_BYTEDANCE = new EditApplicationDescriptorBuilder().withCompany(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_BYTEDANCE).withDeadline(VALID_DEADLINE_BYTEDANCE)
                .withRequirements(VALID_REQUIREMENTS_BYTEDANCE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the Internship, filtered application list and selected application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Internship expectedAddressBook = new Internship(actualModel.getInternship());
        List<Application> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getInternship());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicationList());
    }
    /**
     * Updates Internship's filtered list to show only the application at the given {@code targetIndex} in the
     * Internship's application list.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        Application application = model.getFilteredApplicationList().get(targetIndex.getZeroBased());
        final String[] splitName = application.getCompany().fullCompanyName.split("\\s+");
        model.updateFilteredApplicationList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicationList().size());
    }

}
