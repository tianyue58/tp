package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_AMAZON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Company;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMAZON, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMAZON, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMAZON, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Company.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS); // invalid position
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid priority

        // invalid position followed by valid deadline
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC + DEADLINE_DESC_AMAZON,
                Position.MESSAGE_CONSTRAINTS);

        // valid position followed by invalid position. The test case for invalid position followed by valid position
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + POSITION_DESC_BYTEDANCE + INVALID_POSITION_DESC,
                Position.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEADLINE_DESC + VALID_DEADLINE_AMAZON,
                Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_BYTEDANCE
                + DEADLINE_DESC_AMAZON + PRIORITY_DESC_AMAZON + NAME_DESC_AMAZON;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withCompany(
                VALID_NAME_AMAZON)
                .withPosition(VALID_POSITION_BYTEDANCE).withDeadline(VALID_DEADLINE_AMAZON)
                .withPriority(VALID_PRIORITY_AMAZON).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_AMAZON;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_AMAZON).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMAZON;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_NAME_AMAZON).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // position
        userInput = targetIndex.getOneBased() + POSITION_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withPosition(VALID_POSITION_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withDeadline(VALID_DEADLINE_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_AMAZON;
        descriptor = new EditApplicationDescriptorBuilder().withPriority(VALID_PRIORITY_AMAZON).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON
                + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON
                + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_POSITION_DESC + POSITION_DESC_BYTEDANCE;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPosition(VALID_POSITION_BYTEDANCE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_BYTEDANCE + INVALID_POSITION_DESC
                + POSITION_DESC_BYTEDANCE;
        descriptor = new EditApplicationDescriptorBuilder().withPosition(VALID_POSITION_BYTEDANCE)
                .withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
