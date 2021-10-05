package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REJECTED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Application ALICE = new PersonBuilder().withName("Alice Pauline")
            .withDeadline("alice@example.com")
            .withPosition("94351253")
            .withTags("friends").build();
    public static final Application BENSON = new PersonBuilder().withName("Benson Meier")
            .withDeadline("johnd@example.com").withPosition("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Application CARL = new PersonBuilder().withName("Carl Kurz").withPosition("95352563")
            .withDeadline("heinz@example.com").build();
    public static final Application DANIEL = new PersonBuilder().withName("Daniel Meier").withPosition("87652533")
            .withDeadline("cornelia@example.com").withTags("friends").build();
    public static final Application ELLE = new PersonBuilder().withName("Elle Meyer").withPosition("9482224")
            .withDeadline("werner@example.com").build();
    public static final Application FIONA = new PersonBuilder().withName("Fiona Kunz").withPosition("9482427")
            .withDeadline("lydia@example.com").build();
    public static final Application GEORGE = new PersonBuilder().withName("George Best").withPosition("9482442")
            .withDeadline("anna@example.com").build();

    // Manually added
    public static final Application HOON = new PersonBuilder().withName("Hoon Meier").withPosition("8482424")
            .withDeadline("stefan@example.com").build();
    public static final Application IDA = new PersonBuilder().withName("Ida Mueller").withPosition("8482131")
            .withDeadline("hans@example.com").build();

    // Manually added - Application's details found in {@code CommandTestUtil}
    public static final Application AMY = new PersonBuilder().withName(VALID_NAME_AMAZON)
            .withPosition(VALID_POSITION_AMAZON).withDeadline(VALID_DEADLINE_AMAZON)
            .withTags(VALID_TAG_REJECTED).build();
    public static final Application BOB = new PersonBuilder().withName(VALID_NAME_BYTEDANCE)
            .withPosition(VALID_POSITION_BYTEDANCE).withDeadline(VALID_DEADLINE_BYTEDANCE)
            .withTags(VALID_TAG_PENDING).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Application application : getTypicalPersons()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static List<Application> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
