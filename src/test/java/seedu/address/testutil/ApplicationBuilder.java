package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.application.Application;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Name;
import seedu.address.model.application.Position;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Position phone;
    private Deadline email;
    private Set<Tag> tags;

    /**
     * Creates a {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Position(DEFAULT_PHONE);
        email = new Deadline(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        name = applicationToCopy.getName();
        phone = applicationToCopy.getPosition();
        email = applicationToCopy.getDeadline();
        tags = new HashSet<>(applicationToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Phone} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withPosition(String phone) {
        this.phone = new Position(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withDeadline(String email) {
        this.email = new Deadline(email);
        return this;
    }

    public Application build() {
        return new Application(name, phone, email, tags);
    }

}
