package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Application;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.application.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_NAME = "Gojek";
    public static final String DEFAULT_POSITION = "Machine Learning Engineer";
    public static final String DEFAULT_DEADLINE = "2021-12-04";
    public static final String DEFAULT_COMPLETION = "Uncompleted";
    public static final String DEFAULT_STATUS = "Pending";

    private Name name;
    private Position position;
    private Deadline deadline;
    private Status status;
    private Set<Tag> tags;
    private Completion completion;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        name = new Name(DEFAULT_NAME);
        position = new Position(DEFAULT_POSITION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        status = new Status(DEFAULT_STATUS);
        tags = new HashSet<>();
        completion = new Completion(DEFAULT_COMPLETION);
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        name = applicationToCopy.getName();
        position = applicationToCopy.getPosition();
        deadline = applicationToCopy.getDeadline();
        status = applicationToCopy.getStatus();
        tags = new HashSet<>(applicationToCopy.getTags());
        completion = applicationToCopy.getCompletion();
    }

    /**
     * Sets the {@code Name} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withName(String name) {
        this.name = new Name(name);
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
        return new Application(name, position, deadline, status, tags, completion);
    }

}
