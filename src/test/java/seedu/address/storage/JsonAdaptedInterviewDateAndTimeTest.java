package seedu.address.storage;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.Assert;

public class JsonAdaptedInterviewDateAndTimeTest {
    @Test
    public void toModelType_invalidInterviewDateAndTime_throwsException() {
        JsonAdaptedInterviewDateAndTime invalidInterviewDateAndTime = new JsonAdaptedInterviewDateAndTime("");
        Assert.assertThrows(IllegalValueException.class, invalidInterviewDateAndTime::toModelType);
    }
}
