package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.DeadlineContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.StatusContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Google", "Shopee")));
        assertParseSuccess(parser, " " + PREFIX_COMPANY_NAME + "Google Shopee", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_COMPANY_NAME + " \n Google \n \t Shopee  \t", expectedFindCommand);
    }

    @Test
    public void parse_validPositionArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PositionContainsKeywordsPredicate(Arrays.asList("engineer", "designer")));
        assertParseSuccess(parser, " " + PREFIX_INTERNSHIP_POSITION + "engineer designer", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_INTERNSHIP_POSITION
                + " \n engineer \n \t designer  \t", expectedFindCommand);
    }

    @Test
    public void parse_validDeadlineArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new DeadlineContainsKeywordsPredicate(Arrays.asList("2021-11-12", "2021-11-13")));
        assertParseSuccess(parser, " " + PREFIX_DEADLINE_OF_APPLICATION + "2021-11-12 2021-11-13", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_DEADLINE_OF_APPLICATION
                + " \n 2021-11-12 \n \t 2021-11-13  \t", expectedFindCommand);
    }

    @Test
    public void parse_validCompletionArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompletionContainsKeywordsPredicate(Arrays.asList("Completed", "Uncompleted")));
        assertParseSuccess(parser, " " + PREFIX_COMPLETION + "Completed Uncompleted", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_COMPLETION
                + " \n Completed \n \t Uncompleted  \t", expectedFindCommand);
    }

    @Test
    public void parse_validStatusArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList("accepted", "pending")));
        assertParseSuccess(parser, " " + PREFIX_STATUS + "accepted pending", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_STATUS + " \n accepted \n \t pending  \t", expectedFindCommand);
    }

    @Test
    public void parse_validPriorityArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList("High", "Medium")));
        assertParseSuccess(parser, " " + PREFIX_STATUS + "High Medium", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_STATUS + " \n High \n \t Medium  \t", expectedFindCommand);
    }

    @Test
    public void parse_validRequirementsArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new RequirementsContainsKeywordsPredicate(Arrays.asList("cv", "resume")));
        assertParseSuccess(parser, " " + PREFIX_REQUIREMENT + "cv resume", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_REQUIREMENT
                + " \n cv \n \t resume  \t", expectedFindCommand);
    }
}
