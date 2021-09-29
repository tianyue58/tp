package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_PENDING;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REJECTED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REJECTED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.application.Application;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Application expectedApplication = new PersonBuilder(BOB).withTags(VALID_TAG_REJECTED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_REJECTED, new AddCommand(expectedApplication));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMAZON + NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_REJECTED, new AddCommand(expectedApplication));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_AMAZON + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_REJECTED, new AddCommand(expectedApplication));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_AMAZON + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_REJECTED, new AddCommand(expectedApplication));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_REJECTED, new AddCommand(expectedApplication));

        // multiple tags - all accepted
        Application expectedApplicationMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_REJECTED, VALID_TAG_PENDING)
                .build();
        assertParseSuccess(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_PENDING + TAG_DESC_REJECTED, new AddCommand(expectedApplicationMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Application expectedApplication = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMAZON + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + VALID_POSITION_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + VALID_DEADLINE_BYTEDANCE,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BYTEDANCE + VALID_POSITION_BYTEDANCE + VALID_DEADLINE_BYTEDANCE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_PENDING + TAG_DESC_REJECTED, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + INVALID_POSITION_DESC + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_PENDING + TAG_DESC_REJECTED, Position.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + INVALID_DEADLINE_DESC
                + TAG_DESC_PENDING + TAG_DESC_REJECTED, Deadline.MESSAGE_CONSTRAINTS);


        // invalid tag
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + INVALID_TAG_DESC + VALID_TAG_REJECTED, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE
                + TAG_DESC_PENDING + TAG_DESC_REJECTED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
