package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AcceptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AcceptCommand object
 */
public class AcceptCommandParser implements Parser<AcceptCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AcceptCommand
     * and returns an AcceptCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AcceptCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AcceptCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AcceptCommand.MESSAGE_USAGE), pe);
        }
    }

}
