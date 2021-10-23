package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_DEADLINE_OF_APPLICATION, PREFIX_PRIORITY);

        if (numberOfPrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                PREFIX_DEADLINE_OF_APPLICATION, PREFIX_PRIORITY) == 0
                || numberOfPrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                PREFIX_DEADLINE_OF_APPLICATION, PREFIX_PRIORITY) > 1
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String parameter = null;

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            parameter = "company";
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            parameter = "position";
        }
        if (argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).isPresent()) {
            parameter = "deadline";
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            parameter = "priority";
        }

        if (parameter == null) {
            throw new ParseException(SortCommand.MESSAGE_PARAMETER_NOT_SPECIFIED);
        }

        return new SortCommand(parameter);
    }

    /**
     * Returns the number of prefixes present in the user input.
     */
    private static int numberOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        int i;
        int count = 0;
        for (i = 0; i < prefixes.length; i++) {
            if (argumentMultimap.getValue(prefixes[i]).isPresent()) {
                count++;
            }
        }
        return count;
    }

}
