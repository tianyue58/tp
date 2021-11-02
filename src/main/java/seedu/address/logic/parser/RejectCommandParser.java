package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.RejectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RejectCommand object
 */
public class RejectCommandParser implements Parser<RejectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RejectCommand
     * and returns an RejectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RejectCommand parse(String args) throws ParseException {
        Index index;

        try {
            index = ParserUtil.parseIndex(args);

        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                    RejectCommand.MESSAGE_USAGE), pe);
        }

        if (index.isIndexAbsent()) {
            throw new ParseException(String.format(Messages.MESSAGE_NO_INDEX_PROVIDED
                    + "\n%1$s", RejectCommand.MESSAGE_USAGE));
        }

        return new RejectCommand(index);
    }
}
