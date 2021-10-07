package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.application.Application;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] {
            new Application(new Name("Shopee"), new Position("software engineer"),
                    new Deadline("2021-12-23"), getTagSet("Pending"), new Completion("Uncompleted")),
            new Application(new Name("Google"), new Position("frontend developer"),
                        new Deadline("2021-12-14"), getTagSet("Pending"), new Completion("Uncompleted")),
            new Application(new Name("Huawei"), new Position("software engineer"),
                        new Deadline("2021-12-30"), getTagSet("Pending"), new Completion("Uncompleted")),
            new Application(new Name("Deutsche Bank"), new Position("software engineer"),
                        new Deadline("2021-12-25"), getTagSet("Pending"), new Completion("Uncompleted")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Application sampleApplication : getSampleApplications()) {
            sampleAb.addApplication(sampleApplication);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
