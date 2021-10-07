package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Application;
import seedu.address.model.application.Complete;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_NAME = "Gojek";
    public static final String DEFAULT_POSITION = "Machine Learning Engineer";
    public static final String DEFAULT_DEADLINE = "2021-12-04";
    public static final String DEFAULT_COMPLETE = "Uncompleted";

    private Name name;
    private Position position;
    private Deadline deadline;
    private Set<Tag> tags;
    private Complete complete;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        name = new Name(DEFAULT_NAME);
        position = new Position(DEFAULT_POSITION);
        deadline = new Deadline(DEFAULT_DEADLINE);
        tags = new HashSet<>();
        complete = new Complete(DEFAULT_COMPLETE);
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        name = applicationToCopy.getName();
        position = applicationToCopy.getPosition();
        deadline = applicationToCopy.getDeadline();
        tags = new HashSet<>(applicationToCopy.getTags());
        complete = applicationToCopy.getComplete();
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Application} that we are building.
     */
    public ApplicationBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withComplete(String complete) {
        this.complete = new Complete(complete);
        return this;
    }

    public Application build() {
        return new Application(name, position, deadline, tags, complete);
    }

}
