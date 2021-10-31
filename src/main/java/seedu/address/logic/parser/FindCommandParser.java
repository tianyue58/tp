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
    public static final String COMPLETION_MESSAGE_CONSTRAINTS =
            "Completion can only exactly one of the take case-insensitive 'completed' or 'uncompleted'.";
    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Company name should only contain alphanumeric characters and spaces, and it should not be blank.";
    public static final String NAME_POSITION_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String POSITION_MESSAGE_CONSTRAINTS =
            "Position only contain alphanumeric characters and spaces, and it should not be blank.";
    public static final String PRIORITY_MESSAGE_CONSTRAINTS =
            "Priority can only take exactly one of the case-insensitive 'high', 'medium' or 'low'.";
    public static final String STATUS_MESSAGE_CONSTRAINTS =
            "Status can only take exactly one of the case-insensitive 'pending', 'accepted' or 'rejected'.";
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
            for (int i = 0; i < nameKeywords.length; i++) {
                if (!nameKeywords[i].matches(NAME_POSITION_VALIDATION_REGEX)) {
                    throw new ParseException(
                            String.format(NAME_MESSAGE_CONSTRAINTS));
                }
            }
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] positionKeywords = trimmedArgs.split("\\s+");
            for (int i = 0; i < positionKeywords.length; i++) {
                if (!positionKeywords[i].matches(NAME_POSITION_VALIDATION_REGEX)) {
                    throw new ParseException(
                            String.format(POSITION_MESSAGE_CONSTRAINTS));
                }
            }
            return new FindCommand(new PositionContainsKeywordsPredicate(Arrays.asList(positionKeywords)));
        }


        if (argMultimap.getValue(PREFIX_COMPLETION).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_COMPLETION).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] completionKeywords = trimmedArgs.split("\\s+");
            boolean isValidCompletion = completionKeywords[0].toLowerCase().equals("completed")
                    || completionKeywords[0].toLowerCase().equals("uncompleted");

            if (!isValidCompletion) {
                throw new ParseException(
                        String.format(COMPLETION_MESSAGE_CONSTRAINTS));
            }
            return new FindCommand(new CompletionContainsKeywordsPredicate(Arrays.asList(completionKeywords)));
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_STATUS).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] statusKeywords = trimmedArgs.split("\\s+");
            boolean isValidStatus = statusKeywords.length == 1
                    && (statusKeywords[0].toLowerCase().equals("pending")
                    || statusKeywords[0].toLowerCase().equals("accepted")
                    || statusKeywords[0].toLowerCase().equals("rejected"));
            if (!isValidStatus) {
                throw new ParseException(
                        String.format(STATUS_MESSAGE_CONSTRAINTS));
            }
            return new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList(statusKeywords)));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_PRIORITY).get().trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] priorityKeywords = trimmedArgs.split("\\s+");
            boolean isValidPriority = priorityKeywords.length == 1
                    && (priorityKeywords[0].toLowerCase().equals("high")
                    || priorityKeywords[0].toLowerCase().equals("medium")
                    || priorityKeywords[0].toLowerCase().equals("low"));
            if (!isValidPriority) {
                throw new ParseException(
                        String.format(PRIORITY_MESSAGE_CONSTRAINTS));
            }
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
