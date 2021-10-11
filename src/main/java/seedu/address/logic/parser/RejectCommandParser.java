package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RejectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RejectCommandParser implements Parser<RejectCommand> {

    /**
     * TO ADD JAVADOCS
     * @param args ADD
     * @return ADD
     * @throws ParseException ADD
     */
    public RejectCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RejectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RejectCommand.MESSAGE_USAGE), pe);
        }
    }
}
