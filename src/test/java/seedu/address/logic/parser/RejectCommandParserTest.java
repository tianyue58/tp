package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AcceptCommand;
import seedu.address.logic.commands.RejectCommand;

public class RejectCommandParserTest {
    private RejectCommandParser parser = new RejectCommandParser();

    @Test
    public void parse_validArgs_returnsRejectCommand() {
        assertParseSuccess(parser, "1", new RejectCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                RejectCommand.MESSAGE_USAGE));
    }
}
