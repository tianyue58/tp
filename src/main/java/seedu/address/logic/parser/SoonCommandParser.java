package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SoonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SoonCommand object
 */
public class SoonCommandParser implements Parser<SoonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SoonCommand
     * and returns a SoonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SoonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE_OF_APPLICATION);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).isPresent()) {
            try {
                Index days = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).get().trim());
                return new SoonCommand(days);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE), pe);
            }
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE));
    }
}
