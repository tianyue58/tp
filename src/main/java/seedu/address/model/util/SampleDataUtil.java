package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Internship;
import seedu.address.model.ReadOnlyInternship;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Requirements;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] { new Application(new Company("Shopee"), new Position("software engineer"),
                new Deadline("2021-12-23"), new Completion("Uncompleted"),
                new Status("Pending"), new Requirements("resume"), getTagSet()),
            new Application(new Company("Google"), new Position("frontend developer"),
                    new Deadline("2021-12-14"), new Completion("Uncompleted"),
                    new Status("Pending"), new Requirements("resume, cv"), getTagSet()),
            new Application(new Company("Huawei"), new Position("software engineer"),
                    new Deadline("2021-12-30"), new Completion("Uncompleted"),
                    new Status("Pending"), new Requirements("cv"), getTagSet()),
            new Application(new Company("Deutsche Bank"), new Position("software engineer"),
                    new Deadline("2021-12-25"), new Completion("Uncompleted"),
                    new Status("Pending"), new Requirements("coding challenge"), getTagSet()),
        };
    }

    public static ReadOnlyInternship getSampleInternship() {
        Internship sampleAb = new Internship();
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
