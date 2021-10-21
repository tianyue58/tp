package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Requirements;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_COMPANY = "Gojek";
    public static final String DEFAULT_POSITION = "Machine Learning Engineer";
    public static final String DEFAULT_DEADLINE = "2021-12-04";
    public static final String DEFAULT_COMPLETION = "Uncompleted";
    public static final String DEFAULT_STATUS = "Pending";
    public static final String DEFAULT_REQUIREMENTS = "Resume";

    private Company company;
    private Position position;
    private Deadline deadline;
    private Status status;
    private Set<Tag> tags;
    private Completion completion;
    private Requirements requirements;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        company = new Company(DEFAULT_COMPANY);
        position = new Position(DEFAULT_POSITION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        status = new Status(DEFAULT_STATUS);
        tags = new HashSet<>();
        completion = new Completion(DEFAULT_COMPLETION);
        requirements = new Requirements(DEFAULT_REQUIREMENTS);
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        company = applicationToCopy.getCompany();
        position = applicationToCopy.getPosition();
        deadline = applicationToCopy.getDeadline();
        status = applicationToCopy.getStatus();
        tags = new HashSet<>(applicationToCopy.getTags());
        completion = applicationToCopy.getCompletion();
        requirements = applicationToCopy.getRequirements();
    }

    /**
     * Sets the {@code Name} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Requirements} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withRequirements(String requirements) {
        this.requirements = new Requirements(requirements);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Application} that we are building.
     */
    public ApplicationBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withCompletion(String complete) {
        this.completion = new Completion(complete);
        return this;
    }

    public Application build() {
        return new Application(company, position, deadline, completion, status, requirements, tags);
    }

}
