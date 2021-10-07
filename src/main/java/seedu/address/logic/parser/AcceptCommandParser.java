package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AcceptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AcceptCommandParser {

    /**
     * TO ADD JAVADOCS
     * @param args ADD
     * @return ADD
     * @throws ParseException ADD
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
