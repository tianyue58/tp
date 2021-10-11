package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Company;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;

public class JsonAdaptedApplicationTest {

    private static final String INVALID_COMPANY = "433#$%";
    private static final String INVALID_POSITION = " ";
    private static final String INVALID_DEADLINE = "243114";

    private static final String VALID_COMPANY = AMAZON.getCompany().toString();
    private static final String VALID_POSITION = AMAZON.getPosition().toString();
    private static final String VALID_DEADLINE = AMAZON.getDeadline().toString();
    private static final String VALID_STATUS = AMAZON.getStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = AMAZON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_COMPLETION = AMAZON.getCompletion().toString();


    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedApplication application = new JsonAdaptedApplication(AMAZON);
        assertEquals(AMAZON, application.toModelType());
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(INVALID_COMPANY, VALID_POSITION, VALID_DEADLINE,
                        VALID_STATUS, VALID_TAGS, VALID_COMPLETION);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(null, VALID_POSITION,
                VALID_DEADLINE, VALID_STATUS,
                VALID_TAGS, VALID_COMPLETION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, INVALID_POSITION, VALID_DEADLINE, VALID_STATUS,
                        VALID_TAGS, VALID_COMPLETION);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, null,
                VALID_DEADLINE, VALID_STATUS, VALID_TAGS, VALID_COMPLETION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION, INVALID_DEADLINE,
                        VALID_STATUS, VALID_TAGS, VALID_COMPLETION);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION,
                null, VALID_STATUS, VALID_TAGS, VALID_COMPLETION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }


}
