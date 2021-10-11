package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_GRAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_GRAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMAZON_SG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GRAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GRAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GRAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GRAB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Internship;
import seedu.address.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalApplications {

    // Manually added - Application's details found in {@code CommandTestUtil}
    public static final Application AMAZON = new ApplicationBuilder().withCompany(VALID_NAME_AMAZON)
            .withPosition(VALID_POSITION_AMAZON).withDeadline(VALID_DEADLINE_AMAZON)
            .withCompletion(VALID_COMPLETION_AMAZON)
            .withStatus(VALID_STATUS_AMAZON).withTags(VALID_TAG_AMAZON).build();
    public static final Application AMAZON_SG = new ApplicationBuilder().withCompany(VALID_NAME_AMAZON_SG)
            .withPosition(VALID_POSITION_AMAZON).withDeadline(VALID_DEADLINE_AMAZON)
            .withCompletion(VALID_COMPLETION_AMAZON)
            .withStatus(VALID_STATUS_AMAZON).withTags(VALID_TAG_AMAZON).build();
    public static final Application BYTEDANCE = new ApplicationBuilder().withCompany(VALID_NAME_BYTEDANCE)
            .withPosition(VALID_POSITION_BYTEDANCE).withDeadline(VALID_DEADLINE_BYTEDANCE)
            .withCompletion(VALID_COMPLETION_BYTEDANCE)
            .withStatus(VALID_STATUS_BYTEDANCE).withTags(VALID_TAG_BYTEDANCE).build();
    public static final Application GRAB = new ApplicationBuilder().withCompany(VALID_NAME_GRAB)
            .withPosition(VALID_POSITION_GRAB).withDeadline(VALID_DEADLINE_GRAB)
            .withCompletion(VALID_COMPLETION_GRAB)
            .withStatus(VALID_STATUS_GRAB).withTags(VALID_TAG_GRAB).build();



    private TypicalApplications() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Internship getTypicalInternship() {
        Internship internship = new Internship();
        for (Application application : getTypicalApplications()) {
            internship.addApplication(application);
        }
        return internship;
    }

    public static List<Application> getTypicalApplications() {
        return new ArrayList<>(Arrays.asList(AMAZON, AMAZON_SG, BYTEDANCE));
    }
}
