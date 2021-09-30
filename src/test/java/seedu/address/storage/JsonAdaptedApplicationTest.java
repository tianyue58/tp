package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;

public class JsonAdaptedApplicationTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_POSITION = " ";
    private static final String INVALID_DEADLINE = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_POSITION = BENSON.getPosition().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_POSITION, VALID_DEADLINE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_POSITION, VALID_DEADLINE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    //@Test
    //public void toModelType_invalidPosition_throwsIllegalValueException() {
    //    JsonAdaptedPerson person =
    //            new JsonAdaptedPerson(VALID_NAME, VALID_POSITION, VALID_DEADLINE, VALID_TAGS);
    //    String expectedMessage = Position.MESSAGE_CONSTRAINTS;
    //    assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //}

    //    @Test
    //    public void toModelType_nullPosition_throwsIllegalValueException() {
    //        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_POSITION, VALID_DEADLINE, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }

    //    @Test
    //    public void toModelType_invalidDeadline_throwsIllegalValueException() {
    //        JsonAdaptedPerson person =
    //                new JsonAdaptedPerson(VALID_NAME, VALID_POSITION, VALID_DEADLINE, VALID_TAGS);
    //        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }

    //    @Test
    //    public void toModelType_nullDeadline_throwsIllegalValueException() {
    //        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_POSITION, VALID_DEADLINE, VALID_TAGS);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    //    }


}
