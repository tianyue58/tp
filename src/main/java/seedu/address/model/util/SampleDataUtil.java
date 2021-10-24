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
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Contains utility methods for populating {@code Internship} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] { new Application(new Company("Shopee"), new Position("software engineer"),
                new Deadline("2021-12-23"), new Completion("Uncompleted"),
                new Status("Pending"), new Priority("Medium"), getRequirementSet()),
            new Application(new Company("Google"), new Position("frontend developer"),
                    new Deadline("2021-12-14"), new Completion("Uncompleted"),
                    new Status("Pending"), new Priority("High"), getRequirementSet()),
            new Application(new Company("Huawei"), new Position("software engineer"),
                    new Deadline("2021-12-30"), new Completion("Uncompleted"),
                    new Status("Pending"), new Priority("Medium"), getRequirementSet()),
            new Application(new Company("Deutsche Bank"), new Position("software engineer"),
                    new Deadline("2021-12-25"), new Completion("Uncompleted"),
                    new Status("Pending"), new Priority("Low"), getRequirementSet()),
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
     * Returns a requirement set containing the list of strings given.
     */
    public static Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
                .map(Requirement::new)
                .collect(Collectors.toSet());
    }

}
