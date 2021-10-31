package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.PriorityContainsKeywordsPredicate;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.StatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final int PREFIX_AND_KEYWORD_SIZE = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_COMPLETION, PREFIX_STATUS, PREFIX_PRIORITY, PREFIX_REQUIREMENT);

        if (argMultimap.getSize() != PREFIX_AND_KEYWORD_SIZE || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_COMPANY_NAME).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] positionKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new PositionContainsKeywordsPredicate(Arrays.asList(positionKeywords)));
        }


        if (argMultimap.getValue(PREFIX_COMPLETION).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_COMPLETION).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] completionKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new CompletionContainsKeywordsPredicate(Arrays.asList(completionKeywords)));
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_STATUS).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] statusKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList(statusKeywords)));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_PRIORITY).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] priorityKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList(priorityKeywords)));
        }

        if (argMultimap.getValue(PREFIX_REQUIREMENT).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_REQUIREMENT).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] requirementsKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new RequirementsContainsKeywordsPredicate(Arrays.asList(requirementsKeywords)));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
