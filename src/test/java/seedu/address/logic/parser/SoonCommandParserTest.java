//package seedu.address.logic.parser;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.SoonCommand;
//
//public class SoonCommandParserTest {
//    private SoonCommandParser parser = new SoonCommandParser();
//
//    @Test
//    public void parse_validArgs_returnsSoonCommand() {
//        assertParseSuccess(parser, "1", new SoonCommand(Index.fromOneBased(1)));
//    }
//
//    @Test
//    public void parse_invalidArgs_throwsParseException() {
//        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_multipleParametersPresent_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE);
//        assertParseFailure(parser, "" + INDEX_FIRST_APPLICATION + " " + INDEX_SECOND_APPLICATION, expectedMessage);
//    }
//
//    @Test
//    public void parse_noParameterPresent_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE);
//
//        // command word only
//        assertParseFailure(parser, "", expectedMessage);
//
//        // white space only
//        assertParseFailure(parser, "      ", expectedMessage);
//
//        // no parameter
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY, expectedMessage);
//
//        // unsupported parameter
//        assertParseFailure(parser, "" + PREFIX_COMPLETION, expectedMessage);
//    }
//
//    @Test
//    public void parse_nonEmptyPreamble_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE);
//
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + "1", expectedMessage);
//    }
//}